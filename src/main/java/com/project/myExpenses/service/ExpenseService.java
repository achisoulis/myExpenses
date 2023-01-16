package com.project.myExpenses.service;

import com.project.myExpenses.model.Expense;
import com.project.myExpenses.web.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService  {

    List<Expense> getAllExpenses();

    Expense saveExpense(ExpenseDto expenseDto);

    Expense getExpenseById(Long id);

    Expense updateExpense(Expense expense);



    void deleteExpenseById(Long id);
}
