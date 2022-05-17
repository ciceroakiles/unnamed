package model;

import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "func_atendente")
public class Atendente extends Funcionario {

	@Column(name = "hrEntrada")
	@NotNull
	private LocalTime hrEntrada;
	
	@Column(name = "hrSaida")
	@NotNull
	private LocalTime hrSaida;
	
	@Column(name = "email", length = 50)
	@NotNull
	private String email;
	
	public LocalTime getHrEntrada() {
		return hrEntrada;
	}
	public void setHrEntrada(LocalTime hrEntrada) {
		this.hrEntrada = hrEntrada;
	}
	public LocalTime getHrSaida() {
		return hrSaida;
	}
	public void setHrSaida(LocalTime hrSaida) {
		this.hrSaida = hrSaida;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
