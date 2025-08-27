package com.store.backend2inl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    private int id;
    private String title;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;
    @Embedded
    private Rating rating;
}