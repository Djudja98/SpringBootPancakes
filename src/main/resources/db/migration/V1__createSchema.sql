CREATE SCHEMA IF NOT EXISTS `pancakes` DEFAULT CHARACTER SET utf8 ;
USE `pancakes` ;


CREATE TABLE IF NOT EXISTS `pancakes`.`category` (
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `pancakes`.`ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DECIMAL NOT NULL,
  `isHealthy` TINYINT(1) NOT NULL,
  `category` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ingredient_category_idx` (`category` ASC) VISIBLE,
  CONSTRAINT `fk_ingredient_category`
    FOREIGN KEY (`category`)
    REFERENCES `pancakes`.`category` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `pancakes`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `pancakes`.`pancake` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pancake_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_pancake_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `pancakes`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `pancakes`.`pancake_ingredient` (
  `pancake_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  PRIMARY KEY (`pancake_id`, `ingredient_id`),
  INDEX `fk_pancake_has_ingredient_ingredient1_idx` (`ingredient_id` ASC) VISIBLE,
  INDEX `fk_pancake_has_ingredient_pancake1_idx` (`pancake_id` ASC) VISIBLE,
  CONSTRAINT `fk_pancake_has_ingredient_pancake1`
    FOREIGN KEY (`pancake_id`)
    REFERENCES `pancakes`.`pancake` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pancake_has_ingredient_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `pancakes`.`ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;