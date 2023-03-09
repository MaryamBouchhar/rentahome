-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 mars 2023 à 09:31
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rentahome`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`, `created_at`, `updated_at`) VALUES
(1, 'naima', 'adardor', '123', '2023-03-05 11:12:50', '2023-03-05 11:12:50'),
(2, 'saad', 'libourki', '1134', '2023-03-05 11:12:50', '2023-03-05 11:12:50'),
(3, 'naima', 'adardor', '123', '2023-03-05 11:18:20', '2023-03-05 11:18:20'),
(4, 'saad', 'libourki', '1134', '2023-03-05 11:18:21', '2023-03-05 11:18:21'),
(5, 'naima', 'adardor', '123', '2023-03-05 12:20:30', '2023-03-05 12:20:30'),
(6, 'saad', 'libourki', '1134', '2023-03-05 12:20:30', '2023-03-05 12:20:30'),
(7, 'fatima', 'fatima@gmail.com', '', '2023-03-05 15:53:37', '2023-03-05 15:53:37'),
(8, 'fatima', 'fatima@gmail.com', '', '2023-03-05 15:53:41', '2023-03-05 15:53:41'),
(9, 'jamila', 'soad@gmail.com', '', '2023-03-05 15:56:48', '2023-03-05 15:56:48'),
(10, 'jamila', 'jamila@gmail.com', '', '2023-03-05 15:58:14', '2023-03-05 15:58:14');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `name`, `email`, `phone`, `password`, `created_at`, `updated_at`) VALUES
(1, 'naima', 'naima', '788900', '123', '2023-03-05 21:17:30', '2023-03-05 21:17:30'),
(2, 'naima', 'adardor', '123', '123', '2023-03-06 09:10:16', '2023-03-06 09:10:16'),
(3, 'naima', 'adardor', '123', '123', '2023-03-06 10:01:03', '2023-03-06 10:01:03'),
(4, 'naima', 'adardor', '123', '123', '2023-03-06 15:17:47', '2023-03-06 15:17:47');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `property` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', 'create tables', 'SQL', 'V1__create_tables.sql', -696649720, 'root', '2023-03-01 15:20:23', 12007, 1);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `city` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `address`, `longitude`, `latitude`, `created_at`, `updated_at`, `city`) VALUES
(1, 'jjdkdkd', 123, 4567, '2023-03-06 17:01:32', '2023-03-08 10:41:12', 'Taroudant'),
(8, 'SSSS', 111, 444, '2023-03-08 21:00:55', '2023-03-08 21:00:55', 'KECH'),
(9, 'CC', 134, 556, '2023-03-08 21:16:53', '2023-03-08 21:16:53', 'VVV');

-- --------------------------------------------------------

--
-- Structure de la table `property`
--

CREATE TABLE `property` (
  `id` int(11) NOT NULL,
  `category` enum('Appartment','Studio','Villa','Duplex') NOT NULL DEFAULT 'Appartment',
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `area` double NOT NULL,
  `status` enum('Pending','Reserved','','') NOT NULL DEFAULT 'Pending',
  `location` int(11) NOT NULL,
  `rent_type` enum('Daily','Monthly') NOT NULL DEFAULT 'Monthly',
  `bathroom_count` int(11) NOT NULL DEFAULT 0,
  `room_count` int(11) NOT NULL,
  `is_equipped` tinyint(1) NOT NULL DEFAULT 0,
  `publish_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `property`
--

INSERT INTO `property` (`id`, `category`, `description`, `price`, `area`, `status`, `location`, `rent_type`, `bathroom_count`, `room_count`, `is_equipped`, `publish_date`, `created_at`, `updated_at`) VALUES
(1, 'Appartment', 'jjjj', 10000, 345, 'Pending', 1, 'Monthly', 2, 4, 0, '2023-03-19', '2023-03-06 17:06:49', '2023-03-08 11:09:57'),
(2, 'Appartment', 'jjjj', 10000, 345, 'Pending', 8, 'Monthly', 2, 4, 0, '2023-03-19', '2023-03-08 21:03:22', '2023-03-08 21:03:22');

-- --------------------------------------------------------

--
-- Structure de la table `property_image`
--

CREATE TABLE `property_image` (
  `id` int(11) NOT NULL,
  `property` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `property` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `property` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` enum('Confirmed','Rejected','Pending','') DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `client`, `property`, `start_date`, `end_date`, `created_at`, `updated_at`, `status`) VALUES
(1, 1, 1, '2023-03-08', '2023-03-15', '2023-03-07 17:30:39', '2023-03-08 14:38:53', 'Rejected'),
(2, 2, 1, '2023-03-08', '2023-03-08', '2023-03-08 14:45:26', '2023-03-08 14:45:26', 'Pending');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comment_client` (`client`),
  ADD KEY `fk_comment_property` (`property`);

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt31ykot78u85mbtsoee1sy9mx` (`client_id`),
  ADD KEY `FK56tyj0lfcktin79d0srdpb1kq` (`property_id`);

--
-- Index pour la table `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_property_location` (`location`);

--
-- Index pour la table `property_image`
--
ALTER TABLE `property_image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_property_image_property` (`property`);

--
-- Index pour la table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rating_client` (`client`),
  ADD KEY `fk_rating_property` (`property`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reservation_client` (`client`),
  ADD KEY `fk_reservation_property` (`property`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `property`
--
ALTER TABLE `property`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `property_image`
--
ALTER TABLE `property_image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_comment_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`);

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK56tyj0lfcktin79d0srdpb1kq` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  ADD CONSTRAINT `FKt31ykot78u85mbtsoee1sy9mx` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `fk_property_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`);

--
-- Contraintes pour la table `property_image`
--
ALTER TABLE `property_image`
  ADD CONSTRAINT `fk_property_image_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`);

--
-- Contraintes pour la table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `fk_rating_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_rating_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_reservation_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
