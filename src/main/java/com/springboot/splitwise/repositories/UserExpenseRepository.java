package com.springboot.splitwise.repositories;

import com.springboot.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
}
