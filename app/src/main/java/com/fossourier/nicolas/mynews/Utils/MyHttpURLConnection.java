//package com.fossourier.nicolas.mynews.Utils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class MyHttpURLConnection {
//
//    public static String startHttpRequest(String urlString){
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try {
//            // Nous créons à partir d'une adresse HTTP contenue dans une variable String, un objet URL. Celui-ci nous permettra d'ouvrir une connexion de type HttpURLConnection.
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            // Nous ouvrons un canal vers l'url en question, et récupérons le flux de données correspondant via une InputStream.
//            conn.connect();
//            InputStream in = conn.getInputStream();
//            // Puis enfin, nous lisons ce flux de données brute grâce à un BufferedReader et convertissons le tout ligne par ligne, grâce à un StringBuilder pour retourner au final le résultat dans une variable de type String.
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//
//        } catch (MalformedURLException exception){
//
//        } catch (IOException exception) {
//
//        } catch (Exception e){
//
//        }
//
//        return stringBuilder.toString();
//    }
//
//}