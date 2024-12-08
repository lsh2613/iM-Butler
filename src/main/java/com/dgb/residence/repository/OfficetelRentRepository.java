package com.dgb.residence.repository;

import com.dgb.residence.entity.OfficetelRent;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficetelRentRepository extends JpaRepository<OfficetelRent, Long> {
    Optional<OfficetelRent> findOfficetelRentByPoint(Point point);
}
