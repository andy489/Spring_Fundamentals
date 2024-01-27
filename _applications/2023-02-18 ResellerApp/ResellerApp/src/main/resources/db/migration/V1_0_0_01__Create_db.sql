CREATE SCHEMA IF NOT EXISTS `reseller`;
USE `reseller`;

CREATE TABLE `conditions`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT,
    `condition_name` varchar(255) NOT NULL,
    `description`    tinytext     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ed0h9vmunihdp91pbxxg8kqfr` (`condition_name`)
);

CREATE TABLE `users`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `email`     varchar(255) NOT NULL,
    `password`  varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
    UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`username`)
);

CREATE TABLE `offers`
(
    `id`           bigint         NOT NULL AUTO_INCREMENT,
    `description`  tinytext       NOT NULL,
    `price`        decimal(38, 2) NOT NULL,
    `buyer_id`     bigint DEFAULT NULL,
    `condition_id` bigint DEFAULT NULL,
    `owner_id`     bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKj8fwy778fiat9mcej9syh61kv` (`buyer_id`),
    KEY `FKf54d4y1781tpl4og1tejwnf7q` (`condition_id`),
    KEY `FKq1osn1fnc2f39porcvie25olp` (`owner_id`),
    CONSTRAINT `FKf54d4y1781tpl4og1tejwnf7q` FOREIGN KEY (`condition_id`) REFERENCES `conditions` (`id`),
    CONSTRAINT `FKj8fwy778fiat9mcej9syh61kv` FOREIGN KEY (`buyer_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKq1osn1fnc2f39porcvie25olp` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
);