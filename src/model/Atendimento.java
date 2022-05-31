package model;

import java.time.LocalDateTime;
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
	@ManyToOne
	@JoinColumn(name = "cpf")
	@NotNull
	private Cliente cliente;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id")
	@NotNull
	private Atendente atendente;
	
	@Id
	@Column(name = "data_hora_atendimento")
	@NotNull
	private LocalDateTime dataHora;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}