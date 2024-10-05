package com.springboot.splitwise.services.Strategies;

import com.springboot.splitwise.Dto.TransactionDto;
import com.springboot.splitwise.models.Expense;
import com.springboot.splitwise.models.User;
import com.springboot.splitwise.models.UserExpense;
import com.springboot.splitwise.models.UserExpenseType;

import java.util.*;

public class HeapBasedSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<TransactionDto> settleUp(List<Expense> expenses) {
        HashMap<User, Double> userOutstandingMap = new HashMap<>();
        List<TransactionDto> transactionList = new ArrayList<>();
        // The for loop below calculates the outstanding amount for each user in the group [all users in list of expense]
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User user = userExpense.getUser();
                double currentOutStandingAmount = userOutstandingMap.getOrDefault(user, 0.00);
                userOutstandingMap.put(user, getUpdatedOutStandingAmount(currentOutStandingAmount, userExpense));
            }
        }

        //maxHeap for all PAID users [ +ve balance ]
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        //minHeap for all HasToPay users [ -ve balance ]
        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        for(Map.Entry<User,Double> entry : userOutstandingMap.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(entry);
            } else if(entry.getValue() > 0){
                maxHeap.add(entry);
            } else {
                System.out.println(entry.getKey().getName() + " is already settled, no need to be part of transactions");
            }
        }

        while(!minHeap.isEmpty()) {
            Map.Entry<User, Double> maxHasToPay = minHeap.poll();
            Map.Entry<User, Double> maxPaid = maxHeap.poll();
            TransactionDto transaction = new TransactionDto(
                    maxHasToPay.getKey().getName(),
                    maxPaid.getKey().getName(),
                    Math.min(Math.abs(maxHasToPay.getValue()), Math.abs(maxPaid.getValue())));

            transactionList.add(transaction);

            double outstanding = maxPaid.getValue() + maxHasToPay.getValue();
            if (outstanding < 0) {
                maxHasToPay.setValue(outstanding);
                minHeap.add(maxHasToPay);
            } else if (outstanding > 0) {
                maxPaid.setValue(outstanding);
                maxHeap.add(maxPaid);
            } else {
                System.out.println("Settled");
            }
        }
        return transactionList;
    }

    private double getUpdatedOutStandingAmount(double currentOutStandingAmount, UserExpense userExpense){
        if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
            currentOutStandingAmount = currentOutStandingAmount + userExpense.getAmount();
        } else {
            currentOutStandingAmount = currentOutStandingAmount - userExpense.getAmount();
        }
        return currentOutStandingAmount;
    }
}
