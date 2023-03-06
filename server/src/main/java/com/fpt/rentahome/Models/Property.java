package com.fpt.rentahome.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "property")
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

}