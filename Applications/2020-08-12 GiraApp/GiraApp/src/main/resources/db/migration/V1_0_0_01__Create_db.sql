CREATE SCHEMA IF NOT EXISTS `gira`;
USE `gira`;

CREATE TABLE `users`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
    UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
);

CREATE TABLE `classifications`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `tasks`
(
    `id`                bigint       NOT NULL AUTO_INCREMENT,
    `description`       varchar(255) NOT NULL,
    `due_date`          datetime(6)  NOT NULL,
    `name`              varchar(255) NOT NULL,
    `progress`          varchar(255) NOT NULL,
    `assigned_to_id`    bigint DEFAULT NULL,
    `classification_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_s90mh6056fm69w0ilwka74vut` (`name`),
    KEY `FK4516wfa828r15k9u3iw5er4vi` (`assigned_to_id`),
    KEY `FK7je5hdc7jkvmncyoxxu8xdnyu` (`classification_id`),
    CONSTRAINT `FK4516wfa828r15k9u3iw5er4vi` FOREIGN KEY (`assigned_to_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FK7je5hdc7jkvmncyoxxu8xdnyu` FOREIGN KEY (`classification_id`) REFERENCES `classifications` (`id`)
);