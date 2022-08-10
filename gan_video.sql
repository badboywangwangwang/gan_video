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

 Date: 10/08/2022 10:33:13
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
INSERT INTO `gan_admin_token` VALUES (1, '7821b932d859bdf6da0acaabe6475ec-', '2022-08-10 10:13:08', '2022-08-12 10:13:08');
COMMIT;

-- ----------------------------
-- Table structure for gan_collection
-- ----------------------------
DROP TABLE IF EXISTS `gan_collection`;
CREATE TABLE `gan_collection` (
  `collection_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `video_id` bigint(11) NOT NULL,
  PRIMARY KEY (`collection_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_collection
-- ----------------------------
BEGIN;
INSERT INTO `gan_collection` VALUES (1, 1, 2);
INSERT INTO `gan_collection` VALUES (2, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for gan_commentedstar
-- ----------------------------
DROP TABLE IF EXISTS `gan_commentedstar`;
CREATE TABLE `gan_commentedstar` (
  `commentedstar_id` bigint(22) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(22) DEFAULT NULL,
  `video_id` bigint(22) DEFAULT NULL,
  `star_num` bigint(22) DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL,
  PRIMARY KEY (`commentedstar_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_commentedstar
-- ----------------------------
BEGIN;
INSERT INTO `gan_commentedstar` VALUES (1, 1, 1, 3, '2021-04-30 19:39:23');
INSERT INTO `gan_commentedstar` VALUES (2, 12, 3, 5, '2022-08-09 11:28:46');
INSERT INTO `gan_commentedstar` VALUES (3, 12, 9, 5, '2022-08-09 11:29:48');
COMMIT;

-- ----------------------------
-- Table structure for gan_focus
-- ----------------------------
DROP TABLE IF EXISTS `gan_focus`;
CREATE TABLE `gan_focus` (
  `focus_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `focused_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`focus_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_focus
-- ----------------------------
BEGIN;
INSERT INTO `gan_focus` VALUES (1, 1, 2);
INSERT INTO `gan_focus` VALUES (2, 1, 3);
INSERT INTO `gan_focus` VALUES (3, 1, 4);
INSERT INTO `gan_focus` VALUES (4, 3, 1);
INSERT INTO `gan_focus` VALUES (5, 12, 2);
COMMIT;

-- ----------------------------
-- Table structure for gan_message
-- ----------------------------
DROP TABLE IF EXISTS `gan_message`;
CREATE TABLE `gan_message` (
  `msg_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `msg_title` varchar(255) DEFAULT NULL,
  `msg_context` varchar(255) DEFAULT NULL,
  `msg_send_date` datetime DEFAULT NULL,
  `msg_send_user_id` bigint(11) DEFAULT NULL,
  `msg_receive_user_id` bigint(11) DEFAULT NULL,
  `msg_state_id` bigint(11) DEFAULT NULL,
  `msgtype_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_message
-- ----------------------------
BEGIN;
INSERT INTO `gan_message` VALUES (1, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【请填入你的昵称】的一枚点赞', '2021-04-30 18:52:42', NULL, 1, 6, 3);
INSERT INTO `gan_message` VALUES (2, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【请填入你的昵称】的一枚点赞', '2021-04-30 18:58:40', NULL, 1, 6, 3);
INSERT INTO `gan_message` VALUES (3, '收藏提醒', '你好！你的主题为《霸王别姬》的视频,被用户【yourname】的收藏', '2021-05-04 18:23:22', NULL, 1, 7, 5);
INSERT INTO `gan_message` VALUES (4, '系统提醒', '你好！你的主题为《我不是药神》的视频因违反规定已被下架', '2021-05-24 13:12:00', NULL, 2, 6, 2);
INSERT INTO `gan_message` VALUES (5, '系统提醒', '你好！你的主题为《我不是药神》的视频审核通过', '2021-05-25 15:27:07', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (6, '系统提醒', '你好！你的主题为《霸王别姬》的视频因违反规定已被下架', '2021-05-25 15:27:11', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (7, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 16:47:25', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (8, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:47:30', NULL, 1, 7, 2);
INSERT INTO `gan_message` VALUES (9, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:48:35', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (10, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:49:08', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (11, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:52:09', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (12, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:13', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (13, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:15', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (14, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (15, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (16, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (17, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:17', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (18, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:17', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (19, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:54:29', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (20, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:59:35', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (21, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:01:49', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (22, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:01:53', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (23, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:05:32', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (24, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:05:36', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (25, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:07:05', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (26, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:07:09', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (27, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:33:47', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (28, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:33:50', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (29, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:40:38', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (30, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:40:42', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (31, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:43:01', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (32, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:43:06', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (33, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:47:00', NULL, 1, 7, 2);
INSERT INTO `gan_message` VALUES (35, 'Hello', 'Hello，你视频真好看', '2021-05-31 18:09:28', 1, 2, 6, 1);
INSERT INTO `gan_message` VALUES (36, '你好', '我对你的视频很敢兴趣', '2021-06-02 14:09:29', 1, 1, 6, 1);
INSERT INTO `gan_message` VALUES (37, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频因违反规定已被下架', '2021-06-08 11:03:05', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (38, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频审核失败', '2021-06-08 11:03:12', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (39, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【yourname】的一枚点赞', '2021-06-08 14:05:15', NULL, 1, 6, NULL);
INSERT INTO `gan_message` VALUES (40, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频审核通过', '2021-06-08 14:20:07', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (41, '系统提醒', '你好！你的主题为《让子弹飞》的视频审核通过', '2022-08-09 11:20:41', NULL, 2, 6, 2);
INSERT INTO `gan_message` VALUES (42, '系统提醒', '你好！你的主题为《霸王别姬》的视频因违反规定已被下架', '2022-08-09 11:20:50', NULL, 1, 6, 2);
INSERT INTO `gan_message` VALUES (43, '点赞提醒', '你好! 你的视频《西虹市首富》的视频获得用户【请填入你的昵称】的一枚点赞', '2022-08-09 11:28:38', NULL, 2, 6, NULL);
INSERT INTO `gan_message` VALUES (44, '收藏提醒', '你好！你的主题为《西虹市首富》的视频,被用户【请填入你的昵称】的收藏', '2022-08-09 11:28:40', NULL, 2, 6, 5);
INSERT INTO `gan_message` VALUES (45, '点评提醒', '你好！你的主题为《西虹市首富》的视频获得用户【请填入你的昵称】的5星点评', '2022-08-09 11:28:46', NULL, 2, 6, 4);
INSERT INTO `gan_message` VALUES (46, '111', '1111', '2022-08-09 11:28:55', 12, 2, 6, 1);
INSERT INTO `gan_message` VALUES (47, '点赞提醒', '你好! 你的视频《美食总动员》的视频获得用户【请填入你的昵称】的一枚点赞', '2022-08-09 11:29:44', NULL, 3, 6, NULL);
INSERT INTO `gan_message` VALUES (48, '点评提醒', '你好！你的主题为《美食总动员》的视频获得用户【小周】的5星点评', '2022-08-09 11:29:48', NULL, 3, 6, 4);
INSERT INTO `gan_message` VALUES (49, '系统提醒', '你好！你的主题为《霸王别姬》的视频因违反规定已被下架', '2022-08-09 11:31:49', NULL, 1, 6, 2);
COMMIT;

-- ----------------------------
-- Table structure for gan_msgtype
-- ----------------------------
DROP TABLE IF EXISTS `gan_msgtype`;
CREATE TABLE `gan_msgtype` (
  `msgtype_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `msgtype_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`msgtype_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_msgtype
-- ----------------------------
BEGIN;
INSERT INTO `gan_msgtype` VALUES (1, 'PrivateMsg');
INSERT INTO `gan_msgtype` VALUES (2, 'SystemMsg');
INSERT INTO `gan_msgtype` VALUES (3, 'SuportNotice');
INSERT INTO `gan_msgtype` VALUES (4, 'EvaluateNotice');
INSERT INTO `gan_msgtype` VALUES (5, 'CollectionNotice');
COMMIT;

-- ----------------------------
-- Table structure for gan_predict
-- ----------------------------
DROP TABLE IF EXISTS `gan_predict`;
CREATE TABLE `gan_predict` (
  `pre_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `video_id` bigint(11) DEFAULT NULL,
  `pre_star` float(255,2) DEFAULT NULL,
  PRIMARY KEY (`pre_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_predict
-- ----------------------------
BEGIN;
INSERT INTO `gan_predict` VALUES (1, 1, 1, 2.58);
INSERT INTO `gan_predict` VALUES (2, 1, 2, 4.00);
INSERT INTO `gan_predict` VALUES (3, 1, 3, 2.34);
INSERT INTO `gan_predict` VALUES (4, 1, 4, 0.88);
INSERT INTO `gan_predict` VALUES (5, 1, 5, 3.44);
INSERT INTO `gan_predict` VALUES (6, 1, 6, 2.68);
INSERT INTO `gan_predict` VALUES (7, 1, 7, 3.03);
INSERT INTO `gan_predict` VALUES (8, 1, 8, 3.22);
INSERT INTO `gan_predict` VALUES (9, 1, 9, 3.80);
INSERT INTO `gan_predict` VALUES (10, 1, 10, 2.21);
INSERT INTO `gan_predict` VALUES (11, 1, 11, 1.20);
INSERT INTO `gan_predict` VALUES (12, 1, 12, 1.40);
COMMIT;

-- ----------------------------
-- Table structure for gan_record
-- ----------------------------
DROP TABLE IF EXISTS `gan_record`;
CREATE TABLE `gan_record` (
  `record_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `video_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_record
-- ----------------------------
BEGIN;
INSERT INTO `gan_record` VALUES (1, 1, 1);
INSERT INTO `gan_record` VALUES (2, 2, 1);
INSERT INTO `gan_record` VALUES (3, 1, 6);
INSERT INTO `gan_record` VALUES (4, 1, 5);
INSERT INTO `gan_record` VALUES (5, 1, 3);
INSERT INTO `gan_record` VALUES (6, 1, 4);
INSERT INTO `gan_record` VALUES (7, 3, 6);
INSERT INTO `gan_record` VALUES (8, 5, 1);
INSERT INTO `gan_record` VALUES (9, 3, 4);
INSERT INTO `gan_record` VALUES (10, 3, 1);
INSERT INTO `gan_record` VALUES (11, 3, 40);
INSERT INTO `gan_record` VALUES (14, 1, 26);
INSERT INTO `gan_record` VALUES (15, 1, 7);
COMMIT;

-- ----------------------------
-- Table structure for gan_state
-- ----------------------------
DROP TABLE IF EXISTS `gan_state`;
CREATE TABLE `gan_state` (
  `state_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`state_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_state
-- ----------------------------
BEGIN;
INSERT INTO `gan_state` VALUES (1, '认证中');
INSERT INTO `gan_state` VALUES (2, '已认证');
INSERT INTO `gan_state` VALUES (3, '认证失败');
INSERT INTO `gan_state` VALUES (4, '已上架');
INSERT INTO `gan_state` VALUES (5, '已下架');
INSERT INTO `gan_state` VALUES (6, '未读');
INSERT INTO `gan_state` VALUES (7, '已读');
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
INSERT INTO `gan_user` VALUES (13, '帅小伙111', '13649471013', 'e10adc3949ba59abbe56e057f20f883e', '123jdw@qq.com', 0, 0, '2022-08-03 15:44:18', 1, 1, '2022-09-29 09:49:13', 2, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (14, '13649471014', '13649471014', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 2, 2, '2022-12-29 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (15, '13649471015', '13649471014', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 3, 3, '2022-03-29 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (16, '13649471016', '13649471016', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 1, 0, '2022-08-03 15:44:18', 4, 4, '2023-08-29 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (17, '13649471017', '13649471017', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 0, 0, '2022-08-03 15:44:18', 5, 5, '2123-01-29 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (19, '13649471088', '13649471088', 'e10adc3949ba59abbe56e057f20f883e', '我就是我,不一样的烟火', 1, 0, '2022-08-08 15:57:51', 0, 0, '2023-02-28 09:49:13', 0, '2022-08-08 16:36:50', NULL, 0);
INSERT INTO `gan_user` VALUES (20, '13649471013', '13649471013', '14e1b600b1fd579f47433b88e8d85291', '随新所欲，蜂富多彩', 0, 0, '2022-08-09 16:28:52', 0, 0, '2023-03-29 09:49:13', 0, '2022-08-09 16:28:52', NULL, 0);
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

-- ----------------------------
-- Table structure for gan_video
-- ----------------------------
DROP TABLE IF EXISTS `gan_video`;
CREATE TABLE `gan_video` (
  `video_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `video_title` varchar(255) DEFAULT NULL,
  `video_info` varchar(255) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `thunmbnail_url` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `video_state_id` bigint(11) DEFAULT NULL,
  `view_num` int(11) DEFAULT NULL,
  `pp_num` int(11) DEFAULT NULL,
  `video_type_id` bigint(11) DEFAULT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_video
-- ----------------------------
BEGIN;
INSERT INTO `gan_video` VALUES (1, '霸王别姬', '段小楼（张丰毅）与程蝶衣（张国荣）是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城，为此，两人约定合演一辈子《霸王别姬》。但两人对戏剧与人生关系的理解有本质不同，段小楼深知戏非人生，程蝶衣则是人戏不分。', '2021-04-26 19:59:12', '/static/video/bwbj.mp4', '/video/getVideoImage/video1.png', 4, 142, 8, 1, 1);
INSERT INTO `gan_video` VALUES (2, '我不是药神', '普通中年男子程勇（徐峥 饰）经营着一家保健品店，失意又失婚。不速之客吕受益（王传君 饰）的到来，让他开辟了一条去印度买药做“代购”的新事业，虽然困难重重，但他在这条“买药之路”上发现了商机，一发不可收拾地做起了治疗慢粒白血病的印度仿制药独家代理商。赚钱的同时，他也认识了几个病患及家属，为救女儿被迫做舞女的思慧（谭卓 饰）、说一口流利“神父腔”英语的刘牧师（杨新鸣 饰），以及脾气暴烈的“黄毛”（章宇 饰），几个人合伙做起了生意，利润倍增的同时也危机四伏。程勇昔日的小舅子曹警官（周一围 饰）奉命调查仿制药的源', '2021-04-28 18:56:16', '/static/video/wbsys.mp4', '/video/getVideoImage/video2.png', 4, 0, 1, 9, 1);
INSERT INTO `gan_video` VALUES (3, '西虹市首富', '西虹市丙级球队大翔队的守门员王多鱼（沈腾 饰）因比赛失利被教练开除，一筹莫展之际王多鱼突然收到神秘人士金老板（张晨光 饰）的邀请，被告知自己竟然是保险大亨王老太爷（李立群 饰）的唯一继承人，遗产高达百亿！但是王老太爷给出了一个非常奇葩的条件，那就是要求王多鱼在一个月内花光十亿，还不能告诉身边人，否则失去继承权。王多鱼毫不犹豫签下了“军令状”，与好友庄强（张一鸣 饰）以及财务夏竹（宋芸桦 饰）一起开启了“挥金之旅”，即将成为西虹市首富的王多鱼，第一次感受到了做富人的快乐，同时也发现想要挥金如土实在没有那么简', '2021-04-28 19:02:37', '/static/video/xhssf.mp4', '/video/getVideoImage/video3.png', 4, 5, 2, 1, 2);
INSERT INTO `gan_video` VALUES (4, '你好,李焕英', '2001年的某一天，刚刚考上大学的贾晓玲（贾玲 饰）经历了人生中的一次大起大落。一心想要成为母亲骄傲的她却因母亲突遭严重意外，而悲痛万分。在贾晓玲情绪崩溃的状态下，竟意外的回到了1981年，并与年轻的母亲李焕英（张小斐 饰）相遇，二人形影不离，宛如闺蜜。与此同时，也结识了一群天真善良的好朋友。晓玲以为来到了这片“广阔天地”，她可以凭借自己超前的思维，让母亲“大有作为”，但结果却让晓玲感到意外......', '2021-04-28 19:04:55', '/static/video/nhlhy.mp4', '/video/getVideoImage/video4.png', 4, 19, 1, 1, 2);
INSERT INTO `gan_video` VALUES (5, '大话西游之大圣娶亲', '至尊宝（周星驰 饰）被月光宝盒带回到五百年前，遇见紫霞仙子（朱茵 饰），被对方打上烙印成为对方的人，并发觉自己已变成孙悟空。', '2021-04-28 19:08:16', '/static/video/dhxyzdsqq.mp4', '/video/getVideoImage/video5.png', 4, 3, 1, 1, 2);
INSERT INTO `gan_video` VALUES (6, '少年的你', '陈念（周冬雨 饰）是一名即将参加高考的高三学生，同校女生胡晓蝶（张艺凡 饰）的跳楼自杀让她的生活陷入了困顿之中。胡晓蝶死后，陈念遭到了以魏莱（周也 饰）为首的三人组的霸凌，魏莱虽然表面上看来是乖巧的优等生，实际上却心思毒辣，胡晓蝶的死和她有着千丝万缕的联系。', '2021-05-26 15:19:40', '/static/video/sndn.mp4', '/video/getVideoImage/video6.png', 4, 5, 0, 1, 2);
INSERT INTO `gan_video` VALUES (7, '活着', '根据余华同名小说改编。', '2021-04-28 19:12:24', '/static/video/hz.mp4', '/video/getVideoImage/video7.png', 4, 1, 1, 1, 2);
INSERT INTO `gan_video` VALUES (8, '让子弹飞', '555', '2021-05-19 18:55:36', '/static/video/20210519185611.mp4', '/video/getVideoImage/video8.png', 4, 0, 0, 1, 2);
INSERT INTO `gan_video` VALUES (9, '美食总动员', '小老鼠雷米在嗅觉方面有着无与伦比的天赋，不想过与垃圾堆为伴的生活，心怀成为五星大厨的梦想。', '2021-05-27 13:40:57', NULL, '/video/getVideoImage/video9.png', 4, 2, 1, 4, 3);
INSERT INTO `gan_video` VALUES (10, '音乐', '高中生健治有一天突发奇想，邀请伙伴太田和朝仓一起组乐队，但是三个人都对乐器一窍不通。他们的乐队是一把贝斯，再来一把贝斯，还有鼓，配置十分独特。当他们弹奏出第一个音符，立刻被一种前所未有的感觉淹没了，音乐震撼了他们。', '2021-05-27 13:46:55', NULL, '/video/getVideoImage/video10.png', 4, 0, 0, 5, 3);
INSERT INTO `gan_video` VALUES (11, '别运...别运...', '惊叹!别运...别运...诶...别!惊叹!惊叹!惊叹!', '2021-05-27 13:59:22', '/static/video/byby.mp4', '/video/getVideoImage/video11.png', 4, 0, 0, 11, 2);
INSERT INTO `gan_video` VALUES (12, '更好的生活', 'Carlos Galindo（德米安·比齐尔 Demián Bichir 饰）是一个从墨西哥偷渡到美国的无户籍人士。老婆早已离他而去，如今的他和14岁的儿子Luis Galindo相依为命。儿子在学校因为自己的特殊身份遭受同学的歧视和欺负，但他总是沉默对待父亲的询问，隔阂在两人间越来越深。', '2021-05-27 14:58:27', NULL, '/video/getVideoImage/video12.png', 4, 0, 0, 3, 1);
INSERT INTO `gan_video` VALUES (26, '忠犬八公的故事', '八公（Forest 饰）是一条谜一样的犬，因为没有人知道它从哪里来。教授帕克（理查·基尔 Richard Gere 饰）在小镇的火车站拣到一只走失的小狗，冥冥中似乎注定小狗和帕克教授有着某种缘分，帕克一抱起这只小狗就再也放不下来，最终，帕克对小狗八公的疼爱感化了起初极力反对养狗的妻子卡特', '2021-05-26 15:28:39', '/static/video/20210526152900.mp4', '/video/getVideoImage/video26.png', 4, 1, 0, 1, 2);
INSERT INTO `gan_video` VALUES (36, '无间道', '无间道', '2021-05-26 17:47:21', '/static/video/20210526174758.mp4', '/video/getVideoImage/video36.png', 4, 0, 0, 1, 1);
INSERT INTO `gan_video` VALUES (40, '肖生克的救赎', '肖生克的救赎', '2021-06-08 14:06:06', '/static/video/20210608140844.mp4', '/video/getVideoImage/video40.png', 4, 1, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for gan_videotype
-- ----------------------------
DROP TABLE IF EXISTS `gan_videotype`;
CREATE TABLE `gan_videotype` (
  `videotype_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`videotype_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gan_videotype
-- ----------------------------
BEGIN;
INSERT INTO `gan_videotype` VALUES (1, '影视');
INSERT INTO `gan_videotype` VALUES (2, '新闻');
INSERT INTO `gan_videotype` VALUES (3, '生活');
INSERT INTO `gan_videotype` VALUES (4, '美食');
INSERT INTO `gan_videotype` VALUES (5, '音乐');
INSERT INTO `gan_videotype` VALUES (6, '电视剧');
INSERT INTO `gan_videotype` VALUES (7, '舞蹈');
INSERT INTO `gan_videotype` VALUES (8, '动漫');
INSERT INTO `gan_videotype` VALUES (9, '娱乐');
INSERT INTO `gan_videotype` VALUES (10, '科技数码');
INSERT INTO `gan_videotype` VALUES (11, '体育');
INSERT INTO `gan_videotype` VALUES (12, '美妆');
COMMIT;

-- ----------------------------
-- Table structure for gan_viprule
-- ----------------------------
DROP TABLE IF EXISTS `gan_viprule`;
CREATE TABLE `gan_viprule` (
  `rule_id` varchar(32) NOT NULL COMMENT '租户号',
  `version` varchar(32) DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` varchar(1) DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `interest` text COMMENT '权益介绍',
  `title` varchar(32) DEFAULT NULL COMMENT '会员名称',
  `money` decimal(24,6) DEFAULT NULL COMMENT '金额',
  `duration` int(11) DEFAULT NULL COMMENT '时长',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员规则';

-- ----------------------------
-- Records of gan_viprule
-- ----------------------------
BEGIN;
INSERT INTO `gan_viprule` VALUES ('1', '1', '0', '2022-08-10 00:00:00', '2022-08-10 00:00:00', '月会员是一个按月计算的会员', '月度会员', 9.900000, 30);
INSERT INTO `gan_viprule` VALUES ('2', '1', '0', '2022-08-10 00:00:00', '2022-08-10 00:00:00', '季度会员是一个按月计算的会员', '季度会员', 19.900000, 90);
INSERT INTO `gan_viprule` VALUES ('3', '1', '0', '2022-08-10 00:00:00', '2022-08-10 00:00:00', '半年会员是一个按月计算的会员', '半年会员', 29.900000, 180);
INSERT INTO `gan_viprule` VALUES ('4', '1', '0', '2022-08-10 00:00:00', '2022-08-10 00:00:00', '年会员是一个按月计算的会员', '年会员', 49.900000, 365);
INSERT INTO `gan_viprule` VALUES ('5', '1', '0', '2022-08-10 00:00:00', '2022-08-10 00:00:00', '永久会员是一个按月计算的会员', '永久会员', 99.900000, 99999);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
