package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Dto.ReservationDto;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Reservation;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import com.fpt.rentahome.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/manage-reservation")

public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    @PostMapping("/add-reservation")
    public ResponseEntity<ApiResponse> createReservation(@RequestBody ReservationDto reservation) {
        Optional<Property> propertyOptional = propertyRepository.findById( reservation.getId_property());
        Optional<Client> clientOptional = clientRepository.findById(reservation.getClient().getId());

        if (!propertyOptional.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "property does not exists"), HttpStatus.BAD_REQUEST);
        }
        if (!clientOptional.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "client does not exists"), HttpStatus.BAD_REQUEST);
        }
        reservationService.createReservation(reservation,propertyOptional.get(),clientOptional.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the reservation"), HttpStatus.CREATED);
    }
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    //update reservation
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateReservation(@PathVariable("id") int id, @RequestBody ReservationDto reservation) {

        Optional<Property> optionalProperty = propertyService.getPropertyById(reservation.getId_property());
        Optional<Client> optionalClient = clientService.getClientById(reservation.getClient().getId());

        if (!optionalProperty.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "property is invalid"), HttpStatus.CONFLICT);
        }
        if (!optionalClient.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "property is invalid"), HttpStatus.CONFLICT);
        }

        Property property = optionalProperty.get();
        Client client = optionalClient.get();
        reservationService.updateReservation(id, reservation, property, client);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
