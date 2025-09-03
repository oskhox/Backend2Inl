package com.store.backend2inl.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;
    @Embedded
    private Rating rating;
}