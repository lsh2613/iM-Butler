package com.dgb.residence.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@AllArgsConstructor
@Getter
public class ResidenceTradeFilterSearchCond {
    private Point center;
    private Integer radius;
    private Integer assets;
    private Integer loan;
}
