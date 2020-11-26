/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author viter
 */
@WebServlet(name = "AdicionarEdicaoEventoServlet", urlPatterns = {"/AdicionarEdicaoEventoServlet"})
public class AdicionarEdicaoEventoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscaServlet at " + request.getContextPath() + "</h1>");
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
        JPAEvento jev = new JPAEvento();

        List<EventoEntity> eventos = jev.buscaNomeEvento(request.getParameter("nomeev"));

        int k = eventos.size();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Evento - Resultado de Consulta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado da consulta para evento com nomenome \"" + request.getParameter("nomeev") + "\"</h1>");
            out.println("<p>Foram encontrados " + k + " evento(s) com nome \"" + request.getParameter("nomeev") + "\":<p>");

            Iterator<EventoEntity> eventosAsIterator = eventos.iterator();
            EventoEntity ent = null;
            while (eventosAsIterator.hasNext()) {
                ent = eventosAsIterator.next();
                out.println("<p> ID:" + ent.getId() + "\"</p>");
                out.println("<p> Sigla:" + ent.getSigla_evento() + "\"</p>");
                out.println("<p> Instituição Organizadora:" + ent.getInst_organizadora() + "\"</p>");
                out.println("<p> Area Evento:" + ent.getArea_concent_evento() + "\"</p>");
            }

            
            request.setAttribute("id", ent.getId());
            request.setAttribute("area", ent.getArea_concent_evento());
            request.setAttribute("instorg", ent.getInst_organizadora());
            request.setAttribute("nome", ent.getNome_evento());
            request.setAttribute("sigla", ent.getSigla_evento());            
            request.getRequestDispatcher("AddEditionUpdateEvent.jsp").forward(request, response);
            //colocar a parte do cadastrar que manda parametros

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
