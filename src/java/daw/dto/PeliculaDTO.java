/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author admc0
 */
public class PeliculaDTO {

    @SerializedName("id")
    private int idApi;

    @SerializedName("title")
    private String titulo;

    @SerializedName("overview")
    private String descripcion;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("release_date")
    private String fechaEstreno;

    @SerializedName("vote_average")
    private double puntuacionMedia;

    public int getIdApi() {
        return idApi;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public double getPuntuacionMedia() {
        return puntuacionMedia;
    }
    

    public String getPosterUrlCompleta() {
        if (posterUrl != null && !posterUrl.isEmpty()) {
            return "https://image.tmdb.org/t/p/w500" + posterUrl;
        } else {
            return "https://via.placeholder.com/500x750.png?text=No+Image";
        }
    }

}
