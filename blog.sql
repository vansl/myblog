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
  `role` VARCHAR(20) DEFAULT 'user' COMMENT '用户角色',
  UNIQUE (`username`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 博客表
--
CREATE TABLE `blog` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `title` VARCHAR(90) NOT NULL COMMENT '博客标题',
  `time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `published` INT(1) NOT NULL COMMENT '是否已发表，0已发表，1未发表（在草稿箱）',
  `content` TEXT NOT NULL COMMENT '博客正文（包含html格式）',
  `text` TEXT NOT NULL COMMENT '博客正文（纯文本）',
  `pv`Int NOT NULL COMMENT '博客点击量',
  `user_id` INT NOT NULL NULL COMMENT '用户ID',
  `type_id` INT NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 博客分类表
--
CREATE TABLE `blog_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `type_name` VARCHAR(30) NOT NULL COMMENT '分类名称',
  `parent_id` INT NOT NULL COMMENT '父分类id',
  `user_id` INT NOT NULL COMMENT '用户ID',
  UNIQUE (`type_name`,`parent_id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

--
-- 评论表
--
CREATE TABLE `blog_comment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `time` DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `name` VARCHAR(30) NOT NULL COMMENT '评论者',
  `contact` VARCHAR(90) NOT NULL COMMENT '联系方式',
  `ip` VARCHAR(15) NOT NULL COMMENT '评论者ip',
  `address` TINYTEXT NOT NULL COMMENT '评论者地址',
  `content` TEXT NOT NULL COMMENT '评论内容',
  `blog_id` INT NOT NULL COMMENT '博客ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;