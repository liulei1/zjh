/*
Navicat MySQL Data Transfer

Source Server         : struts
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : zjh

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2016-03-01 16:49:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `authority` varchar(10) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `image` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `usertype` varchar(255) default NULL,
  `real_name` varchar(255) default NULL,
  `image` varchar(255) default NULL,
  `telephone` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `field` varchar(255) default NULL,
  `authority` varchar(255) default NULL,
  `website` varchar(255) default NULL,
  `annotation` varchar(255) default NULL,
  `state` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for consult
-- ----------------------------
DROP TABLE IF EXISTS `consult`;
CREATE TABLE `consult` (
  `id` varchar(255) NOT NULL,
  `state` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `details` varchar(255) default NULL,
  `budget` decimal(19,2) default NULL,
  `release_date` datetime default NULL,
  `deadline` datetime default NULL,
  `checked_id` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `fileName` varchar(255) default NULL,
  `filePath` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  `prof_id` varchar(255) default NULL,
  `com_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK38B6FBECBAE40572` (`prof_id`),
  CONSTRAINT `FK38B6FBECBAE40572` FOREIGN KEY (`prof_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for consult_check
-- ----------------------------
DROP TABLE IF EXISTS `consult_check`;
CREATE TABLE `consult_check` (
  `id` int(11) NOT NULL auto_increment,
  `cons_id` varchar(255) default NULL,
  `admin_id` int(11) default NULL,
  `check_datetime` datetime default NULL,
  `state` varchar(255) default NULL,
  `rejectReason` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` int(10) NOT NULL,
  `proj_id` int(10) NOT NULL,
  `comp_id` int(10) NOT NULL,
  `evaluate_date` datetime NOT NULL,
  `content` varchar(100) default NULL,
  `prof_grade` int(10) NOT NULL,
  `prof_text` varchar(50) NOT NULL,
  `comp_grade` int(10) NOT NULL,
  `comp_text` varchar(50) NOT NULL,
  `state` varchar(8) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `usertype` varchar(255) default NULL,
  `real_name` varchar(255) default NULL,
  `image` varchar(255) default NULL,
  `telephone` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `authority` varchar(255) default NULL,
  `website` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  `identity` varchar(255) default NULL,
  `id_image` varchar(255) default NULL,
  `introduction` varchar(255) default NULL,
  `field` varchar(255) default NULL,
  `achieve` varchar(255) default NULL,
  `annotation` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `com_id` varchar(255) default NULL,
  `cons_id` varchar(255) default NULL,
  `scm_id` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `fileName` varchar(255) default NULL,
  `filePath` varchar(255) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `current_state` varchar(255) default NULL,
  `cost` decimal(19,2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_scheme
-- ----------------------------
DROP TABLE IF EXISTS `project_scheme`;
CREATE TABLE `project_scheme` (
  `id` varchar(255) NOT NULL,
  `cons_id` varchar(255) default NULL,
  `details` varchar(255) default NULL,
  `fileName` varchar(255) default NULL,
  `filePath` varchar(255) default NULL,
  `upload_date` datetime default NULL,
  `prof_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK38E3E98BD4162264` (`prof_id`),
  CONSTRAINT `FK38E3E98BD4162264` FOREIGN KEY (`prof_id`) REFERENCES `professor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `usertype` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_check
-- ----------------------------
DROP TABLE IF EXISTS `user_check`;
CREATE TABLE `user_check` (
  `id` int(10) NOT NULL,
  `u_id` int(10) NOT NULL,
  `a_id` int(10) NOT NULL,
  `check_date` varchar(255) NOT NULL,
  `ischeck` varchar(10) NOT NULL,
  `uncheckReasion` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vocation
-- ----------------------------
DROP TABLE IF EXISTS `vocation`;
CREATE TABLE `vocation` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
