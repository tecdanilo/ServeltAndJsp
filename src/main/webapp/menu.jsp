<%@ page errorPage = "error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span class="glyphicon glyphicon-folder-close">
                </span>Usuários</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="usuario?action=edit">Cadastro</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-flash text-success"></span><a href="usuario?action=listUsr">Lista</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    
    <div class="panel panel-default" style="display: none;">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span class="glyphicon glyphicon-user">
                </span>Produtos</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="produto?action=edit">Cadastro</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-flash text-success"></span><a href="produto?action=listProduto">Consulta</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    
    <!-- <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3"><span class="glyphicon glyphicon-file">
                </span>Reports</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="http://www.jquery2dotnet.com">Catálogo de Produtos</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-flash text-success"></span><a href="http://www.jquery2dotnet.com">Usuários Ativos</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div> -->
</div>