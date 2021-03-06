-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: localhost    Database: patisserie_manager
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergy`
--

DROP TABLE IF EXISTS `allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ingredient` int NOT NULL,
  `item` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKreqa09tuhnbt7cgyy4mvjkguk` (`ingredient`),
  KEY `FKjbdk1t8nb35yxl5t5xcfbc5l5` (`item`),
  CONSTRAINT `FKjbdk1t8nb35yxl5t5xcfbc5l5` FOREIGN KEY (`item`) REFERENCES `allergy_item` (`id`),
  CONSTRAINT `FKreqa09tuhnbt7cgyy4mvjkguk` FOREIGN KEY (`ingredient`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergy`
--

LOCK TABLES `allergy` WRITE;
/*!40000 ALTER TABLE `allergy` DISABLE KEYS */;
INSERT INTO `allergy` VALUES (9,7,2),(10,6,4),(11,5,3),(12,4,2),(14,9,2),(15,10,2),(16,10,21),(20,3,1),(21,2,1),(22,1,1);
/*!40000 ALTER TABLE `allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allergy_item`
--

DROP TABLE IF EXISTS `allergy_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergy_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergy_item`
--

LOCK TABLES `allergy_item` WRITE;
/*!40000 ALTER TABLE `allergy_item` DISABLE KEYS */;
INSERT INTO `allergy_item` VALUES (1,'???'),(2,'???'),(3,'??????'),(4,'??????'),(5,'?????????'),(6,'??????'),(7,'??????'),(8,'?????????'),(9,'???'),(10,'???'),(11,'??????'),(12,'????????????'),(13,'?????????'),(14,'?????????'),(15,'??????'),(16,'?????????'),(17,'???????????????'),(18,'?????????????????????'),(19,'?????????'),(20,'??????'),(21,'??????'),(22,'??????'),(23,'??????'),(24,'????????????'),(25,'??????'),(26,'??????'),(27,'??????');
/*!40000 ALTER TABLE `allergy_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_flag` int NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `delete_flag` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3um79qgwg340lpaw7phtwudtc` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (14,1,'A002','2021-01-01 00:00:00.000000',0,'????????????','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2021-01-02 00:00:00.000000'),(15,1,'A001','2022-01-14 11:48:39.578325',1,'????????????????????????','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-14 12:06:25.721329'),(16,1,'A003','2022-01-14 12:06:59.882701',0,'????????????','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-14 12:06:59.882701'),(17,0,'A004','2022-01-15 09:59:06.852182',0,'????????????','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-15 09:59:06.852182');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `amount_u` varchar(255) DEFAULT NULL,
  `delete_flag` int NOT NULL,
  `maker` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `saler` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

LOCK TABLES `equipments` WRITE;
/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
INSERT INTO `equipments` VALUES (1,1,'???',0,'????????????','???????????????',1000,'????????????'),(2,1,'???',0,'????????????','?????????????????????',2000,'????????????'),(3,5000,'ml',0,'????????????','????????????',2000,'????????????'),(4,5000,'ml',0,'????????????','????????????',2000,'????????????'),(5,100,'???',0,'????????????','????????????',2000,'????????????'),(6,5000,'ml',0,'????????????','???????????????',3000,'????????????');
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `amount_u` varchar(255) DEFAULT NULL,
  `delete_flag` int NOT NULL,
  `energy` double DEFAULT NULL,
  `maker` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `saler` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,600,'g',0,151,'????????????','??????',200,'???????????????'),(2,40,'g',0,47.1,'????????????','??????',13,'???????????????'),(3,20,'g',0,386.9,'????????????','??????',7,'???????????????'),(4,1000,'ml',0,66.9,'??????????????????','??????',200,'??????????????????'),(5,5000,'g',0,368,'????????????','?????????',1000,'????????????'),(6,1000,'g',0,359,'????????????','?????????',400,'????????????'),(7,450,'g',0,745,'??????????????????','?????????',300,'??????????????????'),(8,10000,'g',0,386.9,'????????????','??????????????????',3000,'???????????????'),(9,1000,'ml',0,433.1,'??????????????????','???????????????46%',1500,'??????????????????'),(10,5000,'g',0,587.9,'????????????','??????????????????',9000,'????????????'),(11,100,'???',0,298,'????????????','?????????????????????',10000,'????????????'),(12,180,'g',0,294,'?????????','??????',300,'????????????'),(13,5000,'g',0,384.1,'????????????','?????????',1000,'????????????'),(14,3,'g',1,3,'????????????','????????????????????????',3,'????????????'),(15,NULL,'g',1,NULL,NULL,'aa',NULL,NULL);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joinIngredient`
--

DROP TABLE IF EXISTS `joinIngredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joinIngredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `amount_u` varchar(255) DEFAULT NULL,
  `ingredient` int NOT NULL,
  `recipe` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2u62k563togrhfbfmsb8cwr3` (`ingredient`),
  KEY `FKt4lt6qb77uljqn58lp1f4h8el` (`recipe`),
  CONSTRAINT `FK2u62k563togrhfbfmsb8cwr3` FOREIGN KEY (`ingredient`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `FKt4lt6qb77uljqn58lp1f4h8el` FOREIGN KEY (`recipe`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joinIngredient`
--

LOCK TABLES `joinIngredient` WRITE;
/*!40000 ALTER TABLE `joinIngredient` DISABLE KEYS */;
INSERT INTO `joinIngredient` VALUES (3,1000,'ml',4,1),(4,200,'g',3,1),(5,200,'g',8,1),(6,100,'g',5,1),(7,1,'???',11,1),(8,218,'g',1,2),(9,100,'g',13,2),(10,7,'g',12,2),(11,90,'g',5,2),(12,27,'g',7,2),(13,10,'g',9,2),(14,450,'ml',4,3),(15,0.5,'???',11,3),(16,100,'g',3,3),(17,67.5,'g',8,3),(18,337.5,'g',10,3),(19,168,'g',7,3),(20,67.5,'g',9,3);
/*!40000 ALTER TABLE `joinIngredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JoinOrderHistories`
--

DROP TABLE IF EXISTS `JoinOrderHistories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `JoinOrderHistories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderedDate` varchar(255) NOT NULL,
  `saler` varchar(255) DEFAULT NULL,
  `orderedEmp` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8dhk67r71rydbde9slv190rlg` (`orderedEmp`),
  CONSTRAINT `FK8dhk67r71rydbde9slv190rlg` FOREIGN KEY (`orderedEmp`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JoinOrderHistories`
--

LOCK TABLES `JoinOrderHistories` WRITE;
/*!40000 ALTER TABLE `JoinOrderHistories` DISABLE KEYS */;
INSERT INTO `JoinOrderHistories` VALUES (1,'02???12???(???)','??????????????????',14),(2,'02???12???(???)','????????????',14),(3,'02???12???(???)','???????????????',14),(4,'02???12???(???)','????????????',14),(5,'02???12???(???)','????????????',14),(6,'02???12???(???)','????????????',14),(7,'02???12???(???)','????????????',14),(8,'02???12???(???)','??????????????????',14),(9,'02???12???(???)','??????????????????',14);
/*!40000 ALTER TABLE `JoinOrderHistories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderHistories`
--

DROP TABLE IF EXISTS `orderHistories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderHistories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deliDate` varchar(255) NOT NULL,
  `numberOfOrder` double DEFAULT NULL,
  `orderedDate` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `ingredient` int DEFAULT NULL,
  `orderedEmp` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0f7e22svrh87sictwv1mrg5c` (`ingredient`),
  KEY `FKmmu9vpuqwsou1mobndr73tce2` (`orderedEmp`),
  CONSTRAINT `FKmmu9vpuqwsou1mobndr73tce2` FOREIGN KEY (`orderedEmp`) REFERENCES `employees` (`id`),
  CONSTRAINT `FKp0f7e22svrh87sictwv1mrg5c` FOREIGN KEY (`ingredient`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderHistories`
--

LOCK TABLES `orderHistories` WRITE;
/*!40000 ALTER TABLE `orderHistories` DISABLE KEYS */;
INSERT INTO `orderHistories` VALUES (1,'02???15???(???)',1,'02???12???(???)',1500,9,14),(2,'02???15???(???)',1,'02???12???(???)',300,7,14),(3,'02???15???(???)',1,'02???12???(???)',200,4,14),(4,'02???15???(???)',1,'02???12???(???)',3,14,14),(5,'02???15???(???)',1,'02???12???(???)',1000,13,14),(6,'02???15???(???)',1,'02???12???(???)',400,6,14),(7,'02???15???(???)',1,'02???12???(???)',1000,5,14),(8,'02???15???(???)',1,'02???12???(???)',3000,8,14),(9,'02???15???(???)',1,'02???12???(???)',7,3,14),(10,'02???15???(???)',1,'02???12???(???)',13,2,14),(11,'02???15???(???)',1,'02???12???(???)',200,1,14),(12,'02???15???(???)',1,'02???12???(???)',300,12,14),(13,'02???15???(???)',1,'02???12???(???)',10000,11,14),(14,'02???15???(???)',1,'02???12???(???)',9000,10,14),(15,'02???15???(???)',1,'02???12???(???)',2000,2,14),(16,'02???15???(???)',1,'02???12???(???)',1000,1,14),(17,'02???15???(???)',1,'02???12???(???)',3000,6,14),(18,'02???15???(???)',1,'02???12???(???)',2000,5,14),(19,'02???15???(???)',1,'02???12???(???)',2000,4,14),(20,'02???15???(???)',1,'02???12???(???)',2000,3,14),(21,'02???15???(???)',1,'02???12???(???)',1500,9,14),(22,'02???15???(???)',1,'02???12???(???)',300,7,14),(23,'02???15???(???)',1,'02???12???(???)',200,4,14),(24,'02???15???(???)',999,'02???12???(???)',1498500,9,14),(25,'02???15???(???)',999,'02???12???(???)',299700,7,14),(26,'02???15???(???)',999,'02???12???(???)',199800,4,14);
/*!40000 ALTER TABLE `orderHistories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processRecipe`
--

DROP TABLE IF EXISTS `processRecipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `processRecipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `delete_flag` int NOT NULL,
  `description` longtext,
  `speed` varchar(255) DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `time` double DEFAULT NULL,
  `utensil` varchar(255) DEFAULT NULL,
  `recipe` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg6xp0bvo2c2m2simpswno47k1` (`recipe`),
  CONSTRAINT `FKg6xp0bvo2c2m2simpswno47k1` FOREIGN KEY (`recipe`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processRecipe`
--

LOCK TABLES `processRecipe` WRITE;
/*!40000 ALTER TABLE `processRecipe` DISABLE KEYS */;
INSERT INTO `processRecipe` VALUES (1,0,'????????????????????????????????????????????????????????????',NULL,100,10,'??????',1),(2,0,'??????????????????????????????????????????????????????',NULL,NULL,NULL,NULL,1),(3,0,'?????????????????????????????????????????????????????????',NULL,130,7,'??????',1),(4,0,'??????????????????????????????????????????????????????36?????????????????????','2',40,5,'30?????????????????????',2),(5,0,'4???????????????????????????','4',36,4,'30?????????????????????',2),(6,0,'??????????????????????????????','3',36,3,'30?????????????????????',2),(7,0,'??????????????????????????????','2',36,3,'30?????????????????????',2),(8,0,'??????????????????????????????????????????????????????','1',36,3,'30?????????????????????',2),(9,0,'????????????????????????????????????????????????????????????????????????',NULL,NULL,NULL,NULL,2),(10,0,'????????????????????????????????????????????????????????????',NULL,80,15,'??????',2),(13,0,'??????????????????????????????????????????????????????????????????????????????????????????????????????',NULL,55,15,'??????',3),(14,0,'??????????????????????????????????????????????????????????????????????????????????????????',NULL,85,15,'??????',3),(15,0,'????????????????????????????????????????????????????????????????????????????????????????????????',NULL,NULL,NULL,NULL,3);
/*!40000 ALTER TABLE `processRecipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `delete_flag` int NOT NULL,
  `number` double NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,0,10,'???????????????????????????','????????????'),(2,0,5,'??????????????????','??????'),(3,0,10,'???????????????????????????','?????????'),(4,1,3,'a','????????????');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-22  9:46:48
