package com.fpt.rentahome.Models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private int rating;
    private String created_at;
    private String updated_at;
}
