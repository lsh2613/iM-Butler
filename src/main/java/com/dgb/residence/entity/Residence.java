package com.dgb.residence.entity;


import com.dgb.constant.DealType;
import com.dgb.constant.ResidenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.time.YearMonth;

@Getter
@AllArgsConstructor
public abstract class Residence {
    protected ResidenceType residenceType;
    protected DealType dealType;
    protected YearMonth dealDate;
    protected Point point;
}
