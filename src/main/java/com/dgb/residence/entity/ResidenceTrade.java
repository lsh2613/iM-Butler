package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
@NoArgsConstructor
public abstract class ResidenceTrade extends Residence {
    protected Integer dealAmount;

    public ResidenceTrade(ResidenceType residenceType, YearMonth dealDate, Point point, Integer dealAmount) {
        super(residenceType, DealType.매매, dealDate, point);
        this.dealAmount = dealAmount;
    }
}
