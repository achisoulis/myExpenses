package com.project.myExpenses.web;


import com.project.myExpenses.model.Expense;
import com.project.myExpenses.repository.ExpenseRepository;
import com.project.myExpenses.service.ExpenseService;
import com.project.myExpenses.utils.DateRange;
import com.project.myExpenses.web.dto.ExpenseDto;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Controller
//@RequestMapping("/expenses/api")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;


    public ExpensesController(ExpenseService expenseService) {
        super();
        this.expenseService = expenseService;
    }


    // handler method to handle list students and return mode and view
    @GetMapping("/expenses")
    public String listStudents(Model model, RedirectAttributes attr) {

        model.addAttribute("expenses", expenseService.getAllExpenses());

        return "expenses";
    }

    @GetMapping("/expenses/new")
    public String createExpenseForm(Model model) {

        // create expense object to hold expense form data
        ExpenseDto expenseDto = new ExpenseDto();
        model.addAttribute("expense", expenseDto);

        return "create_expense";

    }


    @PostMapping("/expenses/new")
    public String saveExpense_post(Model model,
                                   @ModelAttribute("expense") @Valid ExpenseDto expenseDto,
                                   BindingResult result,
                                   RedirectAttributes attr
    ) {

        String pattern = "YYYY/MM/DD";
        Pattern pattern_numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

//        // Function call


        if (expenseDto.getExpenseDate()
                .toString() != null) {
            boolean isValidFormat = expenseDto.getExpenseDate()
                    .toString()
                    .matches("\\d{4}-\\d{2}-\\d{2}");

            if (expenseDto.getExpenseDate().toString() == null) {
                result.rejectValue("expenseDate", null, "Date cannot be null");
                return "create_expense";

            }


            if (!isValidFormat) {
                result.rejectValue("expenseDate", null, "Place date on specific format :YYYY/MM/DD");
                return "create_expense";

            } else
                model.addAttribute("fail", " Date cannot be null");
        }

        if (expenseDto.getAmount() == 0) {
            result.rejectValue("amount", null, "Please place your amount purchased at Euro(E)");
            return "create_expense";

        }

        if (expenseDto.getCategory().isEmpty() || expenseDto.getCategory() == null
                || pattern.matches((expenseDto.getCategory()))) {
            result.rejectValue("category", null, "Please select Category of goods, take in mind that Category cannot be a number");
            return "create_expense";

        }

        expenseService.saveExpense(expenseDto);
//        model.addAttribute("expense", expenseDto);
        model.addAttribute("sucessfullMsg", " Expense have been successfully submitted");
        return "success_message";
    }


    @PostMapping("/expenses")
    public String saveExpense(@ModelAttribute("expense") @Valid ExpenseDto expenseDto, BindingResult result, RedirectAttributes attr, Model model) {

        LocalDate expenseDtoDate = expenseDto.getExpenseDate();

//        }
        expenseService.saveExpense(expenseDto);

//        model.addAttribute("expense", expenseRepository.countTotalAmountByDate());

        return "redirect:/expenses";
    }

    @GetMapping("/expenses/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        model.addAttribute("expense", expenseService.getExpenseById(id));
        return "edit_expense";
    }



/*
    @GetMapping("/submitDateRange")
    public String showForm(Model model) {
        model.addAttribute("dateRange", new DateRange());
        return "calculateTotal";
    }
    */


    @PostMapping("/submitDateRange")
    public String submitDate(@ModelAttribute DateRange dateRange, Model model, BindingResult result) {

        String startDateStr = dateRange.getStartDate();
        String endDateStr = dateRange.getEndDate();

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        if ( startDate.toString().isEmpty() )
                result.rejectValue("startDate" , null, "Please choose startdate");

        if ( endDate.toString().isEmpty())
            result.rejectValue("endDate", null, "Please choose enddate");




        List<Expense> totalExpense = expenseRepository.findExpensesBetweenDates(startDate, endDate);

        double sum = 0;

        for (int i = 0; i < totalExpense.size(); i++) {
            sum += totalExpense.get(i).getAmount();
        }

//        totalExpenses = sum;
//
        model.addAttribute("totalExpenses", sum);



        return "calculate_total";

    }
//
//    @PostMapping("/submit")
//    public String totalExpenses(@ModelAttribute


    @PostMapping("/expenses/{id}")
    public String updateExpense(@PathVariable Long id,
                                @ModelAttribute("expense") ExpenseDto expenseDto,
                                Model model, BindingResult result) {

        // get student from database by id
        Expense existingExpense = expenseService.getExpenseById(id);
        existingExpense.setId(id);
        existingExpense.setExpenseDate(expenseDto.getExpenseDate());
        existingExpense.setCategory(expenseDto.getCategory());
        existingExpense.setAmount(expenseDto.getAmount());
//        existingExpense.setUser(expenseDto.getUser());

        String pattern = "YYYY/MM/DD";
        Pattern pattern_numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

        boolean isValidFormat = expenseDto.getExpenseDate().toString().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        if (expenseDto.getExpenseDate() == null) {
            result.rejectValue("expensedate", null, "Date cannot be null");
            return "create_expense";
        }
        if (!isValidFormat) {
            result.rejectValue("expensedate", null, "Please place date on specific format :DD/MM/YYYY");
            return "create_expense";
        }
        if (expenseDto.getAmount() == 0) {
            result.rejectValue("amount", null, "Please place your amount purchased at Euro(E)");
            return "create_expense";
        }
        if (expenseDto.getCategory().isEmpty() || expenseDto.getCategory() == null
                || pattern.matches((expenseDto.getCategory()))) {
            result.rejectValue("category", null, "Please select Category of goods, take in mind that Category cannot be a number");
            return "create_expense";
        }
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
