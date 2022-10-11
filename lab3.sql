CREATE DATABASE IF NOT EXISTS MOROZ;
USE MOROZ;

-- Drop tables if exist

DROP TABLE IF EXISTS clinic_client;
DROP TABLE IF EXISTS clinic_has_client;
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS clinic_has_doctor;
DROP TABLE IF EXISTS treatment;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS `client`;
DROP TABLE IF EXISTS clinic;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS diagnosis;


-- Create Tables
-- Table: region
CREATE TABLE region (
`name` varchar(35) NOT NULL,
CONSTRAINT region_pk PRIMARY KEY (name) 
);

-- Create city
CREATE TABLE city(
`name` varchar(45) NOT NULL,
region_name varchar(35) NOT NULL,
CONSTRAINT city_pk PRIMARY KEY (`name`, region_name)
);


-- Create clinic
CREATE TABLE clinic(
id INT NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT  NULL,
clinic_phone char(12),
street_adress varchar(80),
city_name varchar(45),
CONSTRAINT clinic_pk PRIMARY KEY(id)
);

-- Create doctor
CREATE TABLE doctor(
id INT NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL, 
surname varchar(45) NOT NULL,
work_schedule varchar(105) NOT NULL,
CONSTRAINT doctor_pk PRIMARY KEY(id)
);

-- Create clinic_has_doctor
CREATE TABLE clinic_has_doctor(
clinic_id INT NOT NULL,
doctor_id INT NOT NULL,
CONSTRAINT clinic_has_doctor_pk PRIMARY KEY(clinic_id, doctor_id)
);

-- Create service
CREATE TABLE service(
id INT NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL,
duration VARCHAR(45) NULL,
is_available VARCHAR(45) NULL,
doctor_id INT NOT NULL,
CONSTRAINT service_pk PRIMARY KEY (id)
);

-- Create client
CREATE TABLE `client` (
id INT NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL,
surname varchar(45) NOT NULL,
contact_number char(12),
CONSTRAINT client_id PRIMARY KEY (id)
);

-- Create clinic_client
CREATE TABLE clinic_client (
clinic_id INT NOT NULL,
client_id INT NOT NULL,
CONSTRAINT clinic_client_pk PRIMARY KEY (clinic_id, client_id)
);

-- Create patient
CREATE TABLE patient(
  id INT NOT NULL AUTO_INCREMENT,
  breed VARCHAR(45) NULL,
  health_complains VARCHAR(100) NULL,
  client_id INT NOT NULL,
  CONSTRAINT patient_pk PRIMARY KEY (id)
);

-- Create appointment
CREATE TABLE appointment (
  id INT NOT NULL AUTO_INCREMENT,
  date_time DATETIME NOT NULL,
  service_id INT NOT NULL,
  doctor_id INT NOT NULL,
  patient_id INT NOT NULL,
  patient_client_id INT NOT NULL,
  clinic_id INT NOT NULL,
  CONSTRAINT appointment_id PRIMARY KEY (id)
);

-- Create diagnosis 
CREATE TABLE diagnosis (
  `name` VARCHAR(55) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  CONSTRAINT diagnosis_pk PRIMARY KEY (`name`)
);

-- Create treatment
CREATE TABLE treatment(
id INT NOT NULL AUTO_INCREMENT,
`description` VARCHAR(255) NOT NULL,
recomendation VARCHAR(100) NOT NULL,
diagnosis_name VARCHAR(55) NOT NULL,
patient_id INT NOT NULL,
CONSTRAINT treatment_pk PRIMARY KEY (id, patient_id)
);


-- Add foreign keys 
-- Reference: city_region (table: city)
ALTER TABLE city ADD CONSTRAINT city_region
 FOREIGN KEY city_region (region_name)
 REFERENCES region (`name`) ON DELETE CAscade;
 
 -- Reference: clinic_city (table: clinic)
ALTER TABLE clinic ADD CONSTRAINT clinic_city FOREIGN KEY clinic_city (city_name)
 REFERENCES city (`name`) ON DELETE CAscade;
 
 -- Reference: city_region (table: city)
-- ALTER TABLE city ADD CONSTRAINT city_region FOREIGN KEY city_region (region_name)
 -- REFERENCES region (`name`) ON DELETE CAscade;
 
 -- Reference: clinic_doctor (table: clinic_has_doctor)
ALTER TABLE clinic_has_doctor 
ADD CONSTRAINT clinic_has_doctor_doctor_id 
FOREIGN KEY clinic_doctor (doctor_id)
REFERENCES doctor (id) ON DELETE CAscade,
ADD CONSTRAINT clinic_has_doctor_clinic_id 
FOREIGN KEY clinic_clinic (clinic_id)
REFERENCES clinic (id) ON DELETE CAscade;

 -- Reference: service_doctor (table: service)
ALTER TABLE service ADD CONSTRAINT service_doctor FOREIGN KEY service_doctor (doctor_id)
 REFERENCES doctor (id) ON DELETE CAscade;
 
  -- Reference: clinic_client_clinic_id_client_id (table: clinic_client)
ALTER TABLE clinic_client
 ADD CONSTRAINT clinic_client_clinic_id
 FOREIGN KEY clinic_client_clinic_id (clinic_id)
 REFERENCES clinic (id) ON DELETE CAscade,
 ADD CONSTRAINT clinic_client_client_id
 FOREIGN KEY clinic_client_client_id (client_id)
 REFERENCES `client` (id) ON DELETE CASCADE;
 
 -- Reference: patient_client (table: patient)
ALTER TABLE patient ADD CONSTRAINT patient_client FOREIGN KEY patient_client (client_id)
 REFERENCES client (id) ON DELETE CAscade;
 
 -- Reference: appointment_(service, doctor, patient, patient_client, clinic) (table: appointment)
ALTER TABLE appointment
 ADD CONSTRAINT appointment_service
 FOREIGN KEY appointment_service (service_id)
 REFERENCES service (id) ON DELETE CAscade,
 ADD CONSTRAINT appointment_doctor
 FOREIGN KEY appointment_doctor (doctor_id)
 REFERENCES doctor (id) ON DELETE CAscade,
 ADD CONSTRAINT appointment_patient
 FOREIGN KEY appointment_patient (patient_id)
 REFERENCES patient (id) ON DELETE CAscade,
  ADD CONSTRAINT appointment_patient_client
 FOREIGN KEY appointment_patient_client (patient_client_id)
 REFERENCES patient (client_id) ON DELETE CAscade,
 ADD CONSTRAINT appointment_clinic
 FOREIGN KEY appointment_clinic (clinic_id)
 REFERENCES clinic (id) ON DELETE CAscade
 ;
 
 -- Create indexes  
CREATE INDEX city_name_idx ON city (`name`);   
CREATE INDEX clinic_name_idx ON clinic(`name`); 
CREATE INDEX client_surname_name_idx ON `client` (surname,`name`);  
CREATE INDEX doctor_surname_name_idx ON doctor(surname,`name`); 
 

-- Insert values into tables
-- Region
insert into region(`name`) values('Kyiv region'); 
insert into region(`name`) values('Lviv region');
insert into region(`name`) values('Rivne region');
insert into region(`name`) values('Odesa region');
insert into region(`name`) values('Volyn region');
insert into region(`name`) values('Ternopil region');
insert into region(`name`) values('Ivano-Frankivsk region');
insert into region(`name`) values('Kropyvnytskyi region');
insert into region(`name`) values('Zhytomyr region');
insert into region(`name`) values('Mykolaiv region');
insert into region(`name`) values('Zakarpattia region');
insert into region(`name`) values('Kherson region');

-- City
insert into city(`name`, region_name) values('Rivne', 'Rivne region');
insert into city(`name`, region_name) values('Korets', 'Rivne region');
insert into city(`name`, region_name) values('Kyiv', 'Kyiv region');
insert into city(`name`, region_name) values('Bila Tserkva', 'Kyiv region');
insert into city(`name`, region_name) values('Kalush', 'Ivano-Frankivsk region');
insert into city(`name`, region_name) values('Ivano-Frankivsk', 'Ivano-Frankivsk region');
insert into city(`name`, region_name) values('Lutsk', 'Volyn region');
insert into city(`name`, region_name) values('Kherson', 'Kherson region');
insert into city(`name`, region_name) values('Zhytomyr', 'Zhytomyr region');
insert into city(`name`, region_name) values('Ternopil', 'Ternopil region');
insert into city(`name`, region_name) values('Pochaiv', 'Ternopil region');
insert into city(`name`, region_name) values('Mykolaiv', 'Mykolaiv region');

-- Clinic
insert into clinic (id, `name`, clinic_phone, street_adress, city_name) values
(1, 'Cats and dogs', 380978546184, 'Stryjska 45', 'Rivne');
insert into clinic (id, `name`, clinic_phone, street_adress, city_name) values
(2, 'Feel Healed Vets', 380671256545, 'Shevchenko 55', 'Korets');
insert into clinic (id, `name`, clinic_phone, street_adress, city_name) values
(3, 'Focused Pet Care', 380935648152, 'Mazepa 16', 'Kyiv');
insert into clinic (id, `name`, clinic_phone, street_adress, city_name) values
(4, 'Meow Medical Center', 380632648954, 'Lesya Ukrainka 119', 'Bila Tserkva');
insert into clinic (id, `name`, clinic_phone, street_adress, city_name) values
(5, 'Healthy Bons Co', 380325942568, 'Naukova 79', 'Kalush'),
(6, 'Happy Paws Veterinarians', 380501256354, 'Shevchenko 202', 'Ivano-Frankivsk'),
(7, 'Halo Animal Hospital', 380985266458, 'Vyhovsky 54', 'Lutsk'),
(8, 'Harmony for animals', 380664589653, 'Mazepa 72', 'Kherson'),
(9, 'FastMed Urgen Care', 380964658721, 'Kozara 45', 'Zhytomyr'),
(10, 'Pets Best Clinic', 380326145889, 'Cheremshyny 109', 'Ternopil');

-- Doctor
insert into doctor (id, `name`, surname, work_schedule) values
 (1, 'Ivan', 'Pavlyk', 'Monday, Wednesday, Friday'),
(2, 'Dmytro', 'Pavliv', 'Work schedule is here'),
(3, 'Andriy', 'Yavorskiy', 'Work schedule is here'),
(4, 'Vasyl', 'Moroz', 'Work schedule is here'),
(5, 'Igor', 'Melnychuck', 'Work schedule is here'),
(6, 'Bohdan', 'Sydor', 'Work schedule is here'),
(7, 'Leonid', 'Kindij', 'Work schedule is here'),
(8, 'Mykola', 'Struk', 'Work schedule is here'),
(9, 'Nazar', 'Pashko', 'Work schedule is here'),
(10, 'Taras', 'Pasemko', 'Work schedule is here');

-- Clinic_has_doctor
insert into clinic_has_doctor (clinic_id, doctor_id) values
 (1, 10),
 (2, 9),
 (3, 8),
 (4, 7),
 (5, 6),
 (6, 5),
 (7, 4),
 (8, 3),
 (9, 2),
 (10, 1);
 
 -- service
 insert into service (id, `name`, duration, is_available, doctor_id) values 
 (1, 'consultation', '15 m', true, 10),
 (2, 'service name', '20m', false, 9),
 (3, 'service name', '15 m', true, 8),
 (4, 'service name', '15 m', true, 7),
 (5, 'service name', '15 m', true, 6),
 (6, 'service name', '15 m', true, 5),
 (7, 'service name', '15 m', true, 4),
 (8, 'service name', '15 m', true, 3),
 (9, 'service name', '15 m', true, 2),
 (10, 'service name', '15 m', true, 1);
 
 -- client
 insert into client (id, `name`, surname, contact_number) values 
 (1, 'Shevchenko', 'Bohdan', 380652364589),
 (2, 'Dovzhenko', 'Ostap', 380661252363),
 (3, 'Starchuk', 'Oleksandra', 380502363125),
 (4, 'Zhuk', 'Ivan', 380671728596),
 (5, 'Sashenko', 'Ignatiy', 380669987235),
 (6, 'Ukrainets', 'Oksana', 380964646762),
 (7, 'Polonska', 'Margaryta', 380985266134),
 (8, 'Zhabchak', 'Roman', 380964646587),
 (9, 'Golovinskyi', 'Igor', 380671252393),
 (10, 'Ignatenko', 'Ksenia', 380671252353);
 
 -- clinic_client
 insert into clinic_client (clinic_id, client_id) values 
 (1, 2),
 (3, 4),
 (5, 6),
 (7, 8),
 (9, 10),
 (2, 1),
 (4, 3),
 (6, 5),
 (8, 7),
 (10, 9);
 
 -- patient
 insert into patient (id, breed, health_complains, client_id) values
 (1, 'breed of pet', 'health_complaints here', 10),
 (2, 'breed of pet', 'health_complaints here', 9),
 (3, 'breed of pet', 'health_complaints here', 8),
 (4, 'breed of pet', 'health_complaints here', 7),
 (5, 'breed of pet', 'health_complaints here', 6),
 (6, 'breed of pet', 'health_complaints here', 5),
 (7, 'breed of pet', 'health_complaints here', 4),
 (8, 'breed of pet', 'health_complaints here', 3),
 (9, 'breed of pet', 'health_complaints here', 2),
 (10, 'breed of pet', 'health_complaints here', 1);
 
 -- appointment
 insert into appointment (id, date_time, service_id, doctor_id, patient_id, patient_client_id, clinic_id) values
 (1, '2022-10-06 17:45:00', 1, 2, 1, 10, 1),
 (2, '2022-10-06 17:30:00', 4, 1, 2, 9, 2),
 (3, '2022-10-06 18:00:00', 1, 3, 3, 8, 3),
 (4, '2022-10-06 18:15:00', 7, 1, 4, 7, 4),
 (5, '2022-10-06 18:30:00', 5, 8, 5, 6, 5),
 (6, '2022-10-06 18:45:00', 9, 1, 6, 5, 1),
 (7, '2022-10-06 19:00:00', 1, 7, 7, 4, 7),
 (8, '2022-10-06 19:15:00', 5, 1, 8, 3, 8),
 (9, '2022-10-06 19:35:00', 1, 1, 9, 2, 2),
 (10, '2022-10-06 19:45:00', 8, 7, 10, 1, 9);
 
 -- diagnosis
 insert into diagnosis (`name`, `description`) values('pneumonia', 'description of pneumonia');
insert into diagnosis (`name`, `description`) values('bronchitis', 'description of bronchitis');
insert into diagnosis (`name`, `description`) values('pharyngitis', 'description of pharyngitis');
insert into diagnosis (`name`, `description`) values('poisoning', 'description of poisoning');
insert into diagnosis (`name`, `description`) values('stomatitis', 'description of stomatitis');
insert into diagnosis (`name`, `description`) values('acetonemia', 'description of acetonemia');
insert into diagnosis (`name`, `description`) values('leukemia', 'description of leukemia');
insert into diagnosis (`name`, `description`) values('flu', 'description of flu');
insert into diagnosis (`name`, `description`) values('cystitic', 'description of cystitic');
insert into diagnosis (`name`, `description`) values('actinomycosis', 'description of actinomycosis');

-- treatment
insert into treatment (id, `description`, recomendation, diagnosis_name, patient_id) values
(1, 'description of treatment is here', 'recomendation is here', 'pneumonia', 10),
(2, 'description of treatment is here', 'recomendation is here', 'bronchitis', 9),
(3, 'description of treatment is here', 'recomendation is here', 'pharyngitis', 8),
(4, 'description of treatment is here', 'recomendation is here', 'poisoning', 7),
(5, 'description of treatment is here', 'recomendation is here', 'stomatitis', 6),
(6, 'description of treatment is here', 'recomendation is here', 'acetonemia', 5),
(7, 'description of treatment is here', 'recomendation is here', 'leukemia', 4),
(8, 'description of treatment is here', 'recomendation is here', 'flu', 3),
(9, 'description of treatment is here', 'recomendation is here', 'cystitic', 2),
(10, 'description of treatment is here', 'recomendation is here', 'actinomycosis', 1);