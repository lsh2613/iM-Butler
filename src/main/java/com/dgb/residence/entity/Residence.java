package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Residence {
    @Enumerated(EnumType.STRING)
    protected ResidenceType residenceType;
    @Enumerated(EnumType.STRING)
    protected DealType dealType;
    protected YearMonth dealDate;
    protected Point point;
}
