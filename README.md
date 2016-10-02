# _Wildlife Tracker_

#### _Epicodus: Java Week 4: Advanced Topics In Java_

#### By _**Chance Neff**_

## Description

This application is designed to allow park rangers to track wildlife sightings in the area.

## Specs

##### 1. Program creates animal entry

* _Example Input: name, health, age, type_
* _Example Output: name, health, age, type_

##### 2. Program creates animal sighting specific to animal entry

* _Example Input: location, ranger name, animal Id_
* _Example Output: location, ranger name, animal Id_

## Setup/Installation Requirements

* Clone this repository to your desktop
* In terminal, navigate to /**_wildlife-tracker_** and type: `$ gradle run`
* Navigate to localhost:4567

## Database Setup

##### __In PSQL:__

CREATE DATABASE wildlife_tracker;

CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);

CREATE TABLE sightings (id serial PRIMARY KEY, animalId int, location varchar, rangerName varchar, sightingTime timestamp);

## Known Bugs

_None_

## Support and contact details

Chance Neff: _crneff84@gmail.com_

## Technologies Used

_Java,
Spark,
Velocity_

### License

This webpage is licensed under the GPL license.

Copyright &copy; 2016 **_Chance Neff_**
