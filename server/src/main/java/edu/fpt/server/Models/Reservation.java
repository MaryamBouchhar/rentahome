package edu.fpt.server.Models;

import java.util.Date;

public class Reservation {

    private int id ;
    private int property_id;
    private int  client_id;
    private  String status;
    private Date reservation_date;

    public Reservation(int id, int property_id, int client_id, String status, Date reservation_date) {
        this.id = id;
        this.property_id = property_id;
        this.client_id = client_id;
        this.status = status;
        this.reservation_date = reservation_date;
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

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }
}
