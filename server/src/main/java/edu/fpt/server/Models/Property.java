package edu.fpt.server.Models;

import java.util.Date;

public class Property {
    private int id;
    private String category;
    private String description;
    private float price;
    private float area;
    private String status;
    private int location_id;
    private String rent_type;
    private int bathroom_count;
    private int room_count;
    private boolean is_equipped;
    private Date publish_date;

    public Property() {
    }

    public Property(int id, String category, String description, float price, float area, String status, int location_id, String rent_type, int bathroom_count, int room_count, boolean is_equiped, Date published_date) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.price = price;
        this.area = area;
        this.status = status;
        this.location_id = location_id;
        this.rent_type = rent_type;
        this.bathroom_count = bathroom_count;
        this.room_count = room_count;
        this.is_equipped = is_equiped;
        this.publish_date = published_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getRent_type() {
        return rent_type;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
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

    public boolean isIs_equipped() {
        return is_equipped;
    }

    public void setIs_equipped(boolean is_equipped) {
        this.is_equipped = is_equipped;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }
}
