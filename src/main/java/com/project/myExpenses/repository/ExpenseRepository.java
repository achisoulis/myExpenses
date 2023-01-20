package com.project.myExpenses.repository;

import com.project.myExpenses.model.Expense;
import com.project.myExpenses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

//public List<Expense> findAll();

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    public Expense findByEmail(String email);


//    @Query("SELECT sum(amount) as total FROM Expenses e WHERE e.")
//    public Expense totalAmount(List<Expense> amount);
}
