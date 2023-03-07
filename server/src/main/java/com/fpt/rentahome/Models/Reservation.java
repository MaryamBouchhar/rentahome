package com.fpt.rentahome.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne
    @JoinColumn(name = "property")
    Property property;
    @ManyToOne
    @JoinColumn(name = "client")
    Client client;

    private  String status;
    private Date start_date;
    private Date end_date;



}
