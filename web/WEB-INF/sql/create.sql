CREATE TABLE IF NOT EXISTS `resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` blob NOT NULL,
  `body_vi` blob NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `resource_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` text NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `user_usergroup` (
 `user_id` INT NOT NULL ,
 `group_id` INT NOT NULL ,
 PRIMARY KEY (`user_id`, `group_id`) ,
 INDEX `fk_user_has_group_group1` (`user_id` ASC) ,
 INDEX `fk_user_has_group_user1` (`group_id` ASC) ,
 CONSTRAINT `fk_user_has_group_user1`
 FOREIGN KEY (`user_id` )
 REFERENCES `user` (`id` )
 ON DELETE NO ACTION
 ON UPDATE NO ACTION,
 CONSTRAINT `fk_user_has_group_group1`
 FOREIGN KEY (`group_id` )
 REFERENCES `user_group` (`id` )
 ON DELETE NO ACTION
 ON UPDATE NO ACTION)
ENGINE = InnoDB;