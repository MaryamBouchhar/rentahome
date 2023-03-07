package com.fpt.rentahome.Services;

import com.fpt.rentahome.Dto.ReservationDto;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Reservation;
import com.fpt.rentahome.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public void createReservation(ReservationDto reservationDto, Property property, Client client) {
        Reservation reservation = new Reservation();
        reservation.setStatus(reservationDto.getStatus());
        reservation.setStart_date(reservationDto.getStart_date());
        reservation.setEnd_date(reservationDto.getEnd_date());
        reservation.setProperty(property);
        reservation.setClient(client);

        reservation.setEnd_date(reservationDto.getEnd_date());
        reservationRepository.save(reservation);
    }

    public ReservationDto getReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setStatus(reservation.getStatus());
        reservationDto.setStart_date(reservation.getStart_date());
        reservationDto.setEnd_date(reservation.getEnd_date());

        reservationDto.setEnd_date(reservationDto.getEnd_date());
        reservationDto.setId_property(reservation.getProperty().getId());
        reservationDto.setClient_id(reservation.getClient().getId());
        reservationDto.setId(reservation.getId());
        return reservationDto;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> allReservation = reservationRepository.findAll();

        List<ReservationDto> reservationDto = new ArrayList<>();
        for (Reservation reservation : allReservation) {
            reservationDto.add(getReservationDto(reservation));
        }
        return reservationDto;
    }
}
