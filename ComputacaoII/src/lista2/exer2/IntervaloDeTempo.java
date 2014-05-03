package lista2.exer2;

public class IntervaloDeTempo {
	
	private int hora;
	private int minuto;
	private int segundo;
	
	public IntervaloDeTempo(int tempo){		
		hora = tempo/3600;
		minuto = (tempo%3600)/60;
		segundo = ((tempo%3600)%60);		
	}
	
	public void SomaSegundos(int seg){
		int h = seg/3600;
		int min = (seg%3600)/60;
		int s = ((seg%3600)%60);
		
		if (s + this.segundo >= 60){
			this.minuto++;
			this.segundo = (s + this.segundo) - 60;
		}else{
			this.segundo += s;
		}
		
		if (min + this.minuto >= 60){
			this.hora++;
			this.minuto = (min + this.minuto) - 60;
		}else{
			this.minuto += min;
		}
		
		this.hora += h;
	}
	
	public void SomaIntervalo(IntervaloDeTempo inter){
		
		if (inter.segundo + this.segundo >= 60){
			this.minuto++;
			this.segundo = (inter.segundo + this.segundo) - 60;
		}else{
			this.segundo += inter.segundo;
		}
		
		if (inter.minuto + this.minuto >= 60){
			this.hora++;
			this.minuto = (inter.minuto + this.minuto) - 60;
		}else{
			this.minuto += inter.minuto;
		}
		
		this.hora += inter.hora;
	}

	@Override
	public String toString() {
		return hora + " hora(s), " + minuto + " minuto(s), " + segundo + " segundo(s).";
	}

}
