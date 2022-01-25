use mydb;


ALTER TABLE `stocklevel`
ADD CONSTRAINT `lote` FOREIGN KEY 
(`lote`) REFERENCES `stock` (`numero_lote`);