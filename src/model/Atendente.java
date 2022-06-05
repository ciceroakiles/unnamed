package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "func_atendente")
public class Atendente extends Funcionario {
	
	@Column(name = "hora_entrada")
	@NotNull
	private int horaEntrada;

	@Column(name = "hora_saida")
	@NotNull
	private int horaSaida;

	@Column(name = "email", length = 40)
	@NotNull
	private String email;
	
	public int getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(int horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public int getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Atendente [horaEntrada=" + horaEntrada +
			", horaSaida=" + horaSaida +
			", email=" + email + "]";
	}
}
