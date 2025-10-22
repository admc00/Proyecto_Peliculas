/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import daw.model.Usuarios;
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
import jakarta.transaction.UserTransaction;
import java.util.logging.Logger;

/**
 *
 * @author admc0
 */
@WebServlet(name = "RegistroController", urlPatterns = {"/registro"})
public class RegistroController extends HttpServlet {

    @PersistenceContext(unitName = "Proyecto_PeliculasPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private static final Logger log = Logger.getLogger(RegistroController.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        String vistaJSP = "/WEB-INF/views/registro.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(vistaJSP);
        rd.forward(request, response);
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

        String nombreUsuario = request.getParameter("nombreUsuario");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        if (nombreUsuario == null || nombreUsuario.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("error", "Error: datos no válidos");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        try {
            Usuarios nuevoUsuario = new Usuarios(nombreUsuario, email, contrasena);

            save(nuevoUsuario);

            response.sendRedirect("login");

        } catch (Exception e) {
            // 4. Si hay un error (ej. email duplicado)
            request.setAttribute("error", "Error al registrar. El email quizás ya existe.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
            dispatcher.forward(request, response);
        }

    }

    public void save(Usuarios u) {
        try {
            utx.begin();
            em.persist(u); // Solo persistimos (creamos)
            utx.commit();
        } catch (Exception e) {
            // Log.log(Level.SEVERE, "exception caught", e); // (Opcional)
            throw new RuntimeException(e);
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
