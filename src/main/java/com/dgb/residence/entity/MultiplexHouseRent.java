package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
public class MultiplexHouseRent extends ResidenceRent {
    private String mhouseNm;

    @Builder
    public MultiplexHouseRent(YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent, String mhouseNm) {
        super(ResidenceType.연립다세대, dealDate, point, deposit, monthlyRent);
        this.mhouseNm = mhouseNm;
    }

    @Override
    public String toString() {
        return "MultiplexHouseRent{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", deposit=" + getDeposit() +
                ", monthlyRent=" + getMonthlyRent() +
                ", mhouseNm='" + mhouseNm + '\'' +
                '}';
    }
}
