/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-04-29 11:50:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'root', '修改菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.update()', '{\"parentName\":\"系统管理\",\"name\":\"SQL监控\",\"icon\":\"fa fa-bug\",\"menuId\":5,\"orderNum\":4,\"perms\":\"\",\"type\":1,\"parentId\":1,\"url\":\"http://127.0.0.1:8081/druid/sql.html\"}', '39', '127.0.0.1', '2019-04-28 16:02:33');
INSERT INTO `sys_log` VALUES ('2', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"系统管理\",\"name\":\"日志管理\",\"icon\":\"\",\"menuId\":6,\"orderNum\":5,\"perms\":\"\",\"type\":1,\"parentId\":1,\"url\":\"modules/log/list.html\"}', '42', '127.0.0.1', '2019-04-28 16:07:13');
INSERT INTO `sys_log` VALUES ('3', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"列表\",\"icon\":\"\",\"menuId\":7,\"orderNum\":0,\"perms\":\"sys:user:list\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '24', '127.0.0.1', '2019-04-28 16:10:15');
INSERT INTO `sys_log` VALUES ('4', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"查看\",\"icon\":\"\",\"menuId\":8,\"orderNum\":1,\"perms\":\"sys:user:info\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '43', '127.0.0.1', '2019-04-28 16:11:08');
INSERT INTO `sys_log` VALUES ('5', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"新增\",\"icon\":\"\",\"menuId\":9,\"orderNum\":2,\"perms\":\"sys:user:save\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '32', '127.0.0.1', '2019-04-28 16:11:58');
INSERT INTO `sys_log` VALUES ('6', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"修改\",\"icon\":\"\",\"menuId\":10,\"orderNum\":0,\"perms\":\"sys:user:update\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '44', '127.0.0.1', '2019-04-28 16:13:13');
INSERT INTO `sys_log` VALUES ('7', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"删除\",\"icon\":\"\",\"menuId\":11,\"orderNum\":0,\"perms\":\"sys:user:delete\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '36', '127.0.0.1', '2019-04-28 16:13:48');
INSERT INTO `sys_log` VALUES ('8', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"修改状态\",\"icon\":\"\",\"menuId\":12,\"orderNum\":0,\"perms\":\"sys:user:update:status\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '38', '127.0.0.1', '2019-04-28 16:14:22');
INSERT INTO `sys_log` VALUES ('9', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"管理员管理\",\"name\":\"修改密码\",\"icon\":\"\",\"menuId\":13,\"orderNum\":0,\"perms\":\"sys:user:update:pass\",\"type\":2,\"parentId\":2,\"url\":\"\"}', '35', '127.0.0.1', '2019-04-28 16:15:06');
INSERT INTO `sys_log` VALUES ('10', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"列表\",\"icon\":\"\",\"menuId\":14,\"orderNum\":0,\"perms\":\"sys:role:list\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '26', '127.0.0.1', '2019-04-28 16:15:54');
INSERT INTO `sys_log` VALUES ('11', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"选择角色\",\"icon\":\"\",\"menuId\":15,\"orderNum\":0,\"perms\":\"sys:role:select\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '33', '127.0.0.1', '2019-04-28 16:18:29');
INSERT INTO `sys_log` VALUES ('12', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"查看\",\"icon\":\"\",\"menuId\":16,\"orderNum\":0,\"perms\":\"sys:role:info\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '76', '127.0.0.1', '2019-04-28 16:19:01');
INSERT INTO `sys_log` VALUES ('13', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"新增\",\"icon\":\"\",\"menuId\":17,\"orderNum\":0,\"perms\":\"sys:role:save\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '38', '127.0.0.1', '2019-04-28 16:19:39');
INSERT INTO `sys_log` VALUES ('14', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"修改\",\"icon\":\"\",\"menuId\":18,\"orderNum\":0,\"perms\":\"sys:role:update\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '32', '127.0.0.1', '2019-04-28 16:20:00');
INSERT INTO `sys_log` VALUES ('15', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"角色管理\",\"name\":\"删除\",\"icon\":\"\",\"menuId\":19,\"orderNum\":0,\"perms\":\"sys:role:delete\",\"type\":2,\"parentId\":3,\"url\":\"\"}', '45', '127.0.0.1', '2019-04-28 16:20:17');
INSERT INTO `sys_log` VALUES ('16', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"列表\",\"icon\":\"\",\"menuId\":20,\"orderNum\":0,\"perms\":\"sys:menu:list\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '28', '127.0.0.1', '2019-04-28 16:21:12');
INSERT INTO `sys_log` VALUES ('17', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"左侧导航\",\"icon\":\"\",\"menuId\":21,\"orderNum\":0,\"perms\":\"sys:menu:nav\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '26', '127.0.0.1', '2019-04-28 16:21:49');
INSERT INTO `sys_log` VALUES ('18', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"菜单树\",\"icon\":\"\",\"menuId\":22,\"orderNum\":0,\"perms\":\"sys:menu:tree\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '46', '127.0.0.1', '2019-04-28 16:22:24');
INSERT INTO `sys_log` VALUES ('19', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"选择菜单\",\"icon\":\"\",\"menuId\":23,\"orderNum\":0,\"perms\":\"sys:menu:select\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '32', '127.0.0.1', '2019-04-28 16:23:08');
INSERT INTO `sys_log` VALUES ('20', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"查看\",\"icon\":\"\",\"menuId\":24,\"orderNum\":0,\"perms\":\"sys:menu:info\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '34', '127.0.0.1', '2019-04-28 16:23:27');
INSERT INTO `sys_log` VALUES ('21', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"新增\",\"icon\":\"\",\"menuId\":25,\"orderNum\":0,\"perms\":\"sys:menu:save\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '37', '127.0.0.1', '2019-04-28 16:23:45');
INSERT INTO `sys_log` VALUES ('22', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"修改\",\"icon\":\"\",\"menuId\":26,\"orderNum\":0,\"perms\":\"sys:menu:update\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '59', '127.0.0.1', '2019-04-28 16:24:01');
INSERT INTO `sys_log` VALUES ('23', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"菜单管理\",\"name\":\"删除\",\"icon\":\"\",\"menuId\":27,\"orderNum\":0,\"perms\":\"sys:menu:delete\",\"type\":2,\"parentId\":4,\"url\":\"\"}', '41', '127.0.0.1', '2019-04-28 16:24:16');
INSERT INTO `sys_log` VALUES ('24', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"日志管理\",\"name\":\"列表\",\"icon\":\"\",\"menuId\":28,\"orderNum\":0,\"perms\":\"sys:log:list\",\"type\":2,\"parentId\":6,\"url\":\"\"}', '37', '127.0.0.1', '2019-04-28 16:25:07');
INSERT INTO `sys_log` VALUES ('25', 'root', '保存角色', 'com.cd.shiro.modules.sys.controller.SysRoleController.save()', '{\"createTime\":1556439990324,\"roleId\":2,\"roleName\":\"管理员\",\"remark\":\"拥有基本的权限\",\"menuIdList\":[1,2,7,10,11,12,13,8,9,3,4,20,21,22,23,24,25,26,27,5,6]}', '337', '127.0.0.1', '2019-04-28 16:26:30');
INSERT INTO `sys_log` VALUES ('26', 'root', '保存角色', 'com.cd.shiro.modules.sys.controller.SysRoleController.save()', '{\"createTime\":1556440046406,\"roleId\":3,\"roleName\":\"游客\",\"remark\":\"只能查看\",\"menuIdList\":[1,2,7,3,14,4,20]}', '48', '127.0.0.1', '2019-04-28 16:27:26');
INSERT INTO `sys_log` VALUES ('27', 'root', '保存用户', 'com.cd.shiro.modules.sys.controller.SysUserController.save()', '{\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"roleIdList\":[2],\"createTime\":1556440087800,\"contact\":\"admin\",\"nickname\":\"管理员\",\"mobile\":\"\",\"remark\":\"\",\"userId\":2,\"email\":\"\",\"username\":\"admin\"}', '89', '127.0.0.1', '2019-04-28 16:28:08');
INSERT INTO `sys_log` VALUES ('28', 'root', '保存用户', 'com.cd.shiro.modules.sys.controller.SysUserController.save()', '{\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"roleIdList\":[3],\"createTime\":1556440155084,\"contact\":\"guest\",\"nickname\":\"游客\",\"mobile\":\"\",\"remark\":\"\",\"userId\":3,\"email\":\"\",\"username\":\"guest\"}', '64', '127.0.0.1', '2019-04-28 16:29:15');
INSERT INTO `sys_log` VALUES ('31', 'root', '修改密码', 'com.cd.shiro.modules.sys.controller.SysUserController.password()', 'root', '99', '127.0.0.1', '2019-04-28 18:16:38');
INSERT INTO `sys_log` VALUES ('32', 'root', '删除日志', 'com.cd.shiro.modules.sys.controller.SysLogController.delete()', '[29,30]', '76', '127.0.0.1', '2019-04-28 18:20:56');
INSERT INTO `sys_log` VALUES ('33', 'admin', '修改菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.update()', '{\"parentName\":\"顶级菜单\",\"name\":\"系统管理\",\"icon\":\"fa fa-cog\",\"menuId\":1,\"orderNum\":0,\"perms\":\"\",\"type\":0,\"parentId\":0,\"url\":\"\"}', '46', '127.0.0.1', '2019-04-29 09:21:49');
INSERT INTO `sys_log` VALUES ('36', 'root', '修改密码', 'com.cd.shiro.modules.sys.controller.SysUserController.password()', 'root', '37', '127.0.0.1', '2019-04-29 09:23:15');
INSERT INTO `sys_log` VALUES ('37', 'root', '删除日志', 'com.cd.shiro.modules.sys.controller.SysLogController.delete()', '[34,35]', '50', '127.0.0.1', '2019-04-29 09:23:38');
INSERT INTO `sys_log` VALUES ('38', 'root', '保存用户', 'com.cd.shiro.modules.sys.controller.SysUserController.save()', '{\"password\":\"894023b8ca2d171495e22643176c2068\",\"roleIdList\":[1,2,3],\"createTime\":1556501064664,\"contact\":\"q\",\"nickname\":\"q\",\"mobile\":\"q\",\"remark\":\"q\",\"userId\":4,\"email\":\"q\",\"username\":\"q\"}', '250', '127.0.0.1', '2019-04-29 09:24:25');
INSERT INTO `sys_log` VALUES ('39', 'root', '修改用户状态', 'com.cd.shiro.modules.sys.controller.SysUserController.modifyStatus()', '{\"userId\":4,\"status\":0}', '37', '127.0.0.1', '2019-04-29 09:24:31');
INSERT INTO `sys_log` VALUES ('40', 'root', '修改用户', 'com.cd.shiro.modules.sys.controller.SysUserController.update()', '{\"roleIdList\":[2,3],\"contact\":\"q\",\"nickname\":\"q\",\"mobile\":\"weqeq\",\"remark\":\"q\",\"userId\":4,\"email\":\"eqweq\",\"username\":\"q\"}', '159', '127.0.0.1', '2019-04-29 09:24:40');
INSERT INTO `sys_log` VALUES ('41', 'root', '删除用户', 'com.cd.shiro.modules.sys.controller.SysUserController.delete()', '{\"request\":{},\"servletContext\":{},\"session\":{},\"httpSessions\":true}', '69', '127.0.0.1', '2019-04-29 09:27:15');
INSERT INTO `sys_log` VALUES ('42', 'root', '保存角色', 'com.cd.shiro.modules.sys.controller.SysRoleController.save()', '{\"createTime\":1556501251592,\"roleId\":4,\"roleName\":\"123\",\"remark\":\"aa\",\"menuIdList\":[1,6,28]}', '105', '127.0.0.1', '2019-04-29 09:27:32');
INSERT INTO `sys_log` VALUES ('43', 'root', '修改角色', 'com.cd.shiro.modules.sys.controller.SysRoleController.update()', '{\"roleId\":4,\"roleName\":\"123\",\"remark\":\"aaqwwq\",\"menuIdList\":[1,6,28]}', '69', '127.0.0.1', '2019-04-29 09:28:53');
INSERT INTO `sys_log` VALUES ('44', 'root', '删除角色', 'com.cd.shiro.modules.sys.controller.SysRoleController.delete()', '[4]', '72', '127.0.0.1', '2019-04-29 09:28:59');
INSERT INTO `sys_log` VALUES ('45', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"顶级菜单\",\"name\":\"一级菜单\",\"icon\":\"\",\"menuId\":29,\"orderNum\":0,\"perms\":\"\",\"type\":1,\"parentId\":0,\"url\":\"http://www.baidu.com\"}', '37', '127.0.0.1', '2019-04-29 09:29:49');
INSERT INTO `sys_log` VALUES ('46', 'root', '修改菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.update()', '{\"parentName\":\"顶级菜单\",\"name\":\"一级菜单\",\"icon\":\"\",\"menuId\":29,\"orderNum\":0,\"perms\":\"sys:*\",\"type\":1,\"parentId\":0,\"url\":\"http://www.baidu.com\"}', '34', '127.0.0.1', '2019-04-29 09:30:11');
INSERT INTO `sys_log` VALUES ('47', 'root', '保存菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.save()', '{\"parentName\":\"一级菜单\",\"name\":\"1\",\"icon\":\"\",\"menuId\":30,\"orderNum\":0,\"perms\":\"a\",\"type\":2,\"parentId\":29,\"url\":\"a\"}', '39', '127.0.0.1', '2019-04-29 09:31:19');
INSERT INTO `sys_log` VALUES ('48', 'root', '修改菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.update()', '{\"parentName\":\"一级菜单\",\"name\":\"1\",\"icon\":\"\",\"menuId\":30,\"orderNum\":0,\"perms\":\"azz\",\"type\":2,\"parentId\":29,\"url\":\"azz\"}', '40', '127.0.0.1', '2019-04-29 09:31:28');
INSERT INTO `sys_log` VALUES ('53', 'root', '删除日志', 'com.cd.shiro.modules.sys.controller.SysLogController.delete()', '[52,50,49]', '51', '127.0.0.1', '2019-04-29 09:38:04');
INSERT INTO `sys_log` VALUES ('54', 'root', '修改菜单', 'com.cd.shiro.modules.sys.controller.SysMenuController.update()', '{\"parentName\":\"顶级菜单\",\"name\":\"系统管理\",\"icon\":\"layui-icon-set\",\"menuId\":1,\"orderNum\":0,\"perms\":\"\",\"type\":0,\"parentId\":0,\"url\":\"\"}', '55', '127.0.0.1', '2019-04-29 09:49:47');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', 'layui-icon-set', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员管理', 'modules/user/list.html', '', '1', 'layui-icon-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/role/list.html', '', '1', 'layui-icon-component', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/menu/list.html', '', '1', 'layui-icon-menu-fill', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'Druid监控', 'http://127.0.0.1:8081/druid/sql.html', '', '1', 'layui-icon-vercode', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '日志管理', 'modules/log/list.html', '', '1', 'layui-icon-log', '5');
INSERT INTO `sys_menu` VALUES ('7', '2', '列表', '', 'sys:user:list', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('8', '2', '查看', '', 'sys:user:info', '2', '', '1');
INSERT INTO `sys_menu` VALUES ('9', '2', '新增', '', 'sys:user:save', '2', '', '2');
INSERT INTO `sys_menu` VALUES ('10', '2', '修改', '', 'sys:user:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('11', '2', '删除', '', 'sys:user:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('12', '2', '修改状态', '', 'sys:user:update:status', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('13', '2', '修改密码', '', 'sys:user:update:pass', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('14', '3', '列表', '', 'sys:role:list', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('15', '3', '选择角色', '', 'sys:role:select', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('16', '3', '查看', '', 'sys:role:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('17', '3', '新增', '', 'sys:role:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('18', '3', '修改', '', 'sys:role:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '删除', '', 'sys:role:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('20', '4', '列表', '', 'sys:menu:list', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('21', '4', '左侧导航', '', 'sys:menu:nav', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('22', '4', '菜单树', '', 'sys:menu:tree', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '选择菜单', '', 'sys:menu:select', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '查看', '', 'sys:menu:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '新增', '', 'sys:menu:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '修改', '', 'sys:menu:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('27', '4', '删除', '', 'sys:menu:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('28', '6', '列表', '', 'sys:log:list', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('31', '5', '列表', '', '', '2', '', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '拥有至高无上的权利', '2019-04-28 15:52:53');
INSERT INTO `sys_role` VALUES ('2', '管理员', '拥有基本的权限', '2019-04-28 16:26:30');
INSERT INTO `sys_role` VALUES ('3', '游客', '只能查看', '2019-04-28 16:27:26');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('62', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('63', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('64', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('65', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('66', '3', '14');
INSERT INTO `sys_role_menu` VALUES ('67', '3', '15');
INSERT INTO `sys_role_menu` VALUES ('68', '3', '16');
INSERT INTO `sys_role_menu` VALUES ('69', '3', '4');
INSERT INTO `sys_role_menu` VALUES ('70', '3', '20');
INSERT INTO `sys_role_menu` VALUES ('71', '3', '21');
INSERT INTO `sys_role_menu` VALUES ('72', '3', '22');
INSERT INTO `sys_role_menu` VALUES ('73', '3', '23');
INSERT INTO `sys_role_menu` VALUES ('74', '3', '24');
INSERT INTO `sys_role_menu` VALUES ('193', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('194', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('195', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('196', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('197', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('198', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('199', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('200', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('201', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('202', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('203', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('204', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('205', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('206', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('207', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('208', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('209', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('210', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('211', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('212', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('213', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('214', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('215', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('216', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('217', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('218', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('219', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('220', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('221', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('222', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('223', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('224', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('225', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('226', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('227', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('228', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('229', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('230', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('231', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('232', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('233', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('234', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('235', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('236', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('237', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('238', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('239', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('240', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('241', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('242', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('243', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('244', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('245', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('246', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('247', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('248', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('249', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('250', '2', '28');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人 （用户真实姓名）',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `remark` varchar(100) DEFAULT NULL COMMENT '用户备注',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `login_count` int(10) DEFAULT '0' COMMENT '登录次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `un_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'root', '超级管理员', 'root', '4ddee50a13b5e0e51f6ccc9cce50920e', null, null, '1', null, '127.0.0.1', '2019-04-29 16:11:01', '5', '2019-04-28 15:52:05');
INSERT INTO `sys_user` VALUES ('2', 'admin', '管理员', 'admin', '4ddee50a13b5e0e51f6ccc9cce50920e', '', '', '1', '', null, null, '0', '2019-04-28 16:28:08');
INSERT INTO `sys_user` VALUES ('3', 'guest', '游客', 'guest', '4ddee50a13b5e0e51f6ccc9cce50920e', '', '', '1', '', null, null, '0', '2019-04-28 16:29:15');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '3', '3');
INSERT INTO `sys_user_role` VALUES ('7', '4', '2');
INSERT INTO `sys_user_role` VALUES ('8', '4', '3');
