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
@Table(name = "DETACHED_HOUSE_RENT")
public class DetachedHouseRent extends ResidenceRent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public DetachedHouseRent(YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent) {
        super(ResidenceType.단독_다가구, dealDate, point, deposit, monthlyRent);
    }

    @Override
    public String toString() {
        return "DetachedHouseRent{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", deposit=" + getDeposit() +
                ", monthlyRent=" + getMonthlyRent() +
                '}';
    }
}
