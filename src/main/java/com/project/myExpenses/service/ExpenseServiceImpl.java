package com.project.myExpenses.service;

import com.project.myExpenses.model.Expense;
import com.project.myExpenses.repository.ExpenseRepository;
import com.project.myExpenses.web.dto.ExpenseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        super();
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }


    @Override
    public Expense saveExpense(ExpenseDto expenseDto) {

//        List<Expense> expenses = expenseRepository.findByDate(expenseDto.getExpenseDate());


        Expense expense = new Expense(expenseDto.getExpenseDate(),
                expenseDto.getAmount(),
                expenseDto.getCategory());


        return expenseRepository.save(expense);

}

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).get();
    }


    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense countTotalExpenses(Expense expense) {
        return (Expense) expenseRepository.countTotal(expense.getAmount());
    }
    }
