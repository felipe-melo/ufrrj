package lista1;

public class Principal_Data {
	
	public static void main(String[] args){
		
		Data data = new Data();
		Data data2 = new Data();
		
		data.setDia(30);
		data.setMes(04);
		data.setAno(1991);
		
		data2.setDia(31);
		data2.setMes(04);
		data2.setAno(1991);
		
		System.out.println (data.toString());
		
		System.out.println (data.compara(data2));
		
	}

}
