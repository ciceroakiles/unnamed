package model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento {
	
	@EmbeddedId
	private AtendimentoPKey chave;
	
	public AtendimentoPKey getChave() {
		return chave;
	}
	public void setChave() {
		this.chave = new AtendimentoPKey();
	}
	
	@Override
	public String toString() {
		return "Atendimento [cliente=" + getChave().getCliente() +
			", atendente=" + getChave().getAtendente() +
			", dataHora=" + getChave().getDataHora().toString() + "]";
	}
}
