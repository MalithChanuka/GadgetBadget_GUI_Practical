-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 07:45 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `it18213768`
--

-- --------------------------------------------------------

--
-- Table structure for table `order_gui`
--

CREATE TABLE IF NOT EXISTS `order_gui` (
`orderID` int(10) NOT NULL,
  `orderCode` varchar(10) DEFAULT NULL,
  `customerID` varchar(10) DEFAULT NULL,
  `customerEmail` varchar(30) DEFAULT NULL,
  `customerName` varchar(45) DEFAULT NULL,
  `orderTotalAmount` decimal(8,2) DEFAULT NULL,
  `cardNo` int(20) DEFAULT NULL,
  `cvvNo` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order_gui`
--
ALTER TABLE `order_gui`
 ADD PRIMARY KEY (`orderID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_gui`
--
ALTER TABLE `order_gui`
MODIFY `orderID` int(10) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
