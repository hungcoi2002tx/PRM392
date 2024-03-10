package com.fpt.hungnm.assigmentfinal.Model;

import java.util.Date;

public class Category {
    private int id;
    private String title;
    private String isIncome;
    private Date createDate;

    public Category(int id, String title, String isIncome, Date createDate) {
        this.id = id;
        this.title = title;
        this.isIncome = isIncome;
        this.createDate = createDate;
    }

    public Category(String title, String isIncome, Date createDate) {
        this.title = title;
        this.isIncome = isIncome;
        this.createDate = createDate;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(String isIncome) {
        this.isIncome = isIncome;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
