package com.dgb.residence.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@MappedSuperclass
@Getter
public abstract class Residence {
    protected YearMonth dealDate;
    protected Point point;

    public abstract void update(Residence newResidence);
}
