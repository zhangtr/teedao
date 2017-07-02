CREATE DATABASE  IF NOT EXISTS `rbac` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rbac`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: rbac
-- ------------------------------------------------------
-- Server version	5.5.51

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oplog`
--

DROP TABLE IF EXISTS `oplog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oplog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '管理员ID',
  `site_no` varchar(16) DEFAULT NULL COMMENT '站点编号',
  `content` text NOT NULL COMMENT '操作的具体内容',
  `ip` varchar(15) DEFAULT NULL COMMENT '登录者的IP地址',
  `create_time` bigint(13) NOT NULL COMMENT '操作时间',
  `operate_type` varchar(64) NOT NULL,
  `status` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_oplogs_mid` (`user_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='管理员操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oplog`
--

LOCK TABLES `oplog` WRITE;
/*!40000 ALTER TABLE `oplog` DISABLE KEYS */;
INSERT INTO `oplog` VALUES (1,1,'teedao-rbac','传入参数[{}] java.lang.Long@7e994dc1[value=253]  com.teedao.rbac.param.ResourceParam@4a8e0288[siteNo=teedao-admin,name=二级目录,type=menu,url=/demo,parentId=253,permission=demo:*,icon=,available=true,orderNo=1,length=<null>,start=<null>,draw=0]','0:0:0:0:0:0:0:1',1498925261957,'ResourceController.create(..)','SUCCESS'),(2,1,'teedao-rbac','传入参数[{}] com.teedao.rbac.param.RoleParam@1ec71659[siteNo=teedao-admin,role=后台管理系统管理员,description=后台管理系统管理员,resourceIds=254,available=true,length=<null>,start=<null>,draw=0]','0:0:0:0:0:0:0:1',1498925297024,'RoleController.create(..)','SUCCESS'),(3,1,'teedao-rbac','传入参数[{}] java.lang.Long@4c87547c[value=1]  com.teedao.rbac.param.UserParam@3bea74aa[username=admin,password=<null>,realname=管理员,mobile=12233223r5,email=187s96@qq.com,roleIds=1,25,locked=false,length=<null>,start=<null>,draw=0]','0:0:0:0:0:0:0:1',1498925305347,'UserController.update(..)','SUCCESS');
/*!40000 ALTER TABLE `oplog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site_no` varchar(16) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `order_no` int(11) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL,
  `last_modify_time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_resource_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,'teedao-rbac','用户权限管理','menu','#',0,'','fa-users',1,54,1498924610000,1498924610000),(21,'teedao-rbac','用户管理','menu','/user',1,'user:*','',1,21,1498924610000,1498924610000),(22,'teedao-rbac','用户新增','button','',21,'user:create',NULL,1,22,1498924610000,1498924610000),(23,'teedao-rbac','用户修改','button','',21,'user:update',NULL,1,23,1498924610000,1498924610000),(24,'teedao-rbac','用户删除','button','',21,'user:delete',NULL,1,24,1498924610000,1498924610000),(25,'teedao-rbac','用户查看','button','',21,'user:view',NULL,1,25,1498924610000,1498924610000),(31,'teedao-rbac','资源管理','menu','/resource',1,'resource:*','',1,41,1498924610000,1498924610000),(32,'teedao-rbac','资源新增','button','',31,'resource:create',NULL,1,32,1498924610000,1498924610000),(33,'teedao-rbac','资源修改','button','',31,'resource:update',NULL,1,33,1498924610000,1498924610000),(34,'teedao-rbac','资源删除','button','',31,'resource:delete',NULL,1,34,1498924610000,1498924610000),(35,'teedao-rbac','资源查看','button','',31,'resource:view',NULL,1,35,1498924610000,1498924610000),(41,'teedao-rbac','角色管理','menu','/role',1,'role:*','',1,31,1498924610000,1498924610000),(42,'teedao-rbac','角色新增','button','',41,'role:create',NULL,1,42,1498924610000,1498924610000),(43,'teedao-rbac','角色修改','button','',41,'role:update',NULL,1,43,1498924610000,1498924610000),(44,'teedao-rbac','角色删除','button','',41,'role:delete',NULL,1,44,1498924610000,1498924610000),(45,'teedao-rbac','角色查看','button','',41,'role:view',NULL,1,45,1498924610000,1498924610000),(54,'teedao-rbac','站点管理','menu','/site',1,'site:*','',1,51,1440146948717,1440146967943),(55,'teedao-rbac','站点查询','button','',54,'site:view','',1,1,1440146998361,1440146998361),(56,'teedao-rbac','站点添加','button','',54,'site:create','',1,2,1440147034826,1440147034826),(57,'teedao-rbac','站点修改','button','',54,'site:update','',1,3,1440147060258,1440147060258),(58,'teedao-rbac','站点删除','button','',54,'site:delete','',1,4,1440147086721,1440147086721),(253,'teedao-admin','一级目录','menu','#',0,NULL,'fa-users',1,NULL,1498925224209,1498925224209),(254,'teedao-admin','二级目录','menu','/demo',253,'demo:*','',1,1,1498925261951,1498925261951);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site_no` varchar(16) NOT NULL,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `create_time` bigint(13) DEFAULT NULL,
  `last_modify_time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_role_resource_ids` (`resource_ids`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'teedao-rbac','权限系统角色','权限系统角色','21,41,31,54',1,1440385364670,1470197470051),(25,'teedao-admin','后台管理系统管理员','后台管理系统管理员','254',1,1498925297018,1498925297018);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site_no` varchar(16) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(80) DEFAULT NULL,
  `client_id` varchar(36) DEFAULT NULL,
  `client_secret` varchar(36) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL,
  `last_modify_time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (2,'teedao-rbac','权限管理系统','','74ab8766-359e-4e67-a79c-449dba0d4df4','5a04465d-fdc0-4f74-9f9f-45d79ba9b7eb',1479796219478,1498924158342),(10,'teedao-admin','后台管理系统','/','88d2d777-db89-4892-8685-7c92718ee302','197e3408-3488-4d47-81d6-93e0cd5d7b67',1491009779124,1498924189563);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `create_time` bigint(13) DEFAULT NULL,
  `last_modify_time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','d3c59d25033dbf980d29554025c23a75','管理员','12233223r5','187s96@qq.com','8d78869f470951332959580424d4bf4f','1,25',0,1442475090847,1498925305340);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-02 17:11:58
