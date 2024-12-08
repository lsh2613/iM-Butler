package com.dgb.residence.processor;

import com.dgb.residence.entity.MultiplexHouseRent;
import com.dgb.residence.repository.MultiplexHouseRentRepository;
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
public class MultiplexHouseRentProcessor implements ResidenceProcessor<MultiplexHouseRent> {

    @Value("${data.multiplex-house-hold-rent.endpoint}")
    private String multiHouseHoldRentEndpoint;

    private final MultiplexHouseRentRepository multiplexHouseRentRepository;

    @Override
    public MultiplexHouseRent parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToMHRent(residenceInfo, point);
    }

    @Override
    public Optional<MultiplexHouseRent> findByPoint(Point point) {
        return multiplexHouseRentRepository.findMultiplexHouseRentByPoint(point);
    }

    @Override
    public void save(MultiplexHouseRent entity) {
        multiplexHouseRentRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return multiHouseHoldRentEndpoint;
    }
}
