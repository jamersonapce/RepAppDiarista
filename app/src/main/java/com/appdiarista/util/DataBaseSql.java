package com.appdiarista.util;

/**
 * Created by FR£D on 06/09/2017.
 */

public class DataBaseSql {

    public static String SQL = "CREATE TABLE IF NOT EXISTS `appDiarista`.`tipoUsuario` (" +
            "  `id` INT NOT NULL COMMENT ''," +
            "  `descricao` VARCHAR(45) NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`)  COMMENT ''," +
            "  UNIQUE INDEX `descricao_UNIQUE` (`descricao` ASC)  COMMENT '')" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`usuario` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT COMMENT ''," +
            "  `login` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `senha` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `tipoUsuario_id` INT NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`, `tipoUsuario_id`)  COMMENT ''," +
            "  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT ''," +
            "  INDEX `fk_usuario_tipoUsuario1_idx` (`tipoUsuario_id` ASC)  COMMENT ''," +
            "  CONSTRAINT `fk_usuario_tipoUsuario1`" +
            "    FOREIGN KEY (`tipoUsuario_id`)" +
            "    REFERENCES `appDiarista`.`tipoUsuario` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`diarista` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT COMMENT ''," +
            "  `nome` VARCHAR(200) NOT NULL COMMENT ''," +
            "  `telefone` VARCHAR(20) NOT NULL COMMENT ''," +
            "  `dataNascimento` DATE NULL COMMENT ''," +
            "  `cpf` VARCHAR(14) NULL COMMENT ''," +
            "  `email` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `sobreMim` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `valorDiaria` DECIMAL(7,2) NOT NULL COMMENT ''," +
            "  `latitude` DOUBLE NULL COMMENT ''," +
            "  `longitude` DOUBLE NULL COMMENT ''," +
            "  `usuario_id` INT NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`)  COMMENT ''," +
            "  INDEX `fk_diarista_usuario1_idx` (`usuario_id` ASC)  COMMENT ''," +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT ''," +
            "  CONSTRAINT `fk_diarista_usuario1`" +
            "    FOREIGN KEY (`usuario_id`)" +
            "    REFERENCES `appDiarista`.`usuario` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`contratante` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT COMMENT ''," +
            "  `nome` VARCHAR(200) NOT NULL COMMENT ''," +
            "  `telefone` VARCHAR(20) NOT NULL COMMENT ''," +
            "  `dataNascimento` DATE NULL COMMENT ''," +
            "  `cpf` VARCHAR(14) NULL COMMENT ''," +
            "  `email` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `sobreMim` VARCHAR(255) NOT NULL COMMENT ''," +
            "  `latitude` DOUBLE NULL COMMENT ''," +
            "  `longitude` DOUBLE NULL COMMENT ''," +
            "  `usuario_id` INT NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`)  COMMENT ''," +
            "  INDEX `fk_contratante_usuario1_idx` (`usuario_id` ASC)  COMMENT ''," +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT ''," +
            "  CONSTRAINT `fk_contratante_usuario1`" +
            "    FOREIGN KEY (`usuario_id`)" +
            "    REFERENCES `appDiarista`.`usuario` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`contrato` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT COMMENT ''," +
            "  `dataSolicitacao` DATE NULL COMMENT ''," +
            "  `dataAceito` DATE NULL COMMENT ''," +
            "  `diarista_id` INT NOT NULL COMMENT ''," +
            "  `contratante_id` INT NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`)  COMMENT ''," +
            "  INDEX `fk_contrato_diarista_idx` (`diarista_id` ASC)  COMMENT ''," +
            "  INDEX `fk_contrato_contratante1_idx` (`contratante_id` ASC)  COMMENT ''," +
            "  CONSTRAINT `fk_contrato_diarista`" +
            "    FOREIGN KEY (`diarista_id`)" +
            "    REFERENCES `appDiarista`.`diarista` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `fk_contrato_contratante1`" +
            "    FOREIGN KEY (`contratante_id`)" +
            "    REFERENCES `appDiarista`.`contratante` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`diaria` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT COMMENT ''," +
            "  `data` DATE NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`id`)  COMMENT '')" +
            "ENGINE = InnoDB;" +
            " " +
            "CREATE TABLE IF NOT EXISTS `appDiarista`.`contrato_diaria` (" +
            "  `contrato_id` INT NOT NULL COMMENT ''," +
            "  `diaria_id` INT NOT NULL COMMENT ''," +
            "  PRIMARY KEY (`contrato_id`, `diaria_id`)  COMMENT ''," +
            "  INDEX `fk_contrato_has_diaria_diaria1_idx` (`diaria_id` ASC)  COMMENT ''," +
            "  INDEX `fk_contrato_has_diaria_contrato1_idx` (`contrato_id` ASC)  COMMENT ''," +
            "  CONSTRAINT `fk_contrato_has_diaria_contrato1`" +
            "    FOREIGN KEY (`contrato_id`)" +
            "    REFERENCES `appDiarista`.`contrato` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `fk_contrato_has_diaria_diaria1`" +
            "    FOREIGN KEY (`diaria_id`)" +
            "    REFERENCES `appDiarista`.`diaria` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB;";
}
