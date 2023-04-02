package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import com.fpt.rentahome.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ReservationService reservationService;

    //total clients
    @GetMapping("/clients-count")
    public int totalClients() {
        return clientService.totalClients();
    }

    //total properties
    @GetMapping("/properties-count")
    public int totalProperties() {
        return propertyService.totalProperties();
    }

    //today reservations
    @GetMapping("/today-reservations")
    public int todayReservations() {
        return reservationService.todayReservations();
    }

    //newest clients
    @GetMapping("/newest-clients")
    public List<Client> newestClients() {
        return clientService.newestClients();
    }
}
