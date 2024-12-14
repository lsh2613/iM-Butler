package com.dgb.residence.dto.req;

import java.time.YearMonth;

public record ResidenceSaveReq(
        String lawdCd,
        YearMonth startDealYM,
        YearMonth endDealYM
) {
}
