package com.project.myExpenses.service;

import com.project.myExpenses.model.Expense;
import com.project.myExpenses.web.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService  {

    List<Expense> getAllExpenses();

    Expense saveExpense(ExpenseDto expenseDto);

    Expense getExpenseById(Long id);

//    Expense getTotalAmount(ExpenseDto expenseDto);


    Expense updateExpense(Expense expense);

//    Expense findByDate(String date);

    void deleteExpenseById(Long id);


    Expense countTotalExpenses(Expense expense);

}
