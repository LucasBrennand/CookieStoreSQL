-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cookiestore
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `bonus_cashback`
--

DROP TABLE IF EXISTS `bonus_cashback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bonus_cashback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bonus_total` double NOT NULL,
  `cashback_total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonus_cashback`
--

LOCK TABLES `bonus_cashback` WRITE;
/*!40000 ALTER TABLE `bonus_cashback` DISABLE KEYS */;
INSERT INTO `bonus_cashback` VALUES (1,0,0);
/*!40000 ALTER TABLE `bonus_cashback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `client_ID` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'John Smith','M',35,'May 4th'),(2,'Emily Johnson','F',28,'October 22nd'),(3,'Michael Davis','M',42,'March 3rd'),(4,'Sarah Brown','F',31,'August 12th'),(5,'David Wilson','M',45,'September 29th'),(6,'Jessica Martinez','F',25,'February 2nd'),(7,'Christopher Taylor','M',37,'November 19th'),(8,'Ashley Garcia','F',30,'June 25th'),(9,'Daniel Anderson','M',40,'July 3rd'),(10,'Samantha Martinez','F',29,'Auguest 11th'),(11,'James Rodriguez','M',33,'January 30th'),(12,'Amanda Clark','F',27,'May 18th'),(13,'Matthew Turner','M',39,'February 12th'),(14,'Jennifer Hernandez','F',32,'September 14th'),(15,'Ryan Lewis','M',36,'June 7th'),(16,'Lauren King','F',26,'March 24th'),(17,'Joshua White','M',41,'October 18th'),(18,'Megan Scott','F',28,'Abril 11th'),(19,'Kevin Lee','M',34,'July 28th'),(20,'Rachel Thompson','F',31,'November 5th'),(21,'Ethan Baker','M',37,'January 12th'),(22,'Olivia Wright','F',29,'March 3rd'),(23,'Alexander Green','M',45,'April 20th'),(24,'Ava Taylor','F',33,'June 8th'),(25,'Benjamin Harris','M',40,'July 15th'),(26,'Chloe Martinez','F',26,'August 28th'),(27,'William Clark','M',34,'October 7th'),(28,'Sophia Adams','F',30,'December 11th'),(29,'Lucas Martinez','M',39,'February 18th'),(30,'Emma Roberts','F',27,'April 1st'),(31,'Liam Johnson','M',32,'May 19th'),(32,'Mia Thompson','F',28,'July 25th'),(33,'Noah King','M',38,'September 9th'),(34,'Isabella Lee','F',24,'November 14th'),(35,'Jameson White','M',43,'January 21st'),(36,'Harper Scott','F',31,'March 7th'),(37,'Logan Miller','M',36,'May 23rd'),(38,'Avery Davis','F',25,'August 2nd'),(39,'Jackson Wilson','M',42,'October 30th'),(40,'Charlotte Garcia','F',35,'December 17th'),(41,'Elijah Moore','M',36,'February 14th'),(42,'Grace Robinson','F',32,'April 3rd'),(43,'Henry Hall','M',44,'May 10th'),(44,'Lily Martinez','F',28,'June 28th'),(45,'Andrew Turner','M',41,'August 5th'),(46,'Natalie Hernandez','F',30,'September 12th'),(47,'Jack Brown','M',35,'November 1st'),(48,'Emma Phillips','F',26,'December 20th'),(49,'Jacob Gonzalez','M',40,'January 25th'),(50,'Avery Hill','F',29,'March 9th'),(51,'Madison King','M',33,'April 15th'),(52,'Michaela Perez','F',27,'June 2nd'),(53,'William Martin','M',39,'July 20th'),(54,'Ella Wright','F',31,'August 30th'),(55,'David Adams','M',37,'October 12th'),(56,'Sophia Cooper','F',25,'December 1st'),(57,'Gabriel Rivera','M',38,'January 8th'),(58,'Hannah Mitchell','F',34,'February 22nd'),(59,'Matthew Scott','M',43,'April 7th'),(60,'Samantha White','F',28,'May 18th'),(61,'Nathan Allen','M',42,'June 24th'),(62,'Alyssa Nelson','F',29,'August 11th'),(63,'Christopher Carter','M',35,'September 29th'),(64,'Victoria Cook','F',30,'November 6th'),(65,'Dylan Ramirez','M',39,'December 25th'),(66,'Grace Parker','F',26,'February 3rd'),(67,'Evan Cooper','M',37,'March 17th'),(68,'Sophie Morris','F',31,'April 28th'),(69,'Samuel Stewart','M',44,'June 9th'),(70,'Hailey Bailey','F',28,'July 22nd'),(71,'Tyler Turner','M',36,'September 4th'),(72,'Lillian Foster','F',32,'October 15th'),(73,'Carter Collins','M',41,'November 30th'),(74,'Brooklyn Hayes','F',27,'January 17th'),(75,'Lucas Powell','M',38,'March 1st'),(76,'Makayla Simmons','F',33,'April 12th'),(77,'Daniel Price','M',40,'May 27th'),(78,'Avery Reed','F',25,'July 8th'),(79,'Jackson Hughes','M',43,'August 19th'),(80,'Emma Peterson','F',29,'October 2nd'),(81,'Connor Mitchell','M',34,'November 14th'),(82,'Aubrey Coleman','F',31,'December 27th'),(83,'Gabriel Bell','M',45,'February 5th'),(84,'Maya Cooper','F',28,'March 20th'),(85,'Justin Ward','M',39,'April 8th'),(86,'Leah Murphy','F',27,'May 19th'),(87,'Brandon Sanchez','M',38,'June 10th'),(88,'Savannah Gray','F',30,'July 31st'),(89,'Caleb Rivera','M',37,'September 15th'),(90,'Nora Foster','F',32,'October 26th'),(91,'Adam Coleman','M',36,'December 9th'),(92,'Ariana Reed','F',29,'January 22nd'),(93,'Ian Torres','M',43,'March 5th'),(94,'Brooke Diaz','F',26,'April 16th'),(95,'Colton Evans','M',40,'May 28th'),(96,'Evelyn Stewart','F',33,'July 9th'),(97,'Owen Murphy','M',35,'August 23rd'),(98,'Zoe Sanders','F',30,'October 4th'),(99,'Tristan Bell','M',44,'November 17th'),(100,'Madelyn Jenkins','F',28,'December 30th');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_ID` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Lucas Brennand','M','CEO',10000,'August 15th'),(2,'Adila Almeida','F','Manager',7000,'December 22nd'),(3,'Miguel Ribeiro','M','Janitor',3000,'August 8th'),(4,'Isabela Santiago','F','Vendor',5000,'June 7th'),(5,'Lucia Manta','F','Vendor',5000,'January 3rd'),(6,'Mia Santos','F','Chef',5500,'September 4th');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `most_sold_products`
--

DROP TABLE IF EXISTS `most_sold_products`;
/*!50001 DROP VIEW IF EXISTS `most_sold_products`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `most_sold_products` AS SELECT 
 1 AS `name`,
 1 AS `quantity_sold`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_ID` int NOT NULL,
  `product_Name` varchar(100) DEFAULT NULL,
  `description` text,
  `price` decimal(10,0) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Chocolate','Chocolate flavor',5,NULL),(2,'Vanilla','Vanilla flavor',5,NULL),(3,'Oreo','Oreo flavor',5,NULL),(4,'Red velvet','Red velvet flavor',7,NULL),(5,'Nutella','Nutella flavor',7,NULL),(6,'White chocolate','White chocolate flavor',5,NULL),(7,'Brownie','Brownie flavor',5,NULL),(8,'Mint','Mint flavor',7,NULL),(9,'Bitter Chocolate','Bitter Chocolate flavor',7,NULL),(10,'Ice Cream','Ice Cream flavor',7,NULL),(11,'Strawberry','Strawberry flavor',5,NULL),(12,'Gluten free','Gluten free flavor',10,NULL),(13,'Cream cheese','Cream cheese flavor',10,NULL),(14,'Peanut','Peanut flavor',5,NULL),(15,'Lemon','Lemon flavor',5,NULL),(16,'Oat','Oat flavor',7,NULL),(17,'Marshmallow','Marshmallow flavor',10,NULL),(18,'4 chocolates','4 chocolates flavor',10,NULL),(19,'Crystalized fruit','Crystalized fruit flavor',7,NULL),(20,'Peanutbutter','Peanutbutter flavor',5,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `client_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`sale_id`),
  KEY `employee_id` (`employee_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_ID`),
  CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `sales_per_employee`
--

DROP TABLE IF EXISTS `sales_per_employee`;
/*!50001 DROP VIEW IF EXISTS `sales_per_employee`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `sales_per_employee` AS SELECT 
 1 AS `name`,
 1 AS `total_sold`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `special_client`
--

DROP TABLE IF EXISTS `special_client`;
/*!50001 DROP VIEW IF EXISTS `special_client`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `special_client` AS SELECT 
 1 AS `client_name`,
 1 AS `cashback_total`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `special_clients`
--

DROP TABLE IF EXISTS `special_clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `cashback` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `special_clients_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_clients`
--

LOCK TABLES `special_clients` WRITE;
/*!40000 ALTER TABLE `special_clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_employees`
--

DROP TABLE IF EXISTS `special_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `bonus` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `special_employees_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_employees`
--

LOCK TABLES `special_employees` WRITE;
/*!40000 ALTER TABLE `special_employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `most_sold_products`
--

/*!50001 DROP VIEW IF EXISTS `most_sold_products`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `most_sold_products` AS select `p`.`product_Name` AS `name`,count(`s`.`sale_id`) AS `quantity_sold` from (`products` `p` join `sale` `s` on((`p`.`product_ID` = `s`.`sale_id`))) group by `p`.`product_ID`,`p`.`product_Name` order by `quantity_sold` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `sales_per_employee`
--

/*!50001 DROP VIEW IF EXISTS `sales_per_employee`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sales_per_employee` AS select `e`.`name` AS `name`,sum(`p`.`price`) AS `total_sold` from ((`employees` `e` join `sale` `s` on((`e`.`employee_ID` = `s`.`employee_id`))) join `products` `p` on((`s`.`sale_id` = `p`.`product_ID`))) group by `e`.`employee_ID`,`e`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `special_client`
--

/*!50001 DROP VIEW IF EXISTS `special_client`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `special_client` AS select `c`.`name` AS `client_name`,`sc`.`cashback` AS `cashback_total` from (`clients` `c` join `special_clients` `sc` on((`c`.`client_ID` = `sc`.`client_id`))) */;
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

-- Dump completed on 2024-06-10 16:44:59
