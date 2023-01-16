package com.project.myExpenses.model;

import java.time.Instant;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private String user;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @Column(name = "user")
//    private String user(User user) {
//        return this.user = user.getFirstName();
//    }

    @Column(name= "expensedate")
    private String expensedate;

    @Column(name = "amount")
    private double amount;

    @Column(name= "total")
    private  Integer total;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false)
//    private Date expensedate;
//
//    @PrePersist
//    private void onCreate() {
//        expensedate = new Date();
//    }



    @Column(name = "category")
    private String category;


    public Expense() {
    }


    public Expense(String expensedate, double amount, String category, int total) {
    }



    public String getCategory() {
        return category;
    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }


//    @JsonIgnore

//    @ManyToOne
//    private User user;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }



//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Expense(String expensedate, double amount, String category) {
    this.expensedate = expensedate;
        this.amount = amount;
        this.category = category;
//        this.user = user;
    }
}

