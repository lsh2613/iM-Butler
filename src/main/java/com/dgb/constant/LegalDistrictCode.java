package com.dgb.constant;

public enum LegalDistrictCode {
    // 대구
    군위군("27720"), 남구("27200"), 달서구("27290"), 달성군("27710"), 동구("27140"), 북구("27230"), 서구("27170"), 수성구("27260"), 중구("27110");

    private final String code;

    LegalDistrictCode(String code) {
        this.code = code;
    }
}
