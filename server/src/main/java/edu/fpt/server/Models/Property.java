package edu.fpt.server.Models;

import java.util.Date;

public class Property {
    private int id;
    private String category;
    private String rent_type;
    private int location_id;
    private float price;
    private String description;
    private float price;
    private float area;
    private String status;
    private int location;
    private int bathroom_count;
    private int room_count;
    private boolean is_equiped;
    private Date published_date;

    public Property() {
    }

    public Property(int id, String category, float price, String description, int bathroom_count, int room_count, float area, String status, String city, boolean is_equiped, Date published_date) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.description = description;
        this.bathroom_count = bathroom_count;
        this.room_count = room_count;
        this.area = area;
        this.status = status;
        this.is_equiped = is_equiped;
        this.published_date = published_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation() {
        return location;
    }

    public String getRent_type() {
        return rent_type;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBathroom_count() {
        return bathroom_count;
    }

    public void setBathroom_count(int bathroom_count) {
        this.bathroom_count = bathroom_count;
    }

    public int getRoom_count() {
        return room_count;
    }

    public void setRoom_count(int room_count) {
        this.room_count = room_count;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIs_equiped() {
        return is_equiped;
    }

    public void setIs_equiped(boolean is_equiped) {
        this.is_equiped = is_equiped;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }
}
