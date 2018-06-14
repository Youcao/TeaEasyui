CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_number` int(11) NOT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;


INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(33, 1, 1, 1);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(34, 9, 10, 11);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(35, 3, 3, 4);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(36, 3, 2, 1);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(37, 4, 5, 6);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(38, 5, 6, 7);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(39, 6, 7, 8);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(40, 7, 8, 9);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(41, 8, 9, 10);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(42, 10, 11, 12);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(43, 11, 12, 13);
INSERT INTO cart
(cart_id, user_id, product_id, product_number)
VALUES(44, 12, 13, 14);
