CREATE TABLE `users`
(
    `id`           int                                                           NOT NULL AUTO_INCREMENT,
    `first_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `last_name`    varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `phone_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `image_path`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `email`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `enabled`      bit(1)                                                        NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `roles`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `name`        varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `user_role`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
    INDEX `fk_role` (`role_id` ASC) USING BTREE,
    CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `customers`
(
    `id`                  int                                                           NOT NULL AUTO_INCREMENT,
    `first_name`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `last_name`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `phone_number`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `email`               varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `password`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `created_time`        datetime                                                      NOT NULL,
    `authentication_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `verification_code`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `enabled`             bit(1)                                                        NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `banners`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `enabled`    bit(1)                                                        NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `brands`
(
    `id`         int                                                          NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `image_path` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `categories`
(
    `id`        int                                                          NOT NULL AUTO_INCREMENT,
    `name`      varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `alias`     varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `parent_id` int                                                          NULL DEFAULT NULL,
    `enabled`   bit(1)                                                       NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) USING BTREE,
    INDEX `fk_category_idx` (`parent_id` ASC) USING BTREE,
    CONSTRAINT `fk_category` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `brand_category`
(
    `brand_id`    int NOT NULL,
    `category_id` int NOT NULL,
    PRIMARY KEY (`brand_id`, `category_id`) USING BTREE,
    INDEX `fk_category_idx` (`category_id` ASC) USING BTREE,
    CONSTRAINT `fk_brand_category` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
    CONSTRAINT `fk_category_brand` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `products`
(
    `id`               int                                                           NOT NULL AUTO_INCREMENT,
    `main_image_path`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `alias`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `brand_id`         int                                                           NOT NULL,
    `category_id`      int                                                           NOT NULL,
    `description`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL,
    `created_time`     datetime                                                      NOT NULL,
    `updated_time`     datetime                                                      NULL DEFAULT NULL,
    `price`            float                                                         NOT NULL,
    `percent_discount` float                                                         NULL DEFAULT 0,
    `quantity`         int                                                           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) USING BTREE,
    INDEX `fk_product_brand_idx` (`brand_id` ASC) USING BTREE,
    INDEX `fk_product_category_idx` (`category_id` ASC) USING BTREE,
    CONSTRAINT `fk_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
    CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `product_details`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `value`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `product_id` int                                                           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_product_detail_idx` (`product_id` ASC) USING BTREE,
    CONSTRAINT `fk_product_detail` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `product_images`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `product_id` int                                                           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_product_image_idx` (`product_id` ASC) USING BTREE,
    CONSTRAINT `fk_product_image` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `status`
(
    `id`   int                                                          NOT NULL AUTO_INCREMENT,
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `product_status`
(
    `product_id` int NOT NULL,
    `status_id`  int NOT NULL,
    PRIMARY KEY (`product_id`, `status_id`) USING BTREE,
    INDEX `fk_status_product` (`status_id` ASC) USING BTREE,
    CONSTRAINT `fk_status_product` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `fk_product_status` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `reviews`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `comment`     varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `headline`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `rating`      int                                                           NOT NULL,
    `review_time` datetime                                                      NOT NULL,
    `votes`       int                                                           NOT NULL,
    `customer_id` int                                                           NOT NULL,
    `product_id`  int                                                           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_review_customer` (`customer_id` ASC) USING BTREE,
    INDEX `fk_review_product` (`product_id` ASC) USING BTREE,
    CONSTRAINT `fk_review_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `fk_review_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `orders`
(
    `id`             int      NOT NULL AUTO_INCREMENT,
    `customer_id`    int      NOT NULL,
    `order_time`     datetime NOT NULL,
    `payment_method` bit(1)   NOT NULL,
    `price`          float    NOT NULL,
    `discount`       float    NOT NULL,
    `tax`            float    NOT NULL,
    `total`          float    NOT NULL,
    `delivery_form`  bit(1)   NOT NULL,
    `status`         tinyint  NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_order_customer_idx` (`customer_id` ASC) USING BTREE,
    CONSTRAINT `fk_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `order_details`
(
    `id`           int   NOT NULL AUTO_INCREMENT,
    `order_id`     int   NOT NULL,
    `product_id`   int   NOT NULL,
    `quantity`     int   NOT NULL,
    `product_cost` float NOT NULL,
    `total`        float NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_order_detail_idx` (`order_id` ASC) USING BTREE,
    INDEX `fk_order_product_idx` (`product_id` ASC) USING BTREE,
    CONSTRAINT `fk_order_detail` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `addresses`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `street`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ward`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `district`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `city`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `user_id`     int                                                           NULL DEFAULT NULL,
    `customer_id` int                                                           NULL DEFAULT NULL,
    `order_id`    int                                                           NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_address_user` (`user_id` ASC) USING BTREE,
    INDEX `fk_address_customer` (`customer_id` ASC) USING BTREE,
    INDEX `fk_address_order` (`order_id` ASC) USING BTREE,
    CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `fk_address_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `fk_address_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `settings`
(
    `key`      varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `value`    varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `category` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL,
    PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;


