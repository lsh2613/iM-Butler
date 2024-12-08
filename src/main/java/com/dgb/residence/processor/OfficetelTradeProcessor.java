package com.dgb.residence.processor;

import com.dgb.residence.entity.OfficetelTrade;
import com.dgb.residence.repository.OfficetelTradeRepository;
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
public class OfficetelTradeProcessor implements ResidenceProcessor<OfficetelTrade> {

    @Value("${data.officetel-trade.endpoint}")
    private String officetelTradeEndpoint;

    private final OfficetelTradeRepository officetelTradeRepository;

    @Override
    public OfficetelTrade parse(JSONObject residenceInfo, Point point) throws ParseException {
        return ResidenceParsingUtil.parsingToOffiTrade(residenceInfo, point);
    }

    @Override
    public Optional<OfficetelTrade> findByPoint(Point point) {
        return officetelTradeRepository.findOfficetelTradeByPoint(point);
    }

    @Override
    public void save(OfficetelTrade entity) {
        officetelTradeRepository.save(entity);
    }

    @Override
    public String getEndpoint() {
        return officetelTradeEndpoint;
    }
}
