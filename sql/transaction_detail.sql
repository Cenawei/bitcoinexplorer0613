SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction_detail
-- ----------------------------
DROP TABLE IF EXISTS `transaction_detail`;
CREATE TABLE `transaction_detail` (
                         `tx_detail_id` Bigint NOT NULL auto_increment ,
                         `txhash` char(64) NOT NULL ,
                         `adress` varchar (100),
                         `fees` double,
                         `weight` float ,
                         `totaloutput` double,
                         `totalinput` double,
                         `amount` double,
                         `type` tinyint ,
                         `size` int,
                         PRIMARY KEY (`tx_detail_id`),
                         index `idx_txhash`(`txhash`),
                         index `idx_adress`(`adress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
