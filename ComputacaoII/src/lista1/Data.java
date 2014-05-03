package lista1;

public class Data {
	
	private int dia;
	private int mes;
	private int ano;
	
	@Override
	public String toString() {
		
		String data;
		data = dia + "/" + mes + "/" + ano;
		
		return data;
	}
	
	public int compara(Data data){
		
		int data1, data2;
		
		data1 = (this.ano * 10000) + (this.mes * 100) + (this.dia);
		data2 = (data.ano * 10000) + (data.mes * 100) + (data.dia);
		
		if (data1 > data2){
			return 1;
		}else if (data1 < data2){
			return -1;
		}else return 0;
		
		/*if (this.ano < data.ano){
			return -1;
		}else if (this.ano > data.ano){
			return 1;
		}else if (this.mes < data.mes){
			return -1;
		}else if (this.mes > data.mes){
			return 1;
		}else if (this.dia < data.dia){
			return -1;
		}else if (this.dia > data.dia){
			return 1;
		}else
			return 0;*/
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
