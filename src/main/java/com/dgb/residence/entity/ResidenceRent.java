package com.dgb.residence.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class ResidenceRent extends Residence {
    protected Integer deposit;
    protected Integer monthlyRent;
}
