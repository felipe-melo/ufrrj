package lista3;

public class QuickSort{

	public static void ordenar(Comparavel[] obj){
		
		Comparavel aux;
		
		for (int i = 0; i < obj.length; i++){
			for (int j = i+1; j < obj.length; j++){
				if (obj[i].comparar(obj[j]) == 1){
					aux = obj[i];
					obj[i] = obj[j];
					obj[j] = aux;
				}
			}
		}
	}

}
