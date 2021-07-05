-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelbooking
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `BookingID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int NOT NULL,
  `RoomID` int DEFAULT NULL,
  `ArrivalDate` varchar(55) NOT NULL,
  `DepartureDate` varchar(55) NOT NULL,
  `RoomType` varchar(55) NOT NULL,
  `Status` varchar(55) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (`BookingID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `RoomID` (`RoomID`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`RoomID`) REFERENCES `roominfo` (`RoomID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (2,1,8,'2021-05-09','2021-05-10','Double','Checked-Out'),(4,2,7,'2021-05-11','2021-05-12','Twin','Checked-Out'),(5,3,1,'2021-05-12','2021-05-13','Single','Checked-Out'),(7,4,6,'2021-05-12','2021-05-13','Single','Checked-Out'),(8,5,8,'2021-05-12','2021-05-13','Double','Checked-Out');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Fullname` varchar(55) NOT NULL,
  `Address` varchar(55) NOT NULL,
  `CustomerType` varchar(55) NOT NULL,
  `PhoneNumber` varchar(55) NOT NULL,
  `Gender` varchar(55) NOT NULL,
  `Email` varchar(55) NOT NULL,
  `Password` varchar(55) NOT NULL,
  `CardNumber` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Sulav','ktm','Normal Customer','987131','Male','sulav@gmail.com','SUlav@123','1235456'),(2,'Saroj','ktm','Corporate Customer','98456131','Male','saroj@gmail.com','SAroj@123','123545'),(3,'Sandip','ktm','Normal Customer','986262323','Male','sandip@gmail.com','SAndip@123','123532'),(4,'Bikash','ktm','Normal Customer','9896626','Male','bikash@gmail.com','Bikash@123','123545'),(5,'Sudha','ktm','Corporate Customer','32326665','Female','sudha@gmail.com','Sudha@123','123665');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `ReceiptID` int NOT NULL AUTO_INCREMENT,
  `BookingID` int NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  `ServiceCharge` decimal(10,2) NOT NULL,
  `VATCharge` decimal(10,2) NOT NULL,
  `PaymentStatus` varchar(55) DEFAULT 'Unclear',
  `GrandTotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ReceiptID`),
  KEY `BookingID` (`BookingID`),
  CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`BookingID`) REFERENCES `booking` (`BookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (6,2,3750.00,375.00,487.50,'Clear',4612.50),(7,4,8250.00,825.00,1072.50,'Credit',10147.50),(8,7,2600.00,260.00,338.00,'Cleared',3198.00),(9,7,2600.00,260.00,338.00,'Cleared',3198.00),(10,8,3250.00,325.00,422.50,'Cleared',3997.50);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roominfo`
--

DROP TABLE IF EXISTS `roominfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roominfo` (
  `RoomID` int NOT NULL AUTO_INCREMENT,
  `RoomType` varchar(55) NOT NULL,
  `RoomNo` varchar(55) NOT NULL,
  `RoomPrice` int NOT NULL,
  `RoomStatus` varchar(55) NOT NULL,
  PRIMARY KEY (`RoomID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roominfo`
--

LOCK TABLES `roominfo` WRITE;
/*!40000 ALTER TABLE `roominfo` DISABLE KEYS */;
INSERT INTO `roominfo` VALUES (1,'Single','203',2000,'Vacant'),(2,'Double','208',3000,'Occupied'),(3,'Twin','206',4000,'Vacant'),(4,'Double','302',3000,'Vacant'),(5,'Twin','306',4000,'Occupied'),(6,'Single','310',2000,'Vacant'),(7,'Twin','404',4000,'Vacant'),(8,'Double','407',3000,'Vacant'),(9,'Single','405',2000,'Occupied');
/*!40000 ALTER TABLE `roominfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `ServiceID` int NOT NULL AUTO_INCREMENT,
  `BookingID` int NOT NULL,
  `ServiceType` varchar(55) NOT NULL,
  `Quantity` int NOT NULL,
  `Rate` decimal(10,2) NOT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ServiceID`),
  KEY `BookingID` (`BookingID`),
  CONSTRAINT `service_ibfk_1` FOREIGN KEY (`BookingID`) REFERENCES `booking` (`BookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,2,'Restaurant Item',3,200.00,600.00),(2,2,'Laundary Items',5,30.00,150.00),(3,4,'Restaurant Item',20,200.00,4000.00),(4,4,'Laundary Items',5,50.00,250.00),(5,7,'Bar Item',3,200.00,600.00),(6,8,'Laundary Items',5,50.00,250.00);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-13 23:53:36
