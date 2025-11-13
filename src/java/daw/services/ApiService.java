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
    
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YjNmMDBmODMwMmU4MzNiYjA4Y2I0MmY2ZGM4M2QwOCIsIm5iZiI6MTc2MjQyMDYzOS43MDMsInN1YiI6IjY5MGM2NzlmMTFjZTcyOTg2NjA3MTU2MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4bcfR3nh9Grl3Y_CfiWCblwRNKeF3BG7FX_5FNQcbL0";
    
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
            String url = String.format("%s/search/movie?query=%s&include_adult=false&language=es-ES",
                    API_BASE_URL,queryEncoded);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
           
            String jsonBody = response.body();
            
            RespuestaApiDTO respuestaApi = gson.fromJson(jsonBody, RespuestaApiDTO.class);
            return respuestaApi.getPeliculas();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    
    public PeliculaDTO obtenerDetallesPelicula(int idMovie) {
        try {
            String url = String.format("%s/movie/%d?&language=es-ES",
                    API_BASE_URL,idMovie);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
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
