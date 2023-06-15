-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2023 at 03:27 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizzadatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `ReferralNumber` int(11) NOT NULL,
  `Account` varchar(255) NOT NULL,
  `PizzaType` varchar(255) NOT NULL,
  `Time` text NOT NULL,
  `PizzaFile` text NOT NULL,
  `Rating` double NOT NULL,
  `PizzaMaker` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`ReferralNumber`, `Account`, `PizzaType`, `Time`, `PizzaFile`, `Rating`, `PizzaMaker`) VALUES
(1, 'Derick Kho', 'Peperoni Keju', '2023-05-02 21:15:42', 'profile_1683981770721.jpg', 30, 'justin'),
(2, 'Hendra Aja', 'Keju', '2023-05-02 22:46:14', 'profile_1683902360651.png', 40, 'justin'),
(3, 'Hendra Aja', 'Peperoni', '2023-05-12 21:30:35', 'profile_1683902378096.png', 100, 'justin'),
(4, 'Derick Kho', 'jamur aja', '2023-05-12 21:55:32', 'profile_1683903434340.Bmp', 90, 'kelvin'),
(5, 'Hendra Aja', 'Nanas', '2023-05-13 20:53:16', 'profile_1685625926076.png', 60, 'justin'),
(6, 'Anton Yanto', 'Peperoni', '2023-05-17 22:52:28', 'profile_1684339176154.jpg', 100, 'kelvin'),
(7, 'Derick Kho', 'Peperoni', '2023-05-18 00:02:11', 'profile_1685625953546.png', 0, 'justin');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Account` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Email`, `Password`, `Account`) VALUES
(1, 'christensenderick@gmail.com', 'dhuar', 'Derick Kho'),
(2, 'hendra@gmail.com', 'hehe', 'Hendra Aja'),
(3, 'antoniyanto@gmail.com', 'anton123', 'Anton Yanto'),
(4, 'justin@gmail.com', 'jones', 'Justin'),
(5, 'kelvin@gmail.com', 'wilson', 'kelvin'),
(7, 'christensenderick@gmail.com', 'dhuar', 'derik'),
(9, 'albob@gmail.com', 'bobob', 'albob'),
(10, 'yegia@gmail.com', 'yegyeg', 'yegia'),
(11, 'monica@gmail.com', 'monmon', 'monica');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`ReferralNumber`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `ReferralNumber` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
