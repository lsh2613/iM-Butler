package com.dgb.residence.repository;

import com.dgb.residence.entity.MultiplexHouseRent;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultiplexHouseRentRepository extends JpaRepository<MultiplexHouseRent, Long> {
    Optional<MultiplexHouseRent> findMultiplexHouseRentByPoint(Point point);
}
