-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2016 at 06:12 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webteampromotion`
--

-- --------------------------------------------------------

--
-- Table structure for table `absence`
--

CREATE TABLE `absence` (
  `id` int(11) NOT NULL,
  `clock_in` datetime NOT NULL,
  `clock_out` datetime NOT NULL,
  `description` varchar(100) NOT NULL,
  `no_prm` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `absence`
--

INSERT INTO `absence` (`id`, `clock_in`, `clock_out`, `description`, `no_prm`) VALUES
(1, '2016-05-24 05:25:13', '2016-05-24 11:40:20', 'support', 'PRM1400005'),
(2, '2016-05-30 04:00:00', '2016-05-30 08:00:00', 'tesererere', 'PRM34343'),
(3, '2016-05-26 14:25:00', '2016-05-26 19:25:00', 'testing123', 'PRM1400012'),
(4, '2016-05-28 18:06:00', '2016-05-28 21:06:00', 'testing', 'PRM1400012'),
(232339, '2016-06-07 09:00:00', '2016-06-07 17:00:00', 'support kantor', 'PRM1400012');

-- --------------------------------------------------------

--
-- Table structure for table `available`
--

CREATE TABLE `available` (
  `id` int(11) NOT NULL,
  `available_from` datetime NOT NULL,
  `available_until` datetime NOT NULL,
  `no_prm` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `available`
--

INSERT INTO `available` (`id`, `available_from`, `available_until`, `no_prm`) VALUES
(1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', ''),
(2, '0000-00-00 00:00:00', '0000-00-00 00:00:00', ''),
(3, '2016-05-28 19:55:00', '2016-05-28 20:55:00', 'PRM1400012'),
(4, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'PRM1400012'),
(5, '2016-05-28 20:15:00', '2016-05-28 20:15:00', 'PRM1400012'),
(8, '0000-00-00 00:00:00', '0000-00-00 00:00:00', ''),
(9, '2016-06-02 13:14:00', '2016-06-02 04:09:00', 'PRM1400012');

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `branch_name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`id`, `branch_name`) VALUES
(1, 'Binus University Kemanggisan'),
(2, 'Binus University Alam Sutera'),
(3, 'Binus Internationa University JWC'),
(4, 'Binus Graduate Program'),
(5, 'Binus Bussiness School');

-- --------------------------------------------------------

--
-- Table structure for table `branch_detail`
--

CREATE TABLE `branch_detail` (
  `id` int(11) NOT NULL,
  `id_branch` int(11) NOT NULL,
  `id_faculty` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch_detail`
--

INSERT INTO `branch_detail` (`id`, `id_branch`, `id_faculty`) VALUES
(1, 3, 1),
(2, 3, 2),
(3, 3, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 3, 8),
(9, 3, 22),
(10, 3, 23),
(11, 3, 24);

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` varchar(11) NOT NULL,
  `data_name` varchar(50) NOT NULL,
  `id_staff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`id`, `data_name`, `id_staff`) VALUES
('1', 'Graduation Book Untar 64', 0),
('10', 'Data Expo 8', 0),
('11', 'Data Expo 9', 0),
('12', 'Data Expo 10', 0),
('13', 'Data Expo 11', 0),
('14', 'Data Expo 12', 0),
('2', 'Attendance List BBS Kompas 25 Mei 2015', 0),
('3', 'Data Expo 1', 0),
('4', 'Data Expo 2', 0),
('5', 'Data Expo 3', 0),
('6', 'Data Expo 4', 0),
('7', 'Data Expo 5', 0),
('8', 'Data Expo 6', 0),
('9', 'Data Expo 7', 0);

-- --------------------------------------------------------

--
-- Table structure for table `degree`
--

CREATE TABLE `degree` (
  `id` int(11) NOT NULL,
  `degree_name` varchar(50) NOT NULL,
  `note` text NOT NULL,
  `id_faculty` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `degree`
--

INSERT INTO `degree` (`id`, `degree_name`, `note`, `id_faculty`, `created_at`) VALUES
(1, 'Cyber Security', '', 1, '2016-05-31 16:05:35'),
(2, 'Game Application and Technology', '', 1, '2016-05-31 16:06:32'),
(3, 'Mobile Application and Technology', '', 1, '2016-05-31 16:07:11'),
(4, 'Teknik Informatika', '', 1, '2016-05-31 16:07:43'),
(5, 'Information System Audit', 'tes', 2, '2016-05-31 16:07:59'),
(6, 'Komputerisasi Akuntansi', '', 2, '2016-05-31 16:08:53'),
(7, 'Sistem Informasi', 'tes', 2, '2016-05-31 16:11:50'),
(8, 'Desain Interior', '', 3, '2016-05-31 16:12:18'),
(10, 'DKV Creative Advertising', '', 3, '2016-05-31 16:13:42'),
(11, 'DKV New Media', '', 3, '2016-05-31 16:15:03'),
(12, 'Business Creation', '', 4, '2016-05-31 16:15:44'),
(13, 'International Business Management', '', 4, '2016-05-31 16:16:33'),
(14, 'International Marketing', '', 4, '2016-05-31 16:23:21'),
(16, 'Akuntansi', '', 5, '2016-05-31 16:25:07'),
(17, 'Finance', '', 5, '2016-05-31 16:40:07'),
(18, 'Marketing Communication', '', 5, '2016-06-01 17:29:19'),
(19, 'Hotel Management', '', 5, '2016-06-02 04:03:22'),
(21, 'Business Law', '', 6, '2016-06-03 08:14:40'),
(22, 'Hubungan International', '', 6, '2016-06-03 08:19:05'),
(23, 'Psikologi', '', 6, '2016-06-03 08:25:32'),
(24, 'Sastra Cina', '', 6, '2016-06-03 08:25:34'),
(25, 'Sastra Jepang', '', 6, '2016-06-03 08:25:36'),
(26, 'Sastra Inggris', '', 6, '2016-06-03 08:25:37'),
(27, 'Pendidikan Guru Sekolah Dasar', '', 6, '2016-06-03 08:25:37'),
(31, 'Arsitektur', '', 7, '2016-06-03 08:33:26'),
(32, 'Teknik Sipil', '', 7, '2016-06-03 08:35:37'),
(33, 'Teknik Industri', '', 7, '2016-06-03 08:36:15'),
(87, 'raaaa', ' aaaa', 10, '2016-06-16 05:44:09'),
(37, 'Sistem Komputer', '', 7, '2016-06-05 08:31:13'),
(38, 'Food Techonology', '', 7, '2016-06-05 08:31:37'),
(39, 'Teknik Informatika dan Matematika', '', 0, '2016-06-05 08:37:36'),
(40, 'Teknik Informatika dan Statistika', '', 0, '2016-06-05 08:40:42'),
(41, 'Akuntansi dan Sistem Informasi', '', 0, '2016-06-05 08:41:02'),
(42, 'Manajemen dan Sistem Informasi', '', 0, '2016-06-05 08:41:26'),
(86, 'eeeee', 'ccc', 9, '2016-06-15 16:49:14'),
(45, 'Management', '', 0, '2016-06-10 03:49:44'),
(67, 'tes', ' aa', 0, '2016-06-15 03:19:58'),
(68, 'a', ' aaa', 0, '2016-06-15 03:27:13'),
(69, '67', '', 0, '2016-06-15 04:18:03'),
(70, '26', '', 0, '2016-06-15 04:18:10'),
(71, '7', '', 0, '2016-06-15 04:33:45'),
(72, '1', '', 0, '2016-06-15 05:10:20'),
(73, '69', '', 0, '2016-06-15 05:20:44'),
(74, '1', '', 0, '2016-06-15 05:37:10'),
(75, '1', '', 0, '2016-06-15 05:37:13'),
(76, '7', '', 0, '2016-06-15 05:47:53'),
(77, '38', '', 0, '2016-06-15 12:19:38'),
(78, '1', '', 0, '2016-06-15 15:11:03'),
(79, '2', '', 0, '2016-06-15 15:23:26'),
(80, '1', '', 0, '2016-06-15 15:26:23'),
(81, '1', '', 0, '2016-06-15 15:26:49'),
(88, 'eeee', ' eeee', 11, '2016-06-22 13:50:16'),
(89, 'Bosan', ' asdas', 12, '2016-06-22 14:42:19'),
(90, 'qwewqe', ' asdad', 22, '2016-06-22 15:01:22'),
(91, 'qwewqe', ' asdad', 23, '2016-06-22 15:01:23'),
(92, 'hihihi', 'asdasd', 24, '2016-06-22 15:01:42');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `event_name` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `total_tp` int(11) NOT NULL,
  `latitude` varchar(50) NOT NULL,
  `longitude` varchar(50) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  `image_path` varchar(200) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `id_staff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `event_name`, `location`, `total_tp`, `latitude`, `longitude`, `start`, `end`, `note`, `image_path`, `created_at`, `updated_at`, `id_staff`) VALUES
(1213260, 'bekasi education fair', 'bekasi', 2, '', '', '2016-06-29 03:01:00', '2016-06-29 04:00:00', 'semangt', 'c748d3ebb529c1b96acfd7b0675b2f62.png', '2016-06-20 06:34:04', '2016-06-16 06:32:16', 567490),
(1213261, 'bandung education fair', 'bandung', 2, '', '', '2016-06-22 11:06:00', '2016-06-22 12:06:00', ' asasa', 'ed9af56f3c031c60baea2fd05406ab0b.jpg', '2016-06-20 06:34:07', '2016-06-10 00:28:51', 1),
(1213262, 'mandiri expo', 'Jakarta', 2, '', '', '2016-06-22 10:00:00', '2016-06-22 11:00:00', ' semangat', 'e35067d68e6a22acf8ff239fb0d8186b.png', '2016-06-22 13:05:39', '2016-06-22 06:05:39', 567487),
(1213264, 'Papua super creative', 'papua', 3, '', '', '2016-07-01 10:07:00', '2016-07-01 15:07:00', 'goodtime', '8efe813104e0650f38d0f8e6b486ec4f.png', '2016-06-20 06:34:12', '2016-06-14 05:42:45', 567485),
(1213265, 'Bekasi Education Fair', 'Mall Summarecon Bekasi', 3, '', '', '2016-06-15 10:00:00', '2016-06-15 15:00:00', 'semangat!', 'e7c183b80cd719138370250db077d42e.png', '2016-06-20 06:34:14', '2016-06-17 03:26:57', 567488),
(1213267, 'binus square education', 'jakarta', 2, '', '', '2016-06-30 15:06:00', '2016-06-30 18:06:00', ' hebat!!', 'c8590b36f91faf61308cc9d9d9f15520.png', '2016-06-20 06:34:16', '2016-06-16 11:28:51', 567496),
(1213268, 'expo apa', 'test', 12, '', '', '2016-06-23 12:06:00', '2016-06-23 12:06:00', ' ', '148fb28263179c1e5164ba348b7c72a6.png', '2016-06-22 05:47:13', '2016-06-22 05:47:13', 1),
(1213269, 'expo apa', 'test', 12, '', '', '2016-06-23 22:06:00', '2016-06-23 22:06:00', ' 123123', '3a5da6f8afab876f50af4aa5e2540945.png', '2016-06-22 05:53:18', '2016-06-22 05:53:18', 1),
(1213270, 'asdsad', 'wqweqw121212', 12, '', '', '2016-06-24 22:06:00', '2016-06-24 23:06:00', ' asdasd', 'fc01ce8ebaa0629cb250f1fba059e65d.png', '2016-06-22 05:54:23', '2016-06-22 05:54:23', 1),
(1213271, 'wwoowow', '123123', 12, '', '', '2016-06-24 22:06:00', '2016-06-24 22:06:00', ' asdad', 'd0952c34f53e72a31f49b9165092d3cc.png', '2016-06-22 06:01:28', '2016-06-22 06:01:28', 567496),
(1213272, 'qweqwe', '12312', 2, '', '', '2016-06-24 22:06:00', '2016-06-24 22:06:00', ' ', 'd475e79335d392cf827742dad1cdd8e2.png', '2016-06-22 07:38:56', '2016-06-22 07:38:56', 567496);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `id` int(11) NOT NULL,
  `faculty_name` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`id`, `faculty_name`, `note`) VALUES
(1, 'School of Computer Science (SoCS)', ''),
(2, 'School of Information System (SoIS)', ''),
(3, 'School of Design', ''),
(4, 'School of Business Management', ''),
(5, 'Faculty of Economics and Communication', ''),
(6, 'Faculty of Humanities', ''),
(7, 'Faculty of Engineering', ''),
(8, 'Double Programs', ''),
(12, 'Kacau', ''),
(13, 'indo', ''),
(14, 'indo', ''),
(15, 'pusing', ''),
(16, 'pusing', ''),
(17, 'wowowo', ''),
(18, 'wowowo', ''),
(19, 'wowowo', ''),
(20, 'tes', ''),
(21, 'qweqeqwe', ''),
(22, 'qweqwe', ''),
(23, 'qweqwe', ''),
(24, 'hhhhh', '');

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_10_12_000000_create_users_table', 1),
('2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `password_resets`
--

INSERT INTO `password_resets` (`email`, `token`, `created_at`, `updated_at`) VALUES
('herrihartono@ymail.com', '9258121090473d8ff9320b595252a7f8', '2016-06-09 06:40:29', '2016-06-09 06:40:29'),
('herrihartono@ymail.com', 'daca6e2386ca02ab9a751d7eef49cf4b', '2016-06-09 06:42:56', '2016-06-09 06:42:56'),
('herrihartono@ymail.com', 'b219aee298b0056343200f242e78bfb5', '2016-06-09 06:49:58', '2016-06-09 06:49:58'),
('herrihartono@ymail.com', '074fef67d7ad96c72f55d3fab732591e', '2016-06-09 06:50:29', '2016-06-09 06:50:29'),
('herrihartono@ymail.com', '72fde8f765e5fcec21db47fd74c2c70f', '2016-06-09 06:54:12', '2016-06-09 06:54:12'),
('herrihartono@ymail.com', '424bebeb09d9d61f1cb914f809376aac', '2016-06-09 07:18:38', '2016-06-09 07:18:38'),
('herrihartono@ymail.com', '034a96cd09f5969c794f0ec75acace8f', '2016-06-09 07:21:38', '2016-06-09 07:21:38'),
('herrihartono@ymail.com', '12437be581abbd156bcb51fb140edf15', '2016-06-09 07:26:15', '2016-06-09 07:26:15'),
('herrihartono@ymail.com', 'ce47f92ad8b9ac36271fb3388b50f706', '2016-06-09 07:40:10', '2016-06-09 07:40:10'),
('herrihartono@ymail.com', 'fc6e93f34cb23e13cd38ae4a0da07eea', '2016-06-09 20:03:49', '2016-06-09 20:03:49'),
('herrihartono@ymail.com', 'e0ba9100b7cc44b2ae4d29ac156668a0', '2016-06-09 07:49:49', '2016-06-09 07:49:49'),
('herrihartono@ymail.com', 'd53c59be9d98d43280e96afc3a7ab0b9', '2016-06-09 20:14:20', '2016-06-09 20:14:20'),
('herrihartono@ymail.com', 'dcd7e49a084d3349d70fd5c1dfdeb57b', '2016-06-09 20:19:35', '2016-06-09 20:19:35'),
('herrihartono@ymail.com', 'd5a74a11d3dad473ff6f843e2f4e4404', '2016-06-09 21:15:37', '2016-06-09 21:15:37'),
('herrihartono@ymail.com', '21d144d3865c73d012e1a4f3531bf277', '2016-06-09 21:17:50', '2016-06-09 21:17:50'),
('herrihartono@ymail.com', '700cb91b05c3e52c1041d5d1678912d5', '2016-06-10 03:04:52', '2016-06-10 03:04:52'),
('herrihartono@ymail.com', '55b6ada9fdbef1632b303ae9819bd75e', '2016-06-11 00:33:55', '2016-06-11 00:33:55'),
('', '58ea1278bf46e554f7dd26c93639aa0c', '2016-06-13 03:28:12', '2016-06-13 03:28:12'),
('', '700993e7d9e8973008040849aa96a16e', '2016-06-13 03:28:23', '2016-06-13 03:28:23'),
('hehhe@yahoo.com', 'ff7a47d6d90b2e6a4c138ed4e5f20376', '2016-06-13 03:28:31', '2016-06-13 03:28:31'),
('', '96cc324e954941e25a42aa2bb74ff111', '2016-06-13 03:32:17', '2016-06-13 03:32:17'),
('herrihartono@ymail.com', '2492d90479ca1c8aaad0cc319b8ddd89', '2016-06-13 03:32:24', '2016-06-13 03:32:24'),
('', '6f98ad2165f212d9d91b6fac10695c13', '2016-06-13 08:43:02', '2016-06-13 08:43:02'),
('', '7bbf1291b78dc26670ff789df63092c9', '2016-06-14 01:37:32', '2016-06-14 01:37:32'),
('', '006c528c314d1bb3e59a2c4dbb2c425f', '2016-06-14 05:07:48', '2016-06-14 05:07:48'),
('', '428e9dd46df8a1c34d1472aeadaf95a3', '2016-06-14 05:08:42', '2016-06-14 05:08:42'),
('hehhe@yahoo.com', '750eb81762f544fc00557afca898951a', '2016-06-14 21:39:26', '2016-06-14 21:39:26'),
('herrihartono@ymail.com', '799855c008543dd0f502d20dd89adc15', '2016-06-14 21:41:44', '2016-06-14 21:41:44'),
('herrihartono@ymail.com', '5addc2fe5810d16b2cf6f1a619773f60', '2016-06-15 23:11:33', '2016-06-15 23:11:33'),
('herrihartono@ymail.com', 'd6213266dad0738392b3e20097eeed57', '2016-06-15 23:12:18', '2016-06-15 23:12:18'),
('herrihartono@ymail.com', 'f27e5e45986b4c98934b9a4573be0d10', '2016-06-15 23:13:37', '2016-06-15 23:13:37'),
('herrihartono@ymail.com', '0cbf3e9491236a3063cb5267b0224d6b', '2016-06-15 23:14:24', '2016-06-15 23:14:24'),
('herrihartono@ymail.com', '838d5889a5432b3a86073c97bb3bbe19', '2016-06-15 23:33:12', '2016-06-15 23:33:12'),
('herrihartono@ymail.com', '892c0a880600e2293898a13a02d8c00c', '2016-06-15 23:33:48', '2016-06-15 23:33:48'),
('herrihartono@ymail.com', 'c0bb66b9465a41312465cd3fc60f93ce', '2016-06-16 21:52:14', '2016-06-16 21:52:14'),
('herrihartono@ymail.com', 'b07eb2adac7d7deadd3c76f8cb450109', '2016-06-16 21:55:30', '2016-06-16 21:55:30');

-- --------------------------------------------------------

--
-- Table structure for table `ratting`
--

CREATE TABLE `ratting` (
  `id` int(11) NOT NULL,
  `time_management` float DEFAULT NULL,
  `initiative` float DEFAULT NULL,
  `responsible` float DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `comment` text NOT NULL,
  `from` varchar(20) NOT NULL,
  `to` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ratting`
--

INSERT INTO `ratting` (`id`, `time_management`, `initiative`, `responsible`, `date_created`, `comment`, `from`, `to`) VALUES
(1, 4.5, 2.3, 3.7, '0000-00-00 00:00:00', '0', 'PRM1400012', 'PRM1400010'),
(9, 3, 4, 3, '2016-05-23 19:36:40', 'really ?', 'PRM1400005', 'PRM1400004'),
(3, 3, 4, 2, '2016-05-22 17:34:08', 'hxuxkdn', 'PRM1400012', 'PRM1400005'),
(4, 3, 3, 4, '2016-05-22 22:21:41', 'second', 'PRM1400012', 'PRM1400004'),
(5, 4.5, 5, 5, '2016-05-22 22:26:56', 'testing', 'PRM1400012', 'PRM1400009'),
(10, 3, 5, 3.5, '2016-05-23 19:46:43', 'gfhjshgd', 'PRM1400005', 'PRM1400003'),
(8, 3, 4, 2.5, '2016-05-23 15:41:30', 'good', 'PRM1400005', 'PRM1400012'),
(11, 2.5, 4, 2.5, '2016-05-31 16:24:01', 'good', 'PRM1400012', 'PRM1400005'),
(12, 3, 3.5, 3, '2016-06-03 01:34:19', 'good', 'PRM1400012', 'PRM1400003');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `no_prm` varchar(20) NOT NULL,
  `id_support` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `no_prm`, `id_support`) VALUES
(1, 'PRM1400012', 1),
(2, 'PRM1400012', 6),
(3, 'PRM1400012', 7);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(72) NOT NULL,
  `id_branch` int(11) NOT NULL,
  `staff_name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `username`, `password`, `id_branch`, `staff_name`, `email`, `status`, `created_at`, `updated_at`) VALUES
(1, 'admin', '$2y$10$0z8llRze86LMlzCccrvo9u8oC7LLDqmOHttfb14aJb.zuc36SNoai', 0, 'admin', 'herri.tpunity2016@gmail.com', 1, '2016-06-16 14:51:59', '2016-06-16 00:17:55'),
(567496, 'herrihartono', '$2y$10$UJ9JoT64xGYJuRdAHghXieEaThS8XUdsTRZYzeXT0pg8xay2chiCq', 3, 'herri', 'herrihartono@ymail.com', 0, '2016-06-20 07:48:41', '2016-06-16 06:23:09'),
(567480, 'sasa', 'd41d8cd98f00b204e9800998ecf8427e', 0, '', 'herrihartono@ymail.com', 0, '2016-06-06 05:26:31', '2016-06-06 05:26:31'),
(567487, 'herrihartono', '$2y$10$z8rQ//fIMrLsMf1WMYwZTeyz.ooPaBZ8O6.5q.mw4gt2FSXn7KrJK', 0, 'herri hartono', 'herrihartono@ymail.com', 0, '2016-06-08 09:07:10', '2016-06-08 09:07:10'),
(567488, 'litha', '$2y$10$7JfoacFybu5G62/u2Vt5weJNdkntJ4Npa84jyUvTIjB6Wu0nJ1GFO', 0, 'litha', 'litha@yahoo.com', 0, '2016-06-08 17:10:15', '2016-06-08 09:10:13');

-- --------------------------------------------------------

--
-- Table structure for table `student_candidate`
--

CREATE TABLE `student_candidate` (
  `id` int(11) NOT NULL,
  `candidate_name` varchar(50) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `work_number` varchar(20) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `company` varchar(50) DEFAULT NULL,
  `alumni` varchar(50) DEFAULT NULL,
  `degree` varchar(50) DEFAULT NULL,
  `follow_up_date` datetime NOT NULL,
  `result` tinyint(1) NOT NULL,
  `description` varchar(50) NOT NULL,
  `id_data` char(5) NOT NULL,
  `no_prm` varchar(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_candidate`
--

INSERT INTO `student_candidate` (`id`, `candidate_name`, `address`, `email`, `work_number`, `phone_number`, `company`, `alumni`, `degree`, `follow_up_date`, `result`, `description`, `id_data`, `no_prm`) VALUES
(1, 'Abih Gunawan Untono', 'Kp. Cukanggalih RT 02/02 No. 21 Tangerang 14710', 'obih.imma@yahoo.com', '021-91267433', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-05-18 22:03:43', 0, 'gatau knapa', '1', 'PRM1400012'),
(2, 'Bertin Martahan', 'Perum Sari Bumi I D12a/1 Tangerang 15810', '-', '-', '082114674854', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-05-18 21:53:38', 1, 'mollaaa', '1', 'PRM1400012'),
(3, 'Candra Agus Tiawan', 'Haji marjuki 75 Kedoya Jakarta Barat 11520', 'candraagus266@gmail.com', '021-58303778', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-05-19 00:38:19', 1, 'maybe', '1', 'PRM1400012'),
(4, 'Daniel Mangaraja Tua P', 'Senopati 2 No. 6, Perumnas 4 Tangerang 15138', 'dastro92@gmail.com', '021-5914408', '0838772419', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-05-19 16:23:15', 0, 'gamau', '1', '0'),
(5, 'Effendi', 'Siantan 37 Tanjung Pinang 29124 ', 'penchua@ymail.com', '-', '089602752734', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-01 21:53:55', 1, 'hhmm', '1', 'PRM1400012'),
(6, 'F. Yoshea Sunarya', 'Otista /44 Sukabumi 43111', '-', '0266-217532', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-03 13:26:09', 1, 'xgh', '1', 'PRM1400012'),
(7, 'Gerry Leonanda', 'PCI BLK B14 No. 4 Kedaleman Cilegon', 'gerryleonanda@yahoo.co.id', '021-29335792', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, 'gak mau', '1', 'PRM1400012'),
(8, 'Hamid Buchori', 'Suka Asih Rt 68 Kel K. Anyar Subang 41211', 'hamidbuchori@gmail.com', '-', '08131748531', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, '', '1', '0'),
(9, 'Ian Minggus', 'APT Laguna 25B/37 Jakarta 14450', 'iann2124@gmail.com', '-', '085921237727', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, '', '1', '0'),
(10, 'Jack Budiman', 'Mendawai IV No. 12 Jakarta 12130', '-', '021-7260049', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, '', '1', '0'),
(11, 'Kaisar Hansel', 'BSD Sek 1.1 BLK A2 Angsana Tangerang 15318', '-', '-', '081315146828', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, '', '1', '0'),
(12, 'Laily Nikmah Putri Willda', 'Tj. Duren selatan I GG 6 No. 26 Jakarta barat 11470', '-', '021-5687128', '-', '-', 'Tarumanagara University', 'Faculty of Economics', '2016-06-17 12:37:01', 0, '', '1', '0'),
(13, 'Atria', '', '', '081213850627', '', '', '', '', '2016-06-17 12:37:01', 0, '', '1', '0'),
(14, 'Atria', '', '', '081213850627', '', '', '', '', '2016-06-17 12:37:01', 0, '', '1', '0'),
(15, 'hdhx', '', '', '55434', '', '', '', '', '2016-06-17 12:37:01', 0, '', '1', '0'),
(16, 'Test123', '', '', '0812', '', '', '', '', '2016-06-17 12:37:01', 0, '', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `support`
--

CREATE TABLE `support` (
  `id` int(11) NOT NULL,
  `support_from` datetime NOT NULL,
  `support_until` datetime NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `support`
--

INSERT INTO `support` (`id`, `support_from`, `support_until`, `description`) VALUES
(1, '1970-05-15 12:02:00', '1970-05-15 13:03:00', ' wqwq'),
(6, '1970-01-01 00:00:00', '1970-01-01 00:00:00', ' '),
(7, '2016-05-10 15:05:00', '2016-05-10 15:05:00', ' Hhh'),
(545, '2016-05-06 00:00:00', '2016-05-07 00:00:00', 'fdf'),
(546, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `support_event`
--

CREATE TABLE `support_event` (
  `id` int(11) NOT NULL,
  `id_schedule` int(11) NOT NULL,
  `id_event` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `support_event`
--

INSERT INTO `support_event` (`id`, `id_schedule`, `id_event`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `team_promotion`
--

CREATE TABLE `team_promotion` (
  `id` int(11) NOT NULL,
  `no_prm` varchar(20) NOT NULL,
  `tp_name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `join_date` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `work_number` varchar(20) DEFAULT NULL,
  `score` int(11) DEFAULT '0',
  `password` varchar(50) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `background` varchar(20) NOT NULL,
  `is_follow_up` tinyint(1) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `accepted_tp` tinyint(1) DEFAULT '0',
  `id_ratting` int(11) DEFAULT '0',
  `id_staff` int(11) DEFAULT '0',
  `id_degree` int(11) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team_promotion`
--

INSERT INTO `team_promotion` (`id`, `no_prm`, `tp_name`, `gender`, `join_date`, `email`, `address`, `phone_number`, `work_number`, `score`, `password`, `picture`, `background`, `is_follow_up`, `created_at`, `accepted_tp`, `id_ratting`, `id_staff`, `id_degree`, `birth_date`, `branch_id`) VALUES
(1, '', 'ASDASD', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-22 14:17:09', 0, 0, 567496, 0, '2005-12-07', NULL),
(3, 'PRM1400003', 'Cetrin Dina Tomatala', 'Female', '0000-00-00 00:00:00', '', 'Ambon, Maluku', '', '', 3525, 'dina', 'dina.jpg', '', 0, '2016-06-03 16:18:32', 1, 0, 1, 1, '0000-00-00', NULL),
(4, 'PRM1400004', 'Silvy Octavia', 'Female', '0000-00-00 00:00:00', '', 'Bekasi, Indonesia', '', '', 1346, 'silvy', 'silvy.jpg', '', 0, '2016-06-20 11:07:04', 1, 0, 1, 1, '0000-00-00', NULL),
(5, 'PRM1400005', 'Novita Elisabeth Sirait', 'Female', '0000-00-00 00:00:00', 'nyd_tnd@yahoo.com', 'Jakarta, Indonesia', '', '', 3657, 'banana', 'PRM1400005.jpeg', 'PRM1400005.jpeg', 0, '2016-06-22 14:18:12', 1, 0, 567496, 1, '0000-00-00', NULL),
(9, 'PRM1400009', 'Dini Agnatia', 'Female', '0000-00-00 00:00:00', '', '', '', '', 3573, 'megami', 'dini.jpg', '', 0, '2016-06-03 16:20:00', 1, 0, 2, 1, '0000-00-00', NULL),
(10, 'PRM1400010', 'Revanza Raytama', 'Male', '0000-00-00 00:00:00', 'revanza.gotoku.54@facebook.com', 'Jakarta, Indonesia ', '', '', 5786, 'revan', 'revan.jpg', '', 0, '2016-06-22 14:17:13', 1, 0, 567496, 1, '0000-00-00', NULL),
(12, 'PRM1400012', 'Atria Tya Tobing', 'Female', '0000-00-00 00:00:00', 'tya.atria@yahoo.com', 'Tangerang.', '081213750627', '0215914903', 5620, 'atria', 'PRM1400012.jpeg', 'PRM1400012.jpeg', 0, '2016-06-11 17:14:27', 1, 0, 1, 3, '0000-00-00', NULL),
(55, '', 'stefanus', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-22 14:17:16', 1, 0, 567496, 0, '1997-12-11', NULL),
(57, '', 'cobasatu', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-13 18:15:54', 1, 0, 0, 0, '1997-12-17', NULL),
(58, '', 'cobadua', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-20 11:07:13', 1, 0, 0, 0, '1998-12-04', NULL),
(60, '', 'aaaaaaaaaaaa', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-14 04:32:13', 1, 0, 0, 0, '2005-12-16', NULL),
(61, '', 'asasasasa1212', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-14 10:54:34', 1, 0, 0, 0, '2005-12-29', NULL),
(62, '', 'hebat', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-13 18:25:58', 1, 0, 0, 0, '1996-12-13', NULL),
(65, '', 'sasasasasas', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-14 13:13:00', 1, 0, 0, 0, '2005-12-08', NULL),
(66, '', 'gany', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-14 13:12:22', 0, 0, 0, 0, '1997-12-04', NULL),
(69, '', 'tifany', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 04:18:03', 0, 0, 0, 0, '1997-12-23', NULL),
(70, '', 'tifany', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 04:18:10', 0, 0, 0, 0, '1997-12-23', NULL),
(71, '', 'qwqwqw', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 04:33:46', 0, 0, 0, 0, '2005-12-09', NULL),
(72, '', 'satu', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 05:10:20', 0, 0, 0, 0, '1996-12-26', NULL),
(73, '', 'bony', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 05:20:44', 0, 0, 0, 0, '2005-12-13', NULL),
(75, '', 'kristin', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 05:37:13', 0, 0, 0, 0, '2005-12-21', NULL),
(76, '', 'veve', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 05:47:53', 0, 0, 0, 0, '1996-12-03', NULL),
(77, '', 'herr', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 12:19:38', 0, 0, 0, 0, '2005-12-29', NULL),
(78, '', 'coba', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 15:11:03', 0, 0, 0, 0, '2005-12-06', NULL),
(80, '', 'aDASDADASS', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 15:26:23', 0, 0, 0, 0, '2005-12-08', NULL),
(81, '', 'DASDASD', '', '0000-00-00 00:00:00', '', '', '', '', 0, '', '', '', 0, '2016-06-15 15:26:49', 0, 0, 0, 0, '2005-12-14', NULL),
(86, '', 'melati', '', '0000-00-00 00:00:00', 'melati@yahoo.com', '', '08989281928', '', 0, '', '', '', 0, '2016-06-20 10:32:13', 1, 0, 0, 17, '1996-12-03', NULL),
(90, '', 'asd', '', '0000-00-00 00:00:00', 'acoi@gmail.com', '', '12312', '', 0, '', '', '', 0, '2016-06-20 11:55:13', 0, 0, 0, 21, '2005-12-14', NULL),
(91, '', 'qweqwewq', '', '0000-00-00 00:00:00', 'pp@gmi.com', '', '234234', '', 0, '', '', '', 0, '2016-06-20 12:01:07', 0, 0, 0, 1, '2005-12-07', NULL),
(92, '', 'sasd', '', '0000-00-00 00:00:00', 'eee@gm.com', '', '23423423', '', 0, '', '', '', 0, '2016-06-20 12:02:59', 0, 0, 0, 1, '2005-12-15', NULL),
(93, 'PRM1400016', 'yyyy', '', '0000-00-00 00:00:00', 'yp@gm.com', '', '234234', '', 0, '', '', '', 0, '2016-06-22 13:08:42', 1, 0, 0, 6, '2005-12-14', NULL),
(94, '', 'toi', '', '0000-00-00 00:00:00', 'ok@gm.com', '', '55555', '', 0, '', '', '', 0, '2016-06-21 04:11:40', 0, 0, 0, 7, '2005-12-15', NULL),
(95, 'PRM1400014', 'moi', '', '0000-00-00 00:00:00', 'ppp@gm.com', '', '12312', '', 0, '', '', '', 0, '2016-06-21 04:13:19', 1, 0, 0, 16, '2005-12-20', NULL),
(96, '', 'susi', '', '0000-00-00 00:00:00', 'saya@yahoo.com', '', '92323223', '', 0, '', '', '', 0, '2016-06-22 13:10:02', 0, 0, 0, 12, '2005-12-29', NULL),
(97, 'PRM1400017', 'mimi', '', '0000-00-00 00:00:00', 'hehhe@yahoo.com', '', '888888', '', 0, '', '', '', 0, '2016-06-22 13:10:57', 1, 0, 0, 1, '2005-12-02', NULL),
(98, '', 'hhhhh', '', '0000-00-00 00:00:00', 'aaa@yahoo.com', '', '123123', NULL, 0, NULL, NULL, '', 0, '2016-06-22 15:29:31', 0, 0, 0, 6, '2005-12-07', 1),
(99, '', 'hhhhh', '', '0000-00-00 00:00:00', 'aaa@yahoo.com', '', '123123', NULL, 0, NULL, NULL, '', 0, '2016-06-22 15:29:32', 0, 0, 0, 6, '2005-12-07', 1),
(100, '', 'Lusiana', '', '0000-00-00 00:00:00', 'admin2@yahoo.com', '', '332424', NULL, 0, NULL, NULL, '', 0, '2016-06-22 15:30:39', 0, 0, 0, 8, '2005-12-14', 1),
(101, 'PRM1400018', 'puput', '', '0000-00-00 00:00:00', 'kovan@gmail.com', '', '123123', NULL, 0, NULL, NULL, '', 0, '2016-06-22 15:32:40', 1, 0, 567496, 1, '2005-12-08', 3),
(104, '', 'aaa', '', '0000-00-00 00:00:00', 'sasas@yamail.com', '', '00098', NULL, 0, NULL, NULL, '', 0, '2016-06-22 15:45:08', 0, 0, 0, 1, '2005-12-15', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `available`
--
ALTER TABLE `available`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `branch_detail`
--
ALTER TABLE `branch_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk` (`id_staff`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`),
  ADD KEY `password_resets_token_index` (`token`);

--
-- Indexes for table `ratting`
--
ALTER TABLE `ratting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_candidate`
--
ALTER TABLE `student_candidate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support`
--
ALTER TABLE `support`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_event`
--
ALTER TABLE `support_event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `team_promotion`
--
ALTER TABLE `team_promotion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `no_prm` (`id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absence`
--
ALTER TABLE `absence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=232340;
--
-- AUTO_INCREMENT for table `available`
--
ALTER TABLE `available`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `branch_detail`
--
ALTER TABLE `branch_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `degree`
--
ALTER TABLE `degree`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;
--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1213273;
--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `ratting`
--
ALTER TABLE `ratting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=567497;
--
-- AUTO_INCREMENT for table `student_candidate`
--
ALTER TABLE `student_candidate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `support`
--
ALTER TABLE `support`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=547;
--
-- AUTO_INCREMENT for table `support_event`
--
ALTER TABLE `support_event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `team_promotion`
--
ALTER TABLE `team_promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
