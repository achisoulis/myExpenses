package com.project.myExpenses.web;


import com.project.myExpenses.model.Expense;
import com.project.myExpenses.service.ExpenseService;
import com.project.myExpenses.web.dto.ExpenseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

//        // create expense object to hold expense form data
       ExpenseDto expenseDto = new ExpenseDto();
//        expenseService.saveExpense(expenseDto);


        model.addAttribute("expense", expenseDto );
        return "create_expense";

    }

//    @PostMapping("/expenses/new")
//    public  String calculate_total_expenses(Model model, @ModelAttribute("expense") @Valid ExpenseDto expenseDto)
//    {
//       // Prepei na parw gia kathe Date ( p.x. 02/12/1994 -> Sum amount )
//
//
//        return "redirect:/expenses";
//    }

    @PostMapping("/expenses/new")
    public String saveExpense_post(Model model,@ModelAttribute("expense") @Valid ExpenseDto expenseDto, BindingResult result) {

        String  pattern = "YYYY/MM/DD";
        Pattern pattern_numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

        boolean isValidFormat = expenseDto.getExpensedate().matches("^[0-3]{1}[0-9]{1}/[0-1]{1}[1-2]{1}/[1-9]{1}[0-9]{3}$");
        if (expenseDto.getExpensedate() == null) {
            result.rejectValue("expensedate", null,"Date cannot be null");
            return "create_expense";}
        if ( !isValidFormat ) {
            result.rejectValue("expensedate", null,"Please place date on specific format :DD/MM/YYYY");
            return "create_expense";
        }

        if ( expenseDto.getAmount() == 0)
        {  result.rejectValue("amount", null,"Please place your amount purchased at Euro(E)");
            return "create_expense";}

        if ( expenseDto.getCategory().isEmpty() || expenseDto.getCategory() == null
        || pattern.matches((expenseDto.getCategory())))
        { result.rejectValue("category", null,"Please select Category of goods, take in mind that Category cannot be a number");
            return "create_expense";}


        expenseService.saveExpense(expenseDto);
        model.addAttribute("expense", expenseDto );

        return "redirect:/expenses";
    }
//
//    @ModelAttribute("expense")
//    public ExpenseDto saveExpense() {
//        return new ExpenseDto();
//    }
    @PostMapping("/expenses")
    public String saveExpense(@ModelAttribute("expense") @Valid ExpenseDto expenseDto, BindingResult result) {

         String expenseDtodate = expenseDto.getExpensedate();
//        String patern_date = 'YYYY-MM-DD';
//        if (expenseDate != null && && !existingUser.getEmail().isEmpty()) {
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//            return  "registration";
//        }
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
                                Model model, BindingResult result) {

        // get student from database by id
        Expense existingExpense = expenseService.getExpenseById(id);
        existingExpense.setId(id);
        existingExpense.setExpensedate(expenseDto.getExpensedate());
        existingExpense.setCategory(expenseDto.getCategory());
        existingExpense.setAmount(expenseDto.getAmount());
//        existingExpense.setUser(expenseDto.getUser());
        // save updated student object

        String  pattern = "YYYY/MM/DD";
        Pattern pattern_numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

        boolean isValidFormat = expenseDto.getExpensedate().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        if (expenseDto.getExpensedate() == null) {
            result.rejectValue("expensedate", null,"Date cannot be null");
            return "create_expense";}
        if ( !isValidFormat ) {
            result.rejectValue("expensedate", null,"Please place date on specific format :DD/MM/YYYY");
            return "create_expense";
        }
        if ( expenseDto.getAmount() == 0)
        {  result.rejectValue("amount", null,"Please place your amount purchased at Euro(E)");
            return "create_expense";}
        if ( expenseDto.getCategory().isEmpty() || expenseDto.getCategory() == null
                || pattern.matches((expenseDto.getCategory())))
        { result.rejectValue("category", null,"Please select Category of goods, take in mind that Category cannot be a number");
            return "create_expense";}
//        expenseService.saveExpense(expenseDto);
//        model.addAttribute("expense", expenseDto );
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
