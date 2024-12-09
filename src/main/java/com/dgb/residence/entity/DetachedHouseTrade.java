package com.dgb.residence.entity;

import com.dgb.constant.ResidenceType;
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

    @Override
    public void update(Residence newResidence) {
        if (newResidence instanceof DetachedHouseTrade newDetachedHouseTrade) {
            this.dealDate = newDetachedHouseTrade.getDealDate();
            this.dealType = newDetachedHouseTrade.getDealType();
            this.point = newDetachedHouseTrade.getPoint();
            this.dealAmount = newDetachedHouseTrade.getDealAmount();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}
