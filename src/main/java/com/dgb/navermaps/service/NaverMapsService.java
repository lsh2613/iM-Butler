package com.dgb.navermaps.service;

import com.dgb.navermaps.util.NaverMapsApiUtil;
import com.dgb.residence.entity.ResidenceAddress;
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

        JSONObject addresses = new JSONObject(response.getBody())
                .getJSONArray("addresses")
                .getJSONObject(0);

        double x = addresses.getDouble("x");
        double y = addresses.getDouble("y");

        return new GeometryFactory().createPoint(new Coordinate(y, x));
    }
}
