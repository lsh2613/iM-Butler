package com.dgb.openapi;

import com.dgb.residence.entity.ResidenceAddress;
import com.dgb.data.DataApiUtil;
import com.dgb.residence.util.ResidenceParsingUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
public class DataApiTest {
    @Value("${data.decoding-key}")
    private String decodingKey;

    @Value("${data.apartment-rent.endpoint}")
    private String aptRentEndpoint;
    @Value("${data.apartment-trade.endpoint}")
    private String aptTradeEndpoint;

    @Value("${data.detached-house-rent.endpoint}")
    private String detachedHouseRentEndpoint;
    @Value("${data.detached-house-trade.endpoint}")
    private String detachedHouseTradeEndpoint;

    @Value("${data.multiplex-house-hold-rent.endpoint}")
    private String multiHouseHoldRentEndpoint;
    @Value("${data.multiplex-house-hold-trade.endpoint}")
    private String multiHouseHoldTradeEndpoint;

    @Value("${data.officetel-rent.endpoint}")
    private String officetelRentEndpoint;
    @Value("${data.officetel-trade.endpoint}")
    private String officetelTradeEndpoint;

    static final String lawdCd = "27260";
    static final String dealYMD = "202411";

    @Test
    public void 아파트_전월세_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(aptRentEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);

            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 아파트_매매_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(aptTradeEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 단독다가구_전월세_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(detachedHouseRentEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddressWithoutJibun(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 단독다가구_매매_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(detachedHouseTradeEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 연립다세대_전월세_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(multiHouseHoldRentEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 연립다세대_매매_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(multiHouseHoldTradeEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 오피스텔_전월세_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(officetelRentEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

    @Test
    public void 오피스텔_매매_실거래가_조회() throws JSONException {
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> body = DataApiUtil.createBody(lawdCd, dealYMD, decodingKey);

        URI uri = DataApiUtil.creatUriWithQuery(officetelTradeEndpoint, body);

        ResponseEntity<String> res = rt.getForEntity(uri, String.class);

        JSONArray residenceInfos = ResidenceParsingUtil.extractAddresses(res.getBody());

        for (int i = 0; i < residenceInfos.length(); i++) {
            JSONObject residenceInfo = residenceInfos.getJSONObject(i);
            ResidenceAddress residenceAddress = ResidenceParsingUtil.createResidenceAddress(residenceInfo);
            System.out.println("residenceAddress.mapToString() = " + residenceAddress.mapToString());
        }
    }

}
