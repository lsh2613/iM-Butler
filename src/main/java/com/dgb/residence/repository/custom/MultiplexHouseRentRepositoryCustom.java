package com.dgb.residence.repository.custom;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.entity.DetachedHouseRent;
import com.dgb.residence.entity.MultiplexHouseRent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dgb.residence.entity.QApartmentRent.apartmentRent;
import static com.dgb.residence.entity.QDetachedHouseRent.detachedHouseRent;
import static com.dgb.residence.entity.QMultiplexHouseRent.multiplexHouseRent;

@Repository
@RequiredArgsConstructor
public class MultiplexHouseRentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<MultiplexHouseRent> searchFilteredMultiplexHouseRent(ResidenceRentFilterSearchCond cond) {
        return queryFactory
                .selectFrom(multiplexHouseRent)
                .where(
                        isWithinRadius(cond.getCenter(), cond.getRadius()),
                        loeDeposit(cond.getAssets(), cond.getLoan()),
                        monthlyRentBetween(cond.getMinMonthlyRent(), cond.getMaxMonthlyRent())
                )
                .fetch();
    }

    private BooleanExpression isWithinRadius(Point center, double radius) {
        String sql = String.format(
                "ST_Contains(ST_Buffer(ST_GeomFromText('Point (%s %s)', 4326), %s), %s)",
                center.getY(), center.getX(), radius, multiplexHouseRent.point
        );
        return Expressions.booleanTemplate(sql);
    }

    private BooleanExpression loeDeposit(Integer assets, Integer loan) {
        if (assets == null || loan == null) {
            return null;
        }
        return multiplexHouseRent.deposit.loe(assets + loan);
    }

    private BooleanExpression monthlyRentBetween(Integer minMonthlyRent, Integer maxMonthlyRent) {
        if (minMonthlyRent == null || maxMonthlyRent == null) {
            return null;
        }
        return multiplexHouseRent.monthlyRent.between(minMonthlyRent, maxMonthlyRent);
    }

}
