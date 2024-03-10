package com.fpt.hungnm.assigmentfinal.Model;

import java.util.Date;

public class Budget {
    private int id;
    private String title;
    private String typeOfBudget;
    private String time;
    private String categotyId;
    private Date createDate;

    public Budget(int id, String title, String typeOfBudget, String time, String categotyId, Date createDate) {
        this.id = id;
        this.title = title;
        this.typeOfBudget = typeOfBudget;
        this.time = time;
        this.categotyId = categotyId;
        this.createDate = createDate;
    }

    public Budget(String title, String typeOfBudget, String time, String categotyId, Date createDate) {
        this.title = title;
        this.typeOfBudget = typeOfBudget;
        this.time = time;
        this.categotyId = categotyId;
        this.createDate = createDate;
    }

    public Budget() {
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

    public String getTypeOfBudget() {
        return typeOfBudget;
    }

    public void setTypeOfBudget(String typeOfBudget) {
        this.typeOfBudget = typeOfBudget;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategotyId() {
        return categotyId;
    }

    public void setCategotyId(String categotyId) {
        this.categotyId = categotyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
