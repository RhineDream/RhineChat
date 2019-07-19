/*
Navicat MySQL Data Transfer

Source Server         : 114.115.217.74-中网
Source Server Version : 50640
Source Host           : 114.115.217.74:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-07-19 13:36:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `id` varchar(50) NOT NULL,
  `message` text,
  `send_user` varchar(255) DEFAULT NULL,
  `receive_user` varchar(255) DEFAULT NULL,
  `is_read` varchar(5) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES ('007b5e51-2749-4a6b-a1b7-aeb8b0456a14', '123', '张三', null, '1', '2019-07-05 11:17:15', '111');
INSERT INTO `chat_record` VALUES ('00b38b81-eb4b-4252-a244-25c268fe8d63', '31', '韩非', null, '1', '2019-07-05 11:17:57', '111');
INSERT INTO `chat_record` VALUES ('01849873-54b0-4b41-b35d-1e61707bccb9', '<img src=\"arclist/43.gif\" border=\"0\" />', '李斯', null, '1', '2019-07-04 16:16:28', '111');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `is_online` varchar(5) DEFAULT NULL COMMENT '是否在线 0不在 1在线',
  `session_id` varchar(255) DEFAULT NULL COMMENT 'session_id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('12313213123', 'zhangsan', '123', '2019-07-04 11:30:08', '张三', '0', null);
INSERT INTO `sys_user` VALUES ('b95ce225-579d-4318-b9cc-ff4406fe0ecb', 'lisi', '123', '2019-07-05 13:40:21', '李斯', '0', null);
INSERT INTO `sys_user` VALUES ('0bb2c551-38e6-418d-95e4-700ddf36176f', 'mengzi', '123', '2019-07-05 15:06:20', '孟子', '0', null);
INSERT INTO `sys_user` VALUES ('82781bc8-c0bb-4dc0-8214-836214bbb280', 'xunzi', '123', '2019-07-05 15:11:11', '荀子', '0', null);
INSERT INTO `sys_user` VALUES ('107abc39-352b-4ef7-9480-db8384e36c84', 'kongzi', '123', '2019-07-05 15:12:06', '孔子', '0', null);
