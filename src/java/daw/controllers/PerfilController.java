/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import jakarta.annotation.Resource;
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
import jakarta.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admc0
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/perfil/*"})
public class PerfilController extends HttpServlet {

    @PersistenceContext(unitName = "Proyecto_PeliculasPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private static final Logger LOG = Logger.getLogger(PerfilController.class.getName());

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
        String vista = "/home";
        

        if (action == null) {
            action = "/home";
        }
        
        
         try {
            switch (action) {
                case "/*":
                    vista = "/WEB-INF/views/perfil.jsp";
                    break;

            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error inesperado en doGet", e);
            request.setAttribute("errorMsg", "Ocurrió un error inesperado al procesar tu solicitud.");

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(vista);
        dispatcher.forward(request, response);

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
