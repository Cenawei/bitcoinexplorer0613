SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
                         `tx_hash` char(64) NOT NULL,
                         `block_hash` char(64) NOT NULL ,
                         `size` int ,
                         `weight` float ,
                         `output_total` double,
                         `input_total` double,
                         `transaction_fees` double,
                         `amount` double,
                         `time` datetime ,
                         `confirmations` int,
                         PRIMARY KEY (`tx_hash`),
                         index `idx_time`(`time`),
                         index `idx_block_hash`(`block_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
