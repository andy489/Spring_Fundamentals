CREATE SCHEMA IF NOT EXISTS `planner`;
USE `planner`;

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

CREATE TABLE `priorities`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `tasks`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT,
    `description`    varchar(255) NOT NULL,
    `due_date`       datetime(6)  NOT NULL,
    `assigned_to_id` bigint DEFAULT NULL,
    `priority_id`    bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK4516wfa828r15k9u3iw5er4vi` (`assigned_to_id`),
    KEY `FKnq0d4mra8tpuwwak86ctvhfsb` (`priority_id`),
    CONSTRAINT `FK4516wfa828r15k9u3iw5er4vi` FOREIGN KEY (`assigned_to_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKnq0d4mra8tpuwwak86ctvhfsb` FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`id`)
);