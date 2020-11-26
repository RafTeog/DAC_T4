﻿<%-- 
    Document   : MostraCadastrado.jsp
    Created on : 28/09/2020, 17:33:36
    Author     : viter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda - Página de Falha no Cadastro</title>
    </head>
    <body>
        <h1>A seguinte entrada não foi cadastrada:</h1>        
        <p>Id: <%= request.getAttribute("id") %></p>
        <p>Nome: <%= request.getParameter("nomeev") %></p>
        <p>Sigla: <%= request.getParameter("siglaev") %></p>
        <p>Area: <%= request.getParameter("areaev") %></p>
        <p>Instituição: <%= request.getParameter("instev") %></p></br>
        <p><a href="http://localhost:8080/Demo-002-2020-1/CreateEvent.html">Cadastrar outro</a></p>
    </body>
</html>
