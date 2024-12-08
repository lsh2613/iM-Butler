package com.dgb.residence.processor;

import com.dgb.residence.entity.ApartmentRent;
import com.dgb.residence.repository.ApartmentRentRepository;
import com.dgb.residence.util.ResidenceParsingUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApartmentRentProcessor implements ResidenceProcessor<ApartmentRent> {

    @Value("${data.apartment-rent.endpoint}")
    private String aptRentEndpoint;

    private final ApartmentRentRepository apartmentRentRepository;

    @Override
    public ApartmentRent parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToAptRent(residenceInfo, point);
    }

    @Override
    public Optional<ApartmentRent> findByPoint(Point point) {
        return apartmentRentRepository.findApartmentRentByPoint(point);
    }

    @Override
    public void save(ApartmentRent entity) {
        apartmentRentRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return aptRentEndpoint;
    }
}
