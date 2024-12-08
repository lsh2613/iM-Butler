package com.dgb.residence.controller;

import com.dgb.residence.dto.req.ResidenceSaveReq;
import com.dgb.residence.processor.*;
import com.dgb.residence.service.ResidenceSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

@Controller
@RequiredArgsConstructor
public class ResidenceController {

    private final ResidenceSaveService residenceSaveService;
    private final ApartmentRentProcessor apartmentRentProcessor;
    private final ApartmentTradeProcessor apartmentTradeProcessor;
    private final DetachedHouseRentProcessor detachedHouseRentProcessor;
    private final DetachedHouseTradeProcessor detachedHouseTradeProcessor;
    private final MultiplexHouseRentProcessor multiplexHouseRentProcessor;
    private final MultiplexHouseTradeProcessor multiplexHouseTradeProcessor;
    private final OfficetelRentProcessor officetelRentProcessor;
    private final OfficetelTradeProcessor officetelTradeProcessor;

    @PostMapping("/apartment-rents")
    public ResponseEntity saveApartmentRents(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, apartmentRentProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/apartment-trades")
    public ResponseEntity saveApartmentTrades(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, apartmentTradeProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/detached-house-rents")
    public ResponseEntity saveDetachedHouseRents(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, detachedHouseRentProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/detached-house-trades")
    public ResponseEntity saveDetachedHouseTrades(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, detachedHouseTradeProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/multiplex-house-rents")
    public ResponseEntity saveMultiplexHouseRents(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, multiplexHouseRentProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/multiplex-house-trades")
    public ResponseEntity saveMultiplexHouseTrades(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, multiplexHouseTradeProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/officetel-rents")
    public ResponseEntity saveOfficetelRents(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, officetelRentProcessor);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/officetel-trades")
    public ResponseEntity saveOfficeteltrades(@RequestBody ResidenceSaveReq residenceSaveReq) throws ParseException {
        residenceSaveService.saveResidences(residenceSaveReq, officetelTradeProcessor);
        return ResponseEntity.ok("ok");
    }

}
