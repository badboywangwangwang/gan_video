/*
 Navicat Premium Data Transfer

 Source Server         : nacos
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : gan_video

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 09/08/2022 17:11:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gan_admin
-- ----------------------------
DROP TABLE IF EXISTS `gan_admin`;
CREATE TABLE `gan_admin` (
  `admin_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_admin
-- ----------------------------
BEGIN;
INSERT INTO `gan_admin` VALUES (1, 'admin', 'c33367701511b4f6020ec61ded352059', '13649471088', 0);
COMMIT;

-- ----------------------------
-- Table structure for gan_admin_token
-- ----------------------------
DROP TABLE IF EXISTS `gan_admin_token`;
CREATE TABLE `gan_admin_token` (
  `admin_user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `token` varchar(32) NOT NULL COMMENT 'token值(32位字符串)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `uq_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gan_admin_token
-- ----------------------------
BEGIN;
INSERT INTO `gan_admin_token` VALUES (1, '435b58e13fd2d2f2e719b730a8535b24', '2022-08-09 16:59:57', '2022-08-11 16:59:57');
COMMIT;

-- ----------------------------
-- Table structure for gan_user
-- ----------------------------
DROP TABLE IF EXISTS `gan_user`;
CREATE TABLE `gan_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `login_name` varchar(11) NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `introduce_sign` varchar(100) NOT NULL DEFAULT '' COMMENT '个性签名',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `member_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '会员(0:非会员 1:初级 2:中级 3:高级4:超级 5:永久)',
  `member_pay` tinyint(4) DEFAULT '0' COMMENT '购买的套餐(0:非套餐 1:月 2:季度 3:半年4:年度5:永久)',
  `membership_due` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '会员到期时间',
  `version` tinyint(4) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `fan_num` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_user
-- ----------------------------
BEGIN;
INSERT INTO `gan_user` VALUES (12, '13649471012', '13649471012', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-01 16:09:24', 0, 0, '2022-08-30 00:00:00', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (13, '帅小伙111', '13649471013', 'e10adc3949ba59abbe56e057f20f883e', '123jdw@qq.com', 0, 0, '2022-08-03 15:44:18', 1, 1, '2022-08-30 09:49:13', 2, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (14, '13649471014', '13649471014', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 2, 2, '2022-08-30 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (15, '13649471015', '13649471014', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 3, 3, '2022-08-30 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (16, '13649471016', '13649471016', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 1, 0, '2022-08-03 15:44:18', 4, 4, '2022-08-30 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (17, '13649471017', '13649471017', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 5, 5, '2022-08-30 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (19, '13649471088', '13649471088', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 1, 0, '2022-08-08 15:57:51', 0, 0, '2022-08-08 15:57:51', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (20, '13649471013', '13649471013', '14e1b600b1fd579f47433b88e8d85291', '随新所欲，蜂富多彩', 0, 0, '2022-08-09 16:28:52', 0, 0, '2022-08-09 16:28:52', 0, '2022-08-09 16:28:52', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for gan_user_token
-- ----------------------------
DROP TABLE IF EXISTS `gan_user_token`;
CREATE TABLE `gan_user_token` (
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `token` varchar(32) NOT NULL COMMENT 'token值(32位字符串)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uq_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gan_user_token
-- ----------------------------
BEGIN;
INSERT INTO `gan_user_token` VALUES (12, '3fd25148886287bfa6c118724912a7f7', '2022-08-05 17:39:04', '2022-08-07 17:39:04');
INSERT INTO `gan_user_token` VALUES (13, 'bff0f59261497a61f6aedf345d174d81', '2022-08-03 17:33:21', '2022-08-05 17:33:21');
INSERT INTO `gan_user_token` VALUES (20, '422cec2ae0e90d3a593a7eab6d120212', '2022-08-09 16:34:31', '2022-08-11 16:34:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
