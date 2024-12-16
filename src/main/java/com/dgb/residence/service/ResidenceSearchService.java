package com.dgb.residence.service;

import com.dgb.residence.dto.req.ResidenceRentFilterSearchCond;
import com.dgb.residence.dto.req.ResidenceTradeFilterSearchCond;
import com.dgb.residence.entity.*;
import com.dgb.residence.repository.custom.ApartmentRentRepositoryCustom;
import com.dgb.residence.repository.custom.ApartmentTradeRepositoryCustom;
import com.dgb.residence.repository.facade.ResidenceSearchFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidenceSearchService {

    private final ResidenceSearchFacade residenceSearchFacade;

    public List<ApartmentRent> searchFilteredApartmentRent(ResidenceRentFilterSearchCond cond) {
        List<ApartmentRent> apartmentRents = residenceSearchFacade.searchFilteredApartmentRent(cond);

        return apartmentRents;
    }

    public List<ApartmentTrade> searchFilteredApartmentTrade(ResidenceTradeFilterSearchCond cond) {
        List<ApartmentTrade> apartmentTrades = residenceSearchFacade.searchFilteredApartmentTrade(cond);

        return apartmentTrades;
    }

    public List<DetachedHouseRent> searchFilteredDetachedHouseRent(ResidenceRentFilterSearchCond cond) {
        List<DetachedHouseRent> detachedHouseRents = residenceSearchFacade.searchFilteredDetachedHouseRent(cond);
        return detachedHouseRents;
    }

    public List<DetachedHouseTrade> searchFilteredDetachedHouseTrade(ResidenceTradeFilterSearchCond cond) {
        List<DetachedHouseTrade> detachedHouseTrades = residenceSearchFacade.searchFilteredDetachedHouseTrade(cond);
        return detachedHouseTrades;
    }

    public List<MultiplexHouseRent> searchFilteredMultiplexHouseRent(ResidenceRentFilterSearchCond cond) {
        List<MultiplexHouseRent> multiplexHouseRents = residenceSearchFacade.searchFilteredMultiplexHouseRent(cond);
        return multiplexHouseRents;
    }

    public List<MultiplexHouseTrade> searchFilteredMultiplexHouseTrade(ResidenceTradeFilterSearchCond cond) {
        List<MultiplexHouseTrade> multiplexHouseTrades = residenceSearchFacade.searchFilteredMultiplexHouseTrade(cond);
        return multiplexHouseTrades;
    }

    public List<OfficetelRent> searchFilteredOfficetelRent(ResidenceRentFilterSearchCond cond) {
        List<OfficetelRent> officetelRents = residenceSearchFacade.searchFilteredOfficetelRent(cond);
        return officetelRents;
    }

    public List<OfficetelTrade> searchFilteredOfficetelTrade(ResidenceTradeFilterSearchCond cond) {
        List<OfficetelTrade> officetelTrades = residenceSearchFacade.searchFilteredOfficetelTrade(cond);
        return officetelTrades;
    }
}
