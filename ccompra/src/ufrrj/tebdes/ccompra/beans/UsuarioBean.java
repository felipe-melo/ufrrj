package ufrrj.tebdes.ccompra.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ufrrj.tebdes.ccompra.entidades.Pedido;
import ufrrj.tebdes.ccompra.entidades.Usuario;
import ufrrj.tebdes.ccompra.utils.Constants;
import ufrrj.tebdes.ccompra.utils.JPAUtil;
import ufrrj.tebdes.ccompra.utils.SessionOld;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario;
	
	private List<Usuario> usuarios;
	
	private List<Pedido> pedidos;
	
	private Boolean showCad;
	
	public List<Usuario> getUsuarios() {
		if(this.usuarios == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select u from Usuario u", Usuario.class);
			this.usuarios = q.getResultList();
			em.close();
		}
		return usuarios;
	}
	
	public List<Pedido> getPedidos() {
		if(this.pedidos == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select p from Pedido p where p.usuario.id = :usuarioId", Pedido.class);
			q.setParameter("usuarioId", SessionOld.getInstance().getUsuarioLogado().getId());
			this.pedidos = q.getResultList();
			em.close();
		}
		return pedidos;
	}

	public String salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.close();
			return Constants.FAIL;
		}
		em.close();
		SessionOld.getInstance().setUsuarioLogado(usuario);
		SessionOld.getInstance().setAttribute("isLogged", true);
		return Constants.CAD_USUARIO;
	}
	
	public Boolean isSuperUser(){
		if (SessionOld.getInstance().getUsuarioLogado() != null)
			return SessionOld.getInstance().getUsuarioLogado().getNivel().equals(Constants.NIVEL_SUPER_USER);
		return false;
	}
	
	public String openCadUsuario(){
		SessionOld.getInstance().setAttribute("showUsuarioCad", false);
		return Constants.CAD_USUARIO;
	}
	
	public String getUsuarioNome(){
		if ((Boolean)SessionOld.getInstance().getAttribute("isLogged") &&
				SessionOld.getInstance().getUsuarioLogado() != null){
			return (String)SessionOld.getInstance().getUsuarioLogado().getNome();
		}
		return "";
	}
	
	public void excluir(Usuario usuario){
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		usuario = em.merge(usuario);
		em.remove(usuario);
		tx.commit();
		em.close();
	}

	public Usuario getUsuario() {
		if (SessionOld.getInstance().getUsuarioLogado() != null){
			usuario = SessionOld.getInstance().getUsuarioLogado();
			EntityManager em = JPAUtil.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			usuario = em.merge(usuario);
			em.close();
		}else if (usuario == null){
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public String openHitorico(){
		return Constants.OPEN_HISTORICO;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getShowCad() {
		if (SessionOld.getInstance().getAttribute("showUsuarioCad") != null)
			return (Boolean)SessionOld.getInstance().getAttribute("showUsuarioCad");
		return true;
	}

	public void setShowCad(Boolean showCad) {
		this.showCad = showCad;
	}
}
