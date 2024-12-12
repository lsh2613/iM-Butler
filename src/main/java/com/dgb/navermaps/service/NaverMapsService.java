package com.dgb.navermaps.service;

import com.dgb.navermaps.util.NaverMapsApiUtil;
import com.dgb.residence.entity.ResidenceAddress;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class NaverMapsService {

    @Value("${naver-maps.endpoint}")
    private String endpoint;
    @Value("${naver-maps.client-id}")
    private String clientId;
    @Value("${naver-maps.client-secret}")
    private String clientSecret;

    public Point convertToPoint(ResidenceAddress residenceAddress) {
        RestTemplate rt = new RestTemplate();

        String url = String.format("%s?query=%s", endpoint, residenceAddress.mapToString());

        HttpEntity<String> httpEntity = NaverMapsApiUtil.createHttpReq(clientId, clientSecret);

        ResponseEntity<String> response = rt.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        ;

        JSONArray addresses = new JSONObject(response.getBody())
                .getJSONArray("addresses");

        if (addresses.length() == 0) {
            log.debug("{}에 대한 주소가 좌표 변환에 실패하여 (0, 0)으로 저장되었습니다", residenceAddress.mapToString());
            return new GeometryFactory().createPoint(new Coordinate(0, 0));
        }

        JSONObject address = addresses.getJSONObject(0);

        double x = address.getDouble("x"); // Longitude 경도
        double y = address.getDouble("y"); // Latitude 위도

        Point point = new GeometryFactory().createPoint(new Coordinate(x, y));
        point.setSRID(4326);

        return point;
    }
}
