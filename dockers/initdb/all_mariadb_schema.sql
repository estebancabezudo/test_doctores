DROP DATABASE IF EXISTS internal_medicine;
CREATE DATABASE internal_medicine;
USE internal_medicine;

DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS offices;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS specialities;

CREATE TABLE `specialities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `doctors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `motherLastName` VARCHAR(100) NOT NULL,
  `speciality_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_doctors_speciality` FOREIGN KEY (`speciality_id`) REFERENCES `specialities` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `offices` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT(4) NOT NULL,
  `floor` INT(2) NOT NULL CHECK (`floor` > 0),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `appointments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `office_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `appointmentDateTime` DATETIME NOT NULL,
  `patient` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_appointments_office` FOREIGN KEY (`office_id`) REFERENCES `offices`(`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_appointments_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`) ON DELETE RESTRICT,
  UNIQUE KEY `uniq_office_time` (`office_id`, `appointmentDateTime`),
  UNIQUE KEY `uniq_doctor_time` (`doctor_id`, `appointmentDateTime`),
  INDEX `idx_appointments_office_time` (`office_id`, `appointmentDateTime`),
  INDEX `idx_appointments_doctor_time` (`doctor_id`, `appointmentDateTime`),
  INDEX `idx_appointments_patient_time` (`patient`, `appointmentDateTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `specialities` (name) VALUES('endocrinología');
INSERT INTO `specialities` (name) VALUES('pediatría');
INSERT INTO `specialities` (name) VALUES('otorrinolaringología');
INSERT INTO `specialities` (name) VALUES('gastroenterología');

SET @otorhinolaryngology_id = (SELECT id FROM specialities WHERE name = 'otorrinolaringología');
SET @pediatrics_id = (SELECT id FROM specialities WHERE name = 'pediatría');
SET @endocrinology_id = (SELECT id FROM specialities WHERE name = 'endocrinología');
SET @gastroenterology_id = (SELECT id FROM specialities WHERE name = 'gastroenterología');

INSERT INTO `doctors` (name, lastName, motherLastName, speciality_id) VALUES('Juan', 'Perez', 'Romero', @otorhinolaryngology_id);
INSERT INTO `doctors` (name, lastName, motherLastName, speciality_id) VALUES('Alicia', 'Méndez', 'Trabanco', @pediatrics_id);
INSERT INTO `doctors` (name, lastName, motherLastName, speciality_id) VALUES('Esteban', 'Cabezudo', 'Trabanco', @gastroenterology_id);
INSERT INTO `doctors` (name, lastName, motherLastName, speciality_id) VALUES('Andrés', 'Rocca', 'Bilat', @otorhinolaryngology_id);
INSERT INTO `doctors` (name, lastName, motherLastName, speciality_id) VALUES('Sofia', 'Cabezudo', 'Ojeda', @pediatrics_id);

INSERT INTO `offices` (number, floor) VALUES(10, 1);
INSERT INTO `offices` (number, floor) VALUES(23, 2);
INSERT INTO `offices` (number, floor) VALUES(41, 4);
INSERT INTO `offices` (number, floor) VALUES(42, 4);
