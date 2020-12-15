-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 24, 2017 at 03:41 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `med_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `agancy_detail`
--

CREATE TABLE IF NOT EXISTS `agancy_detail` (
  `a_id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `number` decimal(15,0) NOT NULL,
  `des` varchar(200) NOT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `agancy_detail`
--

INSERT INTO `agancy_detail` (`a_id`, `name`, `address`, `email`, `password`, `number`, `des`) VALUES
(5, 'Dhruv', 'Ahmedabad', 'dhruv@gmail.com', '12345678', '8866101873', 'We provide all types of Medicines to Retailer');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `f_id` int(3) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `fdes` varchar(100) NOT NULL,
  `frate` float NOT NULL,
  `r_id` int(3) NOT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`f_id`, `fname`, `fdes`, `frate`, `r_id`) VALUES
(1, 'Dhruv', 'Excellent work', 4.5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE IF NOT EXISTS `order_detail` (
  `o_id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` decimal(10,0) NOT NULL,
  `pname` varchar(30) NOT NULL,
  `rate` varchar(10) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `spid` int(3) NOT NULL,
  `rid` int(3) NOT NULL,
  `o_date` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=42 ;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`o_id`, `name`, `address`, `phone`, `pname`, `rate`, `qty`, `spid`, `rid`, `o_date`, `status`) VALUES
(22, 'dhruv', 'ahmedabad', '8866101873', 'GASTROMAX SYP', '130', '1', 41, 4, '13/03/17 01:25:53', 'dispatch'),
(23, 'dhruv', 'ahmedabad', '8866101873', 'Q10 + CAPSULE', '90', '1', 33, 4, '13/03/17 01:25:53', 'pending'),
(24, 'dhruv', 'ahmedabad', '8866101873', 'AFINEDAY TABLET', '50', '10', 37, 4, '13/03/17 01:25:53', 'ready');

-- --------------------------------------------------------

--
-- Table structure for table `product_detail`
--

CREATE TABLE IF NOT EXISTS `product_detail` (
  `pid` int(3) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) NOT NULL,
  `pdes` varchar(100) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `product_detail`
--

INSERT INTO `product_detail` (`pid`, `pname`, `pdes`) VALUES
(21, 'Vitamins', 'Vitamins Capsule'),
(22, 'Anaesthetics', 'Injection and Syrup'),
(23, 'Antiallergics', ' Antiallergics Tablet'),
(24, 'Antivirals', 'Antivirals Tablet & Cream'),
(25, 'Digestives', 'Digestives Syrup'),
(26, 'Diabetes', 'Diabetes Tablet'),
(27, 'Skin Infection', 'Skin Infection Cream');

-- --------------------------------------------------------

--
-- Table structure for table `retailer`
--

CREATE TABLE IF NOT EXISTS `retailer` (
  `r_id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  `phone` decimal(10,0) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `retailer`
--

INSERT INTO `retailer` (`r_id`, `name`, `email`, `password`, `phone`, `address`) VALUES
(1, 'conjuring', 'conjuring@gmail.com', '123', '8866101873', 'Ahmedabad'),
(3, 'Dhruv', 'dsp@gmail.com', '87654321', '8877990088', 'goa'),
(4, 'Dhruv', 'dhruv@gmail.com', '12345678', '8866101873', 'Ahmedabad'),
(5, 'Kishan', 'kishan@gmail.com', '12345678', '7069999125', 'Ahmedabad'),
(6, 'Dimpy', 'dimpy@gmail.com', '12345678', '9898787878', 'Ahmedabad');

-- --------------------------------------------------------

--
-- Table structure for table `sub_pro`
--

CREATE TABLE IF NOT EXISTS `sub_pro` (
  `sp_id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `content` varchar(100) NOT NULL,
  `rate` varchar(10) NOT NULL,
  `company` varchar(30) NOT NULL,
  `mfg_date` date NOT NULL,
  `exp_date` date NOT NULL,
  `pid` int(3) NOT NULL,
  `cid` int(3) DEFAULT NULL,
  `stock` int(3) NOT NULL,
  PRIMARY KEY (`sp_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `sub_pro`
--

INSERT INTO `sub_pro` (`sp_id`, `name`, `content`, `rate`, `company`, `mfg_date`, `exp_date`, `pid`, `cid`, `stock`) VALUES
(32, 'ABSOLUT CAPSULE', 'Vitamin B2 ', '110', 'himalaya', '2017-02-08', '2020-08-08', 21, NULL, 100),
(33, 'Q10 + CAPSULE', 'fatty acid ', '90', 'elite', '2017-02-08', '2020-08-08', 21, NULL, 141),
(34, 'ANAWIN INJECTION', 'Anhydrous dextrose', '30', 'shakar', '2017-02-08', '2020-08-08', 22, NULL, -10),
(35, 'ACILOC S  SYRUP', 'Magaldrate + Oxetacaine', '100', 'apollo', '2017-02-18', '2020-07-12', 22, NULL, 100),
(36, 'AL 5MG TABLET', 'Levocetirizine', '110', 'cadila', '2017-02-18', '2020-07-12', 23, NULL, 150),
(37, 'AFINEDAY TABLET', 'Fexofenadine', '50', 'apollo', '2017-02-15', '2020-07-15', 23, NULL, 160),
(38, 'ACIVIR SKIN CREAM', 'Acyclovir', '60', 'himalaya', '2017-02-14', '2020-11-25', 24, NULL, 80),
(39, 'ABALAM TABLET', 'Abacavir', '500', 'torrent', '2017-02-22', '2020-10-27', 24, NULL, 150),
(40, 'ACINIL LIQUID', 'Simethicone ', '80', 'dishman', '2017-02-23', '2020-10-28', 25, NULL, 200),
(41, 'GASTROMAX SYP', 'Aluminium hydroxide', '130', 'cadila', '2017-02-21', '2020-01-14', 25, NULL, 639),
(42, 'GLYREP XL', 'Glimepiride', '110', 'apollo', '2017-02-22', '2020-12-31', 26, NULL, 500),
(43, 'ALFABOSE ', 'Voglibose', '90', 'torrent', '2017-02-22', '2020-10-27', 26, NULL, 270),
(44, ' PROPYGENTA CREAM', 'Clobetasol Propionate', '240', 'himalaya', '2017-02-12', '2020-04-04', 27, NULL, 500),
(45, ' LOZEE CREAM', 'Zinc Sulphate', '50', 'shakar', '2017-02-16', '2020-09-08', 27, NULL, 270);
