package com.project.myExpenses.web;


import com.project.myExpenses.model.Expense;
import com.project.myExpenses.repository.ExpenseRepository;
import com.project.myExpenses.service.ExpenseService;
import com.project.myExpenses.service.ExpenseServiceImpl;
import com.project.myExpenses.service.UserService;
import com.project.myExpenses.web.dto.ExpenseDto;
import com.project.myExpenses.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Controller
//@RequestMapping("/expenses/api")
public class ExpensesController {

    private ExpenseService expenseService;

    public ExpensesController(ExpenseService expenseService) {
        super();
        this.expenseService = expenseService;
    }



    // handler method to handle list students and return mode and view
    @GetMapping("/expenses")
    public String listStudents(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expenses";
    }

    @GetMapping("/expenses/new")
    public String createExpenseForm(Model model) {

        // create expense object to hold expense form data
       ExpenseDto expenseDto = new ExpenseDto();



        model.addAttribute("expense", expenseDto );
        return "create_expense";

    }
//
//    @PostMapping("/expenses/new")
//    public String saveExpense_post(@ModelAttribute("expense") @Valid ExpenseDto expenseDto) {
//        expenseService.saveExpense(expenseDto);
//        return "redirect:/expenses";
//
//    }
//
//    @ModelAttribute("expense")
//    public ExpenseDto saveExpense() {
//        return new ExpenseDto();
//    }
    @PostMapping("/expenses")
    public String saveExpense(@ModelAttribute("expense") @Valid ExpenseDto expenseDto) {
        expenseService.saveExpense(expenseDto);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("expense", expenseService.getExpenseById(id));
        return "edit_expense";
    }

    @PostMapping("/expenses/{id}")
    public String updateExpense(@PathVariable Long id,
                                @ModelAttribute("expense") ExpenseDto expenseDto,
                                Model model) {

        // get student from database by id
        Expense existingExpense = expenseService.getExpenseById(id);
        existingExpense.setId(id);
        existingExpense.setExpensedate(expenseDto.getExpensedate());
        existingExpense.setCategory(expenseDto.getCategory());
        existingExpense.setAmount(expenseDto.getAmount());
//        existingExpense.setUser(expenseDto.getUser());
        // save updated student object
        expenseService.updateExpense(existingExpense);
        return "redirect:/expenses";
    }

    // handler method to handle delete student request

    @GetMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/expenses";
    }
}
