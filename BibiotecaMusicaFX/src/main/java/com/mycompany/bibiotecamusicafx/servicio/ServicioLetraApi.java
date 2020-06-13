package com.mycompany.bibiotecamusicafx.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ServicioLetraApi {

    private ServicioLetraApi() {
    }
    
    public static String getLetraCancion(String artista, String cancion) {
        String letra = "";
        URL url;
        try {
            String urlBase = "https://api.lyrics.ovh/v1/" + artista + "/" + cancion;
            url = new URL(urlBase);
            URLConnection conexion = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            letra = br.readLine();
            letra = letra.substring(11, letra.length() - 2)
                    .replace("\\n", System.lineSeparator());
        } catch (MalformedURLException ex) {
            letra = "URL mal generada";
        } catch (IOException ex) {
            letra = "Letra no encontrada";
        }
        return letra;
    }
}
