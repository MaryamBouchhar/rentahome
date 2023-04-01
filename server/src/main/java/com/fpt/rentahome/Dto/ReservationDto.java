package com.fpt.rentahome.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Reservation;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDto {
    private int id;
    private int id_property;
    private Client client;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy, h:mm:ss a")
    private Date start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy, h:mm:ss a")
    private Date end_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy, h:mm:ss a")
    private Date created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy, h:mm:ss a")
    private Date updated_at;

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.id_property = reservation.getProperty().getId();
        this.client = reservation.getClient();
        this.status = reservation.getStatus();
        this.start_date = reservation.getStart_date();
        this.end_date = reservation.getEnd_date();
        this.created_at = reservation.getCreated_at();
        this.updated_at = reservation.getUpdated_at();
    }
}
