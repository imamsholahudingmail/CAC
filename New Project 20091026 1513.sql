-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.17-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_career_asp
--

CREATE DATABASE IF NOT EXISTS db_career_asp;
USE db_career_asp;

--
-- Definition of table `tbl_cand_education`
--

DROP TABLE IF EXISTS `tbl_cand_education`;
CREATE TABLE `tbl_cand_education` (
  `Cand_Code` int(10) unsigned NOT NULL auto_increment,
  `Higer_Edu` varchar(45) default NULL,
  `University` varchar(45) default NULL,
  `Division` varchar(45) default NULL,
  `Percentage` int(3) default NULL,
  PRIMARY KEY  (`Cand_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_cand_education`
--

/*!40000 ALTER TABLE `tbl_cand_education` DISABLE KEYS */;
INSERT INTO `tbl_cand_education` (`Cand_Code`,`Higer_Edu`,`University`,`Division`,`Percentage`) VALUES 
 (1,'MCA','BU','first',78),
 (2,'MCA','BU','first',80),
 (3,'MCA','BU','first',80);
/*!40000 ALTER TABLE `tbl_cand_education` ENABLE KEYS */;


--
-- Definition of table `tbl_cand_general`
--

DROP TABLE IF EXISTS `tbl_cand_general`;
CREATE TABLE `tbl_cand_general` (
  `Cand_Code` int(10) unsigned NOT NULL auto_increment,
  `Cell_No` varchar(12) default NULL,
  `Home_Phone` varchar(12) default NULL,
  `Gender` varchar(45) default NULL,
  `Address` varchar(45) default NULL,
  `DOB` varchar(45) default NULL,
  `Passport_No` varchar(45) default NULL,
  `Email_Id` varchar(45) default NULL,
  PRIMARY KEY  (`Cand_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_cand_general`
--

/*!40000 ALTER TABLE `tbl_cand_general` DISABLE KEYS */;
INSERT INTO `tbl_cand_general` (`Cand_Code`,`Cell_No`,`Home_Phone`,`Gender`,`Address`,`DOB`,`Passport_No`,`Email_Id`) VALUES 
 (1,'12345','6789','F','Vijaynagar,Blore','1987-1-1','ab444bn','shrutihirur6@yahoo.com'),
 (2,'90012345','23456','M','Bharatngr','1950-1-1','',''),
 (3,'12345666','78787','M','Bharatngr','1950-1-1','','');
/*!40000 ALTER TABLE `tbl_cand_general` ENABLE KEYS */;


--
-- Definition of table `tbl_cand_primary`
--

DROP TABLE IF EXISTS `tbl_cand_primary`;
CREATE TABLE `tbl_cand_primary` (
  `Cand_Code` int(10) unsigned NOT NULL auto_increment,
  `FName` varchar(45) NOT NULL,
  `LName` varchar(45) NOT NULL,
  `Picture` varchar(45) default NULL,
  `Resume` varchar(45) default NULL,
  `Employeed` varchar(3) default NULL,
  PRIMARY KEY  (`Cand_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_cand_primary`
--

/*!40000 ALTER TABLE `tbl_cand_primary` DISABLE KEYS */;
INSERT INTO `tbl_cand_primary` (`Cand_Code`,`FName`,`LName`,`Picture`,`Resume`,`Employeed`) VALUES 
 (1,'Shruti','Hirur','1256544944212.jpg',NULL,'0'),
 (2,'Prashanth','Sharma',NULL,NULL,'0'),
 (3,'Shahab','Uddin',NULL,NULL,'0');
/*!40000 ALTER TABLE `tbl_cand_primary` ENABLE KEYS */;


--
-- Definition of table `tbl_cand_professional`
--

DROP TABLE IF EXISTS `tbl_cand_professional`;
CREATE TABLE `tbl_cand_professional` (
  `Cand_Code` int(10) unsigned NOT NULL auto_increment,
  `Experience` int(2) unsigned default NULL,
  `Expected_Salary` int(10) unsigned default NULL,
  `Preferred_Type` varchar(45) default NULL,
  `Managable` varchar(45) default NULL,
  `Preferred_Jobs` varchar(45) default NULL,
  PRIMARY KEY  (`Cand_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_cand_professional`
--

/*!40000 ALTER TABLE `tbl_cand_professional` DISABLE KEYS */;
INSERT INTO `tbl_cand_professional` (`Cand_Code`,`Experience`,`Expected_Salary`,`Preferred_Type`,`Managable`,`Preferred_Jobs`) VALUES 
 (1,0,50000,'Full Time','Y',''),
 (2,0,0,'Part Time','Y',''),
 (3,0,0,'Part Time','Y','');
/*!40000 ALTER TABLE `tbl_cand_professional` ENABLE KEYS */;


--
-- Definition of table `tbl_comp_general`
--

DROP TABLE IF EXISTS `tbl_comp_general`;
CREATE TABLE `tbl_comp_general` (
  `Comp_Code` int(10) unsigned NOT NULL auto_increment,
  `contactno1` varchar(12) default NULL,
  `contactno2` varchar(12) default NULL,
  `website` varchar(30) default NULL,
  `size` varchar(5) default NULL,
  `address` varchar(45) default NULL,
  PRIMARY KEY  (`Comp_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_comp_general`
--

/*!40000 ALTER TABLE `tbl_comp_general` DISABLE KEYS */;
INSERT INTO `tbl_comp_general` (`Comp_Code`,`contactno1`,`contactno2`,`website`,`size`,`address`) VALUES 
 (1,'12345','67890','wipro.com','1000','Bangalore');
/*!40000 ALTER TABLE `tbl_comp_general` ENABLE KEYS */;


--
-- Definition of table `tbl_comp_primary`
--

DROP TABLE IF EXISTS `tbl_comp_primary`;
CREATE TABLE `tbl_comp_primary` (
  `Comp_Code` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) NOT NULL,
  `Logo` varchar(25) default NULL,
  PRIMARY KEY  (`Comp_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_comp_primary`
--

/*!40000 ALTER TABLE `tbl_comp_primary` DISABLE KEYS */;
INSERT INTO `tbl_comp_primary` (`Comp_Code`,`Name`,`Logo`) VALUES 
 (1,'wipro',NULL);
/*!40000 ALTER TABLE `tbl_comp_primary` ENABLE KEYS */;


--
-- Definition of table `tbl_comp_professional`
--

DROP TABLE IF EXISTS `tbl_comp_professional`;
CREATE TABLE `tbl_comp_professional` (
  `Comp_Code` int(10) unsigned NOT NULL auto_increment,
  `Sal_scale` varchar(15) default NULL,
  `Hrm` varchar(45) default NULL,
  `Email_id` varchar(45) default NULL,
  `Contact_no` varchar(12) default NULL,
  `Fax` varchar(12) default NULL,
  `Emp_Benefits` varchar(45) default NULL,
  PRIMARY KEY  (`Comp_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_comp_professional`
--

/*!40000 ALTER TABLE `tbl_comp_professional` DISABLE KEYS */;
INSERT INTO `tbl_comp_professional` (`Comp_Code`,`Sal_scale`,`Hrm`,`Email_id`,`Contact_no`,`Fax`,`Emp_Benefits`) VALUES 
 (1,'10000-20000','abc','abc@wipro.com','45677','8988898','m,r,');
/*!40000 ALTER TABLE `tbl_comp_professional` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
