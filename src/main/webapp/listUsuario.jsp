<%@ page errorPage = "error.jsp" %>
<%@page import="br.com.avaliacaojavajr.webtest.dto.UsuarioDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Usuarios</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/estilo.css">
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

<% 
	String msg = (String) request.getAttribute("mensagem");
	if ( msg == null ) {
		msg = "";
	}
%>

$( document ).ready(function() {
	
   	var msg = '<%=msg%>;'


   	if ( msg != "" ) {

   	   	if ( msg.indexOf("cadastrado") > 0 ) {
   	   	   	$('#spanMsgSucesso').html( msg );
		   	$('#alertSucess').show();

	   	} else if ( msg.indexOf("editado") > 0 ) {
	   		$('#spanMsgSucesso').html( msg );
		   	$('#alertSucess').show();

	   	} else if ( msg.indexOf("deletado") > 0 ) {
	   		$('#spanMsgSucesso').html( msg );
		   	$('#alertSucess').show();

	   	} else if ( msg.indexOf("cadastrar") > 0 ) {
	   		$('#alertWarn').html( msg );
		   	$('#spanMsgWarn').show();

	   	} else if ( msg.indexOf("editar") > 0 ) {
	   		$('#alertWarn').html( msg );
		   	$('#spanMsgWarn').show();

	   	} else if ( msg.indexOf("deletar") > 0 ) {
	   		$('#alertWarn').html( msg );
		   	$('#spanMsgWarn').show();

		}  
		 
	}
	    
});

</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="alert alert-success alert-dismissable collapse" id="alertSucess">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	  	<strong>Sucesso!</strong> <span id="spanMsgSucesso"></span>
	</div>
	
	<div class="alert alert-info alert-dismissable collapse" id="alertInfo">
	  	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	  	<strong>Infomação!</strong><span id="spanMsgInfo"></span>
	</div>
	
	<div class="alert alert-warning alert-dismissable collapse" id="alertWarn">
	  	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	  	<strong>Alerta!</strong> <span id="spanMsgWarn"></span>
	</div>
	
	<div class="alert alert-danger alert-dismissable collapse" id="alertError">
	  	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	  	<strong>Erro!</strong> <span id="spanMsgError"></span>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			<br />
			<div class="container">
				    <div class="row">
				        <div class="col-xs-3">
				            <jsp:include page="menu.jsp"></jsp:include>
				        </div>
				        <div class="col-xs-9">
				            <div class="well" style="min-height: 400px;">
				                <h3>Lista de usuários</h3>
								<table class="table">
									<tr>
										<td>Id</td>
										<td>Nome</td>
										<td>E-mail</td>
										<td>tipo</td>
										<td>Excluir</td>
									</tr>
									<%
									ArrayList<UsuarioDTO> users = (ArrayList<UsuarioDTO>) request.getAttribute("users");
									for (UsuarioDTO user : users){
									%>
										<tr>
											<td><a href="usuario?action=edit&id=<%= user.getId()%>" ><%= user.getId()%></a></td>
											<td><%= user.getNome() %></td>
											<td><%= user.getEmail() %></td>
											<td><%= user.getTipo().getDescricao() %></td>
											<td><a href="usuario?action=delete&id=<%= user.getId()%>" >X</a></td>
										</tr>
										<%
									}
									%>
									<%-- <c:forEach var="user" items="${users}">
									
									</c:forEach> --%>
								</table>
				            </div>
				        </div>
				    </div>
				</div>
		</div>
	</div>
</body>
</html>
