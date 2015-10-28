package ufrrj.aa;

import java.util.ArrayList;
import java.util.HashMap;

public class Refeicao {
	
	private HashMap<String, String> atributos;	
	private ArrayList<Alimento> prato = new ArrayList<Alimento>();	
	private float[] valores;
	private ArrayList<Linha> tabela;
	
	//Adiciona alimento nÃ£o nulos no prato
	public void AdicionaIngrediente (String descricao, float quantidade) {
		Alimento alim = new Alimento();
		
		alim = Referencia.Busca_Alimento(descricao, quantidade);
		if (alim != null) {
			prato.add(alim);
		}
	}
	
	//Gera a tabela nutricional do prato
	public ArrayList<Linha> Gera_Tabela() {
		
		for (Alimento alim : prato) {
			
			if (alim instanceof Complexo) {
				
				for (int i = 0; i < 27; i++) {
					this.valores[i] += alim.atributos_taco[i];
				}
				
				for (int i = 0; i < 23; i++) {
					this.valores[i+27] += ((Complexo) alim).gordura.atributos_gor[i];
				}
				
				for (int i = 0; i < 19; i++) {
					this.valores[i+50] += ((Complexo) alim).aminoacido.atributos_ami[i];
				}
				continue;
			}
			
			if (alim instanceof Gordura) {
				
				for (int i = 0; i < 27; i++) {
					this.valores[i] += alim.atributos_taco[i];
				}
				
				for (int i = 0; i < 23; i++) {
					this.valores[i+27] += ((Gordura) alim).atributos_gor[i];
				}
				continue;
			}
			
			if (alim instanceof Aminoacido) {
				
				for (int i = 0; i < 27; i++) {
					this.valores[i] += alim.atributos_taco[i];
				}
				
				for (int i = 0; i < 19; i++) {
					this.valores[i+50] += ((Aminoacido) alim).atributos_ami[i];					
				}
				continue;
			}
			
			if (alim instanceof Alimento) {
				
				for (int i = 0; i < 27; i++) {
					this.valores[i] += alim.atributos_taco[i];
				}
			}
		}
		
		//CÃ¡lcula a umidade do prato
		if (this.prato.size() > 0) {
			Linha lin = new Linha();
			lin.setAtributo(this.atributos.get(String.valueOf(0)));
			lin.setValor(String.valueOf(valores[0]/prato.size()));
			this.tabela.add(lin);
		}
		
		//Aloca os atriburos e valores num ArrayList, que representa a tabela
		for (int i = 1; i < valores.length; i++) {
			
			if (valores[i] > 0.0){
				Linha linha = new Linha();
				if (i == 11 || i == 37 || i == 59) { //Elimina as colunas onde o nÃºmero do alimento Ã© repetido
					continue;
				}
				linha.setAtributo(this.atributos.get(String.valueOf(i)));
				linha.setValor(String.valueOf(valores[i]));
				
				this.tabela.add(linha);
			}
		}
		return this.tabela;
	}
	
	public Refeicao() {
		this.valores = new float[69];
		this.tabela = new ArrayList<Linha>();
		
		this.atributos = new HashMap<String, String>();
		
		this.atributos.put("0", "umidade (%)");
		this.atributos.put("1", "Energia (Kcal)");
		this.atributos.put("2", "Energia (KJ)");
		this.atributos.put("3", "ProteÃ­na (g)");
		this.atributos.put("4", "LipÃ­deos (g)");
		this.atributos.put("5", "Colesterol (mg)");
		this.atributos.put("6", "Carboidrato (g)");
		this.atributos.put("7", "Fibra Alimentar (g)");
		this.atributos.put("8", "Cinzas (g)");
		this.atributos.put("9", "CÃ¡lcio (mg)");
		this.atributos.put("10", "MagnÃ©sio (mg)");
		this.atributos.put("11", "NÃºmero");
		this.atributos.put("12", "ManganÃªs (mg)");
		this.atributos.put("13", "FÃ³sforo (mg)");
		this.atributos.put("14", "Ferro (mg)");
		this.atributos.put("15", "SÃ³dio (mg)");
		this.atributos.put("16", "PotÃ¡ssio (mg)");
		this.atributos.put("17", "Cobre (mg)");
		this.atributos.put("18", "Zinco (mg)");
		this.atributos.put("19", "Retinol (mcg)");
		this.atributos.put("20", "RE (mcg)");
		this.atributos.put("21", "RAE (mcg)");
		this.atributos.put("22", "Tiamina (mg)");
		this.atributos.put("23", "Riboflavina (mg)");
		this.atributos.put("24", "Piridoxina (mg)");
		this.atributos.put("25", "Niacina (mg)");
		this.atributos.put("26", "Vitamina C (mg)");
		this.atributos.put("27", "Saturados (g)");
		this.atributos.put("28", "Mono-insaturados (g)");
		this.atributos.put("29", "Poli-insaturados (g)");
		this.atributos.put("30", "12:0 (g)");
		this.atributos.put("31", "14:0 (g)");
		this.atributos.put("32", "16:0 (g)");
		this.atributos.put("33", "18:0 (g)");
		this.atributos.put("34", "20:0 (g)");
		this.atributos.put("35", "22:0 (g)");
		this.atributos.put("36", "24:0 (g)");
		this.atributos.put("37", "NÃºmero");
		this.atributos.put("38", "14:1 (g)");
		this.atributos.put("39", "16:1 (g)");
		this.atributos.put("40", "18:1 (g)");
		this.atributos.put("41", "20:1 (g)");
		this.atributos.put("42", "18:2 n-6 (g)");
		this.atributos.put("43", "18:3 n-3 (g)");
		this.atributos.put("44", "20:4 (g)");
		this.atributos.put("45", "20:5 (g)");
		this.atributos.put("46", "22:5 (g)");
		this.atributos.put("47", "22:6 (g)");
		this.atributos.put("48", "18:1t (g)");
		this.atributos.put("49", "18:2t (g)");
		this.atributos.put("50", "Triptofano (g)");
		this.atributos.put("51", "Treonina (g)");
		this.atributos.put("52", "Isoleucina (g)");
		this.atributos.put("53", "Leucina (g)");
		this.atributos.put("54", "Lisina (g)");
		this.atributos.put("55", "Metionina (g)");
		this.atributos.put("56", "Cistina (g)");
		this.atributos.put("57", "Fenilalanina (g)");
		this.atributos.put("58", "Tirosina (g)");
		this.atributos.put("59", "nÃºmero");
		this.atributos.put("60", "Valina (g)");
		this.atributos.put("61", "Arginina (g)");
		this.atributos.put("62", "Histidina (g)");
		this.atributos.put("63", "Alanina (g)");
		this.atributos.put("64", "Ã�cido AspÃ¡rtico (g)");
		this.atributos.put("65", "Ã�cido GluÃ¢mico (g)");
		this.atributos.put("66", "Glicina (g)");
		this.atributos.put("67", "Prolina (g)");
		this.atributos.put("68", "Serina (g)");
	}

}
