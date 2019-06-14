SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for block
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
                         `block_hash` char(64) NOT NULL,
                         `height` int NOT NULL ,
                         `size` int ,
                         `difficulty` double,
                         `weight` float ,
                         `next_block` CHAR(64),
                         `prev_block` CHAR(64),
                         `time` datetime,
                         `txSize` smallint ,
                         `output_total` double ,
                         `transaction_fees` double,
                         `merkle_root` varchar(100),
                         `miner` varchar (50),
                         PRIMARY KEY (`block_hash`),
                         index `idx_time`(`time`),
                         index `idx_height`(`height`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
