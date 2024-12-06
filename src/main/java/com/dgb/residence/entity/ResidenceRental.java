package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
public abstract class ResidenceRental extends Residence {
    protected Integer deposit;
    protected Integer monthlyRent;

    public ResidenceRental(ResidenceType residenceType, YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent) {
        super(residenceType, monthlyRent == 0 ? DealType.전세 : DealType.월세, dealDate, point);
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
    }
}
