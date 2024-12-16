package com.dgb.data.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DataApiUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

    static public MultiValueMap<String, String> createBody(String lawdCd, YearMonth dealYM, String decodingKey) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("LAWD_CD", lawdCd);
        body.add("DEAL_YMD", formatter.format(dealYM));
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
