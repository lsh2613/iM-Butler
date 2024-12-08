package com.dgb.residence.processor;

import com.dgb.residence.entity.OfficetelRent;
import com.dgb.residence.repository.OfficetelRentRepository;
import com.dgb.residence.util.ResidenceParsingUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OfficetelRentProcessor implements ResidenceProcessor<OfficetelRent> {

    @Value("${data.officetel-rent.endpoint}")
    private String officetelRentEndpoint;

    private final OfficetelRentRepository officetelRentRepository;

    @Override
    public OfficetelRent parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToOffiRent(residenceInfo, point);
    }

    @Override
    public Optional<OfficetelRent> findByPoint(Point point) {
        return officetelRentRepository.findOfficetelRentByPoint(point);
    }

    @Override
    public void save(OfficetelRent entity) {
        officetelRentRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return officetelRentEndpoint;
    }
}
