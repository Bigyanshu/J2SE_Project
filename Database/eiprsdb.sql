/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 6.0.8-alpha-community : Database - eiprsdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eiprsdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `eiprsdb`;

/*Table structure for table `eiprs_tblcollege` */

DROP TABLE IF EXISTS `eiprs_tblcollege`;

CREATE TABLE `eiprs_tblcollege` (
  `cname` char(50) DEFAULT NULL,
  `pname` char(50) DEFAULT NULL,
  `period` char(50) DEFAULT NULL,
  `locality` char(50) DEFAULT NULL,
  `city` char(50) DEFAULT NULL,
  `state` char(50) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `email` char(50) DEFAULT NULL,
  `website` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblcollege` */

insert  into `eiprs_tblcollege`(`cname`,`pname`,`period`,`locality`,`city`,`state`,`phone`,`email`,`website`) values ('CIME','RN SASU','2017-2023','Bhubaneswar','Khurda','Odisha','7689876762','principal@ac.in','www.principal.ac.in'),('IGIT','S.K. Gupta','2019-2023','Bhubaneswar','Khurda','Odisha','9899786541','skgupta@gmail.com','www.skgupta.in'),('IMIT','Ramakant Behra','2018-2023','CDA-6','CUTTACK','Odisha','8765909876','ramakant@outlook.com','www.iimit.ac.in'),('CET','Rakesh Rout','2018-2024','Bhubaneswar','Khurda','Odisha','9890976876','rakeshrout@gmail.com','www.outr.ac.in'),('Trident Residency','Saraswoty Mishra','2020-2025','Sayeed Nagar','Khurda','Odisha','8789865743','saraswotym@gmail.com','www.trident.ac.in'),('SOA','Debadutta Pradhan','2020-2024','Bhubaneswar','Khurda','Odisha','8776986543','dubadutta2024@gmail.com','www.soa.ac.in'),('11SOA','Debadutta Pradhan11','2020-2024111','Bhubaneswar111','Khurda111','Odisha','8776986543111','dubadutta2024@gmail.com1111','www.soa.ac.in111');

/*Table structure for table `eiprs_tbldepartment` */

DROP TABLE IF EXISTS `eiprs_tbldepartment`;

CREATE TABLE `eiprs_tbldepartment` (
  `Dcode` varchar(50) DEFAULT NULL,
  `Dname` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `HOD` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tbldepartment` */

insert  into `eiprs_tbldepartment`(`Dcode`,`Dname`,`Phone`,`HOD`) values ('1003','Master of Computer Application','9822128938','Dr.Rajalaxmi Mishra'),('1004','Master of Business Administration','9622128934','Dr.Debasis MOhanty'),('1005','Computer Science','9622128934','Amrita Mohanty'),('1006','M.Tech','9623567934','Krushna Chandra Ojha'),('1007','B.Tech','9432145432','BadalSahoo'),('1008','B.Farm','9892145432','Kamlesh Shing'),('1009','M.Farm','7866145432','Mohammed Ali'),('1010','ITM','7986549045','Binod Rathod'),('1012','Physics','8976024768','Amresh Bam'),('1013','Mathematics','8769024768','Pooja Swain'),('1014','Geology','7889024768','Pooja Hegde'),('1011','Chemistry','7889024545','Junaid Mitheya');

/*Table structure for table `eiprs_tblleave` */

DROP TABLE IF EXISTS `eiprs_tblleave`;

CREATE TABLE `eiprs_tblleave` (
  `RefNo` varchar(50) DEFAULT NULL,
  `Date` varchar(20) DEFAULT NULL,
  `sID` varchar(50) DEFAULT NULL,
  `Sname` varchar(50) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `LeaveType` varchar(50) DEFAULT NULL,
  `FromDate` varchar(50) DEFAULT NULL,
  `ToDate` varchar(50) DEFAULT NULL,
  `Narration` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblleave` */

insert  into `eiprs_tblleave`(`RefNo`,`Date`,`sID`,`Sname`,`Gender`,`Department`,`Designation`,`LeaveType`,`FromDate`,`ToDate`,`Narration`) values ('5003','06-09-2023','3003','SSG Mishra','Male','Master of Computer Application','Senior Lecture','Child Care Leave','06-09-2023','10-09-2023','Urgent Medical Cases'),('5004','06-09-2023','3004','Bishnupriya Malik','Female','Master of Computer Application','Senior Lecture','Casual Leave','10-09-2023','13-09-2023','For Marrage Cerymony '),('5005','06-09-2023','3005','Pranab Kumar Mohanty','Male','Master of Computer Application','Senior Lecture','Vacation Leave','14-09-2023','16-09-2023','For Summer Vacation'),('5006','06-09-2023','3006','Deepak Kumar Panda','Male','Master of Computer Application','Senior Lecture','Special Casual Leave','18-09-2023','19-09-2023','Medical Cases'),('5007','06-09-2023','3007','Sabita R Behera','Female','Master of Computer Application','Junior Lecture','Duty Leave','21-09-2023','22-09-2023','Medical Cases'),('5008','06-09-2023','3008','Nihar Ranjan Sabat','Male','Master of Computer Application','Junior Lecture','Leave Not Due','24-09-2023','26-09-2023','For Payment Clearance'),('5009','06-09-2023','3009','Susant Kumar Behera','Male','Master of Computer Application','Junior Lecture','Extraordinary Leave','27-09-2023','28-09-2023','For Payment Clearance'),('5010','06-09-2023','3010','Sudhir Kumar Satapathy','Male','Master of Computer Application','Senior Lecture','Maternity Leave','27-09-2023','28-09-2023','For Training Purpose'),('5011','06-09-2023','3011','Rajalaxmi Mishra','Female','Master of Computer Application','HOD','Vacation Leave','27-09-2023','28-09-2023','For Puja Festive'),('5012','06-09-2023','3012','Dr.RN Sahu','Male','Master of Computer Application','Principal','Extraordinary Leave','27-09-2023','28-09-2023','Exam Meeting Purpose'),('5013','06-09-2023','3013','Dr.Debasis Mohanty','Male','Master of Business Administration','HOD','Commuted Leave','27-09-2023','28-09-2023','Management Campus Purpose'),('5014','06-09-2023','3014','Dr.Amitabh Nanda','Male','Master of Business Administration','Junior Lecture','Teachers Appointed On Probation','27-09-2023','28-09-2023','For Purchasing Plot'),('5015','06-09-2023','3015','Mrs.Bandana Sarangi','Female','Master of Business Administration','Junior Lecture','Vacation Leave','27-09-2023','28-09-2023','For Holiday Event.'),('5016','06-09-2023','3016','Dr.Bidya Dash','Female','Master of Business Administration','Senior Lecture','Vacation Leave','27-09-2023','28-09-2023','For Holiday Event.'),('5017','06-09-2023','3017','Debidutta Acharya','Male','Master of Business Administration','Junior Lecture','Leave Salary','27-09-2023','28-09-2023','For Payment Clearance.'),('5018','06-09-2023','3018','Kedareswar Panda','Male','Master of Business Administration','Junior Lecture','Adoption Leave','27-09-2023','28-09-2023','For Home Purchasing.'),('5019','06-09-2023','3019','Dr.M.C. Tripathy','Male','Master of Business Administration','Senior Lecture','Casual Leave','27-09-2023','28-09-2023','For Study Tour With Childrens..'),('5020','06-09-2023','3020','Nandan Kumar Kar','Male','Master of Business Administration','Senior Lecture','Sabbatical Leave','27-09-2023','28-09-2023','Vehicle Repairing Purpose.'),('5021','06-09-2023','3021','Dr.P.D. Das','Male','Master of Business Administration','Senior Lecture','Casual Leave','27-09-2023','28-09-2023','Summer Vacation With Childrens.'),('5022','06-09-2023','3022','Dr.Chandrakanta Sahoo','Male','Master of Business Administration','Senior Lecture','Earned Leave','27-09-2023','28-09-2023','For Internship in Company.');

/*Table structure for table `eiprs_tblleaveapprov` */

DROP TABLE IF EXISTS `eiprs_tblleaveapprov`;

CREATE TABLE `eiprs_tblleaveapprov` (
  `SerialNo` varchar(50) DEFAULT NULL,
  `Date1` varchar(50) DEFAULT NULL,
  `ReferenceCode` varchar(50) DEFAULT NULL,
  `Date2` varchar(50) DEFAULT NULL,
  `StaffID` varchar(50) DEFAULT NULL,
  `StaffName` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `LeaveType` varchar(50) DEFAULT NULL,
  `FromDate` varchar(50) DEFAULT NULL,
  `ToDate` varchar(50) DEFAULT NULL,
  `Narration` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblleaveapprov` */

insert  into `eiprs_tblleaveapprov`(`SerialNo`,`Date1`,`ReferenceCode`,`Date2`,`StaffID`,`StaffName`,`Gender`,`Department`,`Designation`,`LeaveType`,`FromDate`,`ToDate`,`Narration`,`Status`) values ('1','06-09-2023','5003','06-09-2023','3003','SSG Mishra','Male','Master of Computer Application','Senior Lecture','Child Care Leave','06-09-2023','10-09-2023','Urgent Medical Cases','Approved'),('2','06-09-2023','5004','06-09-2023','3004','Bishnupriya Malik','Female','Master of Computer Application','Senior Lecture','Casual Leave','10-09-2023','13-09-2023','For Marrage Cerymony ','Cancelled'),('4','07-09-2023','5006','06-09-2023','3006','Deepak Kumar Panda','Male','Master of Computer Application','Senior Lecture','Special Casual Leave','18-09-2023','19-09-2023','Medical Cases','Cancelled'),('5','06-09-2023','5007','06-09-2023','3007','Sabita R Behera','Female','Master of Computer Application','Junior Lecture','Duty Leave','21-09-2023','22-09-2023','Medical Cases','Approved'),('6','06-09-2023','5008','06-09-2023','3008','Nihar Ranjan Sabat','Male','Master of Computer Application','Junior Lecture','Leave Not Due','24-09-2023','26-09-2023','For Payment Clearance','Cancelled'),('7','06-09-2023','5009','06-09-2023','3009','Susant Kumar Behera','Male','Master of Computer Application','Junior Lecture','Extraordinary Leave','27-09-2023','28-09-2023','For Payment Clearance','Cancelled'),('8','06-09-2023','5010','06-09-2023','3010','Sudhir Kumar Satapathy','Male','Master of Computer Application','Senior Lecture','Maternity Leave','27-09-2023','28-09-2023','For Training Purpose','Approved'),('9','06-09-2023','5011','06-09-2023','3011','Rajalaxmi Mishra','Female','Master of Computer Application','HOD','Vacation Leave','27-09-2023','28-09-2023','For Puja Festive','Cancelled'),('10','06-09-2023','5012','06-09-2023','3012','Dr.RN Sahu','Male','Master of Computer Application','Principal','Extraordinary Leave','27-09-2023','28-09-2023','Exam Meeting Purpose','Approved'),('11','06-09-2023','5013','06-09-2023','3013','Dr.Debasis Mohanty','Male','Master of Business Administration','HOD','Commuted Leave','27-09-2023','28-09-2023','Management Campus Purpose','Approved'),('12','06-09-2023','5014','06-09-2023','3014','Dr.Amitabh Nanda','Male','Master of Business Administration','Junior Lecture','Teachers Appointed On Probation','27-09-2023','28-09-2023','For Purchasing Plot','Approved'),('13','06-09-2023','5015','06-09-2023','3015','Mrs.Bandana Sarangi','Female','Master of Business Administration','Junior Lecture','Vacation Leave','27-09-2023','28-09-2023','For Holiday Event.','Cancelled'),('14','06-09-2023','5016','06-09-2023','3016','Dr.Bidya Dash','Female','Master of Business Administration','Senior Lecture','Vacation Leave','27-09-2023','28-09-2023','For Holiday Event.','Cancelled'),('15','06-09-2023','5017','06-09-2023','3017','Debidutta Acharya','Male','Master of Business Administration','Junior Lecture','Leave Salary','27-09-2023','28-09-2023','For Payment Clearance.','Cancelled'),('16','06-09-2023','5018','06-09-2023','3018','Kedareswar Panda','Male','Master of Business Administration','Junior Lecture','Adoption Leave','27-09-2023','28-09-2023','For Home Purchasing.','Approved'),('17','06-09-2023','5019','06-09-2023','3019','Dr.M.C. Tripathy','Male','Master of Business Administration','Senior Lecture','Casual Leave','27-09-2023','28-09-2023','For Study Tour With Childrens..','Approved'),('18','06-09-2023','5020','06-09-2023','3020','Nandan Kumar Kar','Male','Master of Business Administration','Senior Lecture','Sabbatical Leave','27-09-2023','28-09-2023','Vehicle Repairing Purpose.','Cancelled'),('19','06-09-2023','5021','06-09-2023','3021','Dr.P.D. Das','Male','Master of Business Administration','Senior Lecture','Casual Leave','27-09-2023','28-09-2023','Summer Vacation With Childrens.','Cancelled'),('20','06-09-2023','5022','06-09-2023','3022','Dr.Chandrakanta Sahoo','Male','Master of Business Administration','Senior Lecture','Earned Leave','27-09-2023','28-09-2023','For Internship in Company.','Cancelled'),('3','06-09-2023','5005','06-09-2023','3005','Pranab Kumar Mohanty','Male','Master of Computer Application','Senior Lecture','Vacation Leave','14-09-2023','16-09-2023','For Summer Vacation','Approved');

/*Table structure for table `eiprs_tbllogin` */

DROP TABLE IF EXISTS `eiprs_tbllogin`;

CREATE TABLE `eiprs_tbllogin` (
  `User_Name` varchar(50) DEFAULT NULL,
  `Passwords` varchar(50) DEFAULT NULL,
  `Password_Hint` varchar(50) DEFAULT NULL,
  `Hint_Answer` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tbllogin` */

insert  into `eiprs_tbllogin`(`User_Name`,`Passwords`,`Password_Hint`,`Hint_Answer`) values ('1234','1111','Your Favourite Person','bgu'),('Bigyanshu','BSBJ@4O','Your Favourite Person','First Name Of Your Omnipotent'),('4444','0','Your Favourite Person','bgu');

/*Table structure for table `eiprs_tblsalslip` */

DROP TABLE IF EXISTS `eiprs_tblsalslip`;

CREATE TABLE `eiprs_tblsalslip` (
  `SlipNum` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `MonthOf` varchar(50) DEFAULT NULL,
  `Year` varchar(50) DEFAULT NULL,
  `Staff_ID` varchar(50) DEFAULT NULL,
  `StaffName` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `BasicSalary` decimal(11,2) DEFAULT NULL,
  `Da` decimal(11,2) DEFAULT NULL,
  `Amount1` decimal(11,2) DEFAULT NULL,
  `Pf` decimal(11,2) DEFAULT NULL,
  `Amount2` decimal(11,2) DEFAULT NULL,
  `Hra` decimal(11,2) DEFAULT NULL,
  `Amount3` decimal(11,2) DEFAULT NULL,
  `Tax` decimal(11,2) DEFAULT NULL,
  `Amount4` decimal(11,2) DEFAULT NULL,
  `GrossSalary` decimal(11,2) DEFAULT NULL,
  `NetSalary` decimal(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblsalslip` */

insert  into `eiprs_tblsalslip`(`SlipNum`,`Date`,`MonthOf`,`Year`,`Staff_ID`,`StaffName`,`Gender`,`Department`,`Designation`,`BasicSalary`,`Da`,`Amount1`,`Pf`,`Amount2`,`Hra`,`Amount3`,`Tax`,`Amount4`,`GrossSalary`,`NetSalary`) values ('7003','09-09-2023','January','2023','3003','SSG Mishra','Male','Master of Computer Application','Senior Lecture','100000.00','10.00','10000.00','11.00','11000.00','12.00','12000.00','13.00','13000.00','122000.00','98000.00'),('7004','09-09-2023','January','2023','3004','Bishnupriya Malik','Female','Master of Computer Application','Senior Lecture','120000.00','11.00','13200.00','12.00','14400.00','13.00','15600.00','14.00','16800.00','148800.00','117600.00'),('7005','09-09-2023','January','2023','3005','Pranab Kumar Mohanty','Male','Master of Computer Application','Senior Lecture','150000.00','11.00','16500.00','12.00','18000.00','12.00','18000.00','13.00','19500.00','184500.00','147000.00'),('7006','09-09-2023','January','2023','3007','Sabita R Behera','Female','Master of Computer Application','Junior Lecture','80000.00','10.00','8000.00','12.00','9600.00','11.00','8800.00','13.00','10400.00','96800.00','76800.00'),('7007','09-09-2023','January','2023','3007','Sabita R Behera','Female','Master of Computer Application','Junior Lecture','80000.00','10.00','8000.00','12.00','9600.00','11.00','8800.00','13.00','10400.00','96800.00','76800.00'),('7008','09-09-2023','January','2023','3008','Nihar Ranjan Sabat','Male','Master of Computer Application','Junior Lecture','70000.00','11.00','7700.00','12.00','8400.00','12.00','8400.00','13.00','9100.00','86100.00','68600.00'),('7009','09-09-2023','January','2023','3009','Susant Kumar Behera','Male','Master of Computer Application','Junior Lecture','70000.00','10.00','7000.00','12.00','8400.00','11.00','7700.00','13.00','9100.00','84700.00','67200.00'),('7010','09-09-2023','January','2023','3010','Sudhir Kumar Satapathy','Male','Master of Computer Application','Senior Lecture','150000.00','12.00','18000.00','12.00','18000.00','13.00','19500.00','13.00','19500.00','187500.00','150000.00'),('7011','09-09-2023','January','2023','3011','Rajalaxmi Mishra','Female','Master of Computer Application','HOD','150000.00','12.00','18000.00','12.00','18000.00','13.00','19500.00','14.00','21000.00','187500.00','148500.00'),('7012','09-09-2023','January','2023','3012','Dr.RN Sahu','Male','Master of Computer Application','Principal','300000.00','14.00','42000.00','13.00','39000.00','15.00','45000.00','16.00','48000.00','387000.00','300000.00'),('7013','09-09-2023','January','2023','3013','Dr.Debasis Mohanty','Male','Master of Business Administration','HOD','200000.00','13.00','26000.00','13.00','26000.00','15.00','30000.00','16.00','32000.00','256000.00','198000.00'),('7014','09-09-2023','January','2023','3014','Dr.Amitabh Nanda','Male','Master of Business Administration','Junior Lecture','90000.00','13.00','11700.00','13.00','11700.00','15.00','13500.00','16.00','14400.00','115200.00','89100.00'),('7015','09-09-2023','January','2023','3015','Mrs.Bandana Sarangi','Female','Master of Business Administration','Junior Lecture','80000.00','11.00','8800.00','13.00','10400.00','12.00','9600.00','15.00','12000.00','98400.00','76000.00'),('7016','09-09-2023','January','2023','3016','Dr.Bidya Dash','Female','Master of Business Administration','Senior Lecture','100000.00','11.00','11000.00','13.00','13000.00','12.00','12000.00','14.00','14000.00','123000.00','96000.00'),('7017','09-09-2023','January','2023','3017','Debidutta Acharya','Male','Master of Business Administration','Junior Lecture','90000.00','12.00','10800.00','12.00','10800.00','13.00','11700.00','14.00','12600.00','112500.00','89100.00'),('7018','09-09-2023','January','2023','3018','Kedareswar Panda','Male','Master of Business Administration','Junior Lecture','90000.00','11.00','9900.00','12.00','10800.00','13.00','11700.00','14.00','12600.00','111600.00','88200.00'),('7019','09-09-2023','January','2023','3019','Dr.M.C. Tripathy','Male','Master of Business Administration','Senior Lecture','150000.00','12.00','18000.00','13.00','19500.00','15.00','22500.00','14.00','21000.00','190500.00','150000.00'),('7020','09-09-2023','January','2023','3020','Nandan Kumar Kar','Male','Master of Business Administration','Senior Lecture','150000.00','14.00','21000.00','14.00','21000.00','15.00','22500.00','16.00','24000.00','193500.00','148500.00'),('7021','09-09-2023','January','2023','3021','Dr.P.D. Das','Male','Master of Business Administration','Senior Lecture','150000.00','13.00','19500.00','14.00','21000.00','15.00','22500.00','16.00','24000.00','192000.00','147000.00'),('7022','09-09-2023','January','2023','3022','Dr.Chandrakanta Sahoo','Male','Master of Business Administration','Senior Lecture','130000.00','13.00','16900.00','14.00','18200.00','15.00','19500.00','16.00','20800.00','166400.00','127400.00');

/*Table structure for table `eiprs_tblstaffattend` */

DROP TABLE IF EXISTS `eiprs_tblstaffattend`;

CREATE TABLE `eiprs_tblstaffattend` (
  `Ref_No` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `SID` varchar(50) DEFAULT NULL,
  `Sname` varchar(50) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblstaffattend` */

insert  into `eiprs_tblstaffattend`(`Ref_No`,`Date`,`SID`,`Sname`,`Gender`,`Phone`,`Email`,`Department`,`Designation`,`Status`) values ('4003','30-08-2023','3003','SSG Mishra','Male','7683029786','ssgmishra64@gmail.com','Master of Computer Application','Senior Lecture','Present'),('4004','30-08-2023','3004','Bishnupriya Malik','Female','7876798096','bishnupriya@gmail.com','Master of Computer Application','Senior Lecture','Present/X'),('4005','30-08-2023','3005','Pranab Kumar Mohanty','Male','8987690126','pranabkm@gmail.com','Master of Computer Application','Senior Lecture','Present/X'),('4006','30-08-2023','3006','Deepak Kumar Panda','Male','9657656432','depakkp@gmail.com','Master of Computer Application','Senior Lecture','Present'),('4007','30-08-2023','3007','Sabita R Behera','Female','8921307410','sabitarb12@gmail.com','Master of Computer Application','Junior Lecture','Present'),('4008','30-08-2023','3008','Nihar Ranjan Sabat','Male','7531025830','nihar8778@gmail.com','Master of Computer Application','Junior Lecture','Present'),('4009','30-08-2023','3009','Susant Kumar Behera','Male','9098343914','susantkb32@gmail.com','Master of Computer Application','Junior Lecture','Present/X'),('4010','30-08-2023','3010','Sudhir Kumar Satapathy','Male','7889063541','sudhirks90@gmail.com','Master of Computer Application','Senior Lecture','Present'),('4011','30-08-2023','3011','Rajalaxmi Mishra','Female','8769041520','rajlaxmi90@gmail.com','Master of Computer Application','HOD','Present'),('4012','30-08-2023','3012','Dr.RN Sahu','Male','7989032314','rnsahu980@gmail.com','Master of Computer Application','Principal','Present/X'),('4013','30-08-2023','3013','Dr.Debasis Mohanty','Male','9147092365','debasism77@gmail.com','Master of Business Administration','HOD','X/Present'),('4014','30-08-2023','3014','Dr.Amitabh Nanda','Male','9147089092','nandaamitabh85@gmail.com','Master of Business Administration','Junior Lecture','Present/X'),('4015','30-08-2023','3015','Mrs.Bandana Sarangi','Female','8785869091','bandana86@gmail.com','Master of Business Administration','Junior Lecture','Present'),('4016','30-08-2023','3016','Dr.Bidya Dash','Female','8785869091','bidyadash90@gmail.com','Master of Business Administration','Senior Lecture','Present'),('4017','30-08-2023','3017','Debidutta Acharya','Male','7762731820','debiduttaa83@gmail.com','Master of Business Administration','Junior Lecture','Absent'),('4018','30-08-2023','3018','Kedareswar Panda','Male','8217824357','kedareswar92@gmail.com','Master of Business Administration','Junior Lecture','Present/X'),('4019','30-08-2023','3019','Dr.M.C. Tripathy','Male','9080706050','tripathymc75@gmail.com','Master of Business Administration','Senior Lecture','Absent'),('4020','30-08-2023','3020','Nandan Kumar Kar','Male','8191712131','nkkar89@gmail.com','Master of Business Administration','Senior Lecture','Present/X'),('4021','30-08-2023','3021','Dr.P.D. Das','Male','7382910203','pddas83@gmail.com','Master of Business Administration','Senior Lecture','X/Present'),('4022','30-08-2023','3022','Dr.Chandrakanta Sahoo','Male','9394958284','cksahoo78@gmail.com','Master of Business Administration','Senior Lecture','Absent');

/*Table structure for table `eiprs_tblstaffinfo` */

DROP TABLE IF EXISTS `eiprs_tblstaffinfo`;

CREATE TABLE `eiprs_tblstaffinfo` (
  `SID` varchar(50) DEFAULT NULL,
  `Sname` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `DOB` varchar(50) DEFAULT NULL,
  `Fname` varchar(50) DEFAULT NULL,
  `Mname` varchar(50) DEFAULT NULL,
  `Locality` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `PIN` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `DOJoin` varchar(50) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `Bsalary` decimal(11,2) DEFAULT NULL,
  `DA` decimal(8,2) DEFAULT NULL,
  `HRA` decimal(8,2) DEFAULT NULL,
  `PF` decimal(8,2) DEFAULT NULL,
  `Tax` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `eiprs_tblstaffinfo` */

insert  into `eiprs_tblstaffinfo`(`SID`,`Sname`,`Gender`,`DOB`,`Fname`,`Mname`,`Locality`,`City`,`State`,`PIN`,`Phone`,`Email`,`DOJoin`,`Department`,`Designation`,`Bsalary`,`DA`,`HRA`,`PF`,`Tax`) values ('3004','Bishnupriya Malik','Female','20-09-1980','Manoranjan Malik','Vadreswary Malik','Patia','Bhubaneswar ','Odisha','754011','7876798096','bishnupriya@gmail.com','01-10-2011','Master of Computer Application','Senior Lecture','120000.00','11.00','13.00','12.00','14.00'),('3005','Pranab Kumar Mohanty','Male','12-06-1982','Baishnab Mohanty','Bhumika Mohanty','MahanadiVihar','Cuttack','Odisha','745001','8987690126','pranabkm@gmail.com','01-01-2012','Master of Computer Application','Senior Lecture','150000.00','11.00','12.00','12.00','13.00'),('3006','Deepak Kumar Panda','Male','12-06-1990','Mahavir Panda','Madhusmita Panda','Mancheswar','Khurdha','Odisha','745102','9657656432','depakkp@gmail.com','01-01-2015','Master of Computer Application','Senior Lecture','100000.00','12.00','14.00','12.00','13.00'),('3007','Sabita R Behera','Female','12-06-1993','Khirod Behera','Lubuna Behera','Rasulgarh','Khurdha','Odisha','745100','8921307410','sabitarb12@gmail.com','01-01-2017','Master of Computer Application','Junior Lecture','80000.00','10.00','11.00','12.00','13.00'),('3008','Nihar Ranjan Sabat','Male','12-06-1990','Antaryammi Sabat','Niharika Sabat','CRP','Khurdha','Odisha','745106','7531025830','nihar8778@gmail.com','01-01-2018','Master of Computer Application','Junior Lecture','70000.00','11.00','12.00','12.00','13.00'),('3009','Susant Kumar Behera','Male','12-06-1990','Asis Behera','Lipsa Behera','Patia','Khurdha','Odisha','754011','9098343914','susantkb32@gmail.com','01-04-2018','Master of Computer Application','Junior Lecture','70000.00','10.00','11.00','12.00','13.00'),('3010','Sudhir Kumar Satapathy','Male','12-06-1985','Chandra Sekhar Satapathy','Sideswary Satapathy','Bermuda','Khurdha','Odisha','745020','7889063541','sudhirks90@gmail.com','01-04-2011','Master of Computer Application','Senior Lecture','150000.00','12.00','13.00','12.00','13.00'),('3012','Dr.RN Sahu','Male','12-06-1980','Jyotilal Sahu','Layla Sahu','CRP','Khurda','Odisha','745106','7989032314','rnsahu980@gmail.com','01-04-2007','Master of Computer Application','Principal','300000.00','14.00','15.00','13.00','16.00'),('3013','Dr.Debasis Mohanty','Male','23-08-1977','Dharmendra Mohanty','Madhusmita Mohanty','CRP','Khurda','Odisha','745106','9147092365','debasism77@gmail.com','01-02-2008','Master of Business Administration','HOD','200000.00','13.00','15.00','13.00','16.00'),('3014','Dr.Amitabh Nanda','Male','23-08-1985','Mohan Nanda','Jasmin Nanda','MasterCanteen','Khurda','Odisha','745112','9147089092','nandaamitabh85@gmail.com','01-08-2015','Master of Business Administration','Junior Lecture','90000.00','13.00','15.00','13.00','16.00'),('3015','Mrs.Bandana Sarangi','Female','03-08-1986','Baisnav Sarangi','Bidya Sarangi','MasterCanteen','Khurda','Odisha','745112','8785869091','bandana86@gmail.com','30-06-2017','Master of Business Administration','Junior Lecture','80000.00','11.00','12.00','13.00','15.00'),('3016','Dr.Bidya Dash','Female','02-11-1990','Janmejaya Dash','Rasmita Dash','Sayeed Nagar','Khurda','Odisha','744009','8785869091','bidyadash90@gmail.com','10-10-2019','Master of Business Administration','Senior Lecture','100000.00','11.00','12.00','13.00','14.00'),('3017','Debidutta Acharya','Male','23-03-1983','Saswat Acharya','Vumika Acharya','Sayeed Nagar','Khurda','Odisha','744009','7762731820','debiduttaa83@gmail.com','10-01-2015','Master of Business Administration','Junior Lecture','90000.00','12.00','13.00','12.00','14.00'),('3018','Kedareswar Panda','Male','24-12-1992','Karamchand Panda','Mohini Panda','Nayapali','Khurda','Odisha','743041','8217824357','kedareswar92@gmail.com','01-02-2019','Master of Business Administration','Junior Lecture','90000.00','11.00','13.00','12.00','14.00'),('3019','Dr.M.C. Tripathy','Male','20-04-1975','Karamchand Panda','Mohini Panda','Prachi Vihar','Khurda','Odisha','743001','9080706050','tripathymc75@gmail.com','01-03-2011','Master of Business Administration','Senior Lecture','150000.00','12.00','15.00','13.00','14.00'),('3020','Nandan Kumar Kar','Male','20-04-1989','Nandi Kar','Aswini Kar','Jaydev Vihar','Khurda','Odisha','752072','8191712131','nkkar89@gmail.com','01-04-2012','Master of Business Administration','Senior Lecture','150000.00','14.00','15.00','14.00','16.00'),('3021','Dr.P.D. Das','Male','20-04-1983','Baibav Das','Anjali Das','Sayeed Nagar','Khurda','Odisha','744009','7382910203','pddas83@gmail.com','01-05-2014','Master of Business Administration','Senior Lecture','150000.00','13.00','15.00','14.00','16.00'),('3022','Dr.Chandrakanta Sahoo','Male','06-05-1978','Muktyranjan Sahoo','Aparna Sahoo','Sayeed Nagar','Khurda','Odisha','744009','9394958284','cksahoo78@gmail.com','01-06-2013','Master of Business Administration','Senior Lecture','130000.00','13.00','15.00','14.00','16.00'),('3011','Rajalaxmi Mishra','Female','12-06-1990','Ramakant Mishra','Ipsita Mishra','OMP','Cuttack','Odisha','745010','8769041520','rajlaxmi90@gmail.com','01-04-2008','Master of Computer Application','HOD','150000.00','12.00','13.00','12.00','14.00'),('3003','SSG Mishra','Male','10-030-1988','Gyanendra Mishra','Swaty Mishra','Chauliaganj','Cuttack','Odisha','754001','7683029786','ssgmishra64@gmail.com','10-04-2014','Master of Computer Application','Senior Lecture','100000.00','10.00','12.00','11.00','13.00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
