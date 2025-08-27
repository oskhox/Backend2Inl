package com.store.backend2inl.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rating {
    private double rate;
    private int count;
}