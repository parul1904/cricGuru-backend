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
-- Table structure for table `squads`
--

DROP TABLE IF EXISTS `squads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `squads` (
  `squad_id` int NOT NULL AUTO_INCREMENT,
  `season_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `player_id` int DEFAULT NULL,
  PRIMARY KEY (`squad_id`),
  UNIQUE KEY `season_id` (`season_id`,`team_id`,`player_id`),
  KEY `player_id` (`player_id`),
  KEY `idx_team_squads_season` (`season_id`),
  KEY `idx_team_squads_team` (`team_id`),
  CONSTRAINT `squads_ibfk_1` FOREIGN KEY (`season_id`) REFERENCES `seasons` (`season_id`),
  CONSTRAINT `squads_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`),
  CONSTRAINT `squads_ibfk_3` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=509 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squads`
--

LOCK TABLES `squads` WRITE;
/*!40000 ALTER TABLE `squads` DISABLE KEYS */;
INSERT INTO `squads` VALUES (256,1,1,8),(261,1,1,11),(267,1,1,54),(265,1,1,55),(260,1,1,85),(271,1,1,91),(253,1,1,103),(255,1,1,104),(254,1,1,105),(257,1,1,107),(266,1,1,110),(259,1,1,119),(263,1,1,120),(273,1,1,123),(278,1,1,127),(264,1,1,136),(276,1,1,172),(262,1,1,190),(272,1,1,243),(277,1,1,247),(258,1,1,255),(268,1,1,256),(269,1,1,257),(270,1,1,258),(274,1,1,259),(275,1,1,260),(297,1,2,14),(304,1,2,38),(284,1,2,40),(286,1,2,51),(283,1,2,52),(290,1,2,56),(285,1,2,62),(289,1,2,64),(298,1,2,67),(303,1,2,70),(302,1,2,97),(299,1,2,121),(288,1,2,132),(300,1,2,151),(279,1,2,179),(292,1,2,186),(295,1,2,229),(280,1,2,261),(293,1,2,262),(301,1,2,263),(291,1,2,264),(281,1,2,265),(287,1,2,266),(410,1,2,267),(294,1,2,268),(296,1,2,269),(282,1,2,270),(319,1,2,328),(325,1,3,17),(313,1,3,65),(330,1,3,69),(315,1,3,112),(326,1,3,122),(305,1,3,130),(316,1,3,140),(314,1,3,141),(312,1,3,143),(318,1,3,144),(331,1,3,148),(320,1,3,150),(321,1,3,153),(328,1,3,154),(306,1,3,159),(322,1,3,169),(307,1,3,180),(317,1,3,217),(310,1,3,323),(329,1,3,324),(311,1,3,325),(323,1,3,326),(308,1,3,327),(324,1,3,329),(327,1,3,330),(309,1,3,331),(333,1,4,1),(334,1,4,3),(342,1,4,4),(347,1,4,9),(344,1,4,10),(345,1,4,12),(346,1,4,13),(354,1,4,15),(352,1,4,19),(353,1,4,20),(355,1,4,21),(336,1,4,28),(348,1,4,42),(350,1,4,66),(356,1,4,68),(349,1,4,92),(335,1,4,134),(332,1,4,205),(343,1,4,240),(357,1,4,271),(337,1,4,272),(351,1,4,273),(360,1,5,2),(366,1,5,37),(358,1,5,61),(364,1,5,115),(369,1,5,139),(361,1,5,185),(372,1,5,191),(363,1,5,192),(383,1,5,195),(375,1,5,198),(377,1,5,200),(378,1,5,201),(379,1,5,202),(368,1,5,213),(380,1,5,225),(371,1,5,241),(362,1,5,313),(365,1,5,314),(367,1,5,315),(370,1,5,317),(373,1,5,318),(374,1,5,319),(376,1,5,320),(381,1,5,321),(382,1,5,322),(394,1,6,30),(395,1,6,32),(409,1,6,34),(384,1,6,76),(386,1,6,77),(389,1,6,82),(398,1,6,83),(392,1,6,88),(400,1,6,96),(402,1,6,98),(397,1,6,114),(408,1,6,126),(407,1,6,149),(387,1,6,157),(390,1,6,206),(391,1,6,207),(405,1,6,245),(403,1,6,246),(401,1,6,249),(385,1,6,306),(388,1,6,307),(393,1,6,308),(396,1,6,309),(399,1,6,310),(404,1,6,311),(406,1,6,312),(424,1,7,29),(412,1,7,36),(421,1,7,49),(426,1,7,113),(433,1,7,125),(432,1,7,145),(415,1,7,158),(429,1,7,164),(434,1,7,170),(414,1,7,211),(418,1,7,212),(423,1,7,215),(431,1,7,222),(411,1,7,294),(413,1,7,295),(416,1,7,296),(417,1,7,297),(419,1,7,298),(420,1,7,299),(422,1,7,300),(425,1,7,301),(427,1,7,302),(428,1,7,303),(430,1,7,304),(435,1,7,305),(444,1,8,5),(443,1,8,63),(455,1,8,89),(447,1,8,111),(437,1,8,131),(450,1,8,193),(456,1,8,223),(451,1,8,227),(436,1,8,232),(445,1,8,233),(442,1,8,235),(438,1,8,236),(439,1,8,237),(440,1,8,238),(441,1,8,239),(454,1,8,251),(446,1,8,288),(448,1,8,289),(449,1,8,290),(452,1,8,291),(453,1,8,292),(457,1,8,293),(460,1,9,25),(459,1,9,26),(477,1,9,27),(471,1,9,39),(469,1,9,44),(462,1,9,48),(458,1,9,50),(468,1,9,84),(475,1,9,90),(479,1,9,94),(461,1,9,133),(466,1,9,137),(476,1,9,146),(472,1,9,194),(465,1,9,214),(482,1,9,224),(474,1,9,226),(463,1,9,280),(464,1,9,281),(467,1,9,282),(470,1,9,283),(473,1,9,284),(478,1,9,285),(480,1,9,286),(481,1,9,287),(505,1,10,16),(504,1,10,18),(500,1,10,41),(503,1,10,71),(484,1,10,106),(485,1,10,135),(495,1,10,138),(487,1,10,162),(490,1,10,163),(492,1,10,167),(497,1,10,168),(502,1,10,174),(498,1,10,176),(483,1,10,181),(491,1,10,187),(494,1,10,188),(499,1,10,197),(493,1,10,216),(506,1,10,244),(501,1,10,248),(486,1,10,274),(488,1,10,275),(489,1,10,276),(496,1,10,277),(507,1,10,278),(508,1,10,279),(101,2,1,103),(102,2,1,104),(103,2,1,105),(104,2,1,106),(105,2,1,107),(106,2,1,108),(107,2,1,109),(108,2,1,110),(109,2,1,111),(110,2,1,112),(111,2,1,113),(112,2,1,114),(113,2,1,115),(114,2,1,116),(115,2,1,117),(116,2,1,118),(117,2,1,119),(118,2,1,120),(119,2,1,121),(120,2,1,122),(121,2,1,123),(122,2,1,124),(123,2,1,125),(124,2,1,126),(125,2,1,127),(126,2,1,128),(127,2,1,129),(58,2,2,49),(53,2,2,50),(50,2,2,51),(51,2,2,52),(52,2,2,53),(57,2,2,54),(61,2,2,55),(65,2,2,56),(64,2,2,57),(62,2,2,58),(63,2,2,59),(60,2,2,60),(49,2,2,61),(55,2,2,62),(54,2,2,63),(56,2,2,64),(59,2,2,65),(70,2,2,66),(71,2,2,67),(66,2,2,68),(68,2,2,69),(69,2,2,70),(67,2,2,71),(72,2,2,74),(73,2,2,75),(128,2,3,130),(129,2,3,131),(130,2,3,132),(131,2,3,133),(132,2,3,134),(133,2,3,135),(134,2,3,136),(135,2,3,137),(136,2,3,138),(137,2,3,139),(138,2,3,140),(139,2,3,141),(140,2,3,142),(141,2,3,143),(142,2,3,144),(143,2,3,145),(144,2,3,146),(145,2,3,147),(146,2,3,148),(147,2,3,149),(148,2,3,150),(149,2,3,151),(150,2,3,152),(151,2,3,153),(152,2,3,154),(153,2,3,155),(154,2,3,156),(1,2,4,1),(2,2,4,2),(3,2,4,3),(4,2,4,4),(5,2,4,5),(6,2,4,6),(7,2,4,7),(8,2,4,8),(9,2,4,9),(10,2,4,10),(11,2,4,11),(12,2,4,12),(13,2,4,13),(14,2,4,14),(15,2,4,15),(16,2,4,16),(17,2,4,17),(18,2,4,18),(19,2,4,19),(20,2,4,20),(21,2,4,21),(22,2,4,22),(23,2,4,23),(24,2,4,24),(359,2,5,35),(177,2,5,179),(178,2,5,180),(179,2,5,181),(180,2,5,182),(181,2,5,183),(182,2,5,184),(183,2,5,185),(184,2,5,186),(185,2,5,187),(186,2,5,188),(187,2,5,189),(188,2,5,190),(189,2,5,191),(190,2,5,192),(191,2,5,193),(192,2,5,194),(193,2,5,195),(194,2,5,196),(195,2,5,197),(196,2,5,198),(197,2,5,199),(198,2,5,200),(199,2,5,201),(200,2,5,202),(202,2,5,203),(201,2,5,204),(74,2,6,76),(75,2,6,77),(76,2,6,78),(77,2,6,79),(78,2,6,80),(79,2,6,81),(80,2,6,82),(81,2,6,83),(82,2,6,84),(83,2,6,85),(84,2,6,86),(85,2,6,87),(86,2,6,88),(87,2,6,89),(88,2,6,90),(89,2,6,91),(90,2,6,92),(91,2,6,93),(92,2,6,94),(93,2,6,95),(94,2,6,96),(95,2,6,97),(96,2,6,98),(97,2,6,99),(98,2,6,100),(99,2,6,101),(100,2,6,102),(203,2,7,205),(204,2,7,206),(205,2,7,207),(206,2,7,208),(207,2,7,209),(208,2,7,210),(209,2,7,211),(210,2,7,212),(211,2,7,213),(212,2,7,214),(213,2,7,215),(214,2,7,216),(215,2,7,217),(216,2,7,218),(217,2,7,219),(218,2,7,220),(219,2,7,221),(220,2,7,222),(221,2,7,223),(222,2,7,224),(223,2,7,225),(224,2,7,226),(225,2,7,227),(226,2,7,228),(227,2,7,229),(229,2,7,230),(228,2,7,231),(230,2,8,232),(231,2,8,233),(232,2,8,234),(233,2,8,235),(234,2,8,236),(235,2,8,237),(236,2,8,238),(237,2,8,239),(238,2,8,240),(239,2,8,241),(240,2,8,242),(241,2,8,243),(242,2,8,244),(243,2,8,245),(244,2,8,246),(245,2,8,247),(246,2,8,248),(247,2,8,249),(248,2,8,250),(249,2,8,251),(250,2,8,252),(251,2,8,253),(252,2,8,254),(25,2,9,25),(26,2,9,26),(46,2,9,27),(27,2,9,28),(31,2,9,29),(34,2,9,30),(37,2,9,31),(35,2,9,32),(38,2,9,33),(42,2,9,34),(29,2,9,35),(28,2,9,36),(32,2,9,37),(39,2,9,38),(36,2,9,39),(30,2,9,40),(41,2,9,41),(40,2,9,42),(43,2,9,43),(33,2,9,44),(45,2,9,45),(44,2,9,46),(47,2,9,47),(48,2,9,48),(155,2,10,157),(156,2,10,158),(157,2,10,159),(158,2,10,160),(159,2,10,161),(160,2,10,162),(161,2,10,163),(162,2,10,164),(163,2,10,165),(164,2,10,166),(165,2,10,167),(166,2,10,168),(167,2,10,169),(168,2,10,170),(169,2,10,171),(170,2,10,172),(171,2,10,173),(172,2,10,174),(173,2,10,175),(174,2,10,176),(175,2,10,177),(176,2,10,178);
/*!40000 ALTER TABLE `squads` ENABLE KEYS */;
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

-- Dump completed on 2025-03-15 15:22:03
