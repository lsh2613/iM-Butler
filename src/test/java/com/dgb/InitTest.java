package com.dgb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootTest
public class InitTest {

    @Value("${data-set.encoding-key}")
    private String encodingKey;
    @Value("${data-set.decoding-key}")
    private String decodingKey;

    private final String url = "http://apis.data.go.kr/1613000/RTMSDataSvcAptTrade/getRTMSDataSvcAptTrade";

    @Test
    public void init() {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("LAWD_CD", "41135");
        body.add("DEAL_YMD", "202406");
//        body.add("serviceKey", encodingKey);
        body.add("serviceKey", decodingKey);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(body, headers);
//        String res = rt.getForObject(url, String.class, req);

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(body)
                .build().toUri();

        String res = rt.getForObject(uri, String.class);

        System.out.println(res);
    }
}
