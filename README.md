# Site-Maintenance App

## Brief Description

An application for a Site Maintenance Manager. The Site Maintenance Manager should be able to add a list of the maintenance engineers, <br/>
and for each engineer, add the sites that the engineer maintains.

The main functionalities are:<br>
* Create Site
* Update Site
* Delete Site
* View Site Details
* Assign site engineer
* Dissociate site engineer
* Dissociate all site association occurrences when a site is deleted
* Delete all sites
* Create Engineer
* Update Engineer
* Delete Engineer
* View Engineer Details
* Assign an Engineer to a Site
* Unlink/ dissociate an engineer from a site
* Dissociate all engineer association occurrences when an engineer is deleted
* Delete all engineers

## Authors

* [Kelvin Makamu](https://github.com/kelvinMakamu)

## Set Up Instructions

To start using this project:

* `git clone https://github.com/kelvinMakamu/site-maintenance.git`
* `cd site-maintenance`
* `gradle build`

To set up PostgreSql:
````
psql;
CREATE DATABASE site_maintenance;
\c site_maintenance;
CREATE TABLE engineers(id serial PRIMARY KEY, firstName VARCHAR, lastName VARCHAR, createdAt timestamp);
CREATE TABLE sites(id serial PRIMARY KEY, name VARCHAR,town VARCHAR, createdAt timestamp);
CREATE TABLE engineer_site(id serial PRIMARY KEY, engineerId int, siteId int,createdAt timestamp);
CREATE DATABASE site_maintenance_test WITH TEMPLATE site_maintenance;
````

## Technologies Used

* Java
* JUnit
* Gradle
* PostgreSQL
* GIT
* Maven

## Contact Information

* [Kelvin Makamu](mailto:profmakamu@gmail.com?subject=[GitHub]%20Private%20and%20Confidential)