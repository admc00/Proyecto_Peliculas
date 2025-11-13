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

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author admc0
 */
public class ApiService {

    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YjNmMDBmODMwMmU4MzNiYjA4Y2I0MmY2ZGM4M2QwOCIsIm5iZiI6MTc2MjQyMDYzOS43MDMsInN1YiI6IjY5MGM2NzlmMTFjZTcyOTg2NjA3MTU2MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4bcfR3nh9Grl3Y_CfiWCblwRNKeF3BG7FX_5FNQcbL0";

    private static final String API_BASE_URL = "https://api.themoviedb.org/3";

    private static final String RUTA_CERTIFICADO = "C:\\Users\\admc0\\Desktop\\_.themoviedb.org.crt";

    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        HttpClient tempClient = null;
        try {
            // --- INICIO DE LA SOLUCIÓN SSL SEGURA ---

            // 1. Cargar el archivo .crt del escritorio
            FileInputStream fis = new FileInputStream(RUTA_CERTIFICADO);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate tmdbCert = (X509Certificate) cf.generateCertificate(fis);
            fis.close();

            // 2. Crear un KeyStore en memoria y añadir el certificado
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null); // Inicia un KeyStore vacío
            keyStore.setCertificateEntry("tmdb-alias", tmdbCert); // Añade nuestro certificado

            // 3. Crear un TrustManagerFactory que confíe en nuestro KeyStore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);

            // 4. Crear un SSLContext que use nuestro TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // 5. Construir el HttpClient usando nuestro SSLContext personalizado
            tempClient = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

            // --- FIN DE LA SOLUCIÓN ---
        } catch (Exception e) {
            e.printStackTrace();
            // Si todo falla, usa el cliente inseguro como último recurso (basado en el éxito anterior)
            tempClient = createInsecureHttpClient();
        }

        this.httpClient = tempClient;
        this.gson = new Gson();
    }

    private HttpClient createInsecureHttpClient() {
        try {
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
                new javax.net.ssl.X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return HttpClient.newBuilder().sslContext(sslContext).build();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpClient.newHttpClient(); // Fallback final
        }
    }

    public List<PeliculaDTO> buscarPeliculas(String query) {

        try {

            String queryEncoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = String.format("%s/search/movie?query=%s&include_adult=false&language=es-ES",
                    API_BASE_URL, queryEncoded);

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
                    API_BASE_URL, idMovie);

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
