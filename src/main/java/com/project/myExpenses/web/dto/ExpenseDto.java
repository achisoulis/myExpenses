package com.project.myExpenses.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class ExpenseDto {


//    @DateTimeFormat

    @NotNull
    @NotEmpty
    private String expensedate;


    @NotEmpty(message = "Amount cannot be empty")
    @NotNull(message =  "Amount cannot be null")
    private double amount;


    @NotNull(message = "Category cannot be null")
    @NotEmpty(message = "Category cannot be empty")
    private String category;

//    @NotEmpty
//    @NotNull
    private Integer total;

    public ExpenseDto(String expensedate, int amount, String category, int total) {
        this.expensedate = expensedate;
        this.amount = amount;
        this.category = category;
        this.total = total;
    }

    public ExpenseDto() {

    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getExpensedate() {
        return expensedate;
    }

    public void setExpensedate(String expensedate) {
        this.expensedate = expensedate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
