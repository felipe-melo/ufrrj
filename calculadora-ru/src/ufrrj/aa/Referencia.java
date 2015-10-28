package ufrrj.aa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Referencia {
	
	//Busca nos arquivos os atributos de uma alimento
	public static Alimento Busca_Alimento(String descricao, float quantidade) {
		
		File taco = new File("/home/felipe/Documentos/workspaces/ufrrj/calculadora-ru/taco.csv");
		File agtaco = new File("/home/felipe/Documentos/workspaces/ufrrj/calculadora-ru/agtaco.csv");
		File aminoacido = new File("/home/felipe/Documentos/workspaces/ufrrj/calculadora-ru/aminoacidos.csv");
		
		boolean existe = false;
		
		Alimento alimento = null;
		Gordura gordura = null;
		Aminoacido aminoacido_obj = null;
		float valor;
		
		int id;
		
		try {
			
			FileReader tacofr = new FileReader(taco);
			BufferedReader tacobr = new BufferedReader(tacofr);
			
			while (tacobr.ready()){
				String[] linha = tacobr.readLine().split(";");
				
				if (linha[1].equals(descricao)){
					id = Integer.parseInt(linha[0]);
					existe = true;
					
					float[] line = new float[linha.length - 2]; //Elimina as 2 primeiras colunas que são o id e a descrição do alimentos
					
					String numero = linha[2].replace(',', '.');
					
					try {
						valor = Float.parseFloat(numero);
						line[0] = (valor * quantidade)/100;							
					}catch(NumberFormatException e) {
						line[0] = 0;
					}
					
					for (int i = 1; i < line.length; i++){
						
						numero = linha[i+2].replace(',', '.');
						
						try {
							valor = Float.parseFloat(numero);
							line[i] = (valor * quantidade)/100;							
						}catch(NumberFormatException e) {
							line[i] = 0;
						}						
					}
					
					alimento = new Alimento(id, linha[1], line);					
					break;
				}				
			}
			
			if (!existe){
				System.out.println("Alimento não encontrado.");
			}else{
				FileReader agtacofr = new FileReader(agtaco);
				BufferedReader agtacobr = new BufferedReader(agtacofr);
				
				while (agtacobr.ready()){
					
					String[] linha = agtacobr.readLine().split(";");
					id = Integer.parseInt(linha[0]);
					
					if (id == alimento.id){
						
						float[] line = new float[linha.length-2];
						
						for (int i = 0; i < line.length; i++){
							
							String numero = linha[i].replace(',', '.');
							
							try{
								valor = Float.parseFloat(numero);
								line[i] = (valor * quantidade)/100;								
							}catch(NumberFormatException e){
								line[i] = 0;
							}
						}
						
						gordura = new Gordura(id, alimento.descricao, alimento.atributos_taco, line);						
						break;
					}
				}
				
				FileReader aminoacidofr = new FileReader(aminoacido);
				BufferedReader aminoacidobr = new BufferedReader(aminoacidofr);
				
				while (aminoacidobr.ready()){
					String[] linha = aminoacidobr.readLine().split(";");
					id = Integer.parseInt(linha[0]);
					
					if (id == alimento.id){
						
						float[] line = new float[linha.length-2];
						
						for (int i = 0; i < line.length; i++){
							
							String numero = linha[i].replace(',', '.');
							
							try{
								valor = Float.parseFloat(numero);
								line[i] = (valor * quantidade)/100;								
							}catch(NumberFormatException e){
								line[i] = 0;
							}
						}
						
						aminoacido_obj = new Aminoacido(id, alimento.descricao, alimento.atributos_taco, line);						
						break;
					}
				}
				
			}
			
			//Analis qual tipo de alimento deve ser retornado
			if (alimento != null && gordura == null && aminoacido_obj == null){
				return alimento;
			}else if (gordura != null && aminoacido_obj == null){
				return gordura;
			}else if (aminoacido_obj != null && gordura == null){
				return aminoacido_obj;
			}else if (gordura != null && aminoacido_obj != null){
				Complexo complexo = new Complexo(alimento.id, alimento.descricao, alimento.atributos_taco, gordura, aminoacido_obj);
				return complexo;
			}else{
				return null; //ninguém foi instanciado
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
