CREATE TABLE IF NOT EXISTS `user_permission` (
  `id_user_security` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user_security`,`id_permission`),
  KEY `fk_user_permission_permission` (`id_permission`),
  CONSTRAINT `fk_user_permission` FOREIGN KEY (`id_user_security`) REFERENCES `user_security` (`id`),
  CONSTRAINT `fk_user_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB;