###########################弹幕相关表#########################
CREATE TABLE `danmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) DEFAULT NULL COMMENT '对应user的id，初始为sessionid',
  `view_id` int(11) DEFAULT NULL COMMENT '对应全景图的id',
  `contents` varchar(255) DEFAULT NULL COMMENT '弹幕内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `x` double DEFAULT NULL COMMENT '备用字段，横坐标',
  `y` double DEFAULT NULL COMMENT '备用字段, 纵坐标',
  `color` varchar(7) DEFAULT '#FFFFFF' COMMENT 'rgb值',
  `zan` int DEFAULT 0 COMMENT '点赞次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 点赞关联表,danmu_id和user_id作为联合主键，防止重复点赞
 */
CREATE TABLE danmu_zan (
  `danmu_id` int,
  `user_id` int,
  `time` TIMESTAMP DEFAULT current_timestamp COMMENT '保留字段，点赞时间',
  PRIMARY KEY (`danmu_id`,`user_id`)
)COMMENT '点赞关联表';
#########################弹幕评论相关表#######################
/**
 * 弹幕可以被任何人评论，评论可以被回复，回复又可以回复~~~
 * 想想就好麻烦，没有设计了，以后真的要做这个模块再设计
 */

#########################全景图相关表#########################
#全景图
CREATE TABLE `view`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20),
  `desc` VARCHAR(50) COMMENT '图片描述',
  `path` VARCHAR(50) COMMENT '图片访问地址',
  `min_view` VARCHAR(50) COMMENT '图片对应的略缩图地址'
)COMMENT '全景图';

/**
 * from view 可以到to view，在from的(x,y)坐标中显示to的导航
 */
CREATE TABLE `neighbor_view`(
  `from` INT,
  `to` INT,
  `x` double DEFAULT NULL COMMENT '备用字段，横坐标',
  `y` double DEFAULT NULL COMMENT '备用字段, 纵坐标',
  PRIMARY KEY (`from`,`to`)
) COMMENT '景点关联图，from view 可以到to view，在from的(x,y)坐标显示to的导航';


#场景
CREATE TABLE `scene`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20),
  `desc` VARCHAR(50) COMMENT '场景描述'
);

CREATE TABLE `neighbor_scene`(
  `from` INT,
  `to` INT,
  `view_id` INT,
  `x` double DEFAULT NULL COMMENT '备用字段，横坐标',
  `y` double DEFAULT NULL COMMENT '备用字段, 纵坐标',
  PRIMARY KEY (`from`,`to`)
)COMMENT '场景关联图，from scene可以到to scene，在from的view_id view中的(x,y)坐标显示to的导航';

############################分享界面####################################

#添加has_motto
  #分享图
CREATE TABLE `shot`(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `date` DATE COMMENT '时间',
  `location` VARCHAR(10) COMMENT '地点',
  `path` VARCHAR(50) COMMENT '图片访问地址',
  `has_motto` INT COMMENT '0为否，1为真',
  `desc` VARCHAR(50) COMMENT '备用字段，在选择到此图片的时候，可以弹出描述框'
)COMMENT '时间地点可供选择，在图片右下角一起显示';

#场景--分享图关联表,1:n
CREATE TABLE `scene_shot`(
  `scene_id` INT,
  `shot_id` INT,
  `share_times` INT COMMENT '备用字段，可以作为分享次数',
  PRIMARY KEY (`scene_id`, `shot_id`)
)COMMENT '场景分享图';

############################用户相关表###################################
#用户
CREATE TABLE `user`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20),
  `password` VARCHAR(20),
  `gender` VARCHAR(1) COMMENT '性别，男，女',
  `class_name` VARCHAR(10) COMMENT '班级名称',
  `icon` VARCHAR(50) COMMENT '图片访问地址',
  `city` VARCHAR(10) COMMENT '保留字段,城市,来自的城市或者未来所在城市',
  `profile` VARCHAR(30) COMMENT '保留字段，可作个性签名等'
);