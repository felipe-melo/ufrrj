package ufrrj.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ufrrj.aa.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufrrj.model.Ingredientes;

/**
 * Servlet implementation class Calculadora
 */
@WebServlet("/Calculadora")
public class Calculadora extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calculadora()
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
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	
	{			
		String[] alimentos;
		String aux1;
		float[] gramas;
		byte[] bytes;
		Refeicao prato_do_dia = new Refeicao();
		
		//System.out.println(request.getParameter("ingredients"));
		String str = request.getParameter("ingredients");
		
		//Verifica se nenhum alimento foi enviado
		if (!str.equals("#")){
			
			//Converte as caracteres especiais que vierem destorcidos em padrão UTF-8
			bytes = request.getParameter("ingredients").getBytes("ISO-8859-1");
			
			aux1 = new String(bytes, "UTF-8");		
			String[] vet = aux1.split("#");
			
			alimentos = new String[vet.length-1];
			gramas = new float[vet.length-1];
			
			for (int i = 1; i < vet.length; i++){
				String[] valores = vet[i].split(";");
				for (int j = 1; j < valores.length; j++){
					if (j%2==0){
						
						String[] aux = valores[j].split(",");					
						gramas[i-1] = Float.parseFloat(aux[1]);
						
					}else{
						String[] aux = valores[j].split(",");
						alimentos[i-1] = aux[1];
						
						for (int k = 2; k < aux.length; k++){
							alimentos[i-1] += "," + aux[k];
						}
					}
				}
			}
			
			for (int i = 0; i < alimentos.length; i++){
				prato_do_dia.AdicionaIngrediente(alimentos[i], gramas[i]);				
			}
		}
						
		//Gera o Resultado
		request.setAttribute("result", prato_do_dia.Gera_Tabela());		
		getServletContext().getRequestDispatcher("/WEB-INF/views/resultado.jsp").forward(request, response);
	}
}
