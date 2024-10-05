package com.springboot.splitwise.services;

import com.springboot.splitwise.models.*;
import com.springboot.splitwise.repositories.ExpenseRepository;
import com.springboot.splitwise.repositories.GroupRepository;
import com.springboot.splitwise.repositories.UserExpenseRepository;
import com.springboot.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Override
    public void initialise() {
        User anuj = User.builder()
                .email("anuj@email.com")
                .phoneNumber("1234567890")
                .name("anuj daftary")
                .build();

        User abhi = User.builder()
                .email("abhi@email.com")
                .phoneNumber("2234567890")
                .name("abhi dongre")
                .build();

        User ayush = User.builder()
                .email("ayush@email.com")
                .phoneNumber("3234567890")
                .name("ayush joshi")
                .build();

        User abhishek = User.builder()
                .email("abhishek@email.com")
                .phoneNumber("4234567890")
                .name("abhishek chaurey")
                .build();

        anuj = userRepository.save(anuj);
        abhi = userRepository.save(abhi);
        ayush = userRepository.save(ayush);
        abhishek = userRepository.save(abhishek);

        Group group = new Group();
        group.setDescription("Friends who never pay back on time");
        group.setName("Trip to Manali");
        group.setUsers(List.of(anuj, abhi, ayush, abhishek));

        group =  groupRepository.save(group);

        //4 expenses
        //Expense 1 -> amount 1000, paid By : Omkar, hasTopay : everyone equal
        UserExpense userExpense = new UserExpense();
        userExpense.setUser(anuj);
        userExpense.setAmount(1000);
        userExpense.setUserExpenseType(UserExpenseType.PAID);
        userExpense = userExpenseRepository.save(userExpense);

        UserExpense userExpense1 = new UserExpense();
        userExpense1.setUser(anuj);
        userExpense1.setAmount(250);
        userExpense1.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense1 = userExpenseRepository.save(userExpense1);

        UserExpense userExpense2 = new UserExpense();
        userExpense2.setUser(abhi);
        userExpense2.setAmount(250);
        userExpense2.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense2 = userExpenseRepository.save(userExpense2);

        UserExpense userExpense3 = new UserExpense();
        userExpense3.setUser(abhishek);
        userExpense3.setAmount(250);
        userExpense3.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense3 = userExpenseRepository.save(userExpense3);

        UserExpense userExpense4 = new UserExpense();
        userExpense4.setUser(ayush);
        userExpense4.setAmount(250);
        userExpense4.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense4 = userExpenseRepository.save(userExpense4);

        Expense expense = new Expense();
        expense.setAmount(1000);
        expense.setDescription("Dinner");
        expense.setCurrency(Currency.INR);
        expense.setUserExpenses(List.of(userExpense, userExpense1, userExpense2, userExpense3, userExpense4));

        expenseRepository.save(expense);
    }
}
