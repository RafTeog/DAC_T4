/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RAF
 */
@WebServlet(name = "CadastrarEventoServlet", urlPatterns = {"/CadastrarEventoServlet"})
public class CadastrarEventoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   // </editor-fold>
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarEventoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarEventoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        // Obtain a database connection:
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        //EntityManager em = JPAUtil.getEM();
        JPAEvento JPAev = new JPAEvento();

        EventoEntity ee = new EventoEntity();
        ee.setNome_evento(request.getParameter("nomeev"));
        ee.setSigla_evento(request.getParameter("siglaev"));
        ee.setArea_concent_evento(request.getParameter("areaev"));
        ee.setInst_organizadora(request.getParameter("instev"));

        //EntityTransaction et = em.getTransaction();
        //et.begin();
        //em.persist(entrada);
        //et.commit();
        JPAev.salva(ee);

        Long id = ee.getId();

        request.setAttribute("id", id);

        ServletContext servcontext = request.getServletContext();

        if (JPAev.recupera(id) != null) {
            RequestDispatcher dispatcher = servcontext.getRequestDispatcher("/MostraEventoCadastrado.jsp");
            dispatcher.include(request, response);
        } else {
            RequestDispatcher dispatcher = servcontext.getRequestDispatcher("/MostraFalha.jsp");
            dispatcher.include(request, response);
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
    }//

}
