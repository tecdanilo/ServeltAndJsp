<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/estilo.css">
<style type="text/css">

.errorMessage {
	color: red;
}

</style>
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>

</head>
<body>
	<% 
		String msg = (String) request.getAttribute("mensagem");
		if ( msg == null ) {
			msg = "";
		}
	
	%>
	
	<div class="container-fluid">
		<div class="row">
			<br />
			<div class="container">
				    <div class="row">
				    	<div class="well" style="max-width: 300px; margin: auto">
					        <form action="login" method="post">
					        	<label style="color: red"><%= msg %></label>
					        	
					        	<h3>Login - Stling Gestão</h3>
					        	<label>e-mail*:</label>
								<input type="text" id="txtEmail" name="txtEmail" value="" > <br />
					        	<label>senha*:</label>
								<input type="password" id="txtSenha" name="txtSenha" value="" > <br />
								<button class="btn btn-primary">Entrar</button>
					        </form>
					     </div>
				    </div>
			</div>
		</div>
	</div>
	
</body>
</html>