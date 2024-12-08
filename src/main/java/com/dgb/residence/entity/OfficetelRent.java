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
@Table(name = "OFFICETEL_RENT")
public class OfficetelRent extends ResidenceRent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String offiNm;

    @Builder
    public OfficetelRent(YearMonth dealDate, Point point, Integer deposit, Integer monthlyRent, String offiNm) {
        super(ResidenceType.오피스텔, dealDate, point, deposit, monthlyRent);
        this.offiNm = offiNm;
    }

    @Override
    public String toString() {
        return "OfficetelRent{" +
                "dealDate=" + getDealDate() +
                ", dealType=" + getDealType() +
                ", point=" + getPoint() +
                ", deposit=" + getDeposit() +
                ", monthlyRent=" + getMonthlyRent() +
                ", offiNm='" + offiNm + '\'' +
                '}';
    }

    @Override
    public void update(Residence newEntity) {
        if (newEntity instanceof OfficetelRent newOfficetelRent) {
            this.dealDate = newOfficetelRent.getDealDate();
            this.dealType = newOfficetelRent.getDealType();
            this.point = newOfficetelRent.getPoint();
            this.deposit = newOfficetelRent.getDeposit();
            this.monthlyRent = newOfficetelRent.getMonthlyRent();
            this.offiNm = newOfficetelRent.getOffiNm();
        } else {
            throw new IllegalArgumentException("Invalid entity type for update");
        }
    }
}
