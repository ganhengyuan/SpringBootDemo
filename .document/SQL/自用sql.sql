/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云普通账户140.143.217.99
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 140.143.217.99:10236
 Source Schema         : temp

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 28/12/2019 11:04:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `pid` bigint(20) NULL DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `url_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问路径别称 (用于权限判断)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (463072053665196266, 8328677588097733646, '新建菜单', '/manage/menu/createMenu', 'post:createMenu', '2019-12-05 16:18:18', '2019-12-05 16:18:18', '0');
INSERT INTO `menu` VALUES (535017406740107544, 7873968612967666142, '创建用户', '/manage/menu/createUser', 'post:createUser', '2019-12-05 16:19:03', '2019-12-05 16:19:03', '0');
INSERT INTO `menu` VALUES (701990530108613368, 9196795023432575484, '添加权限', '/manage/role/addRole', 'post:addRole', '2019-12-05 16:48:52', '2019-12-05 16:48:52', '0');
INSERT INTO `menu` VALUES (7873968612967666142, 0, '用户管理', '/manage/user', 'user', '2019-12-05 16:15:20', '2019-12-05 16:15:20', '0');
INSERT INTO `menu` VALUES (8328677588097733646, 0, '菜单管理', '/manage/menu', 'menu', '2019-12-05 16:17:19', '2019-12-05 16:17:19', '0');
INSERT INTO `menu` VALUES (9196795023432575484, 0, '权限管理', '/manage/role', 'role', '2019-12-05 16:48:13', '2019-12-05 16:48:13', '0');

-- ----------------------------
-- Table structure for related_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `related_user_menu`;
CREATE TABLE `related_user_menu`  (
  `id` bigint(18) NOT NULL COMMENT 'id',
  `user_id` bigint(18) NULL DEFAULT NULL COMMENT '用户id',
  `menu_id` bigint(18) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户菜单中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of related_user_menu
-- ----------------------------
INSERT INTO `related_user_menu` VALUES (5005690382069597060, 479945913065455899, 7873968612967666142);
INSERT INTO `related_user_menu` VALUES (5039911212040031722, 479945913065455899, 463072053665196266);
INSERT INTO `related_user_menu` VALUES (5285185877243049359, 479945913065455899, 8328677588097733646);
INSERT INTO `related_user_menu` VALUES (6231148976376871961, 479945913065455899, 535017406740107544);
INSERT INTO `related_user_menu` VALUES (6956383641246355773, 479945913065455899, 9196795023432575484);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(18) NOT NULL COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '用户年龄',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (479945913065455899, 'admin', '123456', '男', 23, '13888888888', '2019-12-05 16:11:20', '2019-12-05 16:11:20', '0');
INSERT INTO `user` VALUES (6813174781050779907, '李四', '123456', '男', 18, NULL, '2019-12-05 16:51:45', '2019-12-05 16:51:45', '0');

SET FOREIGN_KEY_CHECKS = 1;
