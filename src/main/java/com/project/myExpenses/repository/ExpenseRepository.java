package com.project.myExpenses.repository;

import com.project.myExpenses.model.Expense;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

public List<Expense> findAll();

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Expense findByEmail(String email);


    @Query("SELECT e from Expense e WHERE e.expenseDate=:date")
    List<Expense> findByDate(@Param("date") LocalDate date);

    @Query("Select e from Expense e WHERE e.amount=:amount")
    List<Expense> countTotal(@Param("amount") double amount);


    @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate")
    List<Expense> findExpensesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);




//   List<Expense> saveExpense();


}
