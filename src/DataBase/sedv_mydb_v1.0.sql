-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`categoria` ;

CREATE TABLE IF NOT EXISTS `mydb`.`categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`unidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`unidade` ;

CREATE TABLE IF NOT EXISTS `mydb`.`unidade` (
  `idUnidade` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao_unidade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUnidade`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`produto` ;

CREATE TABLE IF NOT EXISTS `mydb`.`produto` (
  `Cod_Produto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco_unitario` DOUBLE NOT NULL,
  `unidade` INT(11) NULL DEFAULT '1',
  `categoria` INT(11) NULL DEFAULT '1',
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  `foto` BLOB NULL,
  PRIMARY KEY (`Cod_Produto`),
  INDEX `fk_Produto_Unidade_idx` (`unidade` ASC) VISIBLE,
  INDEX `fk_produto_catgoria1` (`categoria` ASC) VISIBLE,
  CONSTRAINT `produto_ibfk_1`
    FOREIGN KEY (`unidade`)
    REFERENCES `mydb`.`unidade` (`idUnidade`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `produto_ibfk_2`
    FOREIGN KEY (`categoria`)
    REFERENCES `mydb`.`categoria` (`idCategoria`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `Cod_Funcionario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NULL DEFAULT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `esta_autenticado` CHAR(5) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`Cod_Funcionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`venda` ;

CREATE TABLE IF NOT EXISTS `mydb`.`venda` (
  `Cod_venda` INT(11) NOT NULL AUTO_INCREMENT,
  `data_venda` DATE NOT NULL,
  `total_venda` DOUBLE NOT NULL,
  `nome_cliente` VARCHAR(45) NOT NULL,
  `usuario_Cod_Funcionario` INT(11) NULL DEFAULT NULL,
  `nuit_cliente` VARCHAR(45) NULL,
  PRIMARY KEY (`Cod_venda`),
  INDEX `fk_venda_usuario1` (`usuario_Cod_Funcionario` ASC) VISIBLE,
  CONSTRAINT `venda_ibfk_1`
    FOREIGN KEY (`usuario_Cod_Funcionario`)
    REFERENCES `mydb`.`usuario` (`Cod_Funcionario`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`detalhesvenda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`detalhesvenda` ;

CREATE TABLE IF NOT EXISTS `mydb`.`detalhesvenda` (
  `Produto_Cod_Produto` INT(11) NOT NULL,
  `Venda_Cod_venda` INT(11) NOT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  `qauntidade_vendida` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Produto_Cod_Produto`, `Venda_Cod_venda`),
  INDEX `fk_Produto_has_Venda_Venda1_idx` (`Venda_Cod_venda` ASC) VISIBLE,
  INDEX `fk_Produto_has_Venda_Produto1_idx` (`Produto_Cod_Produto` ASC) VISIBLE,
  CONSTRAINT `detalhesvenda_ibfk_3`
    FOREIGN KEY (`Produto_Cod_Produto`)
    REFERENCES `mydb`.`produto` (`Cod_Produto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `detalhesvenda_ibfk_4`
    FOREIGN KEY (`Venda_Cod_venda`)
    REFERENCES `mydb`.`venda` (`Cod_venda`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`previlegio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`previlegio` ;

CREATE TABLE IF NOT EXISTS `mydb`.`previlegio` (
  `prodPodeRegistar` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `prodPodeAlterarDados` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `ProdPodeAdicionarStock` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `ProdPodeRegistarCateg` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `ProdPodeRegistarUnida` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `relVendasRecentes` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `relVendasPorDaata` VARCHAR(6) NOT NULL DEFAULT 'nao',
  `relProdutosMaisVendidos` VARCHAR(6) NOT NULL DEFAULT 'nao')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`stock` ;

CREATE TABLE IF NOT EXISTS `mydb`.`stock` (
  `idstock` INT NOT NULL,
  `quantidade_recebida` INT NULL,
  `data_entrada` DATE NULL,
  `produto_Cod_Produto` INT(11) NOT NULL,
  `usuario_Cod_Funcionario` INT(11) NOT NULL,
  `numero_lote` VARCHAR(45) NULL,
  `fabricante` VARCHAR(45) NULL,
  PRIMARY KEY (`idstock`),
  INDEX `fk_stock_produto1_idx` (`produto_Cod_Produto` ASC) VISIBLE,
  INDEX `fk_stock_usuario1_idx` (`usuario_Cod_Funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_stock_produto1`
    FOREIGN KEY (`produto_Cod_Produto`)
    REFERENCES `mydb`.`produto` (`Cod_Produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stock_usuario1`
    FOREIGN KEY (`usuario_Cod_Funcionario`)
    REFERENCES `mydb`.`usuario` (`Cod_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stocklevel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`stocklevel` ;

CREATE TABLE IF NOT EXISTS `mydb`.`stocklevel` (
  `idstocklevel` INT NOT NULL,
  `unidades_stock` INT NULL,
  `produto_Cod_Produto` INT(11) NOT NULL,
  PRIMARY KEY (`idstocklevel`),
  INDEX `fk_stocklevel_produto1_idx` (`produto_Cod_Produto` ASC) VISIBLE,
  CONSTRAINT `fk_stocklevel_produto1`
    FOREIGN KEY (`produto_Cod_Produto`)
    REFERENCES `mydb`.`produto` (`Cod_Produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;

-- -----------------------------------------------------
-- procedure AdicionarStock
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`AdicionarStock`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdicionarStock`(in codProduto int, IN quant int )
BEGIN
UPDATE  `mydb`.`produto` SET  `QuantidadeStock` =`QuantidadeStock`+quant  WHERE  `produto`.`Cod_Produto` =codProduto;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ApagarProduto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ApagarProduto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarProduto`(IN Codigo int)
BEGIN
DELETE FROM `mydb`.`produto` WHERE `Cod_Produto` = Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ApagarUnidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ApagarUnidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUnidade`(IN idUnid int)
BEGIN
  
  DELETE FROM `unidade` WHERE idUnidade=idUnid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ApagarUsuario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ApagarUsuario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUsuario`(IN `Cod_Funcionario` int)
BEGIN
  
  DELETE FROM `usuario` WHERE Cod_Funcionario=Cod_Funcionario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Apagarcategoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Apagarcategoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Apagarcategoria`(IN Codigo int)
BEGIN
     DELETE FROM `mydb`.`categoria` WHERE `categoria`.`idCategoria` =Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure AtualizaCategoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`AtualizaCategoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizaCategoria`(IN Codigo int,IN Nom varchar(50))
BEGIN
UPDATE `categoria` SET `Nome` = Nom  WHERE `idCategoria` = Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure CadProduto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`CadProduto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CadProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Quantidade int,IN Unidad int,IN Categor int,IN Descric varchar(50))
BEGIN
INSERT INTO `produto`(`Cod_Produto`, `Nome`, `PrecoUnitario`, `QuantidadeStock`, `Unidade`, `Categoria`, `Descricao`) VALUES (Null, Nom ,Preco ,Quantidade ,Unidad ,Categor ,Descric );
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure CodVendaCorrente
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`CodVendaCorrente`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CodVendaCorrente`()
begin 
SELECT  `Cod_venda` 
FROM venda
ORDER BY  `Cod_venda` DESC 
LIMIT 1;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure DETALHESVENDA
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`DETALHESVENDA`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DETALHESVENDA`(CODP INT, IN CODV INT, IN PREC DOUBLE, IN QUANT INT)
BEGIN
INSERT INTO `mydb`.`detalhesvenda` (`Produto_Cod_Produto`, `Venda_Cod_venda`, `preco`, `Quantidade`) VALUES (CODP, CODV, PREC, QUANT);
UPDATE  `mydb`.`produto` SET  `QuantidadeStock` =`QuantidadeStock`-QUANT   WHERE  `produto`.`Cod_Produto` =CODP;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure DetalhesProdutos
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`DetalhesProdutos`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetalhesProdutos`(in codProduto int )
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, P.`Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade, U.Descricao_Unidade AS Unidade, C.Nome as Categoria
 FROM produto P, categoria C, unidade U
 WHERE `Cod_Produto`=codProduto and P.`Unidade`=U.idUnidade and P.`Categoria`=C.idCategoria	;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure EditarProduto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`EditarProduto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Quantidade int,IN Unidad int,IN Categor int,IN Descric varchar(50))
BEGIN
UPDATE `produto` SET  `Nome`=Nom,  `PrecoUnitario`=Preco,  `QuantidadeStock`=Quantidade,  `Unidade`=Unidad,  `Categoria`=Categor,  `Descricao`=Descric WHERE `produto`.`Cod_Produto` = Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure EditarUnidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`EditarUnidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarUnidade`(IN Codigo int,IN Descricao varchar(50))
BEGIN
UPDATE `unidade` SET `Descricao_Unidade` = Descricao WHERE `idUnidade` = Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Editarusuario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Editarusuario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Editarusuario`(IN Codigo int,IN Nom varchar(50),IN Pass varchar(50),IN Cat varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `Nome` =  Nom,
`Senha` = Pass   ,
`Categoria` = Cat    WHERE  `usuario`.`Cod_Funcionario` =Codigo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure GetCategoriaUsuario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`GetCategoriaUsuario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCategoriaUsuario`(IN username varchar(20), IN pass varchar(20) )
BEGIN
SELECT  `Categoria` ,`Cod_Funcionario`
FROM `usuario` WHERE `Nome`=username AND `Senha`=pass;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTARPREVILEGIOS
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`LISTARPREVILEGIOS`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `LISTARPREVILEGIOS`()
BEGIN
SELECT * FROM `previlegio` WHERE 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ListarCategoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ListarCategoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarCategoria`()
BEGIN
SELECT `idCategoria` AS Codigo,`Nome` AS Categoria FROM `categoria`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ListarProdutos
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ListarProdutos`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarProdutos`()
BEGIN
  SELECT * FROM `produto` ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ListarUnidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ListarUnidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUnidade`()
BEGIN
  SELECT `idUnidade` AS codigo, `Descricao_Unidade` AS Unidade
FROM  `unidade`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ListarUsuarios
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ListarUsuarios`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUsuarios`()
BEGIN
  SELECT * FROM `usuario` ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Login
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Login`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Login`(IN username varchar (50), IN pass varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'nao' ;
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'sim' WHERE  `Nome`=username and `Senha`=pass;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Logout
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Logout`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Logout`()
BEGIN
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'nao' ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PesquisaProduto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`PesquisaProduto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaProduto`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `produto` where Nome= "%Nome%";
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PesquisaUnidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`PesquisaUnidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUnidade`(IN `Descricao_Unidade` varchar(50))
BEGIN
  SELECT * FROM  `produto` where Nome= "%Descricao_Unidade%";
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PesquisaUsuario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`PesquisaUsuario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUsuario`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `unidade` where Nome= "%Nome%";
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Populacombocategoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Populacombocategoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombocategoria`()
BEGIN
       SELECT  `Nome` FROM `categoria`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Populacombounidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`Populacombounidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombounidade`()
BEGIN
       SELECT Descricao_Unidade FROM `unidade`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PrevilegiosFuncionario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`PrevilegiosFuncionario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PrevilegiosFuncionario`(in a varchar(6),in b varchar(6),in c varchar(6),in d varchar(6),in e varchar(6),in f varchar(6),in g varchar(6),in h varchar(6))
BEGIN
UPDATE  `mydb`.`previlegio` SET 
 `prodPodeRegistar` =  a,
`prodPodeAlterarDados` =  b,
`ProdPodeAdicionarStock` =  c,
`ProdPodeRegistarCateg`=d,
`ProdPodeRegistarUnida`=e,
`relVendasRecentes` =  f,
`relVendasPorDaata` =  g,
`relProdutosMaisVendidos` =  h;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProcurarProdutos
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`ProcurarProdutos`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProcurarProdutos`(in param varchar (40))
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, `Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade
 FROM produto 
 WHERE `Cod_Produto`  like CONCAT('%', param, '%') or `Nome` like CONCAT('%', param, '%') ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure SearchProdutoVenda
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`SearchProdutoVenda`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchProdutoVenda`(in Param varchar(50))
BEGIN
 SELECT distinct  p.`Cod_Produto` AS Codigo,p.`Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade,U.Descricao_Unidade as Unidade, C.Nome as Categoria, p.`Descricao`
 FROM produto p inner join unidade U on  p.Unidade=U.idUnidade 
inner join categoria C on  p.Categoria=C.idCategoria 
Where  `Cod_Produto`  like CONCAT('%', param, '%') or p.`Nome` like CONCAT('%', param, '%');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure TodosProdutos
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`TodosProdutos`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TodosProdutos`()
BEGIN
 SELECT  `Cod_Produto` AS Codigo,P. `Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade, U.Descricao_Unidade as Unidade, C.Nome as Categoria, P.`Descricao`
 FROM produto P INNER JOIN unidade U on P.Unidade=U.idUnidade INNER JOIN categoria C on P.Categoria=C.idCategoria;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure UpdateProduto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`UpdateProduto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateProduto`(IN codp int, IN nomep varchar(50), IN preco double, IN quantS int, IN unidad int, IN cat int,IN descr varchar(200))
BEGIN
 UPDATE `mydb`.`produto` SET `Nome` =nomep , `PrecoUnitario`=preco , `QuantidadeStock` = quantS , `Unidade` =unidad, `Categoria` = cat , `Descricao` =  descr 
 WHERE `produto`.`Cod_Produto` = codp;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure UsuarioAutenticado
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`UsuarioAutenticado`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UsuarioAutenticado`(IN name Varchar(50))
BEGIN
  SELECT `Cod_Funcionario`  FROM  usuario   WHERE `Nome`=name;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure VENDA
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`VENDA`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VENDA`( IN TOTAL DOUBLE, IN NOMEC VARCHAR(50) ,IN FUNC INT )
BEGIN
INSERT INTO `mydb`.`venda` (`Cod_venda`, `Data_venda`, `Total_venda`, `Nome_Cliente`, `usuario_Cod_Funcionario`) VALUES (NULL, CURDATE(), TOTAL, NOMEC, FUNC);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure atualizaunidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`atualizaunidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `atualizaunidade`(IN Cod int, IN desnidade varchar(40))
BEGIN
UPDATE  `mydb`.`unidade` SET  `Descricao_Unidade` = desnidade WHERE  `unidade`.`idUnidade` =Cod;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure capturaId
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`capturaId`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaId`(IN Nome varchar(50))
BEGIN
             Select `idUnidade` From `unidade` Where `Descricao_Unidade`= Nome;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure capturaIdcategoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`capturaIdcategoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaIdcategoria`(IN Nom varchar(50))
BEGIN
             Select `idCategoria` From `categoria` Where `Nome`= Nom;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_categoria
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`proc_categoria`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_categoria`(IN Nom varchar(50))
BEGIN
INSERT INTO `mydb`.`categoria` (`idCategoria`, `Nome`) VALUES (NULL, Nom);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_produto
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`proc_produto`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_produto`(IN Cod_Produto int,IN Nome varchar(50),IN PrecoUnitario Double,IN QuantidadeStock int,IN Unidade_Cod_unidade int,IN Categoria int,IN Descricao varchar(50))
BEGIN
 
 INSERT INTO `produto`(`Cod_Produto`,`Nome`,`PrecoUnitario`,`QuantidadeStock`,`Unidade_Cod_unidade`,`Categoria`,`Descricao `) VALUES(NULL,Nome ,PrecoUnitario,QuantidadeStock,Unidade_Cod_unidade,Categoria);
 
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_unidade
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`proc_unidade`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_unidade`(IN descr varchar(50))
BEGIN
    INSERT INTO `mydb`.`unidade` (idUnidade,`Descricao_Unidade`) VALUES (NULL,descr);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_usuario
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`proc_usuario`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_usuario`(IN Cod_Funcionario int,IN Nome  varchar(50),IN  Senha varchar(50),IN  Categoria varchar(50))
BEGIN
    INSERT INTO `usuario`(`Cod_Funcionario`, `Nome`, `Senha`, `Categoria`) VALUES (NULL, Nome , Senha, Categoria);
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
