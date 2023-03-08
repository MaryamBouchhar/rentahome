package com.fpt.rentahome.Models;

import com.fpt.rentahome.Dto.ReservationDto;
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
    private Date created_at;
    private Date updated_at;

    public Reservation(ReservationDto reservationDto, Property property, Client client) {
        this.status = reservationDto.getStatus();
        this.start_date = reservationDto.getStart_date();
        this.end_date = reservationDto.getEnd_date();
        this.property = property;
        this.client = client;
        this.created_at = new Date();
        this.updated_at = new Date();
    }

}
