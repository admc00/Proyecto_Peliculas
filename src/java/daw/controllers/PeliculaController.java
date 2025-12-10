/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import daw.dto.PeliculaDTO;
import daw.model.Reseña;
import daw.services.ApiService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author admc0
 */
@WebServlet(name = "PeliculaController", urlPatterns = {"/pelicula/*"})
public class PeliculaController extends HttpServlet {

    private ApiService apiService = new ApiService();

    @PersistenceContext(unitName = "Proyecto_PeliculasPU")
    private EntityManager em;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        String vista = "/WEB-INF/views/home.jsp";

        try {
            if (action == null) {
                action = "/";
            }

            switch (action) {
                case "/buscar":

                    String query = request.getParameter("query");

                    List<PeliculaDTO> peliculas = apiService.buscarPeliculas(query);

                    request.setAttribute("peliculas", peliculas);
                    request.setAttribute("terminoBusqueda", query);
                    vista = "/WEB-INF/views/resultado.jsp";
                    break;

                case "/detalles":

                    int idApi = Integer.parseInt(request.getParameter("id"));

                    PeliculaDTO pelicula = apiService.obtenerDetallesPelicula(idApi);

                    request.setAttribute("detalles", pelicula);

                    List<Reseña> listaResenas = obtenerResenasPorIdApi(idApi);
                    request.setAttribute("listaResenas", listaResenas);

                    vista = "/WEB-INF/views/pelicula.jsp";
                    break;

                case "/guardar":

                    int idAdd = Integer.parseInt(request.getParameter("id"));
                    HttpSession sessionAdd = request.getSession();

                    List<PeliculaDTO> cesta = (List<PeliculaDTO>) sessionAdd.getAttribute("cestaPeliculas");
                    if (cesta == null) {
                        cesta = new ArrayList<>();
                    }

                    boolean existe = false;
                    for (PeliculaDTO p : cesta) {
                        if (p.getIdApi() == idAdd) {
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        PeliculaDTO peliNueva = apiService.obtenerDetallesPelicula(idAdd);
                        if (peliNueva != null) {
                            cesta.add(peliNueva);
                            sessionAdd.setAttribute("cestaPeliculas", cesta);
                        }
                    }

                    response.sendRedirect(request.getContextPath() + "/pelicula/detalles?id=" + idAdd + "&cestaOpen=true");
                    return;

                case "/eliminar":

                    int idDel = Integer.parseInt(request.getParameter("id"));
                    HttpSession sessionDel = request.getSession();
                    List<PeliculaDTO> cestaDel = (List<PeliculaDTO>) sessionDel.getAttribute("cestaPeliculas");

                    if (cestaDel != null) {
                        cestaDel.removeIf(p -> p.getIdApi() == idDel);
                        sessionDel.setAttribute("cestaPeliculas", cestaDel);
                    }

                   
                    String referer = request.getHeader("Referer");
                    response.sendRedirect(referer != null ? referer : request.getContextPath() + "/");
                    return;

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

    }

    private List<Reseña> obtenerResenasPorIdApi(int idApi) {
        try {
            return em.createQuery("SELECT r FROM Reseña r WHERE r.pelicula.idApi = :idApi ORDER BY r.fecha DESC", Reseña.class)
                    .setParameter("idApi", idApi)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
