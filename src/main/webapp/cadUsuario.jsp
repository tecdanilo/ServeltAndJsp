<%@ page errorPage = "error.jsp" %>
<%@page import="br.com.avaliacaojavajr.webtest.dto.TipoUsuarioDTO"%>
<%@page import="br.com.avaliacaojavajr.webtest.dto.UsuarioDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Usuarios</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/estilo.css">
<script src="bower_components/jquery/dist/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
    if (!window.jQuery) {
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = '//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js';
        document.getElementsByTagName('head')[0].appendChild(script);
    }
</script>

<script>

function validacao() {
	//alert("ok");
	if ( $("#email").val() == null || $("#email").val() == "") {
		$('#modal-content').modal({
		    show: true
		});
		$('#mensagem_validacao').html('O campo email é obrigatório');
		return false
    }
    

	if ( $("#senha").val() == null || $("#senha").val() == "") {
		$('#modal-content').modal({
		    show: true
		});
		$('#mensagem_validacao').html('O campo senha é obrigatório');
		return false
    }

	$("form" ).submit();
}

</script>

<%
UsuarioDTO user = (UsuarioDTO) request.getAttribute("user");
List<TipoUsuarioDTO> tipos = (ArrayList<TipoUsuarioDTO>) request.getAttribute("tipos");
String action = "";
if (user == null){
	action = "insert";
}else{
	action = "update";
}

if (tipos == null){
	tipos = new ArrayList<TipoUsuarioDTO>();
}
%>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
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
				                <form action="usuario" method="post">
				                	<input type="hidden" id="action" name="action" value="<%= action %>">
				                	<input type="hidden" id="id" name="id" value="<%= user != null ? user.getId() : null %>">
									
									<h3>Cadastro de usuários</h3>
									
									
									<!-- 
										Implementar tela. Fique à vontade para utilizar Tags JSTL ao invéz de scriptlets
										Esta tela pode ser reaproveitada para edição dos dados. 
									-->
									
									<label>Nome :</label>
									<input type="text" name="nome" id="nome" value="<%= user != null ? user.getNome() : "" %>">
									<br>
									
									<label>Email:*</label>
									<input type="text" name="email" id="email" value="<%= user != null ? user.getEmail() : "" %>">
									<br>

									<label>Senha*:</label>
									<input type="password" name="senha" id="senha" value="<%= user != null ? user.getSenha() : "" %>">
									<br>
									
									<label>Tipo :</label>									
									<select name="tipoUsuario" id="tipoUsuario">
									<%
										for (TipoUsuarioDTO tipo : tipos) { %>
										<option value="<%= tipo.getId() %>"><%= tipo.getDescricao() %></option>									
									<%  }  %>
									</select>
									<br>
									
									<button value="Salvar" type="button" id="go" name="go" onclick="validacao();">Salvar</button>
									<button value="Cancelar" type="reset">Cancelar</button>
								</form>
				            </div>
				        </div>
				    </div>
				</div>
		</div>
	</div>
	
	<!-- Modal de validacao-->
<div class="modal fade" id="modal-content" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="labelModal">Campo obrigatório</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="mensagem_validacao" >
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
</body>
</html>
