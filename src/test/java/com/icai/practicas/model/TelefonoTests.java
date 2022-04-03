package com.icai.practicas.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TelefonoTests {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    Telefono tel;

    @Test
    public void checksValidPhones(){

        try {
            archivo = new File("src/test/java/com/icai/practicas/model/datos/lista_telefonos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null){
                //System.out.println(linea);
                tel = new Telefono(linea);
                Assertions.assertEquals(true, tel.validar());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    @Test
    public void checksInValidPhones(){
        String[] invalidos = new String[]{"93453457382", "67A98R567", "(042) 1818 87 9919"};
        for (String invalido: invalidos){
            tel = new Telefono(invalido);
            Assertions.assertEquals(false, tel.validar());
        }
    }

}