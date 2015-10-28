package ufrrj.tebdes.ccompra.entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufrrj.tebdes.ccompra.utils.SessionOld;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	public Pedido(){
		this.setItems(new HashSet<ItemPedido>());
	}
	
	@Id @GeneratedValue
	@Column(name="ped_id")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ped_data", nullable=true, length=10)
	private Date data;
	
	private transient Double total;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<ItemPedido> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getTotal() {
		Double preco = new Double(0);
		for (ItemPedido items: getItems()){
			preco += items.getPrecoTotal();
		}
		return new BigDecimal(preco).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemPedido> getItems() {
		return new ArrayList<ItemPedido>(items);
	}
	
	public Set<ItemPedido> getItemsSet() {
		return items;
	}

	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}
}
