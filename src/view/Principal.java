package view;

import java.time.LocalDate;
import org.hibernate.SessionFactory;
import dao.impl.AtendenteDAO;
import model.Atendente;
import util.HibernateUtil;

public class Principal {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		AtendenteDAO aDao = new AtendenteDAO(sf);
		
		Atendente a = new Atendente();
		a.setId(1);
		a.setNome("John Silva");
		a.setEmail("email@example.org");
		a.setDataNascimento(LocalDate.of(1982, 04, 03));
		a.setTelefone("1199966589");
		a.setSalario(2000.0);
		a.setHoraEntrada(6);
		a.setHoraSaida(18);
		
		aDao.remover(a);
		aDao.inserir(a);
		
		Atendente a2 = aDao.buscar(a);
		System.out.println(a2.getNome());
	}

}
