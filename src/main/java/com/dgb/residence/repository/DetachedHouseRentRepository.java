package com.dgb.residence.repository;

import com.dgb.residence.entity.DetachedHouseRent;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetachedHouseRentRepository extends JpaRepository<DetachedHouseRent, Long> {
    Optional<DetachedHouseRent> findDetachedHouseRentByPoint(Point point);
}
