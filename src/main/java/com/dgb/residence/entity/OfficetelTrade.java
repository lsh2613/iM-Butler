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
@Table(name = "OFFICETEL_TRADE")
public class OfficetelTrade extends ResidenceTrade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Override
    public void update(Residence newEntity) {
        if (newEntity instanceof OfficetelTrade newOfficetelTrade) {
            this.dealDate = newOfficetelTrade.getDealDate();
            this.dealType = newOfficetelTrade.getDealType();
            this.point = newOfficetelTrade.getPoint();
            this.dealAmount = newOfficetelTrade.getDealAmount();
            this.offiNm = newOfficetelTrade.getOffiNm();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}
