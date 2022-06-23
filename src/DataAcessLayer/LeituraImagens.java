/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Neusia
 */
public class LeituraImagens {
    private  String directorio_Imagens;

    public String getDirectorio_Imagens() {
        return directorio_Imagens;
    }

    public void setDirectorio_Imagens(String directorio_Imagens) {
        this.directorio_Imagens = directorio_Imagens;
    }
    
    
    
  
    
    
    ReadWriteTextFile rwTextFile = new ReadWriteTextFile();

   
    final static String ficheiro_Imagens = "C:\\config.txt";
  
    List <String> sedv_Imagens;
    
    
     private List<String> LerDadosDeImagens(final String location) {

         try{
            sedv_Imagens = rwTextFile.readSmallTextFile(ficheiro_Imagens);
            
            
         } catch (Exception e) {
            System.out.println("Ficheiro de Imagens nao encontrado");
         }
          return sedv_Imagens;
     }
     
     
                public LeituraImagens (String directorio_Imagens) {
/* 69 */   
                this.directorio_Imagens = directorio_Imagens;
/*    */   }
                
                
                 public LeituraImagens() {
     // ler o ficheiro
     // extrair os valore lidos
             
/* 69 */          sedv_Imagens = LerDadosDeImagens(ficheiro_Imagens);
           // Lipara a textarea

               directorio_Imagens = sedv_Imagens.get(0).substring(10);
          
               
                if (directorio_Imagens.isEmpty()) {
/*     */       
/*  89 */       JOptionPane.showMessageDialog(null, "Localização do ficheiro_Imagens em falta!");
/*     */     }
/*  92 */     
/*    */   }
                 
public static void main(String[] args){
    try{
        FileReader fr;
        fr =new FileReader("C:\\config.txt");
        BufferedReader bf = new BufferedReader(fr);
        String linha = bf.readLine().substring(10);
        System.out.println(linha);
    }catch(Exception e){
        System.out.println("erro"+ e.getMessage());
    }
}
}
