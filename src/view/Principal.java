package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import dao.impl.AtendenteDAO;
import dao.impl.AtendimentoDAO;
import dao.impl.ClienteDAO;
import model.Atendente;
import model.Atendimento;
import model.Cliente;
import util.HibernateUtil;

public class Principal {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static AtendenteDAO aDao = new AtendenteDAO(sf);
	private static ClienteDAO cDao = new ClienteDAO(sf);
	private static AtendimentoDAO atDAO = new AtendimentoDAO(sf);
	
	private static Atendente a4;
	private static Cliente c4;
	
	public static void main(String[] args) {
		operacoesAtendente();
		operacoesCliente();
		operacoesAtendimento();
	}
	
	private static void operacoesAtendente() {
		Atendente a = new Atendente();
		// Dados
		a.setId(1);
		a.setNome("Joao Silva");
		a.setEmail("email1@example.org");
		a.setDataNascimento(LocalDate.of(1982, 04, 03));
		a.setTelefone("1199966589");
		a.setSalario(2000.0);
		a.setHoraEntrada(6);
		a.setHoraSaida(18);
		// (create)
		aDao.inserir(a);
		// (delete)
		aDao.remover(a);
		// (read)
		aDao.inserir(a);
		Atendente a2 = aDao.buscar(a);
		System.out.println(a2.getNome());
		// (update)
		Atendente a3 = a2;
		a3.setNome("John Silva");
		aDao.alterar(a3);
		a4 = aDao.buscar(a3);
		System.out.println(a4.getNome());
	}
	
	private static void operacoesCliente() {
		Cliente c = new Cliente();
		// Dados
		c.setCpf("11122233344");
		c.setNome("Maria Santos");
		c.setTelefone("1199876664");
		c.setEmail("email2@customer.com");
		c.setPronome("Sra.");
		// (create)
		cDao.inserir(c);
		// (delete)
		cDao.remover(c);
		// (read)
		cDao.inserir(c);
		Cliente c2 = cDao.buscar(c);
		System.out.println(c2.getNome());
		// (update)
		Cliente c3 = c2;
		c3.setNome("Mary dos Santos");
		cDao.alterar(c3);
		c4 = cDao.buscar(c3);
		System.out.println(c4.getNome());
	}
	
	private static void operacoesAtendimento() {
		/*
		Atendimento at1 = new Atendimento();
		// Dados
		at1.setAtendente(a4);
		at1.setCliente(c4);
		at1.setDataHora(LocalDateTime.now());
		at1.setChave(new AtendimentoPKey(c4.getNome(), a4.getNome(), at1.getDataHora().toString()));
		// (create)
		atDAO.insere(at1);
		// (delete)
		atDAO.remove(at1);
		// (read - select one)
		atDAO.insere(at1);
		Atendimento at2 = atDAO.selectOne(at1);
		at2.setDataHora(at1.getDataHora());
		System.out.println(at2.getChave().toString());
		 */
		
		Cliente c = new Cliente();
		c.setCpf("22233344455");
		c.setNome("Zelia Santos");
		c.setTelefone("1199866647");
		c.setEmail("email3@customer.com");
		c.setPronome("Sra.");
		cDao.inserir(c);
		
		Atendimento at1 = new Atendimento();
		// Loop com alguns atendimentos (dois clientes)
		for (int i = 0; i < 10; i++) {
			at1.setChave();
			at1.getChave().setAtendente(a4);
			at1.getChave().setDataHora(LocalDateTime.now());
			if (i % 2 == 0) {
				at1.getChave().setCliente(c4);
			} else {
				at1.getChave().setCliente(c);
			}
			atDAO.insere(at1);
		}
		List<Atendimento> lista1 = atDAO.selectOneCliente(at1);
		lista1.forEach(atendimento -> System.out.println(atendimento));
	}
}
