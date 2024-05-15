package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class CatService {

   public static void seeCats() throws URISyntaxException {
       /*OkHttpClient okHttpClient = new OkHttpClient();
*/
       HttpClient client = HttpClient.newHttpClient();//NATIVO//

       Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
       HttpRequest request1 = HttpRequest.newBuilder().uri(new URI("https://api.thecatapi.com/v1/images/search")).GET().build(); //NATIVO//

       try{
           //NATIVO JAVA
           HttpResponse<String> response1 = client.send(request1, HttpResponse.BodyHandlers.ofString()); //NATIVO/
           String result = response1.body().substring(1, response1.body().length());
           result = result.substring(0, result.length()-1);
           System.out.println(result);
           CatDto gato = new Gson().fromJson(result, CatDto.class);
           System.out.println(gato.toString());
           URL catUrlExtracted = new URI(gato.getUrl()).toURL();
           ImageIcon catImage = new ImageIcon(ImageIO.read(catUrlExtracted).getScaledInstance(200, 200, Image.SCALE_SMOOTH ));


           String menu = "Opciones: \n"
                   + " 1. ver otra imagen \n"
                   + " 2. Favorito \n"
                   + " 3. Volver \n";

           String[] botones = { "ver otra imagen", "favorito", "salir" };
           String id_gato = gato.getId();
           String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato, JOptionPane.INFORMATION_MESSAGE, catImage, botones,botones[0]);

           if (opcion != null){
               switch (opcion){
                   case "ver otra imagen":
                       CatService.seeCats();
                       break;
                   case "favorito":
                        CatService.catToFAvorite(gato);
                       break;
                   case "volver":
                       break;
               }
           }else{
               System.out.println("Fue Nulo");
           }

          /* Response response = okHttpClient.newCall(request).execute();
           String responseJson = response.body().string();*/
       }catch (Exception e){
           JOptionPane.showMessageDialog(null , e.getMessage());
       }
   }

   private static void catToFAvorite(CatDto gato){
       JSONObject data1 = new JSONObject();
       data1.put("image_id", gato.getId());
       JSONArray array = new JSONArray();
       array.put(data1);
       System.out.println(gato.getPvtKey());

       try {
           HttpClient client = HttpClient.newBuilder().build();
           HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://api.thecatapi.com/v1/favourites"))
                   .header("Content-Type", "application/json" )
                   .header("x-api-key", gato.getPvtKey())
                   .POST(HttpRequest.BodyPublishers.ofString("{\r\n\t\"image_id\":\""+gato.getId()+"\"\r\n}"))
                   .build();
           HttpResponse<String> gatoResponseFavorite = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
   }

}
