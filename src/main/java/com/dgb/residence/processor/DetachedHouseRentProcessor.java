package com.dgb.residence.processor;

import com.dgb.residence.entity.DetachedHouseRent;
import com.dgb.residence.repository.DetachedHouseRentRepository;
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
public class DetachedHouseRentProcessor implements ResidenceProcessor<DetachedHouseRent> {

    @Value("${data.detached-house-rent.endpoint}")
    private String detachedHouseRentEndpoint;

    private final DetachedHouseRentRepository detachedHouseRentRepository;

    @Override
    public DetachedHouseRent parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToDHRent(residenceInfo, point);
    }

    @Override
    public Optional<DetachedHouseRent> findByPoint(Point point) {
        return detachedHouseRentRepository.findDetachedHouseRentByPoint(point);
    }

    @Override
    public void save(DetachedHouseRent entity) {
        detachedHouseRentRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return detachedHouseRentEndpoint;
    }
}
