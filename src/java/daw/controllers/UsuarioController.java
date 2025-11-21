
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controllers;

import org.mindrot.jbcrypt.BCrypt;
import daw.model.Usuarios;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author admc0
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario/*"})
public class UsuarioController extends HttpServlet {

    @PersistenceContext(unitName = "Proyecto_PeliculasPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        String vista = "error";

        if (action == null) {
            action = "/home";
        }

        try {
            switch (action) {
                case "/login":
                    vista = "/WEB-INF/views/login.jsp";
                    break;

                case "/registro":
                    vista = "/WEB-INF/views/registro.jsp";
                    break;

                case "/perfil":
                    vista = "/WEB-INF/views/perfil.jsp";
                    break;

                case "/logout":
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                        LOG.log(Level.INFO, "Sesion invalidada.");
                    }
                    response.sendRedirect(request.getContextPath() + "?aviso=logoutExitoso");
                    return;

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

        String action = request.getPathInfo();

        if (action == null) {
            request.setAttribute("errorMsg", "Acción POST no especificada.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            switch (action) {
                case "/login":
                    procesarLogin(request, response);
                    break;

                case "/registro":
                    procesarRegistro(request, response);
                    break;

            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error inesperado en doPost", e);
            request.setAttribute("errorMsg", "Ocurrió un error inesperado al procesar tu formulario.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        try {
            TypedQuery<Usuarios> query = em.createQuery(
                    "SELECT u FROM Usuarios u WHERE u.email = :email", Usuarios.class);
            query.setParameter("email", email);
            Usuarios usuario = query.getSingleResult();

            if (verificarPassword(contrasena, usuario.getContrasena())) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", usuario);
                LOG.log(Level.INFO, "Login exitoso para: {0}", email);
                response.sendRedirect(request.getContextPath() + "?aviso=loginExitoso");
            } else {

                throw new SecurityException("Contraseña incorrecta.");
            }

        } catch (NoResultException | SecurityException e) {
            LOG.log(Level.WARNING, "Fallo de login para: {0} ({1})", new Object[]{email, e.getMessage()});
            request.setAttribute("error", "Email o contraseña incorrectos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void procesarRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        try {

            if (nombreUsuario == null || email == null || !email.contains("@") || contrasena == null || contrasena.length() < 4) {
                throw new IllegalArgumentException("Datos de registro inválidos.");
            }

            TypedQuery<Long> queryEmail = em.createQuery("SELECT COUNT(u) FROM Usuarios u WHERE u.email = :email", Long.class);
            queryEmail.setParameter("email", email);
            if (queryEmail.getSingleResult() > 0) {
                throw new IllegalArgumentException("El email ya está registrado.");
            }

            TypedQuery<Long> queryNombre = em.createQuery("SELECT COUNT(u) FROM Usuarios u WHERE u.nombreUsuario = :nombre", Long.class);
            queryNombre.setParameter("nombre", nombreUsuario);
            if (queryNombre.getSingleResult() > 0) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }

            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombreUsuario(nombreUsuario);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setContrasena(hashPassword(contrasena));

            save(nuevoUsuario);
            LOG.log(Level.INFO, "Nuevo usuario registrado: {0}", email);

            response.sendRedirect(request.getContextPath() + "/usuario/login?aviso=registroExitoso");

        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Intento de registro fallido (datos invalidos): {0}", e.getMessage());
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registro.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void save(Usuarios u) throws Exception {
        Long id = u.getId();
        try {
            utx.begin();

            if (id == null) {
                em.persist(u);
                LOG.log(Level.FINE, "Persistiendo nuevo usuario");
            } else {
                em.merge(u);
                LOG.log(Level.FINE, "Usuario {0} actualizado", id);
            }
            utx.commit();
            LOG.log(Level.FINE, "Transacción completada");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error en transacción", e);
            throw new RuntimeException(e);
        }
    }

    private boolean verificarPassword(String passwordIngresada, String hashAlmacenado) {
        try {
            return BCrypt.checkpw(passwordIngresada, hashAlmacenado);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.SEVERE, "Error al verificar hash", e);
            return false;
        }

    }

    private String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt(12));

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
