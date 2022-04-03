package com.icai.practicas.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DNITests {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    DNI dni;

    @Test
    public void checksValidDNI(){

        try {
            archivo = new File("src/test/java/com/icai/practicas/model/datos/lista_nifs.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null){
                //System.out.println(linea);
                dni = new DNI(linea);
                Assertions.assertEquals(true, dni.validar());
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
    public void checksInValidDNIs(){
        String[] invalidos = new String[]{"00000000T", "00000001R", "99999999R","063X35HJL", "67A98RFAIH$", "06311570w","05197923F", "52392713N", "57124742T","35326527M","93436494R"};
        for (String invalido: invalidos){
            dni = new DNI(invalido);
            Assertions.assertEquals(false, dni.validar());
        }
    }


}


