-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: storehouse_db
-- ------------------------------------------------------
-- Server version	5.7.34-log

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

--
-- Table structure for table `entry_receipt`
--

DROP TABLE IF EXISTS `entry_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entry_receipt` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date_of_entry` datetime NOT NULL,
  `description` varchar(45) NOT NULL,
  `recipient` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_receipt`
--

LOCK TABLES `entry_receipt` WRITE;
/*!40000 ALTER TABLE `entry_receipt` DISABLE KEYS */;
INSERT INTO `entry_receipt` VALUES (1,'2021-07-22 00:00:00','Olive oil Order','Pete'),(3,'2021-07-21 00:00:00','Potatoes Order','Bob'),(4,'2021-07-23 00:00:00','Potatoes Order 2','Pete'),(5,'2021-07-23 00:00:00','Tomatoes Order','Pete'),(6,'2021-07-24 00:00:00','Ouzo Order','Petros'),(15,'2021-09-03 00:00:00','test','test');
/*!40000 ALTER TABLE `entry_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry_registration`
--

DROP TABLE IF EXISTS `entry_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entry_registration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shelf_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `quantity` double NOT NULL,
  `entry_receipt_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entry_registration_product_id_idx` (`product_id`),
  KEY `fk_entry_registration_shelf_id_idx` (`shelf_id`),
  KEY `fk_entry_registration_exit_receipt_id_idx` (`entry_receipt_id`),
  CONSTRAINT `fk_entry_registration_entry_receipt_id` FOREIGN KEY (`entry_receipt_id`) REFERENCES `entry_receipt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entry_registration_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entry_registration_shelf_id` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_registration`
--

LOCK TABLES `entry_registration` WRITE;
/*!40000 ALTER TABLE `entry_registration` DISABLE KEYS */;
INSERT INTO `entry_registration` VALUES (1,4,3,5,1),(2,3,3,13,1),(5,1,2,20,3),(6,4,2,25,3),(7,3,2,10,4),(8,4,2,8,4),(9,3,1,8,5),(10,5,1,10,5),(11,8,5,10,6),(12,9,5,15,6);
/*!40000 ALTER TABLE `entry_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exit_receipt`
--

DROP TABLE IF EXISTS `exit_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exit_receipt` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date_of_exit` datetime NOT NULL,
  `reason_for_exit` varchar(45) NOT NULL,
  `sender` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exit_receipt`
--

LOCK TABLES `exit_receipt` WRITE;
/*!40000 ALTER TABLE `exit_receipt` DISABLE KEYS */;
INSERT INTO `exit_receipt` VALUES (8,'2021-09-03 00:00:00','Test','Bob'),(9,'2021-09-03 00:00:00','test','test');
/*!40000 ALTER TABLE `exit_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exit_registration`
--

DROP TABLE IF EXISTS `exit_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exit_registration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shelf_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `quantity` double NOT NULL,
  `exit_receipt_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exit_registration_product_id_idx` (`product_id`),
  KEY `fk_exit_registration_shelft_id_idx` (`shelf_id`),
  KEY `fk_exit_registration_exit_receipt_id_idx` (`exit_receipt_id`),
  CONSTRAINT `fk_exit_registration_exit_receipt_id` FOREIGN KEY (`exit_receipt_id`) REFERENCES `exit_receipt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exit_registration_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exit_registration_shelft_id` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exit_registration`
--

LOCK TABLES `exit_registration` WRITE;
/*!40000 ALTER TABLE `exit_registration` DISABLE KEYS */;
INSERT INTO `exit_registration` VALUES (11,3,2,3,8);
/*!40000 ALTER TABLE `exit_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `barcode` varchar(45) NOT NULL,
  `units` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Tomatoes','10001','kg'),(2,'Potatoes','10002','kg'),(3,'Olive oil','20001','lt'),(5,'Ouzo Plomari','30001','lt');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_shelf`
--

DROP TABLE IF EXISTS `product_shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_shelf` (
  `product_id` bigint(20) unsigned NOT NULL,
  `shelf_id` bigint(20) unsigned NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`product_id`,`shelf_id`),
  KEY `FK_pruduct_shelf_shelf_id_idx` (`shelf_id`),
  CONSTRAINT `FK_pruduct_shelf_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pruduct_shelf_shelf_id` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_shelf`
--

LOCK TABLES `product_shelf` WRITE;
/*!40000 ALTER TABLE `product_shelf` DISABLE KEYS */;
INSERT INTO `product_shelf` VALUES (1,3,8),(1,5,10),(2,1,20),(2,3,7),(2,4,33),(3,3,13),(3,4,5),(5,8,10),(5,9,15);
/*!40000 ALTER TABLE `product_shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelf`
--

DROP TABLE IF EXISTS `shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shelf` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `identifier` varchar(45) NOT NULL,
  `storehouse_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifier_UNIQUE` (`identifier`),
  KEY `FK_storehouse_id_idx` (`storehouse_id`),
  CONSTRAINT `FK_shelf_storehouse_id` FOREIGN KEY (`storehouse_id`) REFERENCES `storehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelf`
--

LOCK TABLES `shelf` WRITE;
/*!40000 ALTER TABLE `shelf` DISABLE KEYS */;
INSERT INTO `shelf` VALUES (1,'Cre_1',1),(2,'Cre_2',1),(3,'Pir_1',2),(4,'Pir_2',2),(5,'Pir_3',2),(8,'Kit_1',4),(9,'Kyt_2',4);
/*!40000 ALTER TABLE `shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storehouse`
--

DROP TABLE IF EXISTS `storehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storehouse` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storehouse`
--

LOCK TABLES `storehouse` WRITE;
/*!40000 ALTER TABLE `storehouse` DISABLE KEYS */;
INSERT INTO `storehouse` VALUES (1,'Storehouse at Crete'),(2,'Big storehouse at Piraeus'),(4,'Storehouse at Kythira');
/*!40000 ALTER TABLE `storehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-03 15:31:39
