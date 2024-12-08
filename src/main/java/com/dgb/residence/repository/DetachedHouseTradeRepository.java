package com.dgb.residence.repository;

import com.dgb.residence.entity.DetachedHouseTrade;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetachedHouseTradeRepository extends JpaRepository<DetachedHouseTrade, Long> {
    Optional<DetachedHouseTrade> findDetachedHouseTradeByPoint(Point point);
}
