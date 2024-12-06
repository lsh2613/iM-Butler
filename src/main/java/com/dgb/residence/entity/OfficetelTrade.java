package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
public class OfficetelTrade extends ResidenceSale{
    private String offiNm;

    @Builder
    public OfficetelTrade(YearMonth dealDate, Point point, Integer dealAmount, String offiNm) {
        super(ResidenceType.오피스텔, dealDate, point, dealAmount);
        this.offiNm = offiNm;
    }

    @Override
    public String toString() {
        return "OfficetelTrade{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", dealAmount=" + getDealAmount() +
                ", offiNm='" + offiNm + '\'' +
                '}';
    }
}
