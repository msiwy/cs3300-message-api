DROP TABLE IF EXISTS `Greeting`, `User`, `GroupParticipant`, `Message`, `Group`;

CREATE TABLE `Greeting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Group` (
  `groupId` int(11) NOT NULL,
  `groupname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  UNIQUE KEY `groupId_UNIQUE` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `GroupParticipant` (
  `gpId` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`gpId`),
  UNIQUE KEY `gpId_UNIQUE` (`gpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `User` (
  `userId` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

