package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "func_entregador")
public class Entregador extends Funcionario {

	@Column(name = "numCNH", length = 50)
	@NotNull
	private String numCNH;
	
	@Column(name = "catCNH")
	@NotNull
	private char catCNH;

	public String getNumCNH() {
		return numCNH;
	}
	public void setNumCNH(String numCNH) {
		this.numCNH = numCNH;
	}
	public char getCatCNH() {
		return catCNH;
	}
	public void setCatCNH(char catCNH) {
		this.catCNH = catCNH;
	}
}
