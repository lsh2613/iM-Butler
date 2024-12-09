package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@MappedSuperclass
@Getter
public abstract class Residence {
    @Enumerated(EnumType.STRING)
    protected DealType dealType;
    protected YearMonth dealDate;
    protected Point point;

    public abstract void update(Residence newResidence);
}
