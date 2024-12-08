package com.dgb.residence.repository;

import com.dgb.residence.entity.ApartmentTrade;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentTradeRepository extends JpaRepository<ApartmentTrade, Long> {
    Optional<ApartmentTrade> findApartmentTradeByPoint(Point point);
}
