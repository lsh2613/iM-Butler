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
@Table(name = "MULTIPLEX_HOUSE_TRADE")
public class MultiplexHouseTrade extends ResidenceTrade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Override
    public void update(Residence newEntity) {
        if (newEntity instanceof MultiplexHouseTrade newMultiplexHouseTrade) {
            this.dealDate = newMultiplexHouseTrade.getDealDate();
            this.dealType = newMultiplexHouseTrade.getDealType();
            this.point = newMultiplexHouseTrade.getPoint();
            this.dealAmount = newMultiplexHouseTrade.getDealAmount();
            this.mhouseNm = newMultiplexHouseTrade.getMhouseNm();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}
