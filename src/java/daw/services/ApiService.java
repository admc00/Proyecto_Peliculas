/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.services;

import com.google.gson.Gson;
import daw.dto.PeliculaDTO;
import daw.dto.RespuestaApiDTO;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author admc0
 */
public class ApiService {
    
    private static final String API_KEY = "7b3f00f8302e833bb08cb42f6dc83d08";
    
    private static final String API_BASE_URL = "https://api.themoviedb.org/3";
    
    private final HttpClient httpClient;
    private final Gson gson;
    
    public ApiService(){
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }
    
    public List<PeliculaDTO> buscarPeliculas(String query) {
        try {
            String queryEncoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = String.format("%s/search/movie?api_key=%s&query=%s&language=es-ES",
                    API_BASE_URL, queryEncoded);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonBody = response.body();
            
            RespuestaApiDTO respuestaApi = gson.fromJson(jsonBody, RespuestaApiDTO.class);
            return respuestaApi.getPeliculas();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver lista vacía en caso de error
        }
    }
    
    
    public PeliculaDTO obtenerDetallesPelicula(int idApi) {
        try {
            String url = String.format("%s/movie/%d?api_key=%s&language=es-ES",
                    API_BASE_URL, idApi);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonBody = response.body();

            return gson.fromJson(jsonBody, PeliculaDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    
    
}
