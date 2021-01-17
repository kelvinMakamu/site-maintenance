CREATE DATABASE site_maintenance;
\c site_maintenance;
CREATE TABLE engineers(id serial PRIMARY KEY, firstName VARCHAR, lastName VARCHAR, createdAt timestamp);
CREATE TABLE sites(id serial PRIMARY KEY, name VARCHAR,createdAt timestamp);
CREATE TABLE engineer_site(id serial PRIMARY KEY, engineerId int, siteId int,createdAt timestamp);
CREATE DATABASE site_maintenance_test WITH TEMPLATE site_maintenance;