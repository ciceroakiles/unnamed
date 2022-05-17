package model;

import java.time.LocalTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "atendimento")
public class Atendimento {

	@Id
	@Column(name = "idAtend")
	@NotNull
	private int idAtend;
	
	@Column(name = "dtAtend")
	@NotNull
	private Date dtAtend;
	
	@Column(name = "hrAtend")
	@NotNull
	private LocalTime hrAtend;
	
	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name = "cpf")
	@NotNull
	private Cliente cliente;
	
	public int getIdAtend() {
		return idAtend;
	}
	public void setIdAtend(int idAtend) {
		this.idAtend = idAtend;
	}
	public Date getDtAtend() {
		return dtAtend;
	}
	public void setDtAtend(Date dtAtend) {
		this.dtAtend = dtAtend;
	}
	public LocalTime getHrAtend() {
		return hrAtend;
	}
	public void setHrAtend(LocalTime hrAtend) {
		this.hrAtend = hrAtend;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
