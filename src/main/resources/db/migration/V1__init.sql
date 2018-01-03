/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `educational_experience`;
CREATE TABLE `educational_experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grad_year` int(11) NOT NULL,
  `expertise_id` bigint(20) DEFAULT NULL,
  `medic_id` bigint(20) DEFAULT NULL,
  `university_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeh9xva4m40cvr10cbtmc3pji` (`expertise_id`),
  KEY `FK907yunuqx0olg3421ln17ob4r` (`medic_id`),
  KEY `FK8yetmp9k58tuii56oxq1y3dy4` (`university_id`),
  CONSTRAINT `FK8yetmp9k58tuii56oxq1y3dy4` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`),
  CONSTRAINT `FK907yunuqx0olg3421ln17ob4r` FOREIGN KEY (`medic_id`) REFERENCES `medic` (`id`),
  CONSTRAINT `FKmeh9xva4m40cvr10cbtmc3pji` FOREIGN KEY (`expertise_id`) REFERENCES `expertise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `expertise`;
CREATE TABLE `expertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `model_status` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `treatment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hjlv13g23n9dnxrlmjiqi93e` (`treatment_id`),
  CONSTRAINT `FK9hjlv13g23n9dnxrlmjiqi93e` FOREIGN KEY (`treatment_id`) REFERENCES `treatment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `medic`;
CREATE TABLE `medic` (
  `current_title` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `license_number` varchar(255) DEFAULT NULL,
  `refferal_code` varchar(255) DEFAULT NULL,
  `reputation_points` bigint(20) NOT NULL,
  `resume_link` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKdyp1jnph3sulhmgbtaptgp0un` FOREIGN KEY (`id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `medical_case`;
CREATE TABLE `medical_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `model_status` varchar(255) DEFAULT NULL,
  `illness_start_date` date DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnonbw61hbta3jxym7i8hxyb8p` (`patient_id`),
  CONSTRAINT `FKnonbw61hbta3jxym7i8hxyb8p` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `medical_case_treatments`;
CREATE TABLE `medical_case_treatments` (
  `case_id` bigint(20) NOT NULL,
  `treatments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`case_id`,`treatments_id`),
  UNIQUE KEY `UK_2g857ax0tee15dxmvn9dicmko` (`treatments_id`),
  CONSTRAINT `FKmdvd51juuiawtjegxjicrtrma` FOREIGN KEY (`treatments_id`) REFERENCES `treatment` (`id`),
  CONSTRAINT `FKqcu5feolrnlp641xpg2pudhp8` FOREIGN KEY (`case_id`) REFERENCES `medical_case` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `medical_experience`;
CREATE TABLE `medical_experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `expertise_id` bigint(20) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  `medic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj1c6fumlg2tdgoysb0mx3y53t` (`city_id`),
  KEY `FKfg86f71wdwblqv8l427d5l9f8` (`expertise_id`),
  KEY `FK905ny035pvu1s4h0bsrud733t` (`hospital_id`),
  KEY `FK5c6rehljrj43bmwyaj14sk1ae` (`medic_id`),
  CONSTRAINT `FK5c6rehljrj43bmwyaj14sk1ae` FOREIGN KEY (`medic_id`) REFERENCES `medic` (`id`),
  CONSTRAINT `FK905ny035pvu1s4h0bsrud733t` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FKfg86f71wdwblqv8l427d5l9f8` FOREIGN KEY (`expertise_id`) REFERENCES `expertise` (`id`),
  CONSTRAINT `FKj1c6fumlg2tdgoysb0mx3y53t` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `model_status` varchar(255) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `relevant_case_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpq6xkp2666wkm7igimjrcf02q` (`relevant_case_id`),
  CONSTRAINT `FKpq6xkp2666wkm7igimjrcf02q` FOREIGN KEY (`relevant_case_id`) REFERENCES `medical_case` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `nicknames`;
CREATE TABLE `nicknames` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `model_status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `past_chronic_disease`;
CREATE TABLE `past_chronic_disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diagnosis` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `past_medicine`;
CREATE TABLE `past_medicine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `medicine_name` varchar(255) DEFAULT NULL,
  `year_ended` int(11) NOT NULL,
  `year_started` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `past_operation`;
CREATE TABLE `past_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `addictive_drug_profile` varchar(255) DEFAULT NULL,
  `alcohol_consumption_profile` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `smoker_profile` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK6lccqwhxi21py0046i5yhtu47` FOREIGN KEY (`id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patient_chronic_diseases`;
CREATE TABLE `patient_chronic_diseases` (
  `patient_id` bigint(20) NOT NULL,
  `chronic_diseases_id` bigint(20) NOT NULL,
  PRIMARY KEY (`patient_id`,`chronic_diseases_id`),
  UNIQUE KEY `UK_b82a7qmspcc0sweu6ymgudawy` (`chronic_diseases_id`),
  CONSTRAINT `FK62ogjoipkavu0avv1pelfy5g9` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKn5kmk76elyd72bhpkyg7ikksy` FOREIGN KEY (`chronic_diseases_id`) REFERENCES `past_chronic_disease` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patient_medications`;
CREATE TABLE `patient_medications` (
  `patient_id` bigint(20) NOT NULL,
  `medications_id` bigint(20) NOT NULL,
  PRIMARY KEY (`patient_id`,`medications_id`),
  UNIQUE KEY `UK_9y3bq8dhffumt1pajaq8cd9u3` (`medications_id`),
  CONSTRAINT `FK9y1euc0qfpwac36a02tr0yfkd` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKme28qvie0ip2bo5vbygqhhqdf` FOREIGN KEY (`medications_id`) REFERENCES `past_medicine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patient_past_operations`;
CREATE TABLE `patient_past_operations` (
  `patient_id` bigint(20) NOT NULL,
  `past_operations_id` bigint(20) NOT NULL,
  PRIMARY KEY (`patient_id`,`past_operations_id`),
  UNIQUE KEY `UK_hsu3ypexnw920g90l37wwa38q` (`past_operations_id`),
  CONSTRAINT `FKajwbmfvvaplrpwa015mm3ydlu` FOREIGN KEY (`past_operations_id`) REFERENCES `past_operation` (`id`),
  CONSTRAINT `FKepil2yir1qij2etq9xayvgawc` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `model_status` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `relevant_case_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKey2hnnxrve2rcd017hlh4x0vc` (`relevant_case_id`),
  CONSTRAINT `FKey2hnnxrve2rcd017hlh4x0vc` FOREIGN KEY (`relevant_case_id`) REFERENCES `medical_case` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `university`;
CREATE TABLE `university` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;