package com.fpt.rentahome.Dto;

import com.fpt.rentahome.Models.Property;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDto {
    private int id ;
    private int id_property;
    private int  client_id;
    private  String status;
    private Date start_date;
    private Date end_date;
}
