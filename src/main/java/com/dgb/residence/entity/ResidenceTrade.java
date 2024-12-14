package com.dgb.residence.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class ResidenceTrade extends Residence {
    protected Integer dealAmount;
}
