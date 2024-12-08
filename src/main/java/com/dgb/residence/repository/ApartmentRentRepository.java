package com.dgb.residence.repository;

import com.dgb.residence.entity.ApartmentRent;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRentRepository extends JpaRepository<ApartmentRent, Long> {
    Optional<ApartmentRent> findApartmentRentByPoint(Point point);
}
