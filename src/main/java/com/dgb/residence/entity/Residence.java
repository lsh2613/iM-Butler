package com.dgb.residence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@MappedSuperclass
@Getter
public abstract class Residence {
    protected YearMonth dealDate;
    @Column(columnDefinition = "POINT NOT NULL SRID 4326")
    protected Point point;

    public abstract void update(Residence newResidence);
}
