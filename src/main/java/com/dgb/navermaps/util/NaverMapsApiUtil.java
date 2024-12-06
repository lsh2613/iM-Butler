package com.dgb.navermaps.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class NaverMapsApiUtil {

    static public HttpEntity<String> createHttpReq(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-ncp-apigw-api-key-id", clientId);
        headers.add("x-ncp-apigw-api-key", clientSecret);
        headers.add("Accept", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return httpEntity;
    }
}
