package comum.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	
	
	public Date date;
	public String pizza;
	public String data;
	public String time;
	public int qtd;
	

	public Pedido(String pedido) {
		String[] primeiroCorte = pedido.split("\t");
		this.data = primeiroCorte[0];
		this.time = primeiroCorte[1];
		this.pizza = primeiroCorte[2];
		this.qtd = Integer.parseInt(primeiroCorte[3]);
		
		this.parserData();
		this.parserTime();
		this.setDate();
		
	}
		
	public void parserData(){
		
		String[] primeiroCorte = data.split("/");
		int mes = Integer.parseInt(primeiroCorte[1]);
		if(mes<10){
			this.data = primeiroCorte[0]+"0"+mes+primeiroCorte[2];
		}else
			this.data = primeiroCorte[0]+primeiroCorte[1]+primeiroCorte[2];
	}
	
	public void parserTime(){
		
		String[] primeiroCorte = time.split(":");
		int hora = Integer.parseInt(primeiroCorte[0]);
		if(hora<10){
			this.time = "0"+hora+":"+primeiroCorte[1];
		}
	}
	
	public void setDate(){
		 try {
			this.date = new SimpleDateFormat("ddMMyyyy HH:mm").parse(data+" "+time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
