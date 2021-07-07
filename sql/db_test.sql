-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Июл 07 2021 г., 00:29
-- Версия сервера: 5.7.25
-- Версия PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `db_test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `d_cat_catalog`
--

CREATE TABLE `d_cat_catalog` (
  `id` int(11) NOT NULL,
  `UUID` varchar(200) DEFAULT NULL,
  `COMPANY` varchar(2000) DEFAULT NULL,
  `DELIVERY_DATE` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Структура таблицы `f_cat_plantsf_cat_plants`
--

CREATE TABLE `f_cat_plantsf_cat_plants` (
  `ID` int(11) NOT NULL,
  `CATALOG_ID` int(11) NOT NULL,
  `COMMON` varchar(2000) DEFAULT NULL,
  `BOTANICAL` varchar(2000) DEFAULT NULL,
  `ZONE` int(11) DEFAULT NULL,
  `LIGHT` varchar(2000) DEFAULT NULL,
  `PRICE` decimal(10,2) DEFAULT NULL,
  `AVAILABILITY` int(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `d_cat_catalog`
--
ALTER TABLE `d_cat_catalog`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Индексы таблицы `f_cat_plantsf_cat_plants`
--
ALTER TABLE `f_cat_plantsf_cat_plants`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CATALOG_ID` (`CATALOG_ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `d_cat_catalog`
--
ALTER TABLE `d_cat_catalog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `f_cat_plantsf_cat_plants`
--
ALTER TABLE `f_cat_plantsf_cat_plants`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `f_cat_plantsf_cat_plants`
--
ALTER TABLE `f_cat_plantsf_cat_plants`
  ADD CONSTRAINT `f_cat_plantsf_cat_plants_ibfk_1` FOREIGN KEY (`CATALOG_ID`) REFERENCES `d_cat_catalog` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
