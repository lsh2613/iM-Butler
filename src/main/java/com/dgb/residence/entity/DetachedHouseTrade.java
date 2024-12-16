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
@Table(name = "DETACHED_HOUSE_TRADE")
public class DetachedHouseTrade extends ResidenceTrade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public DetachedHouseTrade(YearMonth dealDate, Point point, Integer dealAmount) {
        this.dealDate = dealDate;
        this.point = point;
        this.dealAmount = dealAmount;
    }

    @Override
    public String toString() {
        return "DetachedHouseTrade{" +
                "dealDate=" + dealDate +
                ", point=" + point +
                ", dealAmount=" + dealAmount +
                '}';
    }

    @Override
    public void update(Residence newResidence) {
        if (newResidence instanceof DetachedHouseTrade newDetachedHouseTrade) {
            this.dealDate = newDetachedHouseTrade.getDealDate();
            this.point = newDetachedHouseTrade.getPoint();
            this.dealAmount = newDetachedHouseTrade.getDealAmount();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}
