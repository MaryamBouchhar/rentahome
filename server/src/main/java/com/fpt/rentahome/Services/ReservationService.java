package com.fpt.rentahome.Services;

import com.fpt.rentahome.Dto.ReservationDto;
import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Reservation;
import com.fpt.rentahome.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        reservation.setCreated_at(reservationDto.getCreated_at());
        reservation.setUpdated_at(reservationDto.getUpdated_at());
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
        reservationDto.setClient(reservation.getClient());
        reservationDto.setId(reservation.getId());
        reservationDto.setCreated_at(reservation.getCreated_at());
        reservationDto.setUpdated_at(reservation.getUpdated_at());

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

    public static ReservationDto getDtoFromReservation(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto(reservation);
        return reservationDto;
    }

    public static Reservation getReservationFromDto(ReservationDto reservationDto, Property property, Client client) {
        Reservation reservation = new Reservation(reservationDto, property, client);
        return reservation;
    }

    public void updateReservation(Integer reservationID, ReservationDto reservationDto, Property property, Client client) {
        Reservation reservation = getReservationFromDto(reservationDto, property, client);
        reservation.setId(reservationID);
        reservationRepository.save(reservation);
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
