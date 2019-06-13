SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
                         `txhash` char(64) NOT NULL,
                         `blockhash` char(64) NOT NULL ,
                         `size` int ,
                         `fees` double,
                         `weight` float ,
                         `totaloutput` double,
                         `totalinput` double,
                         `amount` double,
                         `time` datetime ,
                         `confirmations` int,
                         PRIMARY KEY (`txhash`),
                         index `idx_time`(`time`),
                         index `idx_blockhash`(`blockhash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
