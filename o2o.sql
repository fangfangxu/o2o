USE o2o;
CREATE TABLE `tb_area`(
	`area_id` INT (2) NOT NULL auto_increment,
	`area_name` VARCHAR (200) NOT NULL,
	`priority` INT (2) NOT NULL DEFAULT '0',
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`area_id`),
UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;
