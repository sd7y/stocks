SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily`  (
                          `id` bigint(11) NOT NULL AUTO_INCREMENT,
                          `ts_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '股票代码',
                          `trade_date` datetime NULL DEFAULT NULL COMMENT '交易日期',
                          `open` float NULL DEFAULT NULL COMMENT '开盘价',
                          `high` float NULL DEFAULT NULL COMMENT '最高价',
                          `low` float NULL DEFAULT NULL COMMENT '最低价',
                          `close` float NULL DEFAULT NULL COMMENT '收盘价',
                          `pre_close` float NULL DEFAULT NULL COMMENT '昨收价【除权价，前复权】',
                          `change` float NULL DEFAULT NULL COMMENT '涨跌额',
                          `pct_chg` float NULL DEFAULT NULL COMMENT '涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】',
                          `vol` float NULL DEFAULT NULL COMMENT '成交量(手)',
                          `amount` float NULL DEFAULT NULL COMMENT '成交额(千元)',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 643287 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '股票日线行情' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
