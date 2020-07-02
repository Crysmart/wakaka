

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ac_hi_child_invoke
-- ----------------------------
CREATE TABLE `ac_hi_child_invoke`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '节点id',
  `invoke_method` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '调用方法',
  `proc_inst_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例id',
  `invoke_class` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例化的方法全限定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_hi_main_invoke
-- ----------------------------
CREATE TABLE `ac_hi_main_invoke`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '主流程节点id',
  `child_task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '子流程节点id',
  `proc_inst_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '主流程实例id',
  `invoke_class` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例化的方法全限定',
  `invoke_method` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '调用的方法',
  `child_proc_inst_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '子流程实例id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_re_child_invoke
-- ----------------------------
CREATE TABLE `ac_re_child_invoke`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '节点id',
  `invoke_method` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '调用方法',
  `proc_inst_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例id',
  `invoke_class` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例化的方法全限定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_re_main_invoke
-- ----------------------------
CREATE TABLE `ac_re_main_invoke`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '主流程节点id',
  `child_task_def_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '子流程节点id',
  `proc_inst_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '主流程实例id',
  `invoke_class` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例化的方法全限定',
  `invoke_method` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '调用的方法',
  `child_proc_inst_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '子流程实例id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_ru_move
-- ----------------------------
CREATE TABLE `ac_ru_move`  (
  `id` bigint(20) NOT NULL,
  `proc_inst_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例id',
  `from_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '来自节点id',
  `from_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '来自节点名称',
  `to_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '去往节点id',
  `to_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '去往节点名称',
  `success` tinyint(1) DEFAULT NULL COMMENT '执行是否成功',
  `reason` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_ru_vars
-- ----------------------------
CREATE TABLE `ac_ru_vars`  (
  `id` bigint(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `proc_inst_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '实例id',
  `execution_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '执行实例id',
  `key_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '键名',
  `key_value` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '键值'
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '变量池，主要针对主线流程扭转' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
