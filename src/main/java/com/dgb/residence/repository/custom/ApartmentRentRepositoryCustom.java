package com.dgb.residence.repository.custom;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.entity.ApartmentRent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dgb.residence.entity.QApartmentRent.apartmentRent;

@Repository
@RequiredArgsConstructor
public class ApartmentRentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<ApartmentRent> searchFilteredApartmentRent(ResidenceRentFilterSearchCond cond) {
        return queryFactory
                .selectFrom(apartmentRent)
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
                center.getY(), center.getX(), radius, apartmentRent.point
        );
        return Expressions.booleanTemplate(sql);
    }

    private BooleanExpression loeDeposit(Integer assets, Integer loan) {
        if (assets == null || loan == null) {
            return null;
        }
        return apartmentRent.deposit.loe(assets + loan);
    }

    private BooleanExpression monthlyRentBetween(Integer minMonthlyRent, Integer maxMonthlyRent) {
        if (minMonthlyRent == null || maxMonthlyRent == null) {
            return null;
        }
        return apartmentRent.monthlyRent.between(minMonthlyRent, maxMonthlyRent);
    }

}
