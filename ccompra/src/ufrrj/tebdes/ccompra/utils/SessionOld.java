package ufrrj.tebdes.ccompra.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ufrrj.tebdes.ccompra.entidades.Pedido;
import ufrrj.tebdes.ccompra.entidades.Usuario;

public class SessionOld {
private static SessionOld instance;
	
	public static SessionOld getInstance(){
		if (instance == null)
			instance = new SessionOld();
		return instance;
	}
	
	private SessionOld(){};
	
	private ExternalContext currentExternalContext(){
		if (FacesContext.getCurrentInstance() == null){
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisilção http");
		}else{
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	public Usuario getUsuarioLogado(){
		return (Usuario) getAttribute("usuarioLogado");
	}
	
	public void setUsuarioLogado(Usuario usuario){
		setAttribute("usuarioLogado", usuario);
	}
	
	public Pedido getCarrinho(){
		if (this.getAttribute("carrinho") == null)
			this.setCarrinho(new Pedido());
		return (Pedido) getAttribute("carrinho");
	}
	
	public void setCarrinho(Pedido Pedido){
		setAttribute("carrinho", Pedido);
	}
	
	public Object getAttribute(String nome){
		return currentExternalContext().getSessionMap().get(nome);
	}
	
	public void setAttribute(String nome, Object valor){
		currentExternalContext().getSessionMap().put(nome, valor);
	}
	
	public void encerrarSession(){
		currentExternalContext().invalidateSession();
	}
}
