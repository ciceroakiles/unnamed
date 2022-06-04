package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AtendimentoPKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "cpf")
	@NotNull
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id")
	@NotNull
	private Atendente atendente;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(getCliente(), getAtendente(), getDataHora());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendimentoPKey other = (AtendimentoPKey) obj;

		return Objects.equals(getCliente(), other.getCliente())
			&& Objects.equals(getAtendente(), other.getAtendente())
			&& Objects.equals(getDataHora(), other.getDataHora());
	}

	@Override
	public String toString() {
		return "AtendPKey [cpfCli=" + cliente.getCpf() +
			", idAtendente=" + atendente.getId() +
			", datahora=" + getDataHora().toString() + "]";
	}

}