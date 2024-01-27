# CREATE SCHEMA IF NOT EXISTS `battleships`;
USE `battleships`;

CREATE TABLE `users`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `email`     varchar(255) NOT NULL,
    `full_name` varchar(255) NOT NULL,
    `password`  varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
    UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`username`)
);

CREATE TABLE `categories`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `name`          int NOT NULL,
    `description`   tinytext,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_5le3ghmfrckg818rfx1xja57o` (`name`)
);

CREATE TABLE `ships`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `created`     datetime(6)  NOT NULL,
    `health`      int NOT NULL,
    `name`        varchar(255) NOT NULL,
    `power`       int NOT NULL,
    `category_id` bigint DEFAULT NULL,
    `owner_id`    bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_kqu32l8579oi7igta353wh26u` (`name`),
    KEY `FKhpxdty95vdyn2wejm4ieelb0g` (`category_id`),
    KEY `FKt9oyqysdfcu0u0aq64rd82q3s` (`owner_id`),
    CONSTRAINT `FKhpxdty95vdyn2wejm4ieelb0g` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
    CONSTRAINT `FKt9oyqysdfcu0u0aq64rd82q3s` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
);