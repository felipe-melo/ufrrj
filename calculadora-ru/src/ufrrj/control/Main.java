package ufrrj.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufrrj.model.Ingredientes;

/**
 * Servlet implementation class Main
 */
@WebServlet("/index.jsp")
public class Main extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		action(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		action(request, response);
	}

	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		File taco = new File("/home/felipe/Documentos/workspaces/ufrrj/calculadora-ru/taco.csv");
		FileReader tacofr = new FileReader(taco);
		BufferedReader tacobr = new BufferedReader(tacofr);
		
		Ingredientes ingredientes = new Ingredientes();
		
		while(tacobr.ready()){
			String[] descricao = tacobr.readLine().split(";");			
			ingredientes.addIngrediente(descricao[1]);
		}
		
		request.setAttribute("ingredientes", ingredientes.getIngredientes());
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}
}
