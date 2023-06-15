-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2023 at 10:57 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pendaftaran_turnamen`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('Albert', '111'),
('Anthony', '333'),
('Derick', '222'),
('Juko', '444');

-- --------------------------------------------------------

--
-- Table structure for table `game_individu`
--

CREATE TABLE `game_individu` (
  `idgame` int(11) NOT NULL,
  `nama_game` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `game_individu`
--

INSERT INTO `game_individu` (`idgame`, `nama_game`) VALUES
(1, 'Tekken'),
(2, 'Gran Turismo'),
(3, 'PES'),
(4, 'Roboto'),
(5, 'Fruit Ninja'),
(15, 'minecraft'),
(16, 'fifa');

-- --------------------------------------------------------

--
-- Table structure for table `game_kelompok`
--

CREATE TABLE `game_kelompok` (
  `idgame` int(11) NOT NULL,
  `nama_game` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `game_kelompok`
--

INSERT INTO `game_kelompok` (`idgame`, `nama_game`) VALUES
(1, 'PUBG'),
(2, 'ML'),
(3, 'Valorant'),
(4, 'Arisan'),
(5, 'dota'),
(6, 'nba');

-- --------------------------------------------------------

--
-- Table structure for table `peserta_individu`
--

CREATE TABLE `peserta_individu` (
  `idpeserta` int(11) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `alamat` varchar(45) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `no_handphone` varchar(45) DEFAULT NULL,
  `nrp` varchar(45) DEFAULT NULL,
  `program_studi` varchar(45) DEFAULT NULL,
  `angkatan` varchar(45) DEFAULT NULL,
  `foto_formal` varchar(45) DEFAULT NULL,
  `win` int(11) DEFAULT NULL,
  `lose` int(11) DEFAULT NULL,
  `game_individu_idgame` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `peserta_individu`
--

INSERT INTO `peserta_individu` (`idpeserta`, `nama`, `alamat`, `tanggal_lahir`, `no_handphone`, `nrp`, `program_studi`, `angkatan`, `foto_formal`, `win`, `lose`, `game_individu_idgame`, `status`) VALUES
(1, 'Kelvin Wilson Utomo', 'Margorejo Indah B-114', '2002-08-30', '085100999900', 'c11200039', 'Teknik Elektro', '2020', NULL, 3, 0, 1, 1),
(2, 'Justin Jones T', 'Gianyar C6/27', '2002-04-28', '081929292', 'c11200012', 'Teknik Elektro', '2020', '', 2, 0, 3, 2),
(3, 'Juan Felix', 'Sidoarjo 100', '2001-03-09', '085989989898', 'c11200029', 'Teknik Elektro', '2021', NULL, 3, 0, 2, 1),
(4, 'Kanaka AW', 'Siwalankerto 100', '2001-02-18', '087676767676', 'c11200028', 'Teknik Elektro', '2021', NULL, 4, 0, 1, 2),
(5, 'Jacquelyn', 'Gianyar C7/28', '2002-05-23', '081929292', 'c11200012', 'Teknik Elektro', '2020', NULL, 4, 0, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `peserta_kelompok`
--

CREATE TABLE `peserta_kelompok` (
  `idpeserta` int(11) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `alamat` varchar(45) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `no_handphone` varchar(45) DEFAULT NULL,
  `nrp` varchar(45) DEFAULT NULL,
  `program_studi` varchar(45) DEFAULT NULL,
  `angkatan` varchar(45) DEFAULT NULL,
  `foto_formal` varchar(45) DEFAULT NULL,
  `team_idteam` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `peserta_kelompok`
--

INSERT INTO `peserta_kelompok` (`idpeserta`, `nama`, `alamat`, `tanggal_lahir`, `no_handphone`, `nrp`, `program_studi`, `angkatan`, `foto_formal`, `team_idteam`) VALUES
(1, 'Kanaka', 'Siwalankerto 100', '2002-01-24', '0851616161', 'c11200022', 'Teknik Elektro', '2020', NULL, 1),
(2, 'Juan', 'Sidoarjo 01', '2002-02-23', '0851717171', 'c11200023', 'Teknik Elektro', '2020', NULL, 1),
(3, 'Jovan', 'Sidoarjo 02', '2002-03-24', '0851818181', 'c11200024', 'Teknik Elektro', '2020', NULL, 1),
(4, 'Gorila', 'Gianyar C6/27', '2002-04-28', '081929292', 'c11200012', 'Teknik Elektro', '2020', '', 2),
(5, 'Jacquelyn', 'Gianyar C7/28', '2002-05-23', '081929292', 'c11200012', 'Teknik Elektro', '2020', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `idteam` int(11) NOT NULL,
  `nama_team` varchar(45) DEFAULT NULL,
  `win` int(11) DEFAULT NULL,
  `lose` int(11) DEFAULT NULL,
  `game_kelompok_idgame` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`idteam`, `nama_team`, `win`, `lose`, `game_kelompok_idgame`, `status`) VALUES
(1, 'KECAMBAH', 5, 2, 1, 1),
(2, 'GABIMARU', 5, 3, 2, 1),
(3, 'Sarada', 0, 0, 3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `game_individu`
--
ALTER TABLE `game_individu`
  ADD PRIMARY KEY (`idgame`);

--
-- Indexes for table `game_kelompok`
--
ALTER TABLE `game_kelompok`
  ADD PRIMARY KEY (`idgame`);

--
-- Indexes for table `peserta_individu`
--
ALTER TABLE `peserta_individu`
  ADD PRIMARY KEY (`idpeserta`),
  ADD KEY `fk_peserta_individu_game_individu1_idx` (`game_individu_idgame`);

--
-- Indexes for table `peserta_kelompok`
--
ALTER TABLE `peserta_kelompok`
  ADD PRIMARY KEY (`idpeserta`),
  ADD KEY `fk_peserta_kelompok_team_idx` (`team_idteam`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`idteam`),
  ADD KEY `fk_team_game_kelompok1_idx` (`game_kelompok_idgame`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game_individu`
--
ALTER TABLE `game_individu`
  MODIFY `idgame` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `game_kelompok`
--
ALTER TABLE `game_kelompok`
  MODIFY `idgame` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `peserta_individu`
--
ALTER TABLE `peserta_individu`
  MODIFY `idpeserta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `peserta_kelompok`
--
ALTER TABLE `peserta_kelompok`
  MODIFY `idpeserta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `idteam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peserta_individu`
--
ALTER TABLE `peserta_individu`
  ADD CONSTRAINT `fk_peserta_individu_game_individu1` FOREIGN KEY (`game_individu_idgame`) REFERENCES `game_individu` (`idgame`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `peserta_kelompok`
--
ALTER TABLE `peserta_kelompok`
  ADD CONSTRAINT `fk_peserta_kelompok_team` FOREIGN KEY (`team_idteam`) REFERENCES `team` (`idteam`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `fk_team_game_kelompok1` FOREIGN KEY (`game_kelompok_idgame`) REFERENCES `game_kelompok` (`idgame`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
