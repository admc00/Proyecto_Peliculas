/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author FX506
 */
@Entity
@Table(name = "reseña")
public class Reseña implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int idUsuario;
    private int idApiPelicula;
    private String texto;
    private int puntuacion;
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reseña() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdApiPelicula() {
        return idApiPelicula;
    }

    public String getTexto() {
        return texto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdApiPelicula(int idApiPelicula) {
        this.idApiPelicula = idApiPelicula;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reseña)) {
            return false;
        }
        Reseña other = (Reseña) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "daw.model.Rese\u00f1a[ id=" + id + " ]";
    }
    
}
