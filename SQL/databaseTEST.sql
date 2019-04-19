-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: calc_o2
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calc_companies`
--

DROP TABLE IF EXISTS `calc_companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `companies_unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_companies`
--

LOCK TABLES `calc_companies` WRITE;
/*!40000 ALTER TABLE `calc_companies` DISABLE KEYS */;
INSERT INTO `calc_companies` VALUES (1,'MROZONKI HEJ');
/*!40000 ALTER TABLE `calc_companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_companies_lines`
--

DROP TABLE IF EXISTS `calc_companies_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_companies_lines` (
  `company_id` bigint(20) NOT NULL,
  `lines_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qxa0j1pv87f5p4dp7k649jxue` (`lines_id`),
  KEY `FKfq0mqeyq3rt7g11bg7oo0c96n` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_companies_lines`
--

LOCK TABLES `calc_companies_lines` WRITE;
/*!40000 ALTER TABLE `calc_companies_lines` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_companies_lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_companies_modules`
--

DROP TABLE IF EXISTS `calc_companies_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_companies_modules` (
  `company_id` bigint(20) NOT NULL,
  `modules_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_d760qjq9gi9irdmqrptlb0cma` (`modules_id`),
  KEY `FK85ads5qyjcrfvvjnrd9bcoq2d` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_companies_modules`
--

LOCK TABLES `calc_companies_modules` WRITE;
/*!40000 ALTER TABLE `calc_companies_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_companies_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_companies_stages`
--

DROP TABLE IF EXISTS `calc_companies_stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_companies_stages` (
  `company_id` bigint(20) NOT NULL,
  `stages_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_tm4ybmue74onjmsjnnc2fxsuf` (`stages_id`),
  KEY `FKcxcmgfwgu7iiwtsuv2fr7lfxt` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_companies_stages`
--

LOCK TABLES `calc_companies_stages` WRITE;
/*!40000 ALTER TABLE `calc_companies_stages` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_companies_stages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_company_register_requests`
--

DROP TABLE IF EXISTS `calc_company_register_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_company_register_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_accepted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `foreign_company_request_id` (`company_id`),
  KEY `foreign_user_request_id` (`user_id`),
  CONSTRAINT `foreign_company_request_id` FOREIGN KEY (`company_id`) REFERENCES `calc_companies` (`id`),
  CONSTRAINT `foreign_user_request_id` FOREIGN KEY (`user_id`) REFERENCES `calc_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_company_register_requests`
--

LOCK TABLES `calc_company_register_requests` WRITE;
/*!40000 ALTER TABLE `calc_company_register_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_company_register_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_lines`
--

DROP TABLE IF EXISTS `calc_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_lines` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `outsourced` int(11) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjuoxscrqlqk054oggxp5o5rpn` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_lines`
--

LOCK TABLES `calc_lines` WRITE;
/*!40000 ALTER TABLE `calc_lines` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_modules`
--

DROP TABLE IF EXISTS `calc_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_modules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` bigint(20) NOT NULL,
  `loss` float NOT NULL,
  `name` varchar(255) NOT NULL,
  `outsourced` int(11) NOT NULL,
  `power` float NOT NULL,
  `resource` bigint(20) NOT NULL,
  `time` int(11) NOT NULL,
  `waste` float NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjb5txg0alyvhxo6ybsal8t61x` (`company_id`),
  KEY `FKc4khhseradrprj5eeagvpw13g` (`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_modules`
--

LOCK TABLES `calc_modules` WRITE;
/*!40000 ALTER TABLE `calc_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_resources`
--

DROP TABLE IF EXISTS `calc_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diesel` float NOT NULL,
  `lpg` float NOT NULL,
  `oil` float NOT NULL,
  `water` float NOT NULL,
  `power` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_resources`
--

LOCK TABLES `calc_resources` WRITE;
/*!40000 ALTER TABLE `calc_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_resources_modules`
--

DROP TABLE IF EXISTS `calc_resources_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_resources_modules` (
  `resource_id` bigint(20) NOT NULL,
  `modules_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_o29xlm55rg5v0bdct3b0p2rsp` (`modules_id`),
  KEY `FKg0bceyns69jmc77l8wjahewvf` (`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_resources_modules`
--

LOCK TABLES `calc_resources_modules` WRITE;
/*!40000 ALTER TABLE `calc_resources_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_resources_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_stages`
--

DROP TABLE IF EXISTS `calc_stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `outsourced` int(11) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrsi5etkebrn3c19ne92856iv3` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_stages`
--

LOCK TABLES `calc_stages` WRITE;
/*!40000 ALTER TABLE `calc_stages` DISABLE KEYS */;
/*!40000 ALTER TABLE `calc_stages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_users`
--

DROP TABLE IF EXISTS `calc_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_users`
--

LOCK TABLES `calc_users` WRITE;
/*!40000 ALTER TABLE `calc_users` DISABLE KEYS */;
INSERT INTO `calc_users` VALUES (1,'Kamil','Klimek','admin@example.com','$2a$12$M8KP/uyMeX6.T6PUzaOrVu4yIckSDB4nhT7Em10jziGt2Ghy2nLo6',_binary '','USER'),(2,'Jan','Kowalski','jan@example.com','$2a$12$M8KP/uyMeX6.T6PUzaOrVu4yIckSDB4nhT7Em10jziGt2Ghy2nLo6',_binary '','SUPER_ADMIN');
/*!40000 ALTER TABLE `calc_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calc_users_job`
--

DROP TABLE IF EXISTS `calc_users_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_users_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcv4e0puurbml3e9xb3shdfdla` (`company_id`),
  KEY `FKt5qrht8juspifxrvqrnjqt8yj` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_users_job`
--

LOCK TABLES `calc_users_job` WRITE;
/*!40000 ALTER TABLE `calc_users_job` DISABLE KEYS */;
INSERT INTO `calc_users_job` VALUES (1,2,1,1);
/*!40000 ALTER TABLE `calc_users_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `calc_users_register_requests`
--

DROP TABLE IF EXISTS `calc_users_register_requests`;
/*!50001 DROP VIEW IF EXISTS `calc_users_register_requests`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `calc_users_register_requests` AS SELECT 
 1 AS `id`,
 1 AS `email`,
 1 AS `last_name`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `calc_vegetables`
--

DROP TABLE IF EXISTS `calc_vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calc_vegetables` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calc_vegetables`
--

LOCK TABLES `calc_vegetables` WRITE;
/*!40000 ALTER TABLE `calc_vegetables` DISABLE KEYS */;
INSERT INTO `calc_vegetables` VALUES (1,'brokuł'),(2,'cebula kostka'),(3,'cebula plastry'),(4,'fasola szparagowa cała'),(5,'fasola szparagowa cięta'),(6,'fasola ziarno'),(7,'groszek'),(8,'kalafior'),(9,'szpinak całe liście'),(10,'szpinak rozdrobniony');
/*!40000 ALTER TABLE `calc_vegetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_admin`
--

DROP TABLE IF EXISTS `companies_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies_admin` (
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  KEY `ca_fore_u` (`user_id`),
  KEY `ca_fore_c` (`company_id`),
  CONSTRAINT `ca_fore_c` FOREIGN KEY (`company_id`) REFERENCES `calc_companies` (`id`),
  CONSTRAINT `ca_fore_u` FOREIGN KEY (`user_id`) REFERENCES `calc_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_admin`
--

LOCK TABLES `companies_admin` WRITE;
/*!40000 ALTER TABLE `companies_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `companies_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_experts`
--

DROP TABLE IF EXISTS `companies_experts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies_experts` (
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  KEY `ce_fore_u` (`user_id`),
  KEY `ce_fore_e` (`company_id`),
  CONSTRAINT `ce_fore_e` FOREIGN KEY (`company_id`) REFERENCES `calc_companies` (`id`),
  CONSTRAINT `ce_fore_u` FOREIGN KEY (`user_id`) REFERENCES `calc_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_experts`
--

LOCK TABLES `companies_experts` WRITE;
/*!40000 ALTER TABLE `companies_experts` DISABLE KEYS */;
/*!40000 ALTER TABLE `companies_experts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies_workers`
--

DROP TABLE IF EXISTS `companies_workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies_workers` (
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  KEY `cw_fore_u` (`user_id`),
  KEY `cw_fore_c` (`company_id`),
  CONSTRAINT `cw_fore_c` FOREIGN KEY (`company_id`) REFERENCES `calc_companies` (`id`),
  CONSTRAINT `cw_fore_u` FOREIGN KEY (`user_id`) REFERENCES `calc_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies_workers`
--

LOCK TABLES `companies_workers` WRITE;
/*!40000 ALTER TABLE `companies_workers` DISABLE KEYS */;
/*!40000 ALTER TABLE `companies_workers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_stages`
--

DROP TABLE IF EXISTS `line_stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `line_stages` (
  `stage_id` bigint(20) NOT NULL,
  `line_id` bigint(20) NOT NULL,
  KEY `FK8m93ivv31v6buci1mwh83h2g9` (`line_id`),
  KEY `FKe57b4j5q2ivm94j2tnwb3hgot` (`stage_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_stages`
--

LOCK TABLES `line_stages` WRITE;
/*!40000 ALTER TABLE `line_stages` DISABLE KEYS */;
/*!40000 ALTER TABLE `line_stages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_vegetables`
--

DROP TABLE IF EXISTS `line_vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `line_vegetables` (
  `vegetable_id` bigint(20) NOT NULL,
  `line_id` bigint(20) NOT NULL,
  KEY `FKhjrh3j8rtao91ek0m70xshuxs` (`line_id`),
  KEY `FK4surj4jm6bxv7n379k3hitsis` (`vegetable_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_vegetables`
--

LOCK TABLES `line_vegetables` WRITE;
/*!40000 ALTER TABLE `line_vegetables` DISABLE KEYS */;
/*!40000 ALTER TABLE `line_vegetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liquibase_change_log_mysql`
--

DROP TABLE IF EXISTS `liquibase_change_log_mysql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `liquibase_change_log_mysql` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquibase_change_log_mysql`
--

LOCK TABLES `liquibase_change_log_mysql` WRITE;
/*!40000 ALTER TABLE `liquibase_change_log_mysql` DISABLE KEYS */;
INSERT INTO `liquibase_change_log_mysql` VALUES ('addCalcUsersTable','klimekk','classpath:liquibase-changelog.yaml','2019-04-10 13:01:23',1,'EXECUTED','8:01e2bef720f9a7b789dad18702f20337','createTable tableName=calc_users; addUniqueConstraint constraintName=users_unique_email, tableName=calc_users','',NULL,'3.6.3',NULL,NULL,'4894082482'),('addCalcCompanyTable','klimekk','classpath:liquibase-changelog.yaml','2019-04-10 13:01:24',2,'EXECUTED','8:5aff4f3094912c6f45a4da3e61193f21','createTable tableName=calc_companies; addUniqueConstraint constraintName=companies_unique_name, tableName=calc_companies','',NULL,'3.6.3',NULL,NULL,'4894082482'),('addCompanyMembersTable1','klimekk','classpath:liquibase-changelog.yaml','2019-04-10 13:01:34',3,'EXECUTED','8:fab733d80eade2e094827e28c114a1a5','createTable tableName=companies_workers; addForeignKeyConstraint baseTableName=companies_workers, constraintName=cw_fore_u, referencedTableName=calc_users; addForeignKeyConstraint baseTableName=companies_workers, constraintName=cw_fore_c, referenc...','',NULL,'3.6.3',NULL,NULL,'4894082482'),('addCompanyRegisterRequestTable1','klimekk','classpath:liquibase-changelog.yaml','2019-04-10 13:01:37',4,'EXECUTED','8:2caa639f182af63ba31d51a1294cda1d','createTable tableName=calc_company_register_requests; addForeignKeyConstraint baseTableName=calc_company_register_requests, constraintName=foreign_company_request_id, referencedTableName=calc_companies; addForeignKeyConstraint baseTableName=calc_c...','',NULL,'3.6.3',NULL,NULL,'4894082482'),('addViewForUserRegisterRequest','klimekk','classpath:liquibase-changelog.yaml','2019-04-10 13:01:38',5,'EXECUTED','8:bc93d77f1d0c3f6edb4f8f23759ff05d','createView viewName=calc_users_register_requests','',NULL,'3.6.3',NULL,NULL,'4894082482');
/*!40000 ALTER TABLE `liquibase_change_log_mysql` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module_vegetables`
--

DROP TABLE IF EXISTS `module_vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `module_vegetables` (
  `vegetable_id` bigint(20) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  KEY `FKb4g9l4jlvungfbj1tb6vrtte2` (`module_id`),
  KEY `FKbjyuthmjv892v0k133bo8tryf` (`vegetable_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_vegetables`
--

LOCK TABLES `module_vegetables` WRITE;
/*!40000 ALTER TABLE `module_vegetables` DISABLE KEYS */;
/*!40000 ALTER TABLE `module_vegetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stage_modules`
--

DROP TABLE IF EXISTS `stage_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stage_modules` (
  `module_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  KEY `FK1n52lxhh2rfpqhurh4fu7vnbi` (`stage_id`),
  KEY `FK9twgachmgj377fv4a9r5tx71n` (`module_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_modules`
--

LOCK TABLES `stage_modules` WRITE;
/*!40000 ALTER TABLE `stage_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `stage_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stage_vegetables`
--

DROP TABLE IF EXISTS `stage_vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stage_vegetables` (
  `vegetable_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  KEY `FKt3crmma68opfx7n1tl750o1mf` (`stage_id`),
  KEY `FKmek0imp7rl803mrc6o1enj6nn` (`vegetable_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_vegetables`
--

LOCK TABLES `stage_vegetables` WRITE;
/*!40000 ALTER TABLE `stage_vegetables` DISABLE KEYS */;
/*!40000 ALTER TABLE `stage_vegetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `calc_users_register_requests`
--

/*!50001 DROP VIEW IF EXISTS `calc_users_register_requests`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `calc_users_register_requests` AS select `uu`.`id` AS `id`,`uu`.`email` AS `email`,`uu`.`last_name` AS `last_name`,`uu`.`name` AS `name` from (`calc_users` `uu` left join `calc_company_register_requests` `rr` on((`uu`.`id` = `rr`.`user_id`))) where ((`uu`.`is_active` = FALSE) and isnull(`rr`.`company_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-19 20:35:37
