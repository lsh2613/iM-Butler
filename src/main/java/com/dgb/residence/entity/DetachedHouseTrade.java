package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
public class DetachedHouseTrade extends ResidenceSale {

    @Builder
    public DetachedHouseTrade(YearMonth dealDate, Point point, Integer dealAmount) {
        super(ResidenceType.단독_다가구, dealDate, point, dealAmount);
    }

    @Override
    public String toString() {
        return "DetachedHouseTrade{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", dealAmount=" + getDealAmount() +
                '}';
    }
}
