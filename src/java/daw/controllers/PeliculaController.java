/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import daw.dto.PeliculaDTO;
import daw.services.ApiService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admc0
 */

@WebServlet(name = "PeliculaController", urlPatterns = {"/pelicula/*"})
public class PeliculaController extends HttpServlet {
    
    private ApiService apiService = new ApiService();

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
                    
                    request.setAttribute("pelicula", pelicula);
                    vista = "/WEB-INF/views/detallesPelicula.jsp"; 
                    break;
                    
                default:
                  
                    response.sendRedirect(request.getContextPath() + "/home");
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
