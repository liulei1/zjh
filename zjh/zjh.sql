/*
Navicat MySQL Data Transfer

Source Server         : struts
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : zjh

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2016-01-26 21:53:10
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
  `id` int(10) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `real_name` varchar(20) NOT NULL,
  `image` varchar(50) default NULL,
  `telephone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `field` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL,
  `ip` varchar(20) default NULL,
  `annotation` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for consult
-- ----------------------------
DROP TABLE IF EXISTS `consult`;
CREATE TABLE `consult` (
  `id` int(11) NOT NULL auto_increment,
  `state` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `details` varchar(255) default NULL,
  `budget` decimal(19,2) default NULL,
  `release_date` datetime default NULL,
  `deadline` datetime default NULL,
  `checked_id` varchar(255) default NULL,
  `category` varchar(50) default NULL,
  `fileName` varchar(255) default NULL,
  `filePath` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for consult_check
-- ----------------------------
DROP TABLE IF EXISTS `consult_check`;
CREATE TABLE `consult_check` (
  `id` int(11) NOT NULL auto_increment,
  `cons_id` int(11) default NULL,
  `admin_id` int(11) default NULL,
  `check_datetime` datetime default NULL,
  `state` int(11) default NULL,
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
-- Table structure for expert
-- ----------------------------
DROP TABLE IF EXISTS `expert`;
CREATE TABLE `expert` (
  `id` int(10) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `real_name` varchar(20) NOT NULL,
  `image` varchar(50) default NULL,
  `telephone` varchar(20) NOT NULL,
  `identity` varchar(20) NOT NULL,
  `id_image` varchar(50) NOT NULL,
  `introduction` varchar(100) NOT NULL,
  `authority` varchar(20) NOT NULL,
  `field` varchar(100) NOT NULL,
  `archieve` int(10) NOT NULL,
  `annotation` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) NOT NULL,
  `cons_id` int(10) NOT NULL,
  `scm_id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `current_state` varchar(20) NOT NULL,
  `document` varchar(50) default NULL,
  `cost` double NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scheme
-- ----------------------------
DROP TABLE IF EXISTS `scheme`;
CREATE TABLE `scheme` (
  `id` int(10) NOT NULL,
  `cons_id` int(10) NOT NULL,
  `prof_id` int(10) NOT NULL,
  `details` varchar(255) NOT NULL,
  `upload_date` datetime NOT NULL,
  `proj_id` int(10) NOT NULL,
  PRIMARY KEY  (`id`)
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
