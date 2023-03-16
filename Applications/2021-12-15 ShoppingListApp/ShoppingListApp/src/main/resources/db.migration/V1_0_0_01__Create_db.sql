CREATE SCHEMA IF NOT EXISTS `shopping_list`;
USE
`shopping_list`;

CREATE TABLE `categories`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
);

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

CREATE TABLE `products`
(
    `id`            bigint         NOT NULL AUTO_INCREMENT,
    `description`   varchar(255)   NOT NULL,
    `name`          varchar(255)   NOT NULL,
    `needed_before` datetime(6) NOT NULL,
    `price`         decimal(38, 2) NOT NULL,
    `category_id`   bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`),
    KEY             `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
    CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
);