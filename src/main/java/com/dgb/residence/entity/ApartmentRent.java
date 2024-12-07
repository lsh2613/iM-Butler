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
@Table(name = "APARTMENT_RENT")
public class ApartmentRent extends ResidenceRent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aptNm;

    @Builder
    public ApartmentRent(YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent, String aptNm) {
        super(ResidenceType.아파트, dealDate, point, deposit, monthlyRent);
        this.aptNm = aptNm;
    }

    @Override
    public String toString() {
        return "ApartmentRent{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", deposit=" + getDeposit() +
                ", monthlyRent=" + getMonthlyRent() +
                ", aptNm='" + aptNm + '\'' +
                '}';
    }
}
