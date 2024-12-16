package com.dgb.residence.repository.facade;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.dto.req.ResidenceTradeFilterSearchCond;
import com.dgb.residence.entity.*;
import com.dgb.residence.repository.custom.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResidenceSearchFacade {

    private final ApartmentRentRepositoryCustom apartmentRentRepositoryCustom;
    private final ApartmentTradeRepositoryCustom apartmentTradeRepositoryCustom;
    private final DetachedHouseRentRepositoryCustom detachedHouseRentRepositoryCustom;
    private final DetachedHouseTradeRepositoryCustom detachedHouseTradeRepositoryCustom;
    private final MultiplexHouseRentRepositoryCustom multiplexHouseRentRepositoryCustom;
    private final MultiplexHouseTradeRepositoryCustom multiplexHouseTradeRepositoryCustom;
    private final OfficetelRentRepositoryCustom officetelRentRepositoryCustom;
    private final OfficetelTradeRepositoryCustom officetelTradeRepositoryCustom;

    public List<ApartmentRent> searchFilteredApartmentRent(ResidenceRentFilterSearchCond cond) {
        return apartmentRentRepositoryCustom.searchFilteredApartmentRent(cond);
    }

    public List<ApartmentTrade> searchFilteredApartmentTrade(ResidenceTradeFilterSearchCond cond) {
        return apartmentTradeRepositoryCustom.searchFilteredApartmentTrade(cond);
    }

    public List<DetachedHouseRent> searchFilteredDetachedHouseRent(ResidenceRentFilterSearchCond cond) {
        return detachedHouseRentRepositoryCustom.searchFilteredDetachedHouseRent(cond);
    }

    public List<DetachedHouseTrade> searchFilteredDetachedHouseTrade(ResidenceTradeFilterSearchCond cond) {
        return detachedHouseTradeRepositoryCustom.searchFilteredDetachedHouseTrade(cond);
    }

    public List<MultiplexHouseRent> searchFilteredMultiplexHouseRent(ResidenceRentFilterSearchCond cond) {
        return multiplexHouseRentRepositoryCustom.searchFilteredMultiplexHouseRent(cond);
    }

    public List<MultiplexHouseTrade> searchFilteredMultiplexHouseTrade(ResidenceTradeFilterSearchCond cond) {
        return multiplexHouseTradeRepositoryCustom.searchFilteredMultiplexHouseTrade(cond);
    }

    public List<OfficetelRent> searchFilteredOfficetelRent(ResidenceRentFilterSearchCond cond) {
        return officetelRentRepositoryCustom.searchFilteredOfficetelRent(cond);
    }

    public List<OfficetelTrade> searchFilteredOfficetelTrade(ResidenceTradeFilterSearchCond cond) {
        return officetelTradeRepositoryCustom.searchFilteredOfficetelTrade(cond);
    }
}
