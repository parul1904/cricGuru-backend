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
-- Table structure for table `venues`
--

DROP TABLE IF EXISTS `venues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venues` (
  `venue_id` int NOT NULL AUTO_INCREMENT,
  `venue_name` varchar(255) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `capacity` varchar(100) DEFAULT NULL,
  `venue_image_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`venue_id`),
  UNIQUE KEY `venue_id` (`venue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venues`
--

LOCK TABLES `venues` WRITE;
/*!40000 ALTER TABLE `venues` DISABLE KEYS */;
INSERT INTO `venues` VALUES (1,'Eden Gardens','Kolkata','India','66,000','../images/venue/edenGarden-Kolkata.jpg','2025-02-28 14:37:42','2025-02-28 14:37:42'),(2,'Rajiv Gandhi International Stadium','Hyderabad','India','55,000','../images/venue/Hyderabad-Stadium.jpg','2025-02-28 14:40:00','2025-02-28 14:40:00'),(3,'MA Chidambaram Stadium','Chennai','India','38,200','../images/venue/Chidambaram-Chennai.jpg','2025-02-28 14:41:10','2025-02-28 14:41:10'),(4,'Dr YS Rajasekhara Reddy ACA-VDCA Cricket Stadium','Visakhapatnam','India','25,000','../images/venue/dr.-y.s.-rajasekhara-reddy-aca-visakhapatnam.jpg','2025-02-28 14:43:28','2025-02-28 14:43:28'),(5,'Narendra Modi Stadium','Ahmedabad','India','1,32,000','../images/venue/NarendraModi-Ahmedabad.jpg','2025-02-28 14:45:01','2025-02-28 14:45:01'),(6,'Barsapara Cricket Stadium','Guwahati','India','37,800','../images/venue/barsapara-cricket-stadium-guwahati.jpg','2025-02-28 14:46:52','2025-02-28 14:46:52'),(7,'Wankhede Stadium','Mumbai','India','33,100','../images/venue/WankhedeStadium-Mumbai.jpg','2025-02-28 14:47:58','2025-02-28 14:47:58'),(8,'Bharat Ratna Shri Atal Bihari Vajpayee Ekana Cricket Stadium','Lucknow','India','50,100','../images/venue/lucknow.jpeg','2025-02-28 14:49:15','2025-02-28 14:49:15'),(9,'M Chinnaswamy Stadium','Bengaluru','India','40,000','../images/venue/Chinnaswamy-Bengaluru.jpg','2025-02-28 14:50:13','2025-02-28 14:50:13'),(10,'New PCA Stadium','New Chandigarh','India','38,000','../images/venue/chandigarh.jpeg','2025-02-28 14:51:40','2025-02-28 14:51:40'),(11,'Sawai Mansingh Stadium','Jaipur','India','24,000','../images/venue/sawai_mansingh_stadium.jpg','2025-02-28 14:53:10','2025-02-28 14:53:10'),(12,'Arun Jaitley Stadium','Delhi','India','35,200','../images/venue/ArunJaitley-Delhi.jpg','2025-02-28 14:54:53','2025-02-28 14:54:53'),(13,'Himachal Pradesh Cricket Association Stadium','Dharamsala','India','21,200','../images/venue/dharamshala.jpeg','2025-02-28 14:57:24','2025-02-28 14:57:24');
/*!40000 ALTER TABLE `venues` ENABLE KEYS */;
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

-- Dump completed on 2025-03-15 15:22:02
