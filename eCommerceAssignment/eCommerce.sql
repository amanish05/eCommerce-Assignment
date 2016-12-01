--------------------------------------------------------
--  DDL for Table CLIENT
--------------------------------------------------------

CREATE TABLE `client` (
  `CLIENT_PGUID` varchar(50) NOT NULL DEFAULT '',
  `USER_ID` varchar(500) DEFAULT NULL,
  `PASSCODE` varchar(500) DEFAULT NULL,
  `CREATED_DATETIME` DATE,
  PRIMARY KEY (`CLIENT_PGUID`)
)

--------------------------------------------------------
--  DDL for Index USER_ID_UNIQUE
--------------------------------------------------------

  ALTER TABLE  client ADD UNIQUE INDEX USER_ID_UNIQUE (USER_ID);
  ALTER TABLE `cs3220stu40`.`client` CHANGE COLUMN `PASSCODE` `PASSCODE` VARCHAR(500) NULL DEFAULT 
  
  
--------------------------------------------------------
--  DDL for INVENTORY 
--------------------------------------------------------

CREATE TABLE `inventory` (
	`INVENTORY_PGUID` int NOT NULL AUTO_INCREMENT ,
	`INVENTORY_NAME` varchar( 50 ) DEFAULT NULL ,
	`INVENTORY_DESC` varchar( 250 ) DEFAULT NULL ,
	`INVENTORY_PRICE_PER_UNIT` FLOAT( 8 ) DEFAULT NULL ,
	`AVAILABLE_QUANTITY` INTEGER( 5 ) DEFAULT NULL ,
	`CREATED_DATETIME` DATE,
	PRIMARY KEY ( `INVENTORY_PGUID` )
)

--------------------------------------------------------
--  DDL for Table INVENTORY_TRANSACTION
--------------------------------------------------------


CREATE TABLE IF NOT EXISTS `inventory_transactions` (
  `ORDER_NUMBER` varchar(50) NOT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `CUSTOMER_EMAIL` varchar(50) DEFAULT NULL,
  `INVENTORY_PGUID` varchar(50) DEFAULT NULL,
  `INVENTORY_NAME` varchar(50) DEFAULT NULL,
  `INVENTORY_DESC` varchar(250) DEFAULT NULL,
  `INVENTORY_PRICE_PER_UNIT` float DEFAULT NULL,
  `ORDERED_QUANTITY` int(5) DEFAULT NULL,
  `AMOUNT` float DEFAULT NULL,
  `ORDERED_DATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;