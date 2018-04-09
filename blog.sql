/**
 * @DATE 20:47 2018/3/21
 */

--
-- 数据库: `myblog`
--

--
-- 用户表
--
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(30) NOT NULL COMMENT '用户名',
  `password` VARCHAR(30) NOT NULL COMMENT '用户密码',
  `role` VARCHAR(20) DEFAULT 'user' COMMENT '用户权限',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 博客表
--
CREATE TABLE `blog` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `title` VARCHAR(90) NOT NULL COMMENT '博客标题',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `content` text NOT NULL COMMENT '博客正文',
  `pv` int NOT NULL COMMENT '博客点击量',
  `user_id` int NOT NULL COMMENT '用户ID',
  `type_id` int NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 博客分类表
--
CREATE TABLE `blog_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category` VARCHAR(30) NOT NULL COMMENT '一级分类',
  `type` VARCHAR(30) NOT NULL COMMENT '二级分类',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 评论表
--
CREATE TABLE `blog_comment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `time` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `name` VARCHAR(30) NOT NULL COMMENT '评论者',
  `contact` VARCHAR(90) NOT NULL COMMENT '联系方式',
  `ip` VARCHAR(15) NOT NULL COMMENT '评论者ip地址',
  `content` text NOT NULL COMMENT '评论内容',
  `blog_id` int NOT NULL COMMENT '博客ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;