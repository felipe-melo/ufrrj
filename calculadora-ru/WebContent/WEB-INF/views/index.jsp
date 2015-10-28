<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>  	
    <title>Calculadora de Alimentos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

    <link href="style.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">UFRRJ</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
        <div class="page-header">
            <br><h1>Calculadora</h1>
            <div class="alert alert-danger fade in" style="display:none;">
                <button aria-hidden="false" class="close" type="button">&times;</button>
                <strong>Problema</strong> Ingrediente ou quantidade faltando ou digitada de forma errada.
            </div>
        </div>
        <form role="form" id="formIngredientes" method="post" action="${pageContext.request.contextPath}/Calculadora">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="ingredientes">Ingrediente</label>
                        <select name="department" class="form-control" id="ingredient" placeholder="Qual o ingrediente?">
          					<c:forEach var="ingrediente" items="${ingredientes}">
            					<option value="${ingrediente.key}">${ingrediente.value}</option>
          					</c:forEach>
        				</select>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="exampleInputPassword1">Quantidade</label>
                        <input type="number" class="form-control" id="quantity" placeholder="Quantidade?" data-bind="value:replyNumber">
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary" style="margin-top: 24px;" id="add"><span class="glyphicon glyphicon-plus"></span></button>
                </div>
            </div>
            <div class="table">
                <table class="table table-condensed" id="ingredients">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Ingrediente</th>
                            <th>Quantidade (Gramas)</th>
                        </tr>
                    </thead>
                    <tbody>
                   </tbody>
               </table>
            </div>
            <button type="submit" class="btn btn-default btn-lg">Gerar</button>
        </form>
    </div> <!-- /container -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            $("#add").click(function() {
               var ingredientId = $('#ingredient').val();
               var ingredient = $('#ingredient option:selected').text();
               var quantity = $("#quantity").val();

               if(ingredient == "" || (quantity == "" || !(quantity >=0 || quantity < 0))) {
                    $(".alert").show();
                    return;
               }
               $(".alert").hide();

               addIngredient($("ingredients"), 0, ingredientId, ingredient, quantity);

               $('#ingredient').val("c");
               $('#quantity').val("")
            });

           	$(".close").click(function(){
                $(".alert").hide();
            });

            $('.twitter-typeahead').css('display','inline');
            $('.tt-hint').addClass("form-control");
        });
        function addIngredient(table, id, ingredientId, ingredient, quantity) {
            var i = $('tr', $("#ingredients").find('tbody')).length;
            $("#ingredients").find("tbody").append("<tr><td>" + ++i + "</td><td>"+ ingredient +"</td><td>" + quantity + "</td></tr>");
        };
        
        $("#formIngredientes").submit(function(){
        	var data = $('table#ingredients tr').map(function() {
				return $(this).find('td').map(function() {
					return $(this).html() + ";";
				}).get() + "#";
        	}).get();
        	
        	$('<input type="hidden" name="ingredients"/>').val(data).appendTo('#formIngredientes');
        	
			return true;
		});
    </script>
  </body>
</html>
