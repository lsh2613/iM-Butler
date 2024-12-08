package com.dgb.residence.processor;

import com.dgb.residence.entity.MultiplexHouseTrade;
import com.dgb.residence.repository.MultiplexHouseTradeRepository;
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
public class MultiplexHouseTradeProcessor implements ResidenceProcessor<MultiplexHouseTrade> {


    @Value("${data.multiplex-house-hold-trade.endpoint}")
    private String multiHouseHoldTradeEndpoint;

    private final MultiplexHouseTradeRepository multiplexHouseTradeRepository;

    @Override
    public MultiplexHouseTrade parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToMHTrade(residenceInfo, point);
    }

    @Override
    public Optional<MultiplexHouseTrade> findByPoint(Point point) {
        return multiplexHouseTradeRepository.findMultiplexHouseTradeByPoint(point);
    }

    @Override
    public void save(MultiplexHouseTrade entity) {
        multiplexHouseTradeRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return multiHouseHoldTradeEndpoint;
    }
}
