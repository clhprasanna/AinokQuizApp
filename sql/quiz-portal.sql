create database quizportal;
use quizportal;
Drop table if exists `user`;
CREATE TABLE `user` (                                   
          `idUser` int(11) NOT NULL AUTO_INCREMENT,             
          `firstName` varchar(25) DEFAULT NULL,                 
          `lastName` varchar(25) DEFAULT NULL,                  
          `emailId` varchar(25) NOT NULL,                       
          `age` int(3) DEFAULT NULL,                            
          `gender` char(1) NOT NULL,                            
          `difficultyLevel` smallint(1) DEFAULT NULL,           
          `noOfQuestions` int(4) DEFAULT NULL,                  
          `userName` varchar(255) DEFAULT NULL,                 
          PRIMARY KEY (`idUser`),                               
          KEY `emailId` (`emailId`)                             
        ) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

Drop table if exists `exam`;
CREATE TABLE `exam` (                                                           
          `idExam` int(11) NOT NULL AUTO_INCREMENT,                                     
          `userId` int(11) DEFAULT NULL,                                                
          PRIMARY KEY (`idExam`),                                                       
          KEY `userId` (`userId`),                                                      
          CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`idUser`)  
        ) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 ;
Drop table if exists `question`;
CREATE TABLE `question` (                               
            `idQuestion` int(11) NOT NULL AUTO_INCREMENT,         
            `question` varchar(500) DEFAULT NULL,                 
            `difficultylevel` int(1) DEFAULT NULL,                
            `count` int(4) DEFAULT 0,                          
            PRIMARY KEY (`idQuestion`)                            
          ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

Drop table if exists `questionchoice`;
CREATE TABLE `questionchoice` (                                                                       
                  `idChoice` int(11) NOT NULL AUTO_INCREMENT,                                                         
                  `choice` varchar(255) DEFAULT NULL,                                                                 
                  `questionId` int(11) DEFAULT NULL,                                                                  
                  `isRightChoice` int(1) DEFAULT NULL,                                                                
                  PRIMARY KEY (`idChoice`),                                                                           
                  KEY `questionId` (`questionId`),                                                                    
                  CONSTRAINT `questionchoice_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `question` (`idQuestion`)  
                ) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
 
Drop table if exists `questionanswer`;
CREATE TABLE `questionanswer` (                                                                                
                  `idQuestionAnswer` int(11) NOT NULL AUTO_INCREMENT,                                                          
                  `isRightAnswer` tinyint(1) DEFAULT NULL,                                                                     
                  `choiceid` int(11) DEFAULT NULL,                                                                             
                  `examid` int(11) DEFAULT NULL,                                                                               
                  `questionId` int(11) DEFAULT NULL,                                                                           
                  `userId` varchar(255) DEFAULT NULL,                                                                          
                  PRIMARY KEY (`idQuestionAnswer`),                                                                            
                  KEY `FK_rqjxi5dfjp41iew17q852x3pu` (`choiceid`),                                                             
                  KEY `FK_c68td9688xkdp7v3yxli9hsq3` (`examid`),                                                               
                  KEY `FK_jclflay2cwfp3krvjapauld5` (`questionId`),                                                            
                  CONSTRAINT `FK_jclflay2cwfp3krvjapauld5` FOREIGN KEY (`questionId`) REFERENCES `question` (`idQuestion`),    
                  CONSTRAINT `FK_c68td9688xkdp7v3yxli9hsq3` FOREIGN KEY (`examid`) REFERENCES `exam` (`idExam`),               
                  CONSTRAINT `FK_rqjxi5dfjp41iew17q852x3pu` FOREIGN KEY (`choiceid`) REFERENCES `questionchoice` (`idChoice`)  
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* insert query for Questions table (25 questions) */
insert  into `question`(`idQuestion`,`question`,`difficultylevel`,`count`) values (1,'Which of the following is not a method of the java.lang.Object class',0,0),(2,'how many methods needs to be implemented by the class which implements the serilizable interface',0,0),(3,'What is byte code in the context of Java',0,0),(4,'What is garbage collection',0,0),(5,'If a thread is to be declared as a daemon thread, it must be declared before',0,0),(6,'Which of these keyword can be used in subclass to call the constructor of superclass',1,0),(7,'What is the process of defining a method in subclass having same name & type signature as a method in its superclass?',1,0),(8,'Which of these keywords can be used to prevent Method overriding?',1,0),(9,'Which of these method of class String is used to compare two String objects for their equality?',1,0),(10,'Which of these methods is used to compare a specific region inside a string with another specific region in another string?',1,0),(11,'Which of these is an interface for control over serialization and deserialization?',2,0),(12,'Which of these access specifiers can be used for an interface?',2,0),(13,'Which of these class is used to make a thread?',2,0),(14,'Which of these interface is implemented by Thread class',2,0),(15,'Which of these method of Thread class is used to Suspend a thread for a period of time?',2,0),(16,' Which function of pre defined class Thread is used to check weather current thread being checked is still running?',3,0),(17,'Which of these keywords is not a part of exception handling?',3,0),(18,'Which of these keywords is used to manually throw an exception?',3,0),(19,'Which of these methods can be used to obtain set of all keys in a map?',3,0),(20,'Which of these method is used add an element and corresponding key to a map?',3,0),(21,'Which of these package contains classes and interfaces for networking?',4,0),(22,'Which of these is a protocol for breaking and sending packets to an address across a network?',4,0),(23,'How many ports of TCP/IP are reserved for specific protocols?',4,0),(24,'How many bits are in a single IP address?',4,0),(25,'Which of these is a full form of DNS?',4,0);


/* insert query for QuestionChoice table */
insert  into `questionchoice`(`idChoice`,`choice`,`questionId`,`isRightChoice`) values (1,'equals()',1,0),(2,'hashcode()',1,0),(3,'compare()',1,1),(4,'wait()',1,0),(5,'1',2,0),(6,'2',2,0),(7,'3',2,0),(8,'0',2,1),(9,'The type of code generated by a Java compiler',3,1),(10,'The type of code generated by a Java Virtual Machine',3,0),(11,'It is another name for a Java source file',3,0),(12,'It is the code written within the instance methods of a class',3,0),(13,'The operating system periodically deletes all of the java files available on the system',4,0),(14,'Any package imported in a program and not used is automatically deleted',4,0),(15,'When all references to an object are gone, the memory used by the object is automatically reclaimed',4,1),(16,'The JVM checks the output of any Java program and deletes anything that doesn\'t make sense',4,0),(17,'start method',5,1),(18,'run method',5,0),(19,'stop method',5,0),(20,'none',5,0),(21,'super',6,1),(22,'this',6,0),(23,'extent',6,0),(24,'extends',6,0),(25,'Method overloading',7,0),(26,'Method overriding',7,1),(27,'Method hiding',7,0),(28,'None of the mentioned',7,0),(29,'static',8,0),(30,'constant',8,0),(31,'protected',8,0),(32,'final',8,1),(33,'equals()',9,1),(34,'Equals()',9,0),(35,'isequal()',9,0),(36,'Isequal()',9,0),(37,'regionMatch()',10,0),(38,'match()',10,0),(39,'RegionMatches()',10,0),(40,'regionMatches()',10,1),(41,'Serializable',11,0),(42,'Externalizable',11,1),(43,'FileFilter',11,0),(44,'ObjectInput',11,0),(45,'Public',12,1),(46,'Protected',12,0),(47,'private',12,0),(48,'All of the mentioned',12,0),(49,'String',13,0),(50,'System',13,0),(51,'Thread',13,1),(52,'Runnable',13,0),(53,'Runnable',14,1),(54,'Connections',14,0),(55,'Set',14,0),(56,'MapConnections',14,0),(57,'sleep()',15,1),(58,'terminate()',15,0),(59,'suspend()',15,0),(60,'stop()',15,0),(61,'isAlive()',16,1),(62,'Join()',16,0),(63,'isRunning()',16,0),(64,'Alive()',16,0),(65,'try',17,0),(66,'finally',17,0),(67,'thrown',17,1),(68,'catch',17,0),(69,'try',18,1),(70,'finally',18,0),(71,'throw',18,0),(72,'catch',18,0),(73,'getAll()',19,0),(74,'getKeys()',19,0),(75,'keyall()',19,0),(76,'keySet()',19,1),(77,'put()',20,1),(78,'set()',20,0),(79,'redo()',20,0),(80,'add()',20,0),(81,'java.io',21,0),(82,'java.util',21,0),(83,'java.net',21,1),(84,'java.network',21,0),(85,'TCIP/IP',22,1),(86,'DNS',22,0),(87,'Socket',22,0),(88,'Proxy Server',22,0),(89,'10',23,0),(90,'1024',23,1),(91,'2048',23,0),(92,'512',23,0),(93,'8',24,0),(94,'16',24,0),(95,'32',24,1),(96,'64',24,0),(97,'Data Network Service',25,0),(98,'Data Name Service',25,0),(99,'Domain Network Service',25,0),(100,'Domian Name Service',25,1);
