SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction_detail
-- ----------------------------
DROP TABLE IF EXISTS `transaction_detail`;
CREATE TABLE `transaction_detail` (
                         `tx_detail_id` Bigint NOT NULL auto_increment ,
                         `tx_hash` char(64) NOT NULL ,
                         `adress` varchar (100),
                         `transaction_fees` double,
                         `weight` float ,
                         `output_total` double,
                         `input_total` double,
                         `amount` double,
                         `type` tinyint ,
                         `size` int,
                         PRIMARY KEY (`tx_detail_id`),
                         index `idx_tx_hash`(`tx_hash`),
                         index `idx_adress`(`adress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
