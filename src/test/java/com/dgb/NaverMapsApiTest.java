package com.dgb;

import com.dgb.navermaps.util.NaverMapsApiUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class NaverMapsApiTest {

    @Value("${naver-maps.endpoint}")
    private String endpoint;
    @Value("${naver-maps.client-id}")
    private String clientId;
    @Value("${naver-maps.client-secret}")
    private String clientSecret;

    @Test
    void 지번을_좌표로_변환() throws JSONException {
        RestTemplate rt = new RestTemplate();

        String address = "경기 성남시 분당구 수내동 36";
        String url = String.format("%s?query=%s", endpoint, address);

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

        String x = addresses.getString("x");
        String y = addresses.getString("y");

        System.out.println("addresses = " + addresses);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }


}
