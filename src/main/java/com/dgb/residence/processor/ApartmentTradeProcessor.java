package com.dgb.residence.processor;

import com.dgb.residence.entity.ApartmentTrade;
import com.dgb.residence.repository.ApartmentTradeRepository;
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
public class ApartmentTradeProcessor implements ResidenceProcessor<ApartmentTrade> {

    @Value("${data.apartment-trade.endpoint}")
    private String aptTradeEndpoint;

    private final ApartmentTradeRepository apartmentTradeRepository;

    @Override
    public ApartmentTrade parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToAptTrade(residenceInfo, point);
    }

    @Override
    public Optional<ApartmentTrade> findByPoint(Point point) {
        return apartmentTradeRepository.findApartmentTradeByPoint(point);
    }

    @Override
    public void save(ApartmentTrade entity) {
        apartmentTradeRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return aptTradeEndpoint;
    }
}
