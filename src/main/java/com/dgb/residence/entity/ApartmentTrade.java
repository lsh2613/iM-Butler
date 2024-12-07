package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
public class ApartmentTrade extends ResidenceTrade {

    private String aptNm;

    @Builder
    public ApartmentTrade(YearMonth dealDate, Point point, Integer dealAmount, String aptNm) {
        super(ResidenceType.아파트, dealDate, point, dealAmount);
        this.aptNm = aptNm;
    }

    @Override
    public String toString() {
        return "ApartmentTrade{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", dealAmount=" + getDealAmount() +
                ", aptNm='" + aptNm + '\'' +
                '}';
    }
}

