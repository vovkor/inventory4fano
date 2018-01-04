package ru.nw.vir.model;

import java.util.Date;
public class Customer {

    int id;
    String accenumb;
    String genus;
    Date created_date;

    public Customer(int id, String accenumb, String genus, Date created_date) {
        this.id = id;
        this.accenumb = accenumb;
        this.genus = genus;
        this.created_date = created_date;
    }

    //getters and setters and toString...
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accenumb='" + accenumb + '\'' +
                ", genus='" + genus + '\'' +
                ", created_date=" + created_date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccenumb() {
        return accenumb;
    }

    public void setAccenumb(String accenumb) {
        this.accenumb = accenumb;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }	
}