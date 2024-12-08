package com.dgb.residence.repository;

import com.dgb.residence.entity.MultiplexHouseTrade;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultiplexHouseTradeRepository extends JpaRepository<MultiplexHouseTrade, Long> {
    Optional<MultiplexHouseTrade> findMultiplexHouseTradeByPoint(Point point);
}
