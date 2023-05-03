package com.project.myExpenses.model;

import com.project.myExpenses.web.dto.ExpenseDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "EXPENSE_DATE")    // expenseDate
    private LocalDate expenseDate;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CATEGORY")
    private String category;

    public Expense() {
    }

    public String getCategory() {
        return category;
    }

    // LocalDate
    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Expense(LocalDate expenseDate,
                   double amount,
                   String category
                   )
    {
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category = category;
    }


//    public int getTotal(ExpenseDto expenseDto) {
//        ArrayList<>
//    }

}

