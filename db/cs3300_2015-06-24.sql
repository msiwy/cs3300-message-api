# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: aaltyqzbel93he.c1jgqriefhdd.us-east-1.rds.amazonaws.com (MySQL 5.6.23-log)
# Database: cs3300
# Generation Time: 2015-06-24 07:13:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Group`;

CREATE TABLE `Group` (
  `groupId` int(11) NOT NULL,
  `groupname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  UNIQUE KEY `groupId_UNIQUE` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;

INSERT INTO `Group` (`groupId`, `groupname`)
VALUES
	(1502940243,'Chatroom');

/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table GroupParticipant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `GroupParticipant`;

CREATE TABLE `GroupParticipant` (
  `gpId` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`gpId`),
  UNIQUE KEY `gpId_UNIQUE` (`gpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `GroupParticipant` WRITE;
/*!40000 ALTER TABLE `GroupParticipant` DISABLE KEYS */;

INSERT INTO `GroupParticipant` (`gpId`, `groupId`, `userId`)
VALUES
	(1,1502940243,82025895),
	(2,1502940243,82025894);

/*!40000 ALTER TABLE `GroupParticipant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` int(11) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT NULL,
  `groupId` int(11) NOT NULL,
  `documentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`messageId`),
  UNIQUE KEY `idMessage_UNIQUE` (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;

INSERT INTO `Message` (`messageId`, `senderId`, `dateCreated`, `content`, `groupId`, `documentId`)
VALUES
	(1,82025894,'2015-06-24 07:06:49','\"Hello Message!\"',1502940243,-1);

/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `userId` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`userId`, `username`)
VALUES
	(82025894,'User1'),
	(82025895,'User2');

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
