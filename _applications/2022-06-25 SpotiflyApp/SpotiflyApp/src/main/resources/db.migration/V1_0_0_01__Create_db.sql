CREATE SCHEMA IF NOT EXISTS `spotify`;
USE `spotify`;

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

CREATE TABLE `styles`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_t66pj9594i558j902po967bfq` (`name`)
);

CREATE TABLE `songs`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `duration`     int          NOT NULL,
    `performer`    varchar(255) NOT NULL,
    `release_date` date         NOT NULL,
    `title`        varchar(255) NOT NULL,
    `style_id`     bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKrmpfs3krhyicwu4ia52m3i744` (`style_id`),
    CONSTRAINT `FKrmpfs3krhyicwu4ia52m3i744` FOREIGN KEY (`style_id`) REFERENCES `styles` (`id`)
);

CREATE TABLE `users_songs`
(
    `users_id` bigint NOT NULL,
    `songs_id` bigint NOT NULL,
    PRIMARY KEY (`users_id`, `songs_id`),
    KEY `FKhd5jnagwlkb5jcnj06ubybr4i` (`songs_id`),
    CONSTRAINT `FKh8ld7yqe71ko0mkoio1lxa8gi` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKhd5jnagwlkb5jcnj06ubybr4i` FOREIGN KEY (`songs_id`) REFERENCES `songs` (`id`)
);