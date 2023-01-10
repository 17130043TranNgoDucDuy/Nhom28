-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 05, 2023 lúc 01:28 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ltw_ck`
--
CREATE DATABASE IF NOT EXISTS `ltw_ck` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ltw_ck`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `addresses`
--

INSERT INTO `addresses` (`id`, `street`, `ward`, `district`, `city`, `user_id`, `customer_id`, `order_id`) VALUES
(1, '134 Quang Trung', 'Phường 6', 'TP Cà Mau', 'Cà Mau', 2, NULL, NULL),
(2, '202/35 Phạm Văn Hai', 'Phường 15', 'Quận Tân Bình', 'Thành phố Hồ Chí Minh', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `banners`
--

CREATE TABLE `banners` (
  `id` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `banners`
--

INSERT INTO `banners` (`id`, `image_path`, `name`, `enabled`) VALUES
(1, '112.jpg', 'banner1', b'1'),
(2, '122.jpg', 'banner2', b'1'),
(3, '132.jpg', 'banner3', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `alias` varchar(45) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `alias`, `parent_id`, `enabled`) VALUES
(1, 'bông băng gạc', 'bông-băng-gạc', NULL, b'1'),
(2, 'dụng cụ hút sữa', 'dụng-cụ-hút-sữa', 1, b'1'),
(3, 'gel rửa tay', 'gel-rửa-tay', 1, b'1'),
(4, 'khẩu trang y tế', 'khẩu-trang-y-tế', 1, b'1'),
(5, 'máy đo đường huyết', 'máy-đo-đường-huyết', 1, b'1'),
(6, 'máy đo huyết áp', 'máy-đo-huyết-áp', 1, b'1'),
(7, 'nhiệt kế', 'nhiệt-kế', 1, b'1'),
(8, 'vật tư tiêu hao', 'vật-tư-tiêu-hao', 1, b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(64) NOT NULL,
  `created_time` datetime NOT NULL,
  `authentication_type` varchar(10) DEFAULT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`id`, `first_name`, `last_name`, `phone_number`, `email`, `password`, `created_time`, `authentication_type`, `verification_code`, `enabled`) VALUES
(1, 'huy', 'nguyen', '01201323', 'chung41901@gmail.com', '090179', '2023-01-04 19:20:09', NULL, NULL, b'1'),
(2, 'chung', 'nguyen', '0123456789', 'man@gmail.com', 'matkhau', '2022-12-30 10:26:07', NULL, NULL, b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_time` datetime NOT NULL,
  `payment_method` bit(1) NOT NULL,
  `price` float NOT NULL,
  `discount` double NOT NULL,
  `tax` float NOT NULL,
  `total` float NOT NULL,
  `delivery_form` bit(1) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_cost` float NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `main_image_path` varchar(255) NOT NULL,
  `name` varchar(256) NOT NULL,
  `alias` varchar(256) NOT NULL,
  `category_id` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `price` float NOT NULL,
  `percent_discount` float DEFAULT 0,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `main_image_path`, `name`, `alias`, `category_id`, `description`, `created_time`, `updated_time`, `price`, `percent_discount`, `quantity`) VALUES
(1, 'shopList/bong-bang-gac/band-aid-50.jpg', 'Băng cá nhân Fabric', 'Johnson & Johnson', 1, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 100000000, 0.8, 50),
(2, 'shopList/bong-bang-gac/bang-cuon-y-te.jpg', 'Băng cuộn y tế Đông Fa', 'Đông Pha', 3, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 70000000, 0.8, 50),
(3, 'shopList/bong-bang-gac/bang-cuon-y-te-dong-pha-bich-50-cuon-924.jpg', 'Băng cuộn y tế Đông Fa bịch 50', 'Đông Fa', 3, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 57000000, 0.8, 50),
(4, 'shopList/bong-bang-gac/bong-gon-y-te-bach-tuyet-45g-146.jpg\r\n', 'Bông gòn y tế Bạch Tuyết 100g', 'Bạch tuyết', 3, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 35000000, 0.8, 50),
(5, 'shopList/bong-bang-gac/bong-hut-nuoc-50g.jpg', 'Bông hút nước Baby 50gr', 'Baby', 3, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 85000000, 0.8, 50),
(6, 'shopList/bong-bang-gac/bong-hut-nuoc-trung-tin-100g.jpg', 'Bông hút nước trung tín 100gr', 'Trung tín', 1, 'Uy tín và chất lượng', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 55000000, 0.8, 50),
(7, 'shopList/bong-bang-gac/bong-y-te.jpg', 'bông y tế', 'bảo thạch', 1, 'uy tín chất lượng', '2023-01-04 15:02:28', '2023-01-25 21:02:29', 20000, 0.2, 50),
(8, 'shopList/bong-bang-gac/gac-y-te.jpg', 'gạc y tế', 'đông pha', 1, 'uy tín', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 10000, 0.2, 50),
(9, 'shopList/bong-bang-gac/gac-y-te-tiet-trung-bao-thach-8cm-x-10cm-x-12-lop-553.jpg', 'Gạc y tế Bảo Thạch 8cm x 10cm x 12 lớp, Hộp 10 miếng', 'bảo thạch', 1, 'uy tín', '2023-01-04 15:09:21', '2023-01-31 21:09:21', 10000, 0.2, 50),
(10, 'shopList/dung-cu-hut-sua/bo-dung-cu-hut-sua-tanako-0.jpg', 'Bộ dụng cụ hút sữa Tanako', 'Tanako', 2, 'uy tín', '2023-01-04 15:12:12', '2023-01-31 21:12:12', 20000, 0, 50),
(11, 'shopList/dung-cu-hut-sua/dem-massage-may-hut-sua-dien-sanity-ap154ae-284.jpg', 'Đệm massage máy hút sữa điện Sanity AP-154AE', 'Sanity', 2, 'uy tín', '2023-01-04 15:13:28', '2023-01-31 21:13:28', 20000, 0.2, 50);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_details`
--

CREATE TABLE `product_details` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `id` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_status`
--

CREATE TABLE `product_status` (
  `product_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
(1, 'quản trị viên', 'quản lý tất cả'),
(2, 'nhân viên bán hàng', 'quản lý sản phẩm'),
(3, 'nhân viên giao hàng', 'quản lý đơn hàng'),
(4, 'nhân viên biên tập', 'quản lý thể loại, nhãn hiệu, quảng cáo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `image_path` varchar(64) DEFAULT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `phone_number`, `image_path`, `email`, `password`, `enabled`) VALUES
(1, 'admin', 'admin', '0123456789', NULL, 'admin@gmail.com', '0192023a7bbd73250516f069df18b500', b'1'),
(2, 'Huy', 'Nguyen', '0123456789', 'author.jpg', 'chung41901@gmail.com', '090179huy', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(2, 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_address_user` (`user_id`) USING BTREE,
  ADD KEY `fk_address_customer` (`customer_id`) USING BTREE,
  ADD KEY `fk_address_order` (`order_id`) USING BTREE;

--
-- Chỉ mục cho bảng `banners`
--
ALTER TABLE `banners`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `name_UNIQUE` (`name`) USING BTREE,
  ADD KEY `fk_category_idx` (`parent_id`) USING BTREE;

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `email_UNIQUE` (`email`) USING BTREE;

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_order_customer_idx` (`customer_id`) USING BTREE;

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_order_detail_idx` (`order_id`) USING BTREE,
  ADD KEY `fk_order_product_idx` (`product_id`) USING BTREE;

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `name_UNIQUE` (`name`) USING BTREE,
  ADD KEY `fk_product_category_idx` (`category_id`) USING BTREE;

--
-- Chỉ mục cho bảng `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_product_detail_idx` (`product_id`) USING BTREE;

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_product_image_idx` (`product_id`) USING BTREE;

--
-- Chỉ mục cho bảng `product_status`
--
ALTER TABLE `product_status`
  ADD PRIMARY KEY (`product_id`,`status_id`) USING BTREE,
  ADD KEY `fk_status_product` (`status_id`) USING BTREE;

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `name_UNIQUE` (`name`) USING BTREE;

--
-- Chỉ mục cho bảng `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `email_UNIQUE` (`email`) USING BTREE;

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  ADD KEY `fk_role` (`role_id`) USING BTREE;

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `banners`
--
ALTER TABLE `banners`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `product_details`
--
ALTER TABLE `product_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `fk_address_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_address_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION;

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_order_detail` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION;

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION;

--
-- Các ràng buộc cho bảng `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `fk_product_detail` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `fk_product_image` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `product_status`
--
ALTER TABLE `product_status`
  ADD CONSTRAINT `fk_product_status` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_status_product` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
