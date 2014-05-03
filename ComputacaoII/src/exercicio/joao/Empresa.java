package exercicio.joao;
import java.util.ArrayList;
import java.util.HashSet;


public class Empresa {
	
	ArrayList<Pessoa> Users = new ArrayList<Pessoa>();
	HashSet<Encontro> Encontros = new HashSet<Encontro>();
	
	public void Marca_Encontros (Pessoa p1, Pessoa p2){
		
		Encontro encontro = new Encontro();
		
		if (p1.tipo == "romantica"){
			encontro.setCod_p1(p1.cod_p);
			encontro.setCod_p2(p2.cod_p);
			Encontros.add(encontro);
		}else if (p1.equals(p2)){
			encontro.setCod_p1(p1.cod_p);
			encontro.setCod_p2(p2.cod_p);
			Encontros.add(encontro);
		}else
			System.out.println("Pessoas incompativeis.");
	}

}
