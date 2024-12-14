package com.dgb.serario;

import com.dgb.residence.dto.req.ResidenceTradeFilterSearchCond;
import com.dgb.residence.entity.ApartmentTrade;
import com.dgb.residence.repository.ApartmentTradeRepository;
import com.dgb.residence.service.ResidenceSearchService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.YearMonth;
import java.util.List;

@Transactional
@SpringBootTest
public class ScenarioTest {

    @Autowired
    private ApartmentTradeRepository apartmentTradeRepository;
    @Autowired
    private ResidenceSearchService residenceSearchService;

    private String[][] residences = {
            {"128.6023599", "35.9183469", "2021", "1", "15000", "대구동서변주공그린빌아파트"},
            {"128.6275805", "35.8916006", "2022", "2", "30000", "대구복현푸르지오아파트"}, // 5km 이내
            {"128.5128779", "35.8607334", "2023", "3", "40000", "서대구역서한이다음더퍼스트아파트"},
            {"128.5187583", "35.8594635", "2024", "4", "17000", "성서보성타운2차아파트"},
            {"128.5334213", "35.8444073", "2021", "5", "33000", "감삼우방드림시티아파트"},
            {"128.5408959", "35.8060367", "2022", "6", "20000", "미리샘마을주공2단지아파트"},
            {"128.6070240", "35.8663445", "2023", "7", "38000", "삼덕청아람리슈빌아파트"}, // 5km 이내
            {"128.6309531", "35.8704625", "2024", "8", "74000", "더샵디어엘로"}, // 5km 이내
            {"128.6451170", "35.8156041", "2021", "9", "48000", "수성하늘채 르레브"},
            {"128.6678152", "35.8812572", "2022", "10", "18000", "방촌역태왕아너스아파트"},
            {"128.5949726", "35.8339678", "2023", "11", "25000", "대덕맨션2차아파트"}, // 5km 이내
            {"128.6376382", "35.8424413", "2024", "12", "88000", "황금동캐슬골드파크4단지아파트"}, // 5km 이내
    };

    private void saveApartmentTrade() {
        for (String[] residence : residences) {
            Point point = new GeometryFactory().createPoint(new Coordinate(Double.valueOf(residence[0]), Double.valueOf(residence[1])));
            point.setSRID(4326);
            ApartmentTrade apartmentTrade = new ApartmentTrade(YearMonth.of(Integer.valueOf(residence[2]), Integer.valueOf(residence[3])), point, Integer.valueOf(residence[4]), residence[5]);
            apartmentTradeRepository.save(apartmentTrade);
        }
    }

    private void printInfo(List<ApartmentTrade> allTrades, List<ApartmentTrade> filteredTrades, int radius, int assets, int loan) {
        System.out.println("=======================================\n");
        System.out.println("[ 전체 아파트 매매 목록 조회 ]");
        System.out.println("총 " + allTrades.size() + "개의 매물이 저장되어 있습니다.");
        int index = 1;
        for (ApartmentTrade trade : allTrades) {
            System.out.println(String.format("%d. %s", index++, trade));
        }

        System.out.println("\n=======================================\n");
        System.out.println("[ 조건에 맞는 아파트 매매 목록 조회 ]");
        System.out.println(String.format("  > 검색 조건: 반경 %.2fkm, 자산 %d만 + 보증금 %d만 ", radius / 1000.0, assets, loan));

        System.out.println("총 " + filteredTrades.size() + "개의 매물이 조회되었습니다.");
        index = 1;
        for (ApartmentTrade trade : filteredTrades) {
            System.out.println(String.format("%d. %s", index++, trade));
        }
    }
    /**
     * 대구 은행역을 기준으로 반경 5km 이내의 거주지 매물 중
     * 자산 2000만 + 보증금 2000만으로 구매할 수 있는 아파트 매매 매물 조회
     */
    @Test
    void 대구_아파트_매매_조회() {
        //given
        saveApartmentTrade();
        List<ApartmentTrade> allApartmentTrades = apartmentTradeRepository.findAll();

        // 대구은행역을 기준으로
        Point center = new GeometryFactory().createPoint(new Coordinate(128.614151, 35.859769));
        center.setSRID(4326);

        int radius = 5000;
        int assets = 20000;
        int loan = 20000;
        ResidenceTradeFilterSearchCond cond = new ResidenceTradeFilterSearchCond(center, radius, assets, loan);

        //when
        List<ApartmentTrade> searchedApartmentTrades = residenceSearchService.searchFilteredApartmentTrade(cond);

        //then
        printInfo(allApartmentTrades, searchedApartmentTrades, radius, assets, loan);
    }
}
