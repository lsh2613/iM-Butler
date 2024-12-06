package com.dgb.residence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidenceAddress {

    protected String umdNm;
    protected String jibun;

    public String mapToString() {
        return String.format("%s %s", umdNm, jibun);
    }

}
