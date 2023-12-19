package com.project.myExpenses.web.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class ExpenseDto {


    @NotNull(message = "Date cannot be null")
    @NotEmpty(message = "Date cannot be empty")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expenseDate;

    @NotEmpty(message = "Amount cannot be empty")
    @NotNull(message =  "Amount cannot be null")
    private double amount;


    @NotNull(message = "Category cannot be null")
    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @NotEmpty
    @NotNull
    private double total;


    public ExpenseDto(LocalDate expenseDate, int amount, String category) {
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category = category;

    }

    public ExpenseDto() {

    }

////    public LocalDate getExpenseDate() {
////        return expenseDate;
////    }
////
////    public void setExpenseDate(LocalDate expenseDate) {
////        this.expenseDate = expenseDate;
////    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
}
