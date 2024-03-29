CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (13,'Alimentares'),(14,'Cosmeticos'),(15,'Limpeza'),(16,'Canalizacao'),(18,'Contrucao'),(19,'Ferramenta'),(20,'Electricidade'),(21,'Outro');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhesvenda`
--

DROP TABLE IF EXISTS `detalhesvenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhesvenda` (
  `Id_detalhesVenda` int NOT NULL AUTO_INCREMENT,
  `Produto_Cod_Produto` int NOT NULL,
  `Venda_Cod_venda` int NOT NULL,
  `preco` double DEFAULT NULL,
  `qauntidade_vendida` double DEFAULT NULL,
  `Subtotal` double DEFAULT NULL,
  PRIMARY KEY (`Id_detalhesVenda`,`Produto_Cod_Produto`),
  KEY `fk_Produto_has_Venda_Venda1_idx` (`Venda_Cod_venda`),
  KEY `fk_Produto_has_Venda_Produto1_idx` (`Produto_Cod_Produto`),
  CONSTRAINT `detalhesvenda_ibfk_3` FOREIGN KEY (`Produto_Cod_Produto`) REFERENCES `produto` (`Cod_Produto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhesvenda`
--

LOCK TABLES `detalhesvenda` WRITE;
/*!40000 ALTER TABLE `detalhesvenda` DISABLE KEYS */;
INSERT INTO `detalhesvenda` VALUES (10,11,40,180,1,180),(11,63,40,120,1,120),(12,11,41,180,6,1080),(13,12,41,235,3,705),(14,63,42,120,2,240),(15,63,43,120,1,120),(26,63,47,120,2,240),(27,63,48,120,1,120),(28,11,48,180,2,360),(29,12,49,235,2,470),(30,11,50,180,2,360),(31,12,50,235,2,470),(32,63,51,120,1,120),(33,63,52,120,1,120),(34,63,53,120,1,120),(35,63,54,120,1,120),(36,63,55,120,1,120),(37,12,55,235,1,235),(38,11,56,180,1,180),(39,12,56,235,1,235),(40,63,57,120,1,120),(41,63,58,120,1,120),(42,63,59,120,1,120),(43,12,60,235,1,235),(44,12,61,235,4,940),(45,12,62,235,5,1175);
/*!40000 ALTER TABLE `detalhesvenda` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `diminuir_stockLevel` AFTER INSERT ON `detalhesvenda` FOR EACH ROW BEGIN
update stocklevel as  stl set
    stl.unidades_stock =  stl.unidades_stock - new.qauntidade_vendida
     where stl.produto_Cod_Produto = new.produto_Cod_Produto;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `ID_inventario` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `usuario` int DEFAULT NULL,
  `tipo_inventario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_inventario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (4,'2023-09-29',7,'Parcial'),(5,'2023-09-30',7,'Parcial'),(6,'2023-10-13',7,'Parcial'),(7,'2023-10-13',7,'Parcial'),(8,'2023-10-13',7,'Geral'),(9,'2023-10-22',7,'Parcial'),(10,'2023-10-22',7,'Parcial'),(11,'2023-10-22',7,'Parcial'),(12,'2023-10-22',7,'Parcial');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `inventario_AFTER_INSERT` AFTER INSERT ON `inventario` FOR EACH ROW BEGIN
UPDATE mydb.inventario_item as ii

        SET ii.codigo_inventario = new.ID_inventario
        WHERE ii.codigo_inventario  = 0;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `inventario_item`
--

DROP TABLE IF EXISTS `inventario_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario_item` (
  `ID_inventario_item` int NOT NULL AUTO_INCREMENT,
  `codigo_produto` int DEFAULT NULL,
  `codigo_inventario` int DEFAULT NULL,
  `quantidade_existente` double DEFAULT NULL,
  `quantidade_contada` double DEFAULT NULL,
  `diferenca` double DEFAULT NULL,
  PRIMARY KEY (`ID_inventario_item`),
  KEY `codigo_produto` (`codigo_produto`),
  CONSTRAINT `inventario_item_ibfk_1` FOREIGN KEY (`codigo_produto`) REFERENCES `produto` (`Cod_Produto`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_item`
--

LOCK TABLES `inventario_item` WRITE;
/*!40000 ALTER TABLE `inventario_item` DISABLE KEYS */;
INSERT INTO `inventario_item` VALUES (1,12,5,19,20,-1),(2,74,5,0,0,0),(3,21,5,0,10,-10),(4,11,5,39,40,-1),(5,11,6,0,20,-20),(6,42,7,0,0,0),(7,45,7,0,0,0),(8,47,7,0,0,0),(9,52,7,0,0,0),(10,53,7,0,0,0),(11,62,7,0,0,0),(12,63,7,0,0,0),(13,74,7,0,0,0),(14,11,7,20,21,-1),(15,12,7,0,32,-32),(16,21,7,0,0,0),(17,22,7,0,0,0),(18,25,7,0,0,0),(19,26,7,0,0,0),(20,27,7,0,0,0),(21,28,7,0,0,0),(22,29,7,0,0,0),(23,30,7,0,0,0),(24,31,7,0,0,0),(25,32,7,0,0,0),(26,33,7,0,0,0),(27,34,7,0,0,0),(28,35,7,0,0,0),(29,36,7,0,0,0),(30,37,7,0,0,0),(31,38,7,0,0,0),(32,39,7,0,0,0),(33,40,7,0,0,0),(34,41,7,0,0,0),(35,43,7,0,0,0),(36,48,7,0,0,0),(37,49,7,0,0,0),(38,50,7,0,0,0),(39,51,7,0,0,0),(40,61,7,0,0,0),(41,64,7,0,0,0),(42,65,7,0,0,0),(43,71,7,0,0,0),(44,73,7,0,0,0),(45,75,7,0,0,0),(46,76,7,0,0,0),(47,46,7,0,0,0),(48,42,8,0,0,0),(49,45,8,0,0,0),(50,47,8,0,0,0),(51,52,8,0,0,0),(52,53,8,0,0,0),(53,62,8,0,0,0),(54,63,8,0,0,0),(55,74,8,0,0,0),(56,11,8,22,23,-1),(57,12,8,32,0,0),(58,21,8,0,0,0),(59,22,8,0,0,0),(60,25,8,0,0,0),(61,26,8,0,0,0),(62,27,8,0,0,0),(63,28,8,0,0,0),(64,29,8,0,0,0),(65,30,8,0,0,0),(66,31,8,0,0,0),(67,32,8,0,0,0),(68,33,8,0,0,0),(69,34,8,0,0,0),(70,35,8,0,0,0),(71,36,8,0,0,0),(72,37,8,0,0,0),(73,38,8,0,0,0),(74,39,8,0,0,0),(75,40,8,0,0,0),(76,41,8,0,0,0),(77,43,8,0,0,0),(78,48,8,0,0,0),(79,49,8,0,0,0),(80,50,8,0,0,0),(81,51,8,0,0,0),(82,61,8,0,0,0),(83,64,8,0,0,0),(84,65,8,0,0,0),(85,71,8,0,0,0),(86,73,8,0,0,0),(87,75,8,0,0,0),(88,76,8,0,0,0),(89,46,8,0,0,0),(90,63,9,30,28,2),(91,11,9,21,5,16),(92,63,10,27,27,0),(93,11,10,5,4,1),(94,63,11,27,27,0),(95,11,11,4,5,-1),(96,63,12,27,28,-1),(97,11,12,5,5,0);
/*!40000 ALTER TABLE `inventario_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `previlegio`
--

DROP TABLE IF EXISTS `previlegio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `previlegio`
--

LOCK TABLES `previlegio` WRITE;
/*!40000 ALTER TABLE `previlegio` DISABLE KEYS */;
INSERT INTO `previlegio` VALUES ('nao','nao','sim','sim','sim','sim','sim','sim');
/*!40000 ALTER TABLE `previlegio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `Cod_Produto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `preco_unitario` double NOT NULL,
  `unidade` int DEFAULT '1',
  `categoria` int DEFAULT '1',
  `descricao` varchar(45) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Cod_Produto`),
  KEY `fk_Produto_Unidade_idx` (`unidade`),
  KEY `fk_produto_catgoria1` (`categoria`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`unidade`) REFERENCES `unidade` (`idUnidade`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (11,'Nivea For Man',180,11,14,'Loção corporal','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\nivea.jpg'),(12,'Sabao Maeva',235,11,15,'Sabão C','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\sabao.jpg'),(21,'Tubo PVC 50',850,11,16,'Tubo de canalizacao','C:\\sedv\\img\\tubo.jpg'),(22,'Alicate de Corte Universal',250,11,16,'Alicate de Corte Universal',NULL),(25,'Chapa de Zinco IBR',535,11,18,'Chapa de Zinco IBR',NULL),(26,'Regua Modular',495,11,19,'Regua Modular','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\depositphotos_25269157-stock-photo-white-plus-sign-isolated-on.jpg'),(27,'Ferro 8 SA',128,11,18,'Ferro 8 SA',NULL),(28,'Cimento Limak 42',380,11,18,'Cimento Limak 42',NULL),(29,'Cimento Limak 32',267,11,18,'Cimento Limak 32',NULL),(30,'Martelo',550,11,19,'Martelo',NULL),(31,'Chave de Fenda',250,11,19,'Chave de fenda',NULL),(32,'Chave de Fenda',300,11,19,'Chave de Fenda',NULL),(33,'Chave Estrela',350,11,19,'Chave Estrela',NULL),(34,'Chave Estrelao',330,11,19,'Chave Estrelao',NULL),(35,'Chavinha',200,11,19,'Chavinha',NULL),(36,'Chaves',100,11,16,'chaves',NULL),(37,'ddd',333,11,15,'cc',NULL),(38,'Tinta 5L',2000,11,18,'Tinta 5L','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\carrinho-de-compras-vermelho-do-mercado-conceito-d-do-disconto-30551366.jpg'),(39,'Tinta 1L',400,11,18,'Tinta 1L',NULL),(40,'PortaChaves',200,11,18,'PortaChaves',NULL),(41,'Sabao Universal',50,11,15,'sabao',NULL),(42,'OMO',450,10,15,'sabao em pó','C:\\\\sedv\\\\img\\\\omo.jpg'),(43,'ddd',33,11,15,'sss',NULL),(45,'uvinha',530,10,13,'fruta','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\Grapes-PNG-Transparent-Image.png'),(46,'orange',230,12,13,'fruta','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\Laranja.jpg.png'),(47,'uva',178,10,13,'fruta','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\Grapes-PNG-Transparent-Image.png.png'),(48,'chaveEstrelinha',470,11,19,'chaveEstrelinha','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\chave fenda.png'),(49,'chaveEstrelinha2',500,11,19,'chaveEstrelinha2','C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\chave fenda.png.png'),(50,'bilha',44,11,18,'bilha',NULL),(51,'ameixa',20,11,13,'fruta','C:\\\\sedv\\\\Grapes-PNG-Transparent-Image.png'),(52,'Ameixas',33,10,13,'fruits','C:\\\\sedvGrapes-PNG-Transparent-Image.png'),(53,'orang',33,10,13,'frutas','C:\\\\sedv\\\\Laranja.jpg'),(61,'Chave de Fenda',444,11,19,'ferramenta','C:\\sedv\\img\\chave fenda.png'),(62,'dsdsd',33,10,14,'ddsds',NULL),(63,'Laranja',120,10,21,'fruta','C:\\sedv\\img\\Laranja.jpg'),(64,'chave estrela5',600,11,18,'ferrameta','C:\\sedv\\img\\chave estrela.jpg'),(65,'ass',333,11,15,'sss',NULL),(71,'Uva',200,11,14,'fruta','C:\\sedv\\img\\Grapes-PNG-Transparent-Image.png'),(73,'Uva',200,11,14,'fruta',NULL),(74,'sasa',11,10,14,'ssdsds',NULL),(75,'dsds',12,11,14,'dsds',NULL),(76,'chave',100,11,19,'ferramenta','C:\\sedv\\img\\chave estrela.jpg'),(77,'Agua',300,12,13,'liquido','C:\\sedv\\img\\ataques.jfif'),(78,'Sabao Liquido',150,11,14,'yugdyugdsa',NULL);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `insert_stock_level` AFTER INSERT ON `produto` FOR EACH ROW INSERT INTO stocklevel(unidades_stock,produto_Cod_Produto)
VALUES (0,new.Cod_Produto) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `produto_history`
--

DROP TABLE IF EXISTS `produto_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `cod_Produto` int DEFAULT NULL,
  `quantidade_anterior` int DEFAULT NULL,
  `quantidade_introduzida` int DEFAULT NULL,
  `cod_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cod_Produto` (`cod_Produto`),
  KEY `cod_usuario` (`cod_usuario`),
  CONSTRAINT `produto_history_ibfk_1` FOREIGN KEY (`cod_Produto`) REFERENCES `produto` (`Cod_Produto`),
  CONSTRAINT `produto_history_ibfk_2` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`Cod_Funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_history`
--

LOCK TABLES `produto_history` WRITE;
/*!40000 ALTER TABLE `produto_history` DISABLE KEYS */;
INSERT INTO `produto_history` VALUES (1,'2023-07-20',11,39,12,7),(2,'2023-07-20',11,41,2,7),(3,'2023-10-05',12,31,12,7),(4,'2023-10-13',11,22,1,7),(5,'2023-11-15',11,25,20,7),(6,'2023-11-15',12,20,6,7),(7,'2023-11-20',11,43,18,7);
/*!40000 ALTER TABLE `produto_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `idstock` int NOT NULL AUTO_INCREMENT,
  `quantidade_recebida` int DEFAULT NULL,
  `data_entrada` date DEFAULT NULL,
  `produto_Cod_Produto` int NOT NULL,
  `usuario_Cod_Funcionario` int NOT NULL,
  `numero_lote` varchar(45) DEFAULT NULL,
  `fabricante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idstock`),
  KEY `fk_stock_produto1_idx` (`produto_Cod_Produto`),
  KEY `fk_stock_usuario1_idx` (`usuario_Cod_Funcionario`),
  CONSTRAINT `fk_stock_produto1` FOREIGN KEY (`produto_Cod_Produto`) REFERENCES `produto` (`Cod_Produto`),
  CONSTRAINT `fk_stock_usuario1` FOREIGN KEY (`usuario_Cod_Funcionario`) REFERENCES `usuario` (`Cod_Funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,20,'2022-02-18',11,7,'zbsdhdfdd','zbsdhdfdd'),(2,10,'2022-02-21',12,8,'dfdf','cerlac'),(3,23,'2022-05-10',11,7,'ewew','Mylan'),(4,2,'2022-04-12',11,7,'ee','dd'),(5,2,'2022-04-12',11,7,'ee','dd'),(6,2,'2022-04-12',11,7,'ee','dd'),(7,3,'2022-04-12',11,7,'ff','ff'),(8,3,'2022-04-12',12,7,'ee','fffg'),(9,3,'2022-04-12',11,7,'ffff','xddd'),(10,2,'2022-04-12',12,7,'ee','dd'),(11,2,'2022-04-12',11,7,'ww','dd'),(12,2,'2022-04-05',12,7,'ww','ee'),(13,4,'2022-05-15',11,7,'mjjmjm','kkk'),(14,3,'2022-05-17',11,7,'fffddddddd','fffffvvvvvvvvvv'),(15,7,'2022-05-17',12,7,'kkkkkk','dsdd'),(16,3,'2022-05-19',12,7,'ee','dd'),(17,3,'2022-05-19',12,7,'eee','dd'),(18,3,'2022-05-19',12,7,'ww','ee'),(19,3,'2022-05-19',11,7,'jjjjjjjj','ww'),(20,6,'2022-06-30',11,7,'dfdf','rfrfrf'),(21,3,'2022-06-30',11,7,'kjk','hhghg'),(22,120,'2023-04-13',63,7,'21221','dssds'),(23,5,'2023-05-10',12,7,'333','fdd'),(24,3,'2023-05-12',12,7,'eee','vffv'),(25,20,'2023-06-13',63,7,'rgtgtgt','efrfrfr'),(26,12,'2023-07-20',11,7,'sdsd','dsds'),(27,2,'2023-07-20',11,7,'dssd','sdds'),(28,12,'2023-10-05',12,7,'sdde','dedede'),(29,1,'2023-10-13',11,7,'hdhdh','ddd'),(32,18,'2023-11-20',11,7,'efkj','ewfaiu');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_stock_level` AFTER INSERT ON `stock` FOR EACH ROW update stocklevel as  stl set
    stl.unidades_stock =  stl.unidades_stock + new.quantidade_recebida
     where stl.produto_Cod_Produto = new.produto_Cod_Produto */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `updatehistory_AFTER_INSERT` AFTER INSERT ON `stock` FOR EACH ROW BEGIN
    DECLARE quantidade_anterior INT;
    SELECT unidades_stock INTO quantidade_anterior FROM stocklevel WHERE produto_Cod_Produto = NEW.produto_Cod_Produto;
    INSERT INTO produto_history (data, cod_Produto, quantidade_anterior, quantidade_introduzida, cod_usuario)
    VALUES (CURDATE(), NEW.produto_Cod_Produto, quantidade_anterior, NEW.quantidade_recebida, NEW.usuario_Cod_Funcionario
);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `stocklevel`
--

DROP TABLE IF EXISTS `stocklevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocklevel` (
  `idstocklevel` int NOT NULL AUTO_INCREMENT,
  `unidades_stock` double DEFAULT NULL,
  `produto_Cod_Produto` int NOT NULL,
  PRIMARY KEY (`idstocklevel`),
  KEY `fk_stocklevel_produto1_idx` (`produto_Cod_Produto`),
  CONSTRAINT `fk_stocklevel_produto1` FOREIGN KEY (`produto_Cod_Produto`) REFERENCES `produto` (`Cod_Produto`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocklevel`
--

LOCK TABLES `stocklevel` WRITE;
/*!40000 ALTER TABLE `stocklevel` DISABLE KEYS */;
INSERT INTO `stocklevel` VALUES (1,43,11),(2,15,12),(3,0,21),(4,0,22),(7,0,25),(8,0,26),(9,0,27),(10,0,28),(11,0,29),(12,0,30),(13,0,31),(14,0,32),(15,0,33),(16,0,34),(17,0,35),(18,0,36),(19,0,37),(20,0,38),(21,0,39),(22,0,40),(23,0,41),(24,0,42),(25,0,43),(27,0,45),(28,0,46),(29,0,47),(30,0,48),(31,0,49),(32,0,50),(33,0,51),(34,12,52),(35,0,53),(43,0,61),(44,0,62),(45,26,63),(46,0,64),(47,0,65),(53,0,71),(55,0,73),(56,0,74),(57,0,75),(58,0,76),(59,0,77),(60,0,78);
/*!40000 ALTER TABLE `stocklevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidade`
--

DROP TABLE IF EXISTS `unidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidade` (
  `idUnidade` int NOT NULL AUTO_INCREMENT,
  `descricao_unidade` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnidade`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade`
--

LOCK TABLES `unidade` WRITE;
/*!40000 ALTER TABLE `unidade` DISABLE KEYS */;
INSERT INTO `unidade` VALUES (10,'Kg'),(11,'Singular'),(12,'Caixa');
/*!40000 ALTER TABLE `unidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Cod_Funcionario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `categoria` varchar(45) NOT NULL,
  `esta_autenticado` char(5) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`Cod_Funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (7,'Admin','1234','Gerente','sim'),(8,'user','0000','Funcionario','nao');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `Cod_venda` int NOT NULL AUTO_INCREMENT,
  `data_venda` date NOT NULL,
  `total_venda` double NOT NULL,
  `nome_cliente` varchar(45) DEFAULT NULL,
  `usuario_Cod_Funcionario` int DEFAULT NULL,
  `nuit_cliente` int DEFAULT NULL,
  `Forma_Pagamento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Cod_venda`),
  KEY `fk_venda_usuario1` (`usuario_Cod_Funcionario`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`usuario_Cod_Funcionario`) REFERENCES `usuario` (`Cod_Funcionario`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (40,'2023-06-13',300,'Neu',7,123,'MPESA'),(41,'2023-06-13',1785,'Nadia',7,1234,'CASH'),(42,'2023-06-14',240,'Neusia',7,12,'CARTÃO DE CRÉDITO'),(43,'2023-06-14',120,'neu',7,12,'CASH'),(47,'2023-06-19',240,NULL,7,NULL,'CARTÃO DE CRÉDITO'),(48,'2023-06-29',480,NULL,7,NULL,'MPESA'),(49,'2023-06-30',470,NULL,7,NULL,'CASH'),(50,'2023-07-06',830,NULL,7,NULL,'MPESA'),(51,'2023-07-06',120,NULL,7,NULL,'CASH'),(52,'2023-07-13',120,NULL,7,NULL,'MPESA'),(53,'2023-07-18',120,NULL,7,NULL,'CASH'),(54,'2023-07-20',120,NULL,7,NULL,'CARTÃO DE CRÉDITO'),(55,'2023-10-05',355,NULL,7,NULL,'CARTÃO DE CRÉDITO'),(56,'2023-10-13',415,'neusiahilario',7,14256722,'MPESA'),(57,'2023-10-22',120,NULL,7,NULL,'MPESA'),(58,'2023-10-31',120,NULL,7,NULL,'MPESA'),(59,'2023-10-31',120,NULL,7,NULL,'MPESA'),(60,'2023-11-14',235,NULL,7,NULL,'CASH'),(61,'2023-11-15',940,NULL,7,NULL,'MPESA'),(62,'2023-11-20',1175,NULL,7,NULL,'CASH');
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `venda_AFTER_INSERT` AFTER INSERT ON `venda` FOR EACH ROW BEGIN
UPDATE mydb.detalhesvenda as dv

        SET dv.Venda_Cod_venda = new.Cod_venda
        WHERE dv.Venda_Cod_venda  = 0;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'mydb'
--

--
-- Dumping routines for database 'mydb'
--
/*!50003 DROP PROCEDURE IF EXISTS `ActualizarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarStock`(IN Codigo int ,In Stock Double)
BEGIN

DECLARE exit handler for sqlexception
SELECT 'Erro ao Actualizar Stock' AS status_execucao;
UPDATE `stocklevel` SET  unidades_stock=Stock WHERE stocklevel.produto_Cod_Produto = Codigo;
select 'Sucesso' as status_execucao;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AdicionarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdicionarStock`(in codProduto int, IN quant int )
BEGIN
UPDATE  mydb.stock as S
inner join mydb.stocklevel as SL 
on S.produto_Cod_Produto = SL.produto_Cod_Produto
SET  S.quantidade_recebida =S.quantidade_recebida+quant, SL.unidades_stock = S.quantidade_recebida

 WHERE  S.produto_Cod_Produto =codProduto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Apagarcategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Apagarcategoria`(IN Codigo int)
BEGIN
     DELETE FROM `mydb`.`categoria` WHERE `categoria`.`idCategoria` =Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ApagarProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarProduto`(IN Codigo int)
BEGIN
DELETE FROM `mydb`.`produto` WHERE `Cod_Produto` = Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ApagarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarStock`(IN Codigo int)
BEGIN
DELETE mydb.stock , mydb.stocklevel
from mydb.stock
inner join mydb.stocklevel
on stock.produto_Cod_Produto =stocklevel.produto_Cod_Produto
 WHERE `produto_Cod_Produto` = Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ApagarUnidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUnidade`(IN idUnid int)
BEGIN
  
  DELETE FROM `unidade` WHERE idUnidade=idUnid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ApagarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApagarUsuario`(IN `Cod_Funcionario` int)
BEGIN
  
  DELETE FROM `usuario` WHERE Cod_Funcionario=Cod_Funcionario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AtualizaCategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizaCategoria`(IN Codigo int,IN Nom varchar(50))
BEGIN
UPDATE `categoria` SET `nome` = Nom  WHERE `idCategoria` = Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `atualizaunidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `atualizaunidade`(IN Cod int, IN desnidade varchar(40))
BEGIN
UPDATE  `mydb`.`unidade` SET  `descricao_unidade` = desnidade WHERE  `unidade`.`idUnidade` =Cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BuscaStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaStock`()
BEGIN
SELECT distinct sl.produto_cod_Produto, sl.unidades_stock
FROM stocklevel sl, stock st
WHERE sl.produto_cod_Produto= st.produto_cod_Produto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CadProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CadProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Unidad int,IN Categor int,IN Descric varchar(50), IN Foto varchar(200))
BEGIN

DECLARE exit handler for sqlexception
 SELECT 'Erro ao inserir Produto' AS status_execucao;
INSERT INTO `produto`(`Cod_Produto`, `nome`, `preco_unitario`, `unidade`, `categoria`, `descricao`, `foto`) VALUES (Null, Nom , Preco, Unidad , Categor ,Descric, Foto );
select 'Sucesso' as status_execucao;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `capturaId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaId`(IN Nome varchar(50))
BEGIN
             Select `idUnidade` From `unidade` Where `descricao_unidade`= Nome;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `capturaIdcategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `capturaIdcategoria`(IN Nom varchar(50))
BEGIN
             Select `idCategoria` From `categoria` Where `nome`= Nom;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CodVendaCorrente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CodVendaCorrente`()
begin 
SELECT  `Cod_venda` 
FROM venda
ORDER BY  `Cod_venda` DESC 
LIMIT 1;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DetalhesProdutos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetalhesProdutos`(in codProduto int)
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, P.`nome`,`preco_unitario` AS Preco, U.descricao_unidade AS Unidade, C.nome as Categoria, descricao, foto, St.unidades_stock
 FROM produto P, categoria C, unidade U, stocklevel St
 WHERE `Cod_Produto`= codProduto and P.`unidade`=U.idUnidade and P.`categoria`= C.idCategoria and St.produto_Cod_Produto= P.Cod_Produto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DetalhesStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetalhesStock`(in codProduto int )
BEGIN
SELECT distinct P.Cod_Produto AS Codigo, S.idstock, S.quantidade_recebida, S.data_entrada, S.produto_Cod_Produto, S.usuario_Cod_Funcionario, S.numero_lote, S.fabricante
 FROM produto P, stock S
 WHERE `Cod_Produto`=codProduto and P.Cod_Produto=S.produto_Cod_Produto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DetalhesUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetalhesUsuario`(in codUsuario int)
BEGIN
SELECT distinct `Cod_Funcionario`,`nome`, categoria
 FROM usuario 
 WHERE `Cod_Funcionario`= codUsuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DETALHESVENDA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DETALHESVENDA`(CODP INT, IN CODV INT, IN PREC DOUBLE, IN QUANT INT)
BEGIN
INSERT INTO `mydb`.`detalhesvenda` (`Produto_Cod_Produto`, `Venda_Cod_venda`, `preco`, `qauntidade_vendida`) VALUES (CODP, CODV, PREC, QUANT);
UPDATE  `mydb`.`stock` SET  `quantidade_recebida` =`quantidade_recebida`-QUANT   WHERE  `stock`.`produto_Cod_Produto` =CODP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Detalhes_Venda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Detalhes_Venda`(in Cod_Venda int)
BEGIN
SELECT distinct dv.Venda_Cod_venda as Codigo, p.Cod_Produto, p.nome as Nome_Produto, dv.preco as Preco, dv.qauntidade_vendida as Quantidade, dv.Subtotal 
FROM detalhesvenda dv, produto p
 WHERE dv.Venda_Cod_venda= Cod_Venda and dv.Produto_Cod_Produto=p.Cod_Produto ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EditarProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarProduto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Unidad int,IN Categor int,IN Descric varchar(50), IN Foto varchar(200))
BEGIN
DECLARE exit handler for sqlexception
SELECT 'Erro ao inserir Produto' AS status_execucao;
UPDATE `produto` SET  `nome`=Nom,  `preco_unitario`= Preco, `unidade`=Unidad,  `categoria`=Categor,  `descricao`=Descric, foto=Foto WHERE `produto`.`Cod_Produto` = Codigo;
select 'Sucesso' as status_execucao;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EditarProdutoSemFoto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarProdutoSemFoto`(IN Codigo int ,IN Nom varchar(50),In Preco Double,IN Unidad int,IN Categor int,IN Descric varchar(50))
BEGIN

SELECT 'Erro ao inserir Produto' AS status_execucao;
UPDATE `produto` SET  `nome`=Nom,  `preco_unitario`= Preco, `unidade`=Unidad,  `categoria`=Categor,  `descricao`=Descric WHERE `produto`.`Cod_Produto` = Codigo;
select 'Sucesso' as status_execucao;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EditarUnidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditarUnidade`(IN Codigo int,IN Descricao varchar(50))
BEGIN
UPDATE `unidade` SET `descricao_unidade` = Descricao WHERE `idUnidade` = Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Editarusuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Editarusuario`(IN Codigo int,IN Nom varchar(50),IN Pass varchar(50),IN Cat varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `nome` =  Nom,
`senha` = Pass   ,
`categoria` = Cat    WHERE  `usuario`.`Cod_Funcionario` =Codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetCategoriaUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCategoriaUsuario`(IN username varchar(20), IN pass varchar(20) )
BEGIN
SELECT  `categoria` ,`Cod_Funcionario`
FROM `usuario` WHERE `nome`=username AND `senha`=pass;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Listagemdeproduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Listagemdeproduto`()
BEGIN
SELECT distinct `Cod_Produto` , P.`nome`,`preco_unitario`, C.nome as Categoria, U.descricao_unidade AS Unidade, P.descricao, foto, st.unidades_stock
 FROM produto P, categoria C, unidade U, stocklevel st
 WHERE P.`unidade`=U.idUnidade and P.`categoria`=C.idCategoria and st.produto_Cod_Produto= P.Cod_Produto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListarCategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarCategoria`()
BEGIN
SELECT `idCategoria` AS Codigo,`nome` AS Categoria FROM `categoria`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `LISTARPREVILEGIOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `LISTARPREVILEGIOS`()
BEGIN
SELECT * FROM `previlegio` WHERE 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListarProdutos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarProdutos`()
BEGIN
 SELECT * FROM `produto` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarStock`()
BEGIN
SELECT * FROM `stock` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListarUnidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUnidade`()
BEGIN
  SELECT `idUnidade` AS codigo, `descricao_unidade` AS Unidade
FROM  `unidade`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListarUsuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarUsuarios`()
BEGIN
  SELECT * FROM `usuario` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Lista_Venda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Lista_Venda`()
BEGIN
SELECT distinct `Cod_venda`, data_venda, total_venda, nome_cliente, nuit_cliente, u.nome as nome, Forma_Pagamento
 FROM venda v, usuario u
 WHERE v.usuario_Cod_Funcionario=u.Cod_Funcionario; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Login`(IN username varchar (50), IN pass varchar(50))
BEGIN
UPDATE  `mydb`.`usuario` SET  `esta_autenticado` =  'nao' ;
UPDATE  `mydb`.`usuario` SET  `esta_autenticado` =  'sim' WHERE  `nome`=username and `senha`=pass;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Logout` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Logout`()
BEGIN
UPDATE  `mydb`.`usuario` SET  `esta_autenticado` =  'nao' ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PesquisaProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaProduto`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `produto` where nome= "%Nome%";
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PesquisaUnidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUnidade`(IN `descricao_unidade` varchar(50))
BEGIN
  SELECT * FROM  `produto` where nome= "%descricao_unidade%";
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PesquisaUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PesquisaUsuario`(IN Nome varchar(50))
BEGIN
  SELECT * FROM  `unidade` where nome= "%Nome%";
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Populacombocategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombocategoria`()
BEGIN
       SELECT  `nome` FROM `categoria`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Populacombounidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Populacombounidade`()
BEGIN
       SELECT descricao_unidade FROM `unidade`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PrevilegiosFuncionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProcurarProdutos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProcurarProdutos`(in param varchar (40))
BEGIN
SELECT distinct `Cod_Produto` AS Codigo, `nome`,`preco_unitario` AS Preco
FROM produto 
 WHERE `Cod_Produto`  like CONCAT('%', param, '%') or `nome` like CONCAT('%', param, '%') ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_categoria`(IN Nom varchar(50))
BEGIN
INSERT INTO `mydb`.`categoria` (`idCategoria`, `nome`) VALUES (NULL, Nom);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_produto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_produto`(IN Cod_Produto int,IN Nome varchar(50),IN PrecoUnitario Double,IN Unidade_Cod_unidade int,IN Categoria int,IN Descricao varchar(50), Foto blob)
BEGIN
 
 INSERT INTO `produto`(`Cod_Produto`,`nome`,`preco_unitario`,`unidade`,`categoria`,`descricao `, `foto`) VALUES(NULL,Nome ,PrecoUnitario,Unidade_Cod_unidade,Categoria, Descricao, Foto);
 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_unidade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_unidade`(IN descr varchar(50))
BEGIN
    INSERT INTO `mydb`.`unidade` (idUnidade,`descricao_unidade`) VALUES (NULL,descr);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_usuario`(IN Cod_Funcionario int,IN Nome  varchar(50),IN  Senha varchar(50),IN  Categoria varchar(50))
BEGIN
    INSERT INTO `usuario`(`Cod_Funcionario`, `nome`, `senha`, `categoria`) VALUES (NULL, Nome , Senha, Categoria);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProdutosExistentes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProdutosExistentes`()
BEGIN
SELECT distinct  P.`nome`,`preco_unitario`, C.nome as Categoria, U.descricao_unidade AS Unidade, P.descricao, st.unidades_stock
FROM produto P, categoria C, unidade U, stocklevel St
 WHERE P.`unidade`=U.idUnidade and P.`categoria`=C.idCategoria and st.produto_Cod_Produto= P.Cod_Produto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `produtos_com_pouco_stock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `produtos_com_pouco_stock`()
BEGIN
select distinct p.Cod_Produto, p.nome, p.preco_unitario, st.unidades_stock
from produto p, stocklevel st
where st.produto_Cod_Produto= p.Cod_Produto and st.unidades_stock<1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `RegistarDetalhesVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `RegistarDetalhesVenda`(IN Cod_Prod INT, IN Preco double, In Quantidade_Produto Double, IN Subtotal double)
BEGIN
INSERT INTO `mydb`.`detalhesvenda` (`Id_detalhesVenda`, `Produto_Cod_Produto`, `Venda_Cod_venda`, `preco`, `qauntidade_vendida`, Subtotal ) VALUES (NULL, Cod_Prod, "0", Preco, Quantidade_Produto, Subtotal);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `RegistarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `RegistarStock`(IN quantidade_recebida int,In data_Entrada date,IN Cod_Produto int,IN Cod_Funcionario int,IN numero_lote varchar(45), IN Fabricante varchar(45))
BEGIN
INSERT INTO `stock`(`idStock`,`quantidade_recebida`, `data_entrada`, `produto_Cod_Produto`, `usuario_Cod_Funcionario`, `numero_lote`, `fabricante`) VALUES (null, quantidade_recebida , data_Entrada, Cod_Produto , Cod_Funcionario ,numero_lote, Fabricante );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Registar_Inventario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Registar_Inventario`(IN Data date, IN User int , In Tipo_Inventario varchar(20))
BEGIN
INSERT INTO `mydb`.`inventario` (`ID_inventario`, `data`, `usuario`, `tipo_inventario`) VALUES (NULL, Data, User, Tipo_Inventario);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Registar_Inventario_Item` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Registar_Inventario_Item`(IN Cod_Prod INT, In Quantidade_Existente Double, IN Quantidade_Contada double, IN Diferenca DOUBLE)
BEGIN

INSERT INTO `mydb`.`inventario_item` (`ID_inventario_item`, `codigo_produto`, `codigo_inventario`, `quantidade_existente`, `quantidade_contada`, diferenca ) VALUES (NULL, Cod_Prod, "0", Quantidade_Existente, Quantidade_Contada, Diferenca);


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SearchProdutoVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchProdutoVenda`(in Param varchar(50))
BEGIN
 SELECT distinct  p.`Cod_Produto` AS Codigo,p.`nome`,`preco_unitario` AS Preco,U.descricao_unidade as Unidade, C.nome as Categoria, p.`descricao`
 FROM produto p inner join unidade U on  p.Unidade=U.idUnidade 
inner join categoria C on  p.Categoria=C.idCategoria 
Where  `Cod_Produto`  like CONCAT('%', param, '%') or p.`Nome` like CONCAT('%', param, '%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TodosProdutos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `TodosProdutos`()
BEGIN
 SELECT  `Cod_Produto` AS Codigo,P. `nome`,`preco_unitario` AS Preco, U.descricao_unidade as Unidade, C.nome as Categoria, P.`descricao`
 FROM produto P INNER JOIN unidade U on P.Unidade=U.idUnidade INNER JOIN categoria C on P.Categoria=C.idCategoria;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TodoStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `TodoStock`()
BEGIN
SELECT  `idstock` , `quantidade_recebida`,`data_entrada` , P.Cod_Produto as produto_Cod_Produto, U.Cod_Funcionario as usuario_Cod_Funcionario, `numero_lote`, fabricante
 FROM stock S INNER JOIN produto P on 	S.produto_Cod_Produto=P.Cod_Produto INNER JOIN Usuario U on S.usuario_Cod_Funcionario=U.Cod_Funcionario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UpdateProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateProduto`(IN codp int, IN nomep varchar(45), IN preco double, IN unidad int, IN cat int,IN descr varchar(45), Foto varchar(200))
BEGIN
 UPDATE `mydb`.`produto` SET `nome` =nomep , `preco_unitario`=preco , `unidade` =unidad, `categoria` = cat , `descricao` =  descr , `foto` = Foto
 WHERE `produto`.`Cod_Produto` = codp;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UsuarioAutenticado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UsuarioAutenticado`(IN name Varchar(50))
BEGIN
  SELECT `Cod_Funcionario`  FROM  usuario   WHERE `nome`=name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `VENDA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `VENDA`( IN Codigo INT, IN Data_Venda date, IN Total_Venda DOUBLE, IN Funcionario INT, IN Forma_Pagamento varchar(30), IN Nuit_Cliente int, IN Nome_Cliente VARCHAR(45)  )
BEGIN
INSERT INTO `mydb`.`venda` (`Cod_venda`, `data_venda`, `total_venda`, `nome_cliente`, `usuario_Cod_Funcionario`, nuit_cliente, Forma_Pagamento ) VALUES (NULL, Data_Venda, Total_Venda, Nome_Cliente, Funcionario, Nuit_Cliente, Forma_Pagamento);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Vendas_Num_Periodo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Vendas_Num_Periodo`(  IN DataInicio DATE,
    IN DataFim DATE)
BEGIN
 SELECT 
        Cod_venda AS Codigo,
        DATE_FORMAT(data_venda, "%d %m %Y") AS data_venda,
        total_venda,
        IF(nome_cliente IS NULL, "", nome_cliente) AS nome_cliente,
        u.nome,
        Forma_Pagamento
    FROM 
        venda v
    JOIN 
        usuario u ON v.usuario_Cod_Funcionario = u.Cod_Funcionario
    WHERE 
        v.data_venda BETWEEN DataInicio AND DataFim;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Venda_Por_Mes_Por_Ano` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Venda_Por_Mes_Por_Ano`()
BEGIN
SELECT COUNT(Cod_venda), EXTRACT(YEAR FROM data_venda) AS ano, EXTRACT(MONTH FROM data_venda) AS mes
FROM venda
GROUP BY ano, mes
ORDER BY ano, mes;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-16 11:34:11
