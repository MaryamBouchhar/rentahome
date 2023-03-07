package com.fpt.rentahome.Models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String city;
    private Double longitude;
    private Double latitude;
    @OneToOne(mappedBy = "location")
    private Property property;

}
