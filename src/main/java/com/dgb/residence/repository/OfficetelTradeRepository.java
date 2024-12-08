package com.dgb.residence.repository;

import com.dgb.residence.entity.OfficetelTrade;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficetelTradeRepository extends JpaRepository<OfficetelTrade, Long> {
    Optional<OfficetelTrade> findOfficetelTradeByPoint(Point point);
}
