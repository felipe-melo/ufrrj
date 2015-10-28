 package ufrrj.tebdes.ccompra.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ufrrj.tebdes.ccompra.entidades.Usuario;
import ufrrj.tebdes.ccompra.utils.Constants;
import ufrrj.tebdes.ccompra.utils.JPAUtil;
import ufrrj.tebdes.ccompra.utils.SessionOld;

@ManagedBean
public class MainBean {
	
	private String email;
	private String senha;
	private Boolean isLogged;
	
	public String login() {
		if (getEmail() == null || getEmail().isEmpty()
				|| getSenha() == null || getSenha().isEmpty()){
			FacesMessage msg = new FacesMessage("Login ou senha inválidos!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return Constants.FAIL;
		}
		Usuario usuario;
		try {
			EntityManager em = JPAUtil.getEntityManager();
			String jpql = "select u from Usuario u where u.login.email = :email and u.login.senha = :senha";
			Query q = em.createQuery(jpql, Usuario.class);
			q.setParameter("email", getEmail());
			q.setParameter("senha", getSenha());
			usuario = (Usuario) q.getSingleResult();
			em.close();
		} catch (NoResultException e){
			System.out.println("usuário ou senha inválidos");
			usuario = null;
		}
		
		if (usuario != null){
			SessionOld.getInstance().setUsuarioLogado(usuario);
			SessionOld.getInstance().setAttribute("isLogged", true);
			return Constants.LOGGED;
		}else{
			FacesMessage msg = new FacesMessage("Login ou senha inválidos!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return Constants.FAIL;
		}
	}
	
	public String reload(){
		SessionOld.getInstance().setAttribute("showUsuarioCad", true);
		return Constants.RELOAD;
	}
	
	public Usuario getUsuario(){
		return (Usuario) SessionOld.getInstance().getUsuarioLogado();
	}
	
	public String logout() {
		SessionOld.getInstance().encerrarSession();
		return Constants.LOGOUT;
	}
	
	public String cadProduto() {
		Usuario usuario = getUsuario();
		if (usuario != null && usuario.getNivel().equals(1))
			return Constants.CAD_PRODUTO;
		else
			return Constants.FAIL;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getIsLogged() {
		if (SessionOld.getInstance().getUsuarioLogado() != null && SessionOld.getInstance().getAttribute("isLogged") != null)
			return (Boolean) SessionOld.getInstance().getAttribute("isLogged");
		return false;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

}
