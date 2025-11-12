/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author admc0
 */
public class RespuestaApiDTO {

    @SerializedName("results")
    private List<PeliculaDTO> peliculas;

    public List<PeliculaDTO> getPeliculas() {
        return peliculas;
    }

}
