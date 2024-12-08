ALTER TABLE apartment_rent MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_apartment_rent ON apartment_rent (point);

ALTER TABLE apartment_trade MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_apartment_trade ON apartment_trade (point);

ALTER TABLE detached_house_rent MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_detached_house_rent ON detached_house_rent (point);

ALTER TABLE detached_house_trade MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_detached_house_trade ON detached_house_trade (point);

ALTER TABLE multiplex_house_rent MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_multiplex_house_rent ON multiplex_house_rent (point);

ALTER TABLE multiplex_house_trade MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_multiplex_house_trade ON multiplex_house_trade (point);

ALTER TABLE officetel_rent MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_officetel_rent ON officetel_rent (point);

ALTER TABLE officetel_trade MODIFY COLUMN point POINT NOT NULL SRID 4326;
CREATE SPATIAL INDEX spatial_idx_officetel_trade ON officetel_trade (point);
