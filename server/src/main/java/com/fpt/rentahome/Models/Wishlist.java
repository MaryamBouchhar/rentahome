package com.fpt.rentahome.Models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Setter
@Getter
@Entity
@Table(name="wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client ;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
