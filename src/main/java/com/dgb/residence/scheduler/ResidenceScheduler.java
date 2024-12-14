package com.dgb.residence.scheduler;

import com.dgb.constant.LegalDistrictCode;
import com.dgb.residence.dto.req.ResidenceSaveReq;
import com.dgb.residence.processor.*;
import com.dgb.residence.service.ResidenceSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.YearMonth;

@RequiredArgsConstructor
@Component
public class ResidenceScheduler {

    private final ResidenceSaveService residenceSaveService;

    private final ApartmentRentProcessor apartmentRentProcessor;
    private final ApartmentTradeProcessor apartmentTradeProcessor;
    private final DetachedHouseRentProcessor detachedHouseRentProcessor;
    private final DetachedHouseTradeProcessor detachedHouseTradeProcessor;
    private final MultiplexHouseRentProcessor multiplexHouseRentProcessor;
    private final MultiplexHouseTradeProcessor multiplexHouseTradeProcessor;
    private final OfficetelRentProcessor officetelRentProcessor;
    private final OfficetelTradeProcessor officetelTradeProcessor;

    /**
     * 매월 말 자정
     */
    @Scheduled(cron = "0 0 0 L * ?", zone = "Asia/Seoul")
    void updateResidences() throws ParseException {
        YearMonth now = YearMonth.now();
        LegalDistrictCode[] legalDistrictCodes = LegalDistrictCode.values();
        for (LegalDistrictCode lawdCd : legalDistrictCodes) {
            ResidenceSaveReq req = new ResidenceSaveReq(lawdCd.getCode(), now, now);
            residenceSaveService.saveResidences(req, apartmentRentProcessor);
            residenceSaveService.saveResidences(req, apartmentTradeProcessor);
            residenceSaveService.saveResidences(req, detachedHouseRentProcessor);
            residenceSaveService.saveResidences(req, detachedHouseTradeProcessor);
            residenceSaveService.saveResidences(req, multiplexHouseRentProcessor);
            residenceSaveService.saveResidences(req, multiplexHouseTradeProcessor);
            residenceSaveService.saveResidences(req, officetelRentProcessor);
            residenceSaveService.saveResidences(req, officetelTradeProcessor);
        }
    }
}
