package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
public abstract class ResidenceSale extends Residence {
    protected Integer dealAmount;

    public ResidenceSale(ResidenceType residenceType, YearMonth dealDate, Point point, Integer dealAmount) {
        super(residenceType, DealType.매매, dealDate, point);
        this.dealAmount = dealAmount;
    }
}
