package edu.fpt.server.Models;

import java.util.Date;

public class Reservation {

    private int id ;
    private int property_id;
    private int  client_id;
    private  String status;
    private Date start_date;
    private Date end_date;


    public Reservation() {
    }

    public Reservation(int id, int property_id, int client_id, String status, Date start_date, Date end_date) {
        this.id = id;
        this.property_id = property_id;
        this.client_id = client_id;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
