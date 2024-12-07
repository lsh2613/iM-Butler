package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class ResidenceRent extends Residence {
    protected Integer deposit;
    protected Integer monthlyRent;

    public ResidenceRent(ResidenceType residenceType, YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent) {
        super(residenceType, monthlyRent == 0 ? DealType.전세 : DealType.월세, dealDate, point);
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
    }
}
