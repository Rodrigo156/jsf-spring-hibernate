CREATE DATABASE  IF NOT EXISTS `jsf_example` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jsf_example`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: jsf_example
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_permissions`
--

DROP TABLE IF EXISTS `auth_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `auth_role_permissions_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_name_UNIQUE` (`permission_name`),
  KEY `auth_role_permissions_category_id` (`auth_role_permissions_category_id`),
  CONSTRAINT `auth_role_permissions_category_id` FOREIGN KEY (`auth_role_permissions_category_id`) REFERENCES `auth_role_permissions_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permissions`
--

LOCK TABLES `auth_permissions` WRITE;
/*!40000 ALTER TABLE `auth_permissions` DISABLE KEYS */;
INSERT INTO `auth_permissions` VALUES (1,'admin:write',NULL,4);
/*!40000 ALTER TABLE `auth_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role_permission`
--

DROP TABLE IF EXISTS `auth_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_roles_id` int(11) NOT NULL,
  `auth_permissions_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_permission_user_roles_idx` (`auth_roles_id`),
  KEY `fk_auth_role_permission_auth_permissions1_idx` (`auth_permissions_id`),
  CONSTRAINT `fk_auth_role_permission_auth_permissions1` FOREIGN KEY (`auth_permissions_id`) REFERENCES `auth_permissions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_permission_user_roles` FOREIGN KEY (`auth_roles_id`) REFERENCES `auth_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role_permission`
--

LOCK TABLES `auth_role_permission` WRITE;
/*!40000 ALTER TABLE `auth_role_permission` DISABLE KEYS */;
INSERT INTO `auth_role_permission` VALUES (1,1,1);
/*!40000 ALTER TABLE `auth_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role_permissions_category`
--

DROP TABLE IF EXISTS `auth_role_permissions_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_permissions_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role_permissions_category`
--

LOCK TABLES `auth_role_permissions_category` WRITE;
/*!40000 ALTER TABLE `auth_role_permissions_category` DISABLE KEYS */;
INSERT INTO `auth_role_permissions_category` VALUES (4,'ADMIN'),(3,'EMPLOYEE');
/*!40000 ALTER TABLE `auth_role_permissions_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_roles`
--

DROP TABLE IF EXISTS `auth_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_roles`
--

LOCK TABLES `auth_roles` WRITE;
/*!40000 ALTER TABLE `auth_roles` DISABLE KEYS */;
INSERT INTO `auth_roles` VALUES (1,'ADMIN'),(2,'EMPLOYEE');
/*!40000 ALTER TABLE `auth_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password_expiry` datetime NOT NULL,
  `user_locked` bit(1) DEFAULT NULL,
  `user_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'siposn','$2a$10$.YFSB9129kYlW9V5Hbs2Su3xwbSYIaAiSQutA/zugmYNko1cTOO1O','Norbert','Sipos','t@t.hu','2014-12-29 00:00:00','\0',''),(2,'test','$2a$10$yLpC57unQ/acWsGol7DLfe5cpRMEkvQq0OjwQksB.qyuiOJ5PmjUi','User','Test','test@test.com','2015-01-10 11:47:37','\0','');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_roles`
--

DROP TABLE IF EXISTS `auth_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_user_id` int(11) NOT NULL,
  `auth_roles_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_auth_user_roles_auth_user1_idx` (`auth_user_id`),
  KEY `fk_auth_user_roles_auth_roles1_idx` (`auth_roles_id`),
  CONSTRAINT `fk_auth_user_roles_auth_roles1` FOREIGN KEY (`auth_roles_id`) REFERENCES `auth_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auth_user_roles_auth_user1` FOREIGN KEY (`auth_user_id`) REFERENCES `auth_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_roles`
--

LOCK TABLES `auth_user_roles` WRITE;
/*!40000 ALTER TABLE `auth_user_roles` DISABLE KEYS */;
INSERT INTO `auth_user_roles` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `auth_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event`
--

DROP TABLE IF EXISTS `logging_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logging_event` (
  `timestmp` mediumtext NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(255) NOT NULL,
  `level_string` varchar(255) NOT NULL,
  `thread_name` varchar(255) NOT NULL,
  `reference_flag` smallint(6) NOT NULL,
  `caller_filename` varchar(255) NOT NULL,
  `arg0` varchar(255) DEFAULT NULL,
  `arg1` varchar(255) DEFAULT NULL,
  `arg2` varchar(255) DEFAULT NULL,
  `arg3` varchar(255) DEFAULT NULL,
  `caller_class` varchar(255) NOT NULL,
  `caller_method` varchar(255) NOT NULL,
  `caller_line` varchar(255) NOT NULL,
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event`
--

LOCK TABLES `logging_event` WRITE;
/*!40000 ALTER TABLE `logging_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event_exception`
--

DROP TABLE IF EXISTS `logging_event_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logging_event_exception` (
  `event_id` int(11) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event_exception`
--

LOCK TABLES `logging_event_exception` WRITE;
/*!40000 ALTER TABLE `logging_event_exception` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_event_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event_property`
--

DROP TABLE IF EXISTS `logging_event_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logging_event_property` (
  `event_id` int(11) NOT NULL,
  `mapped_key` varchar(255) NOT NULL,
  `mapped_value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event_property`
--

LOCK TABLES `logging_event_property` WRITE;
/*!40000 ALTER TABLE `logging_event_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_event_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password_complexity` varchar(200) DEFAULT NULL,
  `password_expiry` int(11) DEFAULT NULL,
  `session_timeout` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

LOCK TABLES `meta` WRITE;
/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` VALUES (2,'[a-zA-Z_0-9.\\!\\#*\\\\$\\\\@\\\\+\\\\-\\\\%]{6,32}',180,15);
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-14 11:49:39
