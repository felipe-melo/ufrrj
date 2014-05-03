package lista1;

public class Www {
	
	private String dominio;
	private String porta;
	private String recurso;
	
	public Www (String dominio, String porta, String recurso){
		this.dominio = dominio;
		this.porta = porta;
		this.recurso = recurso;
	}
	
	public Www (String endereco){
		
		String dom = "";
		String porta = "";
		String recurso = "";
		
		if (endereco.startsWith("http://")){
			String[] str = endereco.split("//");
			dom = str[0] + "//";
			
			if (!str[1].contains(":")){
				if (!str[1].contains("/")){
					dom += str[1];
				}else{
					String[] str2 = str[1].split("/");
					dom += str2[0];
					recurso = str2[1];
					for (int i = 2; i < str2.length; i++){
						recurso += "/" + str2[i];
					}
				}
			}else{
				String[] str2 = str[1].split(":");
				dom += str2[0];
				if (str2[1].contains("/")){
					String[] str3 = str2[1].split("/");
					porta = str3[0];
					recurso = str3[1];
					for (int i = 2; i < str3.length; i++){
						recurso += "/" + str3[i];
					}
				}else{
					porta = str2[1];
				}
			}
		}else{
			if (endereco.startsWith("www")){
				if (endereco.contains(":")){
					String[] str = endereco.split(":");
					dom = str[0];
					if (str[1].contains("/")){
						String[] str2 = str[1].split("/");
						porta = str2[0];
						recurso = str2[1];
						for (int i = 2; i < str2.length; i++){
							recurso += "/" + str2[i];
						}
					}else{
						porta = str[1];
					}
				}else{
					if (endereco.contains("/")){
						String[] str = endereco.split("/");
						dom = str[0];
						recurso = str[1];
						for (int i = 2; i < str.length; i++){
							recurso += "/" + str[i];
						}
					}else{
						dom = endereco;
					}
				}
			}
		}
		
		this.dominio = dom;
		this.porta = porta;
		this.recurso = recurso;
		
		//System.out.println(dom);
		//System.out.println(porta);
		//System.out.println(recurso);
	}
	
	public String RetornaEndereco(){
		String end = " ";
		
		if (this.porta == "80" || this.porta == ""){
			end += this.dominio + "/" + this.recurso;
		}else{
			end += this.dominio + ":" + this.porta + "/" + this.recurso;
		}
		
		return end;
	}
	
	private boolean VerificaEndereco(){
		if (!this.dominio.startsWith("http") || !this.dominio.startsWith("www"))
			return false;
		if (porta != ""){
			
			try {
				int p = Integer.parseInt(porta);
			}catch (Exception e){
				System.out.println("A porta de ser um número.");
				return false;
			}
		}
		
		if (recurso.contains("@") || recurso.contains("*") || recurso.contains("%") || 
			recurso.contains("#") || recurso.contains("$") || recurso.contains("&")){
			
			return false;			
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object arg0) {
		
		Www end = (Www) arg0;
		
		System.out.println(end.dominio);
		System.out.println(this.dominio);
		
		if (this.dominio.equals(end.dominio))
			return true;
		else
			return false;
	}
	
	public String getDominio() {
		return dominio;
	}

	public String getPorta() {
		return porta;
	}

	public String getRecurso() {
		return recurso;
	}

}
