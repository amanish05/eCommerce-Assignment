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
  `INVENTORY_PGUID` varchar(50) NOT NULL DEFAULT '',
  `INVENTORY_NAME` varchar(50) DEFAULT NULL,
  `INVENTORY_DESC` varchar(250) DEFAULT NULL,
  `INVENTORY_PRICE_PER_UNIT` FLOAT(8) DEFAULT NULL,
  `AVAILABLE_QUANTITY` INTEGER(5) DEFAULT NULL,
  `CREATED_DATETIME` DATE,
  PRIMARY KEY (`INVENTORY_PGUID`)
)