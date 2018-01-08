<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="br.com.avaliacaojavajr.webtest.dto.UsuarioDTO"%>
<div class="navbar navbar-default navbar-fixed-top"	style="margin-bottom: 0; padding-left: 0; list-style: none;">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href=".">Stling Gestão</a>
		</div>
		<% UsuarioDTO usuarioLogado = (UsuarioDTO) session.getAttribute("usuarioLogado"); %>
		<div class="pull-right" style="padding: 10px;">Bem vindo, <%= usuarioLogado != null ? usuarioLogado.getNome() : "" %>  &nbsp;&nbsp; <a href="login">Sair</a></div>
	</div>
</div>