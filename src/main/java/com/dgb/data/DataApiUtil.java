package com.dgb.data;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class DataApiUtil {

    static public MultiValueMap<String, String> createBody(String lawdCd, String dealYMD, String decodingKey) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("LAWD_CD", lawdCd);
        body.add("DEAL_YMD", dealYMD);
        body.add("serviceKey", decodingKey);
        return body;
    }

    static public URI creatUriWithQuery(String aptRentEndpoint, MultiValueMap<String, String> body) {
        URI uri = UriComponentsBuilder.fromHttpUrl(aptRentEndpoint)
                .queryParams(body)
                .build()
                .toUri();
        return uri;
    }
}
