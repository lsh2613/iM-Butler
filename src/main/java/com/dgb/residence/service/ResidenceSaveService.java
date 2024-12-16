package com.dgb.residence.service;

import com.dgb.data.util.DataApiUtil;
import com.dgb.navermaps.service.NaverMapsService;
import com.dgb.residence.dto.req.ResidenceSaveReq;
import com.dgb.residence.entity.Residence;
import com.dgb.residence.entity.ResidenceAddress;
import com.dgb.residence.processor.ResidenceProcessor;
import com.dgb.residence.util.ResidenceParsingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.time.YearMonth;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResidenceSaveService {
    @Value("${data.decoding-key}")
    private String decodingKey;

    private final NaverMapsService naverMapsService;

    public <T extends Residence> void saveResidences(ResidenceSaveReq req, ResidenceProcessor<T> processor) throws ParseException {
        // 공공데이터 포털의 주거지 실거래가 API에서 startDealYM ~ endDealYM 까지의 데이터 조회
        for (YearMonth current = req.startDealYM(); !current.isAfter(req.endDealYM()); current = current.plusMonths(1)) {
            // 공공데이터 포털 api 조회
            ResponseEntity<String> res = getResponseByDataApi(processor.getEndpoint(), req.lawdCd(), current);
            // response body 파싱 -> 거주지 목록
            JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

            for (int i = 0; i < residenceInfos.length(); i++) {
                // 거주지 조회
                JSONObject residenceInfo = residenceInfos.getJSONObject(i);

                // 주소 파싱
                ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
                // 주소 -> 위치 좌표(위도, 경도) 변환
                Point point = naverMapsService.convertToPoint(residenceAddress);

                T newResidence = processor.parse(residenceInfo, point);
                Optional<T> oldResidenceOpt = processor.findByPoint(point);

                // 같은 좌표라면 수정
                if (oldResidenceOpt.isPresent() && oldResidenceOpt.get().getPoint().equals(point)) {
                    oldResidenceOpt.get().update(newResidence);
                } else { // 중복되지 않은 다른 좌표라면 새로 저장
                    processor.save(newResidence);
                }
            }
        }
    }

    private ResponseEntity<String> getResponseByDataApi(String endpoint, String lawdCd, YearMonth dealYM) {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYM, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(endpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);
        return res;
    }
}
