-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2023 at 08:51 PM
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
-- Database: `spotitube`
--

-- --------------------------------------------------------

--
-- Table structure for table `playlists`
--

CREATE TABLE `playlists` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `ownerid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlists`
--

INSERT INTO `playlists` (`id`, `name`, `ownerid`) VALUES
(2, 'Test ', 1),
(3, 'Test 2', 1),
(4, 'Test 3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `trackinplaylist`
--

CREATE TABLE `trackinplaylist` (
  `playlistid` int(11) NOT NULL,
  `trackid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tracks`
--

CREATE TABLE `tracks` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `performer` varchar(255) NOT NULL,
  `duration` int(11) NOT NULL,
  `albumtitle` varchar(255) NOT NULL,
  `playcount` int(11) NOT NULL,
  `publicationdate` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `offlineavailable` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tracks`
--

INSERT INTO `tracks` (`id`, `title`, `performer`, `duration`, `albumtitle`, `playcount`, `publicationdate`, `description`, `offlineavailable`) VALUES
(1, 'Falling', 'Harry Styles', 240, 'Fine Line', 444, '0000-00-00', 'Very nice song', 0),
(2, 'Teabb', 'Harry Styles', 240, 'Fine Line', 14, '2022-03-10', 'Very nice song 2', 1),
(3, 'Bohemian Rhapsody', 'Queen', 354, 'A Night at the Opera', 1000000, '1975-10-31', 'A classic rock song with a blend of opera and rock music.', 1),
(4, 'Shape of You', 'Ed Sheeran', 234, '÷ (Divide)', 2000000, '2017-01-06', 'A pop song with a catchy melody and romantic lyrics.', 1),
(5, 'Stairway to Heaven', 'Led Zeppelin', 482, 'Led Zeppelin IV', 900000, '1971-11-08', 'One of the greatest rock songs ever written, with a memorable guitar solo.', 0),
(6, 'Billie Jean', 'Michael Jackson', 292, 'Thriller', 1500000, '1983-01-02', 'A dance-pop song with a groovy beat and an iconic music video.', 1),
(7, 'Hello', 'Adele', 295, '25', 1800000, '2015-10-23', 'A soulful ballad about lost love, showcasing Adele\'s powerful vocals.', 1),
(8, 'Smells Like Teen Spirit', 'Nirvana', 302, 'Nevermind', 800000, '1991-09-10', 'A grunge anthem that defined a generation.', 0),
(9, 'Thriller', 'Michael Jackson', 357, 'Thriller', 1200000, '1982-11-30', 'The title track of the best-selling album of all time, with a spooky music video.', 1),
(10, 'Uptown Funk', 'Mark Ronson ft. Bruno Mars', 271, 'Uptown Special', 3000000, '2014-11-10', 'A funk-inspired song with a retro feel and a fun music video.', 1),
(11, 'Imagine', 'John Lennon', 183, 'Imagine', 500000, '1971-09-09', 'A peace anthem that encourages listeners to imagine a better world.', 1),
(12, 'Purple Rain', 'Prince', 508, 'Purple Rain', 700000, '1984-06-25', 'A power ballad that showcases Prince\'s guitar skills and emotive vocals.', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `password`, `email`, `token`) VALUES
(1, 'Ali Yilmaz', 'yilmaz19', 'test1234', 'test1234@gmail.com', '20281e88-1c31-4645-aef8-acf0d5290c11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `playlists`
--
ALTER TABLE `playlists`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tracks`
--
ALTER TABLE `tracks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `playlists`
--
ALTER TABLE `playlists`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tracks`
--
ALTER TABLE `tracks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
