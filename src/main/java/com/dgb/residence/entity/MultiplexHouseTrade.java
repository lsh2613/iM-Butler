package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
public class MultiplexHouseTrade extends ResidenceTrade {
    private String mhouseNm;

    @Builder
    public MultiplexHouseTrade(YearMonth dealDate, Point point, Integer dealAmount, String mhouseNm) {
        super(ResidenceType.연립다세대, dealDate, point, dealAmount);
        this.mhouseNm = mhouseNm;
    }

    @Override
    public String toString() {
        return "MultiplexHouseTrade{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", dealAmount=" + getDealAmount() +
                ", mhouseNm='" + mhouseNm + '\'' +
                '}';
    }
}
