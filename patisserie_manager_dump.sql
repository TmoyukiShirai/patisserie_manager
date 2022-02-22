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
INSERT INTO `allergy_item` VALUES (1,'卵'),(2,'乳'),(3,'小麦'),(4,'そば'),(5,'落花生'),(6,'エビ'),(7,'カニ'),(8,'いくら'),(9,'鮭'),(10,'鯖'),(11,'いか'),(12,'オレンジ'),(13,'キウイ'),(14,'バナナ'),(15,'もも'),(16,'りんご'),(17,'アーモンド'),(18,'カシューナッツ'),(19,'くるみ'),(20,'ごま'),(21,'大豆'),(22,'山芋'),(23,'椎茸'),(24,'ゼラチン'),(25,'鶏肉'),(26,'豚肉'),(27,'牛肉');
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
INSERT INTO `employees` VALUES (14,1,'A002','2021-01-01 00:00:00.000000',0,'白井友之','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2021-01-02 00:00:00.000000'),(15,1,'A001','2022-01-14 11:48:39.578325',1,'ç½äºåä¹','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-14 12:06:25.721329'),(16,1,'A003','2022-01-14 12:06:59.882701',0,'白井友之','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-14 12:06:59.882701'),(17,0,'A004','2022-01-15 09:59:06.852182',0,'白井裕梨','7C8C502348817AB52EA4703C8D8C90239F9E79993CA771AD727FC292D63DE8FC','2022-01-15 09:59:06.852182');
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
INSERT INTO `equipments` VALUES (1,1,'本',0,'サンリツ','ホイッパー',1000,'カリョー'),(2,1,'本',0,'サンリツ','パレットナイフ',2000,'カリョー'),(3,5000,'ml',0,'ライオン','ブリーチ',2000,'永野物産'),(4,5000,'ml',0,'ライオン','中性洗剤',2000,'永野物産'),(5,100,'枚',0,'サンリツ','ダスター',2000,'渡辺商店'),(6,5000,'ml',0,'サンリツ','アルコール',3000,'渡辺商店');
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
INSERT INTO `ingredients` VALUES (1,600,'g',0,151,'山内農場','全卵',200,'福山八百屋'),(2,40,'g',0,47.1,'山内農場','卵白',13,'福山八百屋'),(3,20,'g',0,386.9,'山内農場','卵黄',7,'福山八百屋'),(4,1000,'ml',0,66.9,'タカナシ乳業','牛乳',200,'タカナシ乳業'),(5,5000,'g',0,368,'日本製粉','小麦粉',1000,'カリョー'),(6,1000,'g',0,359,'日本製粉','蕎麦粉',400,'カリョー'),(7,450,'g',0,745,'タカナシ乳業','バター',300,'タカナシ乳業'),(8,10000,'g',0,386.9,'日本製粉','グラニュー糖',3000,'福山八百屋'),(9,1000,'ml',0,433.1,'タカナシ乳業','生クリーム46%',1500,'タカナシ乳業'),(10,5000,'g',0,587.9,'山内貿易','チョコレート',9000,'永野物産'),(11,100,'本',0,298,'山内貿易','バニラビーンズ',10000,'永野物産'),(12,180,'g',0,294,'その他','蜂蜜',300,'永野物産'),(13,5000,'g',0,384.1,'日本製粉','上白糖',1000,'カリョー'),(14,3,'g',1,3,'山内貿易','オレンジピューレ',3,'カリョー'),(15,NULL,'g',1,NULL,NULL,'aa',NULL,NULL);
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
INSERT INTO `joinIngredient` VALUES (3,1000,'ml',4,1),(4,200,'g',3,1),(5,200,'g',8,1),(6,100,'g',5,1),(7,1,'本',11,1),(8,218,'g',1,2),(9,100,'g',13,2),(10,7,'g',12,2),(11,90,'g',5,2),(12,27,'g',7,2),(13,10,'g',9,2),(14,450,'ml',4,3),(15,0.5,'本',11,3),(16,100,'g',3,3),(17,67.5,'g',8,3),(18,337.5,'g',10,3),(19,168,'g',7,3),(20,67.5,'g',9,3);
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
INSERT INTO `JoinOrderHistories` VALUES (1,'02月12日(土)','タカナシ乳業',14),(2,'02月12日(土)','カリョー',14),(3,'02月12日(土)','福山八百屋',14),(4,'02月12日(土)','永野物産',14),(5,'02月12日(土)','カリョー',14),(6,'02月12日(土)','渡辺商店',14),(7,'02月12日(土)','永野物産',14),(8,'02月12日(土)','タカナシ乳業',14),(9,'02月12日(土)','タカナシ乳業',14);
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
INSERT INTO `orderHistories` VALUES (1,'02月15日(火)',1,'02月12日(土)',1500,9,14),(2,'02月15日(火)',1,'02月12日(土)',300,7,14),(3,'02月15日(火)',1,'02月12日(土)',200,4,14),(4,'02月15日(火)',1,'02月12日(土)',3,14,14),(5,'02月15日(火)',1,'02月12日(土)',1000,13,14),(6,'02月15日(火)',1,'02月12日(土)',400,6,14),(7,'02月15日(火)',1,'02月12日(土)',1000,5,14),(8,'02月15日(火)',1,'02月12日(土)',3000,8,14),(9,'02月15日(火)',1,'02月12日(土)',7,3,14),(10,'02月15日(火)',1,'02月12日(土)',13,2,14),(11,'02月15日(火)',1,'02月12日(土)',200,1,14),(12,'02月15日(火)',1,'02月12日(土)',300,12,14),(13,'02月15日(火)',1,'02月12日(土)',10000,11,14),(14,'02月15日(火)',1,'02月12日(土)',9000,10,14),(15,'02月15日(火)',1,'02月12日(土)',2000,2,14),(16,'02月15日(火)',1,'02月12日(土)',1000,1,14),(17,'02月15日(火)',1,'02月12日(土)',3000,6,14),(18,'02月15日(火)',1,'02月12日(土)',2000,5,14),(19,'02月15日(火)',1,'02月12日(土)',2000,4,14),(20,'02月15日(火)',1,'02月12日(土)',2000,3,14),(21,'02月15日(火)',1,'02月12日(土)',1500,9,14),(22,'02月15日(火)',1,'02月12日(土)',300,7,14),(23,'02月15日(火)',1,'02月12日(土)',200,4,14),(24,'02月15日(火)',999,'02月12日(土)',1498500,9,14),(25,'02月15日(火)',999,'02月12日(土)',299700,7,14),(26,'02月15日(火)',999,'02月12日(土)',199800,4,14);
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
INSERT INTO `processRecipe` VALUES (1,0,'牛乳にバニラビーンズを入れて沸騰させる。',NULL,100,10,'銅鍋',1),(2,0,'卵黄とグラニュー糖をよくかき混ぜる。',NULL,NULL,NULL,NULL,1),(3,0,'牛乳と卵黄を合わせ、手早く炊き上げる。',NULL,130,7,'銅鍋',1),(4,0,'全卵・上白糖・蜂蜜をミキサーに入れ、36℃まで温める。','2',40,5,'30コートミキサー',2),(5,0,'4速で４分泡立てる。','4',36,4,'30コートミキサー',2),(6,0,'３速で３分泡立てる。','3',36,3,'30コートミキサー',2),(7,0,'２速で３分泡立てる。','2',36,3,'30コートミキサー',2),(8,0,'１速で荒い気泡を潰し、キメを整える。','1',36,3,'30コートミキサー',2),(9,0,'粉を合わせ、グルテンが形成されるまでよく混ぜる。',NULL,NULL,NULL,NULL,2),(10,0,'熱をつけたバターと生クリームを合わせる。',NULL,80,15,'手鍋',2),(13,0,'チョコレート・バター・生クリームを温めて乳化させ、ガナッシュを作る。',NULL,55,15,'手鍋',3),(14,0,'牛乳・バニラ・卵黄・グラニュー糖でアングレーズベースを炊く。',NULL,85,15,'銅鍋',3),(15,0,'ガナッシュとアングレーズベースを合わせ、ブレンダーで乳化させる。',NULL,NULL,NULL,NULL,3);
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
INSERT INTO `recipes` VALUES (1,0,10,'カスタードクリーム','クリーム'),(2,0,5,'ジェノワーズ','生地'),(3,0,10,'テリーヌ・ショコラ','その他'),(4,1,3,'a','クリーム');
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
