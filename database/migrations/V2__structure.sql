
CREATE TABLE `procjson`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_producer` INT NULL,
  `type` VARCHAR(50) NULL,
  `status` VARCHAR(50) NULL,
  `message` TEXT(100) NULL,
  `creationDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `procjson`.`info_head` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `date` DATE NULL,
  `items` INT NULL,
  `creationDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `procjson`.`info_detail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_head` INT NOT NULL,
  `item_number` INT NOT NULL,
  `item_description` VARCHAR(250) NULL,
  `item_value` DECIMAL(10,2) NULL,
  `creationDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_info_detail_info_head_1`
    FOREIGN KEY (`id_head`)
    REFERENCES `procjson`.`info_head` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
