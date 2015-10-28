package ufrrj.tebdes.ccompra.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ufrrj.tebdes.ccompra.entidades.Estoque;
import ufrrj.tebdes.ccompra.entidades.ItemPedido;
import ufrrj.tebdes.ccompra.entidades.Pedido;
import ufrrj.tebdes.ccompra.entidades.Produto;
import ufrrj.tebdes.ccompra.entidades.Usuario;
import ufrrj.tebdes.ccompra.utils.Constants;
import ufrrj.tebdes.ccompra.utils.JPAUtil;
import ufrrj.tebdes.ccompra.utils.Pagina;
import ufrrj.tebdes.ccompra.utils.SessionOld;

@ManagedBean
@RequestScoped
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private List<Pagina> paginas;
	
	private ArrayList<ItemPedido> itensPedidos;
	
	private String pesquisa;
	
	private int pagina;
	
	private Boolean showErroAlert = false;
	private Boolean showSucessoAlert = false;
	private Boolean showErroLoginAlert = false;
	private Boolean emptyCarrinho = false;

	public String salvar() {
		EntityManager em = JPAUtil.getEntityManager();;
		try {
			produto.getEstoque().setProduto(produto);
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			em.close();
			return Constants.FAIL;
		}
		em.close();
		return Constants.CAD_PRODUTO;
	}
	
	public String addCarrinho(Produto produto) {
		if (SessionOld.getInstance().getCarrinho() == null){
			SessionOld.getInstance().setCarrinho(new Pedido());
		}
		ItemPedido item = new ItemPedido();
		item.setQuantidade(1);
		item.setProduto(produto);
		if (SessionOld.getInstance().getCarrinho().getItemsSet().contains(item)){
			for (ItemPedido i : SessionOld.getInstance().getCarrinho().getItems()){
				if (i.equals(item)){
					i.iterateQuantidade(1);
					break;
				}
			}
		}else{
			SessionOld.getInstance().getCarrinho().getItemsSet().add(item);
		}
		return Constants.ADD_CARRINHO;
	}
	
	public String finalizarCompra(){
		if (SessionOld.getInstance().getCarrinho().getItemsSet().size() == 0){
			this.setEmptyCarrinho(true);
			return Constants.EMPTY_CAR;
		}
		
		if (SessionOld.getInstance().getAttribute("isLogged") != null && (Boolean)SessionOld.getInstance().getAttribute("isLogged")){
			EntityManager em = JPAUtil.getEntityManager();
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				SessionOld.getInstance().getCarrinho().setData(cal.getTime());
				Usuario usu = em.merge(SessionOld.getInstance().getUsuarioLogado());
				
				Set<ItemPedido> listPedidos = new HashSet<ItemPedido>();
				Set<Estoque> listEstoque = new HashSet<Estoque>();
				for (ItemPedido item : SessionOld.getInstance().getCarrinho().getItems()){ 
					ItemPedido i = em.merge(item);
					listPedidos.add(i);
					Estoque estoque = i.getProduto().getEstoque();
					estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
					listEstoque.add(estoque);
				}
				SessionOld.getInstance().getCarrinho().setUsuario(usu);
				SessionOld.getInstance().getCarrinho().setItems(listPedidos);
				
				em.getTransaction().begin();
				em.persist(SessionOld.getInstance().getCarrinho());
				
				for (Estoque estoque : listEstoque){
					Estoque i = em.merge(estoque);
					em.persist(i);
				}
				
				em.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				em.getTransaction().rollback();
				em.close();
				this.showErroAlert = true;
				return Constants.COMPRA_NAO_FINALIZADA;
			}
			em.close();
			this.showSucessoAlert = true;
			SessionOld.getInstance().setCarrinho(null);
			return Constants.COMPRA_FINALIZADA;
		}else{
			this.showErroLoginAlert = true;
			return Constants.COMPRA_NAO_FINALIZADA;
		}
	}
	
	public String removeCarrinho(ItemPedido pedido) {			
		SessionOld.getInstance().getCarrinho().getItemsSet().remove(pedido);
		return Constants.REMOVE_CARRINHO;
	}
	
	public String atualizaCarrinho(ItemPedido pedido) {
		SessionOld.getInstance().getCarrinho().getItems().add(pedido);
		return Constants.REMOVE_CARRINHO;
	}
	
	public String getTotalCarrinho() {
		double valor = new BigDecimal(SessionOld.getInstance().getCarrinho().getTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return "Subtotal: " + valor;
	}
	
	public String getTotalCompra() {
		return "R$ " + SessionOld.getInstance().getCarrinho().getTotal();
	}
	
	public String pesquisar(String value){
		SessionOld.getInstance().setAttribute("pesquisa", value);
		return Constants.PESQUISAR;
	}
	
	public String finalizar(){
		if (SessionOld.getInstance().getCarrinho().getItemsSet().size() == 0){
			this.setEmptyCarrinho(true);
			return Constants.EMPTY_CAR;
		}
		return Constants.FINALIZAR;
	}
	
	public String openCadProduto() {
		return Constants.CAD_PRODUTO;
	}
	
	public void next(int numero) {
		pagina = numero - 1;
		System.out.println("pagina: " + pagina);
	}
	
	public void excluir(Produto produto){
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		produto = em.merge(produto);
		em.remove(produto);
		tx.commit();
		em.close();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			System.out.println("pagina:_ " + pagina);
			EntityManager em = JPAUtil.getEntityManager();
			String jpql = "SELECT p FROM Produto p";
			boolean cond = SessionOld.getInstance().getAttribute("pesquisa") != null && ((String)SessionOld.getInstance().getAttribute("pesquisa")).length() > 0; 
			if (cond){
				jpql += " WHERE p.nome like :nome or p.descricao like :desc";
			}
			try{
				Query q = em.createQuery(jpql, Produto.class);
				q.setFirstResult(pagina * 9).setMaxResults(pagina * 9 + 9);
				if (cond){
					q.setParameter("nome", "%" + SessionOld.getInstance().getAttribute("pesquisa") + "%");
					q.setParameter("desc", "%" + SessionOld.getInstance().getAttribute("pesquisa") + "%");
				}
				this.produtos = q.getResultList();
				em.close();
			}catch (Exception ex){
				ex.printStackTrace();
				em.close();
			}
		}
		SessionOld.getInstance().setAttribute("pesquisa", null);
		return produtos;
	}
	
	public List<ItemPedido> getItensPedidos() {
		return itensPedidos = new ArrayList<ItemPedido>(SessionOld.getInstance().getCarrinho().getItems());
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Pagina> getPaginas() {
		if(this.paginas == null) {
			EntityManager em = JPAUtil.getEntityManager();
			String jpql = "SELECT COUNT(p) FROM Produto p";
			boolean cond = SessionOld.getInstance().getAttribute("pesquisa") != null && ((String)SessionOld.getInstance().getAttribute("pesquisa")).length() > 0; 
			if (cond){
				jpql += " WHERE p.nome like :nome or p.descricao like :desc";
			}
			try{
				Query q = em.createQuery(jpql);
				if (cond){
					q.setParameter("nome", "%" + SessionOld.getInstance().getAttribute("pesquisa") + "%");
					q.setParameter("desc", "%" + SessionOld.getInstance().getAttribute("pesquisa") + "%");
				}
				Long count = (Long) q.getSingleResult();
				Pagina p; int j = 0;
				paginas = new ArrayList<Pagina>();
				for (int i = 0; i < count; i += 9){
					p = new Pagina();
					p.setNumero(++j);
					p.setPrimeiraPag(i);
					paginas.add(p);
				}
				em.close();
			}catch (Exception ex){
				ex.printStackTrace();
				em.close();
			}
		}
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public Boolean getShowErroAlert() {
		return showErroAlert;
	}

	public void setShowErroAlert(Boolean showErroAlert) {
		this.showErroAlert = showErroAlert;
	}
	
	public Boolean getShowSucessoAlert() {
		return showSucessoAlert;
	}

	public void setShowSucessoAlert(Boolean showSucessoAlert) {
		this.showSucessoAlert = showSucessoAlert;
	}
	
	public Boolean getShowErroLoginAlert() {
		return showErroLoginAlert;
	}

	public void setShowErroLoginAlert(Boolean showErroLoginAlert) {
		this.showErroLoginAlert = showErroLoginAlert;
	}

	public Boolean getEmptyCarrinho() {
		return emptyCarrinho;
	}

	public void setEmptyCarrinho(Boolean emptyCarrinho) {
		this.emptyCarrinho = emptyCarrinho;
	}
	
}
