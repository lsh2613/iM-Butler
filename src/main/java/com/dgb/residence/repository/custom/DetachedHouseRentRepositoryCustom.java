package com.dgb.residence.repository.custom;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.entity.ApartmentRent;
import com.dgb.residence.entity.DetachedHouseRent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dgb.residence.entity.QApartmentRent.apartmentRent;
import static com.dgb.residence.entity.QDetachedHouseRent.detachedHouseRent;

@Repository
@RequiredArgsConstructor
public class DetachedHouseRentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<DetachedHouseRent> searchFilteredDetachedHouseRent(ResidenceRentFilterSearchCond cond) {
        return queryFactory
                .selectFrom(detachedHouseRent)
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
                center.getY(), center.getX(), radius, detachedHouseRent.point
        );
        return Expressions.booleanTemplate(sql);
    }

    private BooleanExpression loeDeposit(Integer assets, Integer loan) {
        if (assets == null || loan == null) {
            return null;
        }
        return detachedHouseRent.deposit.loe(assets + loan);
    }

    private BooleanExpression monthlyRentBetween(Integer minMonthlyRent, Integer maxMonthlyRent) {
        if (minMonthlyRent == null || maxMonthlyRent == null) {
            return null;
        }
        return detachedHouseRent.monthlyRent.between(minMonthlyRent, maxMonthlyRent);
    }

}
