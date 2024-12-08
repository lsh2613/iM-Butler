package com.dgb.residence.processor;

import com.dgb.residence.entity.DetachedHouseTrade;
import com.dgb.residence.repository.DetachedHouseTradeRepository;
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
public class DetachedHouseTradeProcessor implements ResidenceProcessor<DetachedHouseTrade> {

    @Value("${data.detached-house-trade.endpoint}")
    private String detachedHouseTradeEndpoint;

    private final DetachedHouseTradeRepository detachedHouseTradeRepository;

    @Override
    public DetachedHouseTrade parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToDHTrade(residenceInfo, point);
    }

    @Override
    public Optional<DetachedHouseTrade> findByPoint(Point point) {
        return detachedHouseTradeRepository.findDetachedHouseTradeByPoint(point);
    }

    @Override
    public void save(DetachedHouseTrade entity) {
        detachedHouseTradeRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return detachedHouseTradeEndpoint;
    }
}
