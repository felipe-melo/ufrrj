package ufrrj.tebdes.ccompra.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class GenericBean<T> {
	
	/*private T usuario = new T();
	
	private List<T> list;
	
	public List<T> getTs() {
		if(this.list == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select a from T a", T.class);
			this.list = q.getResultList();
			em.close();
		}
		return list;
	}

	public void salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public void excluir(T usuario){
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		usuario = em.merge(usuario);
		em.remove(usuario);
		tx.commit();
		em.close();
	}

	public T getT() {
		return usuario;
	}

	public void setT(T usuario) {
		this.usuario = usuario;
	}*/
}
