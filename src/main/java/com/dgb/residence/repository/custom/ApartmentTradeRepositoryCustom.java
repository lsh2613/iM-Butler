package com.dgb.residence.repository.custom;

import com.dgb.residence.dto.req.ResidenceTradeFilterSearchCond;
import com.dgb.residence.entity.ApartmentTrade;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dgb.residence.entity.QApartmentTrade.apartmentTrade;

@Repository
@RequiredArgsConstructor
public class ApartmentTradeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<ApartmentTrade> searchFilteredApartmentTrade(ResidenceTradeFilterSearchCond cond) {
        return queryFactory
                .selectFrom(apartmentTrade)
                .where(
                        isWithinRadius(cond.getCenter(), cond.getRadius()),
                        loeDealAmount(cond.getAssets(), cond.getLoan())
                )
                .fetch();
    }

    private BooleanExpression isWithinRadius(Point center, double radius) {
        String sql = String.format(
                "ST_Contains(ST_Buffer(ST_GeomFromText('Point (%s %s)', 4326), %s), %s)",
                center.getY(), center.getX(), radius, apartmentTrade.point
        );
        return Expressions.booleanTemplate(sql);
    }

    private BooleanExpression loeDealAmount(Integer assets, Integer loan) {
        if (assets == null || loan == null) {
            return null;
        }
        return apartmentTrade.dealAmount.loe(assets + loan);
    }
}
