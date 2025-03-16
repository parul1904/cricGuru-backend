-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: cricguru-db.cpiius8geitg.ap-south-1.rds.amazonaws.com    Database: cricguru_db
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) NOT NULL,
  `team_short_name` varchar(10) NOT NULL,
  `team_logo_url` varchar(255) DEFAULT NULL,
  `captain` varchar(255) DEFAULT NULL,
  `coach` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `title_won` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `team_id` (`team_id`),
  UNIQUE KEY `team_name` (`team_name`),
  UNIQUE KEY `team_short_name` (`team_short_name`),
  UNIQUE KEY `team_logo_url` (`team_logo_url`),
  UNIQUE KEY `captain` (`captain`),
  UNIQUE KEY `coach` (`coach`),
  UNIQUE KEY `venue` (`venue`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'Chennai Super Kings','CSK','../images/logo/CSK.png','Ruturaj Gaikwad','Stephen Fleming','M. A. Chidambaram Stadium','2010, 2011, 2018, 2021, 2023','2025-03-01 07:24:27','2025-03-01 07:24:27'),(2,'Delhi Capitals','DC','../images/logo/DC.png','KL Rahul','Hemang Badani','Arun Jaitley Stadium','','2025-03-01 07:29:58','2025-03-01 07:29:58'),(3,'Gujrat Titans','GT','../images/logo/GT.png','Shubman Gill','Ashish Nehra','Narendra Modi Stadium','2022','2025-03-01 08:02:43','2025-03-01 08:02:43'),(4,'Kolkata Knight Riders','KKR','../images/logo/KKR.png','Venkatesh Iyer','Chandrakant Pandit','Eden Gardens','2012, 2014, 2024','2025-03-01 08:03:28','2025-03-01 08:03:28'),(5,'Lucknow Super Gaints','LSG','../images/logo/LSG.png','Rishabh Pant','Justin Langer','BRSABV Ekana Cricket Stadium','','2025-03-01 08:04:09','2025-03-01 08:04:09'),(6,'Mumbai Indians','MI','../images/logo/MI.png','Hardik Pandya','Mahela Jayawardene','Wankhede Stadium','2013, 2015, 2017, 2019, 2020','2025-03-01 08:04:41','2025-03-01 08:04:41'),(7,'Punjab Kings','PBKS','../images/logo/PBK.png','Shreyas Iyer','Ricky Ponting','PCA New Stadium, Mullanpur','','2025-03-01 08:05:28','2025-03-01 08:05:28'),(8,'Rajasthan Royals','RR','../images/logo/RR.png','Sanju Samson','Rahul Dravid','Sawai Mansingh Stadium','2008','2025-03-01 08:06:18','2025-03-01 08:06:18'),(9,'Royal Challengers Bengaluru','RCB','../images/logo/RCB.png','Rajat Patidar','Andy Flower','M. Chinnaswamy Stadium','','2025-03-01 08:06:48','2025-03-01 08:06:48'),(10,'Sunrisers Hyderabad','SRH','../images/logo/SRH.png','Pat Cummins','Daniel Vettori','Rajiv Gandhi Intl. Cricket Stadium','2016','2025-03-01 08:07:19','2025-03-01 08:07:19'),(100,'TBD','TBD','../images/logo/TBD.png','-','-','-','-','2025-03-01 19:46:11','2025-03-01 19:46:11');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-15 15:22:01
