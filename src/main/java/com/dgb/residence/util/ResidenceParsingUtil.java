package com.dgb.residence.util;

import com.dgb.residence.entity.*;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locationtech.jts.geom.Point;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.YearMonth;

public class ResidenceParsingUtil {

    static DecimalFormat df = new DecimalFormat("#,###");

    public static JSONArray extractAddresses(String responseBody) {
        JSONArray addresses = null;
        try {
            JSONObject responseJson = new JSONObject(responseBody);
            addresses = responseJson
                    .getJSONObject("response")
                    .getJSONObject("body")
                    .getJSONObject("items")
                    .getJSONArray("item");
        } catch (JSONException e) {
            throw new IllegalStateException("데이터 조회에 실패했습니다", e);
        }
        return addresses;
    }

    public static ResidenceAddress createResidenceAddress(JSONObject residenceInfo) {
        String umdNm = residenceInfo.get("umdNm").toString();
        String jibun = residenceInfo.get("jibun").toString();
        return new ResidenceAddress(umdNm, jibun);
    }

    public static ResidenceAddress createResidenceAddressWithoutJibun(JSONObject residenceInfo) {
        String umdNm = residenceInfo.get("umdNm").toString();
        return new ResidenceAddress(umdNm, Strings.EMPTY);
    }

    public static ApartmentRent parsingToAptRent(JSONObject residenceInfo, Point point) throws ParseException {
        return ApartmentRent.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .deposit(getDeposit(residenceInfo))
                .monthlyRent(getMonthlyRent(residenceInfo))
                .aptNm(getAptNm(residenceInfo))
                .build();
    }

    public static ApartmentTrade parsingToAptTrade(JSONObject residenceInfo, Point point) throws ParseException {
        return ApartmentTrade.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .dealAmount(getDealAmount(residenceInfo))
                .aptNm(getAptNm(residenceInfo))
                .build();
    }

    public static DetachedHouseRent parsingToDHRent(JSONObject residenceInfo, Point point) throws ParseException {
        return DetachedHouseRent.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .deposit(getDeposit(residenceInfo))
                .monthlyRent(getMonthlyRent(residenceInfo))
                .build();
    }

    public static DetachedHouseTrade parsingToDHTrade(JSONObject residenceInfo, Point point) throws ParseException {
        return DetachedHouseTrade.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .dealAmount(getDealAmount(residenceInfo))
                .build();
    }

    public static MultiplexHouseRent parsingToMHRent(JSONObject residenceInfo, Point point) throws ParseException {
        return MultiplexHouseRent.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .deposit(getDeposit(residenceInfo))
                .monthlyRent(getMonthlyRent(residenceInfo))
                .mhouseNm(getMhouseNm(residenceInfo))
                .build();
    }

    public static MultiplexHouseTrade parsingToMHTrade(JSONObject residenceInfo, Point point) throws ParseException {
        return MultiplexHouseTrade.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .dealAmount(getDealAmount(residenceInfo))
                .mhouseNm(getMhouseNm(residenceInfo))
                .build();
    }

    public static OfficetelRent parsingToOffiRent(JSONObject residenceInfo, Point point) throws ParseException {
        return OfficetelRent.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .deposit(getDeposit(residenceInfo))
                .monthlyRent(getMonthlyRent(residenceInfo))
                .offiNm(getOffiNm(residenceInfo))
                .build();
    }

    public static OfficetelTrade parsingToOffiTrade(JSONObject residenceInfo, Point point) throws ParseException {
        return OfficetelTrade.builder()
                .dealDate(getDealDate(residenceInfo))
                .point(point)
                .dealAmount(getDealAmount(residenceInfo))
                .offiNm(getOffiNm(residenceInfo))
                .build();
    }

    private static String getOffiNm(JSONObject residenceInfo) {
        return residenceInfo.getString("offiNm");
    }

    private static String getMhouseNm(JSONObject residenceInfo) {
        return residenceInfo.getString("mhouseNm");
    }

    private static Integer getDealAmount(JSONObject residenceInfo) throws ParseException {
        return df.parse(residenceInfo.get("dealAmount").toString()).intValue();
    }

    private static String getAptNm(JSONObject residenceInfo) {
        return residenceInfo.getString("aptNm");
    }

    private static Integer getDeposit(JSONObject residenceInfo) throws ParseException {
        return df.parse(residenceInfo.get("deposit").toString()).intValue();
    }

    private static Integer getMonthlyRent(JSONObject residenceInfo) throws ParseException {
        return df.parse(residenceInfo.get("monthlyRent").toString()).intValue();
    }


    private static YearMonth getDealDate(JSONObject residenceInfo) {
        return YearMonth.of(residenceInfo.getInt("dealYear"), residenceInfo.getInt("dealMonth"));
    }
}
