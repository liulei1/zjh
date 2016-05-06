/*
Navicat MySQL Data Transfer

Source Server         : struts
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : zjh

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2016-05-04 22:12:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administer
-- ----------------------------
DROP TABLE IF EXISTS `administer`;
CREATE TABLE `administer` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `usertype` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) default NULL,
  `usertype` varchar(255) NOT NULL,
  `real_name` varchar(255) NOT NULL,
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
  `state` varchar(255) NOT NULL,
  `title` varchar(255) default NULL,
  `details` varchar(255) default NULL,
  `budget` decimal(19,2) default NULL,
  `release_date` datetime default NULL,
  `deadline` datetime default NULL,
  `checked_id` varchar(255) default NULL,
  `category` varchar(255) NOT NULL,
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
  `id` varchar(255) NOT NULL,
  `proj_id` int(11) default NULL,
  `prof_id` varchar(255) default NULL,
  `begin_date` varchar(255) default NULL,
  `prof_grade` int(11) default NULL,
  `prof_text` varchar(255) default NULL,
  `com_grade` int(11) default NULL,
  `com_text` varchar(255) default NULL,
  `state` varchar(255) default NULL,
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
  `address` varchar(255) default NULL,
  `website` varchar(255) default NULL,
  `real_name` varchar(255) default NULL,
  `image` varchar(255) default NULL,
  `telephone` varchar(255) default NULL,
  `identity` varchar(255) default NULL,
  `id_image` varchar(255) default NULL,
  `introduction` varchar(255) default NULL,
  `field` varchar(255) default NULL,
  `authority` varchar(255) default NULL,
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
  `prof_id` varchar(255) default NULL,
  `scm_id` varchar(255) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `current_state` varchar(255) default NULL,
  `com_id` varchar(255) default NULL,
  `consult_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `consult_id` (`consult_id`),
  KEY `FKED904B19C3753F6E` (`consult_id`),
  CONSTRAINT `FKED904B19C3753F6E` FOREIGN KEY (`consult_id`) REFERENCES `consult` (`id`)
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
  `upload_date` varchar(255) default NULL,
  `prof_id` varchar(255) default NULL,
  `title` varchar(255) default NULL,
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
  `email` varchar(50) default NULL,
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

-- ----------------------------
-- Table structure for webmessage
-- ----------------------------
DROP TABLE IF EXISTS `webmessage`;
CREATE TABLE `webmessage` (
  `id` varchar(255) NOT NULL,
  `recipientId` varchar(255) NOT NULL,
  `sendTime` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
