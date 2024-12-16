package com.dgb.residence.service;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.dto.req.ResidenceSaveReq;
import com.dgb.residence.dto.req.ResidenceTradeFilterSearchCond;
import com.dgb.residence.entity.*;
import com.dgb.residence.processor.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.List;

@Transactional
@SpringBootTest
class ResidenceSearchServiceTest {

    @Autowired
    private ResidenceSaveService residenceSaveService;
    @Autowired
    private ResidenceSearchService residenceSearchService;

    @Autowired
    private ApartmentRentProcessor apartmentRentProcessor;
    @Autowired
    private ApartmentTradeProcessor apartmentTradeProcessor;
    @Autowired
    private DetachedHouseRentProcessor detachedHouseRentProcessor;
    @Autowired
    private DetachedHouseTradeProcessor detachedHouseTradeProcessor;
    @Autowired
    private MultiplexHouseRentProcessor multiplexHouseRentProcessor;
    @Autowired
    private MultiplexHouseTradeProcessor multiplexHouseTradeProcessor;
    @Autowired
    private OfficetelRentProcessor officetelRentProcessor;
    @Autowired
    private OfficetelTradeProcessor officetelTradeProcessor;


    static final String lawdCd = "27260";
    static final YearMonth startDealYMD = YearMonth.of(2024, 10);
    static final YearMonth endDealYMD = YearMonth.of(2024, 11);
    static int radius = 5000;
    static int assets = 5000;
    static int loan = 10000;
    static int minMonthlyRent = 0;
    static int maxMonthlyRent = 10000;

    private void printResidenceRentInfo(List<? extends Residence> filteredResidences) {
        System.out.println("=======================================\n");
        System.out.println("[ 조건에 맞는 주택 전월세 목록 조회 ]");
        System.out.println(String.format("  > 검색 조건: 반경 %.2fkm, 자산 %d만 + 보증금 %d만, 월세 %s ~ %s만", radius / 1000.0, assets, loan, minMonthlyRent, maxMonthlyRent));

        System.out.println("총 " + filteredResidences.size() + "개의 매물이 조회되었습니다.");
        int index = 1;
        for (Residence residence : filteredResidences) {
            System.out.println(String.format("%d. %s", index++, residence));
        }
    }
    private void printResidenceTradeInfo(List<? extends Residence> filteredResidences) {
        System.out.println("=======================================\n");
        System.out.println("[ 조건에 맞는 주택 매매 목록 조회 ]");
        System.out.println(String.format("  > 검색 조건: 반경 %.2fkm, 자산 %d만 + 보증금 %d만 ", radius / 1000.0, assets, loan));

        System.out.println("총 " + filteredResidences.size() + "개의 매물이 조회되었습니다.");
        int index = 1;
        for (Residence residence : filteredResidences) {
            System.out.println(String.format("%d. %s", index++, residence));
        }
    }

    @Test
    void 아파트_전월세_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, apartmentRentProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceRentFilterSearchCond cond = new ResidenceRentFilterSearchCond(center, radius, assets, loan, minMonthlyRent, maxMonthlyRent);

        //when
        List<ApartmentRent> apartmentRents = residenceSearchService.searchFilteredApartmentRent(cond);

        //then
        printResidenceRentInfo(apartmentRents);
    }

    @Test
    void 아파트_매매_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, apartmentTradeProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceTradeFilterSearchCond cond = new ResidenceTradeFilterSearchCond(center, radius, assets, loan);

        //when
        List<ApartmentTrade> apartmentTrades = residenceSearchService.searchFilteredApartmentTrade(cond);

        //then
        printResidenceTradeInfo(apartmentTrades);
    }

    @Test
    void 단독주택_전월세_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, detachedHouseRentProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceRentFilterSearchCond cond = new ResidenceRentFilterSearchCond(center, radius, assets, loan, minMonthlyRent, maxMonthlyRent);

        //when
        List<DetachedHouseRent> detachedHouseRents = residenceSearchService.searchFilteredDetachedHouseRent(cond);

        //then
        printResidenceRentInfo(detachedHouseRents);
    }

    @Test
    void 단독주택_매매_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, detachedHouseTradeProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceTradeFilterSearchCond cond = new ResidenceTradeFilterSearchCond(center, radius, assets, loan);

        //when
        List<DetachedHouseTrade> detachedHouseTrades = residenceSearchService.searchFilteredDetachedHouseTrade(cond);

        //then
        printResidenceTradeInfo(detachedHouseTrades);
    }

    @Test
    void 다가구주택_전월세_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, multiplexHouseRentProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceRentFilterSearchCond cond = new ResidenceRentFilterSearchCond(center, radius, assets, loan, minMonthlyRent, maxMonthlyRent);

        //when
        List<MultiplexHouseRent> multiplexHouseRents = residenceSearchService.searchFilteredMultiplexHouseRent(cond);

        //then
        printResidenceRentInfo(multiplexHouseRents);
    }

    @Test
    void 다가구주택_매매_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, multiplexHouseTradeProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceTradeFilterSearchCond cond = new ResidenceTradeFilterSearchCond(center, radius, assets, loan);

        //when
        List<MultiplexHouseTrade> multiplexHouseTrades = residenceSearchService.searchFilteredMultiplexHouseTrade(cond);

        //then
        printResidenceTradeInfo(multiplexHouseTrades);
    }

    @Test
    void 오피스텔_전월세_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, officetelRentProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceRentFilterSearchCond cond = new ResidenceRentFilterSearchCond(center, radius, assets, loan, minMonthlyRent, maxMonthlyRent);

        //when
        List<OfficetelRent> officetelRents = residenceSearchService.searchFilteredOfficetelRent(cond);

        //then
        printResidenceRentInfo(officetelRents);
    }

    @Test
    void 오피스텔_매매_필터_검색() throws ParseException {
        //given
        ResidenceSaveReq req = new ResidenceSaveReq(lawdCd, startDealYMD, endDealYMD);
        residenceSaveService.saveResidences(req, officetelTradeProcessor);

        Point center = new GeometryFactory().createPoint(new Coordinate(128.6460, 35.8580));
        center.setSRID(4326);

        ResidenceTradeFilterSearchCond cond = new ResidenceTradeFilterSearchCond(center, radius, assets, loan);

        //when
        List<OfficetelTrade> officetelTrades = residenceSearchService.searchFilteredOfficetelTrade(cond);

        //then
        printResidenceTradeInfo(officetelTrades);
    }

}
