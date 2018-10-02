package com.danhammant.jobtracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Job  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String company;
    private String location;
    private String url;
    private String status;

    public Job() {

    }

    public Job(String title, String company, String location, String url) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.url = url;
        status = "NOT APPLIED";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nCompany: " + company + "\nLocation: " + location +
                "URL: " + url + "\nApplication Status: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(title, job.title) &&
                Objects.equals(company, job.company) &&
                Objects.equals(location, job.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, company, location);
    }
}

