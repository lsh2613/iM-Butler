package com.dgb.residence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "APARTMENT_TRADE")
public class ApartmentTrade extends ResidenceTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aptNm;

    @Builder
    public ApartmentTrade(YearMonth dealDate, Point point, Integer dealAmount, String aptNm) {
        this.dealDate = dealDate;
        this.point = point;
        this.dealAmount = dealAmount;
        this.aptNm = aptNm;
    }

    @Override
    public String toString() {
        return "ApartmentTrade{" +
                "dealDate=" + dealDate +
                ", dealType=" + dealType +
                ", point=" + point +
                ", dealAmount=" + dealAmount +
                ", aptNm='" + aptNm + '\'' +
                '}';
    }

    @Override
    public void update(Residence newResidence) {
        if (newResidence instanceof ApartmentTrade newApartmentTrade) {
            this.dealDate = newApartmentTrade.getDealDate();
            this.dealType = newApartmentTrade.getDealType();
            this.point = newApartmentTrade.getPoint();
            this.dealAmount = newApartmentTrade.getDealAmount();
            this.aptNm = newApartmentTrade.getAptNm();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}

