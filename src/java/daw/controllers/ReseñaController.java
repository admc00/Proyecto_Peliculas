/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import daw.dto.PeliculaDTO;
import daw.model.Peliculas;
import daw.model.Reseña;
import daw.model.Usuarios;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admc0
 */
@WebServlet(name = "ReseñaController", urlPatterns = {"/resena/*"})
public class ReseñaController extends HttpServlet {

    @PersistenceContext(unitName = "Proyecto_PeliculasPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        String vista = "/WEB-INF/views/home.jsp";

        try {
            if (action == null) {
                action = "/";
            }

            switch (action) {
                case "/guardar":
                    guardarResena(request, response);

                    break;

                case "/eliminar":
                    
                    eliminarResena();

                    break;
                    
                    
                case "/editar":
                    
                    editarResena();
                    
                    break;

                default:

                    response.sendRedirect(request.getContextPath() + "");
                    return;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(vista);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Error al procesar la solicitud de película.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void eliminarResena(){
        
        
    }
    
    private void editarResena(){
        
    }

    private void guardarResena(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/usuario/login");
            return;
        }

        try {
            int idApi = Integer.parseInt(request.getParameter("idApiPelicula"));
            String titulo = request.getParameter("tituloPelicula");
            String poster = request.getParameter("posterPelicula");
            String fechaStr = request.getParameter("fechaPelicula");
            String descripcion = request.getParameter("descripcionPelicula");
            double media = Double.parseDouble(request.getParameter("puntuacionMedia"));

            int puntuacionUsuario = Integer.parseInt(request.getParameter("puntuacion"));
            String textoResena = request.getParameter("texto");

            utx.begin();

            Peliculas pelicula;
            try {
                TypedQuery<Peliculas> query = em.createQuery(
                        "SELECT p FROM Peliculas p WHERE p.idApi = :idApi", Peliculas.class);
                query.setParameter("idApi", idApi);
                pelicula = query.getSingleResult();
            } catch (NoResultException e) {

                pelicula = new Peliculas();
                pelicula.setId((long) idApi);
                pelicula.setIdApi(idApi);
                pelicula.setTitulo(titulo);
                pelicula.setPosterUrl(poster);
                pelicula.setDescripcion(descripcion);
                pelicula.setPuntuacionMedia(media);
                pelicula.setFechaEstreno(new Date());

                em.persist(pelicula);
            }

            Reseña nuevaResena = new Reseña();
            nuevaResena.setUsuario(usuario);
            nuevaResena.setPelicula(pelicula);
            nuevaResena.setPuntuacion(puntuacionUsuario);
            nuevaResena.setTexto(textoResena);
            nuevaResena.setFecha(new Date());

            em.persist(nuevaResena);

            utx.commit();

            response.sendRedirect(request.getContextPath() + "/pelicula/detalles?id=" + idApi);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
