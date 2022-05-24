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
	
	@ManyToOne(targetEntity = Atendente.class)
	@JoinColumn(name = "id")
	@NotNull
	private Atendente atendente;
	
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
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
}
