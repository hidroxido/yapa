package com.radioccc.yetanotherpingapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MonitorUtils {
    // Método para verificar la disponibilidad de un servidor mediante ping ICMP.
    public static boolean isServerReachable(String ipAddress) {
        try {
            InetAddress server = InetAddress.getByName(ipAddress);
            return server.isReachable(3000); // Espera hasta 3 segundos para respuesta.
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para verificar la disponibilidad de una página web mediante solicitud HTTP y devuelve el código de respuesta.
    public static int checkWebsiteAvailability(String url) {
        try {
            URL websiteUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) websiteUrl.openConnection();
            // Establece la conexión HTTPS en lugar de HTTP.
            if (websiteUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) websiteUrl.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                return httpsURLConnection.getResponseCode();
            } else {
                httpURLConnection.setRequestMethod("GET");
                return httpURLConnection.getResponseCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error al conectar.
        }
    }
}

