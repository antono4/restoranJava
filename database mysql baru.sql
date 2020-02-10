-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2019 at 01:52 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restoranjava`
--

-- --------------------------------------------------------

--
-- Table structure for table `foodcategory`
--

CREATE TABLE `foodcategory` (
  `categoryID` int(4) NOT NULL,
  `categoryName` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `foodcategory`
--

INSERT INTO `foodcategory` (`categoryID`, `categoryName`) VALUES
(1, 'Makanan'),
(2, 'Minuman'),
(3, 'Ayam');

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `foodID` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `isAvailable` tinyint(1) NOT NULL DEFAULT 1,
  `foodCategory` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`foodID`, `name`, `price`, `isAvailable`, `foodCategory`) VALUES
(1, 'Ayam Bakar', '10000.00', 1, 3),
(2, 'Es Teh', '4000.00', 1, 2),
(4, 'Ayam Panggang', '15000.00', 1, 3),
(5, 'Jus Alpokat', '10000.00', 1, 2),
(6, 'Jus Mangga', '10000.00', 1, 2),
(7, 'Es Susu', '5000.00', 1, 2),
(8, 'Bebek', '20000.00', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems` (
  `ID` int(11) NOT NULL,
  `orderID` int(11) NOT NULL,
  `foodID` int(4) NOT NULL,
  `priceEach` decimal(10,2) NOT NULL,
  `orderQuantity` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderitems`
--

INSERT INTO `orderitems` (`ID`, `orderID`, `foodID`, `priceEach`, `orderQuantity`) VALUES
(1, 1, 6, '10000.00', 2),
(2, 1, 2, '4000.00', 2),
(3, 1, 7, '5000.00', 3),
(4, 1, 2, '4000.00', 2),
(5, 1, 6, '10000.00', 3),
(6, 2, 5, '10000.00', 2),
(7, 2, 8, '20000.00', 2),
(8, 3, 2, '4000.00', 2),
(9, 3, 7, '5000.00', 3),
(10, 4, 4, '15000.00', 3),
(11, 5, 2, '4000.00', 3),
(12, 5, 7, '5000.00', 3),
(13, 5, 8, '20000.00', 6);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL,
  `orderNumber` varchar(16) NOT NULL,
  `orderTime` datetime NOT NULL,
  `orderQuantity` int(4) NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `isPaid` tinyint(1) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderID`, `orderNumber`, `orderTime`, `orderQuantity`, `totalPrice`, `isPaid`, `status`) VALUES
(1, 'XB9833', '2019-12-09 19:49:35', 12, '81000.00', 0, 0),
(2, 'DD8855', '2019-12-09 19:49:49', 4, '60000.00', 0, 1),
(3, 'UV8900', '2019-12-09 19:49:59', 5, '23000.00', 1, 0),
(4, 'CK8289', '2019-12-09 19:50:05', 3, '45000.00', 0, 1),
(5, 'YD6880', '2019-12-09 19:50:17', 12, '147000.00', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `userprofile`
--

CREATE TABLE `userprofile` (
  `ID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `fullName` varchar(32) NOT NULL,
  `phoneNumber` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userprofile`
--

INSERT INTO `userprofile` (`ID`, `userID`, `fullName`, `phoneNumber`) VALUES
(1, 1, 'MY NAME IS ADMIN', '082281666584'),
(4, 4, 'Martin MS', '082281666583'),
(5, 5, 'MY NAME IS CHEF 006', '082281666584');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userGrant` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `userGrant`) VALUES
(1, 'myadmin', '21232f297a57a5a743894a0e4a801fc3', 1),
(4, 'kasirjava', 'C7911AF3ADBD12A035B289556D96470A', 2),
(5, 'dapurku', 'DE20B1D289DD6005BA8116085122F144', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `foodcategory`
--
ALTER TABLE `foodcategory`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodID`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `food` (`foodID`),
  ADD KEY `ord` (`orderID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `foodcategory`
--
ALTER TABLE `foodcategory`
  MODIFY `categoryID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `foodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
