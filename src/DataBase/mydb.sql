/*
SQLyog Job Agent Version 10.0 Beta1 Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.6.48-log : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `categoria` */

insert  into `categoria` values (13,'Alimentares'),(14,'Cosmeticos'),(15,'Limpeza'),(16,'Outro');

/*Table structure for table `detalhesvenda` */

DROP TABLE IF EXISTS `detalhesvenda`;

CREATE TABLE `detalhesvenda` (
  `Produto_Cod_Produto` int(11) NOT NULL,
  `Venda_Cod_venda` int(11) NOT NULL,
  `preco` double DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`Produto_Cod_Produto`,`Venda_Cod_venda`),
  KEY `fk_Produto_has_Venda_Venda1_idx` (`Venda_Cod_venda`),
  KEY `fk_Produto_has_Venda_Produto1_idx` (`Produto_Cod_Produto`),
  CONSTRAINT `detalhesvenda_ibfk_3` FOREIGN KEY (`Produto_Cod_Produto`) REFERENCES `produto` (`Cod_Produto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalhesvenda_ibfk_4` FOREIGN KEY (`Venda_Cod_venda`) REFERENCES `venda` (`Cod_venda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detalhesvenda` */

insert  into `detalhesvenda` values (11,1,180,1),(11,2,180,1),(11,4,180,1),(11,5,180,1),(11,8,180,1),(11,10,360,2),(11,11,180,1),(11,14,360,2),(11,15,180,1),(11,16,180,1),(11,17,180,1),(11,27,180,1),(12,2,234,1),(12,3,234,1),(12,4,234,1),(12,8,234,1),(12,15,234,1),(12,16,234,1),(12,18,234,1),(12,28,234,1),(12,31,234,1),(12,33,234,1),(12,34,234,1),(14,1,180,4),(14,2,45,1),(14,3,45,1),(14,4,45,1),(14,6,45,1),(14,7,90,2),(14,10,180,4),(14,11,45,1),(14,12,180,4),(14,15,45,1),(14,18,90,2),(14,20,45,1),(14,21,45,1),(14,28,90,2),(14,30,45,1),(14,31,45,1),(14,34,45,1),(16,7,240,2),(16,9,120,1),(16,10,120,1),(16,11,120,1),(16,19,120,1),(16,22,120,1),(16,23,120,1),(16,33,120,1),(17,11,120,1),(17,12,240,2),(17,14,240,2),(17,16,240,2),(17,18,120,1),(17,21,120,1),(18,12,360,2),(18,13,360,2),(18,15,360,2);

/*Table structure for table `previlegio` */

DROP TABLE IF EXISTS `previlegio`;

CREATE TABLE `previlegio` (
  `prodPodeRegistar` varchar(6) NOT NULL DEFAULT 'nao',
  `prodPodeAlterarDados` varchar(6) NOT NULL DEFAULT 'nao',
  `ProdPodeAdicionarStock` varchar(6) NOT NULL DEFAULT 'nao',
  `ProdPodeRegistarCateg` varchar(6) NOT NULL DEFAULT 'nao',
  `ProdPodeRegistarUnida` varchar(6) NOT NULL DEFAULT 'nao',
  `relVendasRecentes` varchar(6) NOT NULL DEFAULT 'nao',
  `relVendasPorDaata` varchar(6) NOT NULL DEFAULT 'nao',
  `relProdutosMaisVendidos` varchar(6) NOT NULL DEFAULT 'nao'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `previlegio` */

insert  into `previlegio` values ('nao','nao','sim','sim','sim','sim','sim','sim');

/*Table structure for table `produto` */

DROP TABLE IF EXISTS `produto`;

CREATE TABLE `produto` (
  `Cod_Produto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `PrecoUnitario` double NOT NULL,
  `QuantidadeStock` int(11) NOT NULL,
  `Unidade` int(11) DEFAULT '1',
  `Categoria` int(11) DEFAULT '1',
  `Descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Cod_Produto`),
  KEY `fk_Produto_Unidade_idx` (`Unidade`),
  KEY `fk_produto_catgoria1` (`Categoria`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`Unidade`) REFERENCES `unidade` (`idUnidade`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`Categoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `produto` */

insert  into `produto` values (11,'Nivea For Man',180,10,10,13,NULL),(12,'Sabao Maeva',234,5,12,14,''),(14,'Bolachas Maria',45,11,10,13,'Bolachas de leite'),(16,'Cafe Milo',120,11,10,13,''),(17,'Tomate Sause',120,11,10,13,''),(18,'Worse',180,7,10,13,'Carne moida');

/*Table structure for table `unidade` */

DROP TABLE IF EXISTS `unidade`;

CREATE TABLE `unidade` (
  `idUnidade` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao_Unidade` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnidade`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `unidade` */

insert  into `unidade` values (10,'Kg'),(11,'Singular'),(12,'Caixa');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `Cod_Funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Senha` varchar(45) DEFAULT NULL,
  `Categoria` varchar(45) NOT NULL,
  `EstaAutenticado` char(5) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`Cod_Funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario` values (7,'Admin','1234','Gerente','nao'),(8,'user','0000','Funcionario','nao');

/*Table structure for table `venda` */

DROP TABLE IF EXISTS `venda`;

CREATE TABLE `venda` (
  `Cod_venda` int(11) NOT NULL AUTO_INCREMENT,
  `Data_venda` date NOT NULL,
  `Total_venda` double NOT NULL,
  `Nome_Cliente` varchar(45) NOT NULL,
  `usuario_Cod_Funcionario` int(11) DEFAULT NULL,
  PRIMARY KEY (`Cod_venda`),
  KEY `fk_venda_usuario1` (`usuario_Cod_Funcionario`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`usuario_Cod_Funcionario`) REFERENCES `usuario` (`Cod_Funcionario`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

/*Data for the table `venda` */

insert  into `venda` values (1,'2012-12-31',368,'Osvaldo Samuel.\\',7),(2,'2012-12-31',463,'Matilde',7),(3,'2012-12-31',279,'asv',8),(4,'2012-12-31',459,'Neusia',7),(5,'2012-12-31',180,'eu',7),(6,'2012-12-31',65,'ewsdx',7),(7,'2013-01-06',330,'Agnaldo',7),(8,'2013-01-06',414,'Le Van Hoang',7),(9,'2013-01-06',124,'Bonomar',7),(10,'2013-01-06',660,'Hilario',8),(11,'2013-01-07',465,'Massango',8),(12,'2013-01-09',780,'Jurisa',7),(13,'2013-01-09',360,'dsgv',7),(14,'2013-01-09',604,'zsdf',7),(15,'2013-01-10',831,'Bonomar',8),(16,'2013-01-10',654,'Neusia',8),(17,'2013-01-10',180,'ads',7),(18,'2013-01-11',444,'hoang',7),(19,'2013-01-11',124,' fbxc',7),(20,'2013-01-11',45,'czb ',7),(21,'2013-01-11',165,'afdsc',7),(22,'2013-01-11',120,'xzc xzc ',7),(23,'2013-01-11',120,'adsc',7),(24,'2013-01-11',4,'asczx ',7),(25,'2013-01-11',4,'ac',7),(26,'2013-01-11',4,'cszx',7),(27,'2013-01-11',180,'sDVzx',7),(28,'2013-01-13',324,'Lous',7),(29,'2013-01-13',4,'xc ',7),(30,'2013-01-14',57,'asfdgf',7),(31,'2013-01-14',1179,'qD',7),(32,'2013-01-14',8,'aef',7),(33,'2013-01-14',804,'fbxd',7),(34,'2013-01-14',287,'saf',7),(35,'2013-01-14',450,'Hipolito',7),(36,'2013-01-14',112,'HIpolito',7),(37,'2013-01-14',46,'eqf',7);

/* Procedure structure for procedure `AdicionarStock` */

/*!50003 DROP PROCEDURE IF EXISTS  `AdicionarStock` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `AdicionarStock`(in codProduto int, IN quant int )
BEGIN
UPDATE  `mydb`.`produto` SET  `QuantidadeStock` =`QuantidadeStock`+quant  WHERE  `produto`.`Cod_Produto` =codProduto;
END */$$
DELIMITER ;

/* Procedure structure for procedure `Apagarcategoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `Apagarcategoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Apagarcategoria`(IN Codigo int)
BEGIN
     DELETE FROM `mydb`.`categoria` WHERE `categoria`.`idCategoria` =Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ApagarProduto` */

/*!50003 DROP PROCEDURE IF EXISTS  `ApagarProduto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarProduto`(IN Codigo int)
BEGIN
DELETE FROM `mydb`.`produto` WHERE `Cod_Produto` = Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ApagarUnidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `ApagarUnidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUnidade`(IN idUnid int)
BEGIN
  
  DELETE FROM `unidade` WHERE idUnidade=idUnid;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ApagarUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `ApagarUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUsuario`(IN `Cod_Funcionario` int)
BEGIN
  
  DELETE FROM `usuario` WHERE Cod_Funcionario=Cod_Funcionario;
END */$$
DELIMITER ;

/* Procedure structure for procedure `AtualizaCategoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `AtualizaCategoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizaCategoria`(IN Codigo int,IN Nom varchar(50))
BEGIN
UPDATE `categoria` SET `Nome` = Nom  WHERE `idCategoria` = Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `atualizaunidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `atualizaunidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `atualizaunidade`(IN Cod int, IN desnidade varchar(40))
BEGIN
UPDATE  `mydb`.`unidade` SET  `Descricao_Unidade` = desnidade WHERE  `unidade`.`idUnidade` =Cod;
END */$$
DELIMITER ;

/* Procedure structure for procedure `CadProduto` */

/*!50003 DROP PROCEDURE IF EXISTS  `CadProduto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CadProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Quantidade int,IN Unidad int,IN Categor int,IN Descric varchar(50))
BEGIN
INSERT INTO `produto`(`Cod_Produto`, `Nome`, `PrecoUnitario`, `QuantidadeStock`, `Unidade`, `Categoria`, `Descricao`) VALUES (Null, Nom ,Preco ,Quantidade ,Unidad ,Categor ,Descric );
END */$$
DELIMITER ;

/* Procedure structure for procedure `capturaId` */

/*!50003 DROP PROCEDURE IF EXISTS  `capturaId` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaId`(IN Nome varchar(50))
BEGIN
             Select `idUnidade` From `unidade` Where `Descricao_Unidade`= Nome;
END */$$
DELIMITER ;

/* Procedure structure for procedure `capturaIdcategoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `capturaIdcategoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaIdcategoria`(IN Nom varchar(50))
BEGIN
             Select `idCategoria` From `categoria` Where `Nome`= Nom;
END */$$
DELIMITER ;

/* Procedure structure for procedure `CodVendaCorrente` */

/*!50003 DROP PROCEDURE IF EXISTS  `CodVendaCorrente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `CodVendaCorrente`()
begin 
SELECT  `Cod_venda` 
FROM venda
ORDER BY  `Cod_venda` DESC 
LIMIT 1;
end */$$
DELIMITER ;

/* Procedure structure for procedure `DetalhesProdutos` */

/*!50003 DROP PROCEDURE IF EXISTS  `DetalhesProdutos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DetalhesProdutos`(in codProduto int )
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, P.`Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade, U.Descricao_Unidade AS Unidade, C.Nome as Categoria
 FROM produto P, categoria C, unidade U
 WHERE `Cod_Produto`=codProduto and P.`Unidade`=U.idUnidade and P.`Categoria`=C.idCategoria	;
END */$$
DELIMITER ;

/* Procedure structure for procedure `DETALHESVENDA` */

/*!50003 DROP PROCEDURE IF EXISTS  `DETALHESVENDA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DETALHESVENDA`(CODP INT, IN CODV INT, IN PREC DOUBLE, IN QUANT INT)
BEGIN
INSERT INTO `mydb`.`detalhesvenda` (`Produto_Cod_Produto`, `Venda_Cod_venda`, `preco`, `Quantidade`) VALUES (CODP, CODV, PREC, QUANT);
UPDATE  `mydb`.`produto` SET  `QuantidadeStock` =`QuantidadeStock`-QUANT   WHERE  `produto`.`Cod_Produto` =CODP;
END */$$
DELIMITER ;

/* Procedure structure for procedure `EditarProduto` */

/*!50003 DROP PROCEDURE IF EXISTS  `EditarProduto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Quantidade int,IN Unidad int,IN Categor int,IN Descric varchar(50))
BEGIN
UPDATE `produto` SET  `Nome`=Nom,  `PrecoUnitario`=Preco,  `QuantidadeStock`=Quantidade,  `Unidade`=Unidad,  `Categoria`=Categor,  `Descricao`=Descric WHERE `produto`.`Cod_Produto` = Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `EditarUnidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `EditarUnidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarUnidade`(IN Codigo int,IN Descricao varchar(50))
BEGIN
UPDATE `unidade` SET `Descricao_Unidade` = Descricao WHERE `idUnidade` = Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `Editarusuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `Editarusuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Editarusuario`(IN Codigo int,IN Nom varchar(50),IN Pass varchar(50),IN Cat varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `Nome` =  Nom,
`Senha` = Pass   ,
`Categoria` = Cat    WHERE  `usuario`.`Cod_Funcionario` =Codigo;
END */$$
DELIMITER ;

/* Procedure structure for procedure `GetCategoriaUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `GetCategoriaUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCategoriaUsuario`(IN username varchar(20), IN pass varchar(20) )
BEGIN
SELECT  `Categoria` ,`Cod_Funcionario`
FROM `usuario` WHERE `Nome`=username AND `Senha`=pass;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ListarCategoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `ListarCategoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarCategoria`()
BEGIN
SELECT `idCategoria` AS Codigo,`Nome` AS Categoria FROM `categoria`;
END */$$
DELIMITER ;

/* Procedure structure for procedure `LISTARPREVILEGIOS` */

/*!50003 DROP PROCEDURE IF EXISTS  `LISTARPREVILEGIOS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `LISTARPREVILEGIOS`()
BEGIN
SELECT * FROM `previlegio` WHERE 1;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ListarProdutos` */

/*!50003 DROP PROCEDURE IF EXISTS  `ListarProdutos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarProdutos`()
BEGIN
  SELECT * FROM `produto` ;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ListarUnidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `ListarUnidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUnidade`()
BEGIN
  SELECT `idUnidade` AS codigo, `Descricao_Unidade` AS Unidade
FROM  `unidade`;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ListarUsuarios` */

/*!50003 DROP PROCEDURE IF EXISTS  `ListarUsuarios` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUsuarios`()
BEGIN
  SELECT * FROM `usuario` ;
END */$$
DELIMITER ;

/* Procedure structure for procedure `Login` */

/*!50003 DROP PROCEDURE IF EXISTS  `Login` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Login`(IN username varchar (50), IN pass varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'nao' ;
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'sim' WHERE  `Nome`=username and `Senha`=pass;
END */$$
DELIMITER ;

/* Procedure structure for procedure `Logout` */

/*!50003 DROP PROCEDURE IF EXISTS  `Logout` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Logout`()
BEGIN
UPDATE  `mydb`.`usuario` SET  `EstaAutenticado` =  'nao' ;
END */$$
DELIMITER ;

/* Procedure structure for procedure `PesquisaProduto` */

/*!50003 DROP PROCEDURE IF EXISTS  `PesquisaProduto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaProduto`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `produto` where Nome= "%Nome%";
END */$$
DELIMITER ;

/* Procedure structure for procedure `PesquisaUnidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `PesquisaUnidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUnidade`(IN `Descricao_Unidade` varchar(50))
BEGIN
  SELECT * FROM  `produto` where Nome= "%Descricao_Unidade%";
END */$$
DELIMITER ;

/* Procedure structure for procedure `PesquisaUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `PesquisaUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUsuario`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `unidade` where Nome= "%Nome%";
END */$$
DELIMITER ;

/* Procedure structure for procedure `Populacombocategoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `Populacombocategoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombocategoria`()
BEGIN
       SELECT  `Nome` FROM `categoria`;
END */$$
DELIMITER ;

/* Procedure structure for procedure `Populacombounidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `Populacombounidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombounidade`()
BEGIN
       SELECT Descricao_Unidade FROM `unidade`;
END */$$
DELIMITER ;

/* Procedure structure for procedure `PrevilegiosFuncionario` */

/*!50003 DROP PROCEDURE IF EXISTS  `PrevilegiosFuncionario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `PrevilegiosFuncionario`(in a varchar(6),in b varchar(6),in c varchar(6),in d varchar(6),in e varchar(6),in f varchar(6),in g varchar(6),in h varchar(6))
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
END */$$
DELIMITER ;

/* Procedure structure for procedure `ProcurarProdutos` */

/*!50003 DROP PROCEDURE IF EXISTS  `ProcurarProdutos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ProcurarProdutos`(in param varchar (40))
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, `Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade
 FROM produto 
 WHERE `Cod_Produto`  like CONCAT('%', param, '%') or `Nome` like CONCAT('%', param, '%') ;
END */$$
DELIMITER ;

/* Procedure structure for procedure `proc_categoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `proc_categoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_categoria`(IN Nom varchar(50))
BEGIN
INSERT INTO `mydb`.`categoria` (`idCategoria`, `Nome`) VALUES (NULL, Nom);
END */$$
DELIMITER ;

/* Procedure structure for procedure `proc_produto` */

/*!50003 DROP PROCEDURE IF EXISTS  `proc_produto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_produto`(IN Cod_Produto int,IN Nome varchar(50),IN PrecoUnitario Double,IN QuantidadeStock int,IN Unidade_Cod_unidade int,IN Categoria int,IN Descricao varchar(50))
BEGIN
 
 INSERT INTO `produto`(`Cod_Produto`,`Nome`,`PrecoUnitario`,`QuantidadeStock`,`Unidade_Cod_unidade`,`Categoria`,`Descricao `) VALUES(NULL,Nome ,PrecoUnitario,QuantidadeStock,Unidade_Cod_unidade,Categoria);
 
END */$$
DELIMITER ;

/* Procedure structure for procedure `proc_unidade` */

/*!50003 DROP PROCEDURE IF EXISTS  `proc_unidade` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_unidade`(IN descr varchar(50))
BEGIN
    INSERT INTO `mydb`.`unidade` (idUnidade,`Descricao_Unidade`) VALUES (NULL,descr);
END */$$
DELIMITER ;

/* Procedure structure for procedure `proc_usuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `proc_usuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_usuario`(IN Cod_Funcionario int,IN Nome  varchar(50),IN  Senha varchar(50),IN  Categoria varchar(50))
BEGIN
    INSERT INTO `usuario`(`Cod_Funcionario`, `Nome`, `Senha`, `Categoria`) VALUES (NULL, Nome , Senha, Categoria);
END */$$
DELIMITER ;

/* Procedure structure for procedure `SearchProdutoVenda` */

/*!50003 DROP PROCEDURE IF EXISTS  `SearchProdutoVenda` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchProdutoVenda`(in Param varchar(50))
BEGIN
 SELECT distinct  P.`Cod_Produto` AS Codigo,P.`Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade,U.Descricao_Unidade as Unidade, C.Nome as Categoria, p.`Descricao`
 FROM produto P inner join unidade U on  P.Unidade=U.idUnidade 
inner join categoria C on  P.Categoria=C.idCategoria 
Where  `Cod_Produto`  like CONCAT('%', param, '%') or P.`Nome` like CONCAT('%', param, '%');
END */$$
DELIMITER ;

/* Procedure structure for procedure `TodosProdutos` */

/*!50003 DROP PROCEDURE IF EXISTS  `TodosProdutos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `TodosProdutos`()
BEGIN
 SELECT  `Cod_Produto` AS Codigo,P. `Nome`,`PrecoUnitario` AS Preco,`QuantidadeStock` as Quantidade, U.Descricao_Unidade as Unidade, C.Nome as Categoria, P.`Descricao`
 FROM produto P INNER JOIN unidade U on P.Unidade=U.idUnidade INNER JOIN categoria C on P.Categoria=C.idCategoria;
END */$$
DELIMITER ;

/* Procedure structure for procedure `UpdateProduto` */

/*!50003 DROP PROCEDURE IF EXISTS  `UpdateProduto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateProduto`(IN codp int, IN nomep varchar(50), IN preco double, IN quantS int, IN unidad int, IN cat int,IN descr varchar(200))
BEGIN
 UPDATE `mydb`.`produto` SET `Nome` =nomep , `PrecoUnitario`=preco , `QuantidadeStock` = quantS , `Unidade` =unidad, `Categoria` = cat , `Descricao` =  descr 
 WHERE `produto`.`Cod_Produto` = codp;
end */$$
DELIMITER ;

/* Procedure structure for procedure `UsuarioAutenticado` */

/*!50003 DROP PROCEDURE IF EXISTS  `UsuarioAutenticado` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `UsuarioAutenticado`(IN name Varchar(50))
BEGIN
  SELECT `Cod_Funcionario`  FROM  usuario   WHERE `Nome`=name;
END */$$
DELIMITER ;

/* Procedure structure for procedure `VENDA` */

/*!50003 DROP PROCEDURE IF EXISTS  `VENDA` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `VENDA`( IN TOTAL DOUBLE, IN NOMEC VARCHAR(50) ,IN FUNC INT )
BEGIN
INSERT INTO `mydb`.`venda` (`Cod_venda`, `Data_venda`, `Total_venda`, `Nome_Cliente`, `usuario_Cod_Funcionario`) VALUES (NULL, CURDATE(), TOTAL, NOMEC, FUNC);
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
