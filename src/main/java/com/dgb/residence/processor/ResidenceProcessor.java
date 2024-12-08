package com.dgb.residence.processor;

import com.dgb.residence.entity.Residence;
import org.json.JSONObject;
import org.locationtech.jts.geom.Point;

import java.text.ParseException;
import java.util.Optional;

public interface ResidenceProcessor<T extends Residence> {
    T parse(JSONObject residenceInfo, Point point) throws ParseException;

    Optional<T> findByPoint(Point point);

    void save(T entity);

    String getEndpoint();
}
