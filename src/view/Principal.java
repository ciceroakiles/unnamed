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
	public static AtendenteDAO aDao = new AtendenteDAO(sf);
	public static ClienteDAO cDao = new ClienteDAO(sf);
	public static AtendimentoDAO atDAO = new AtendimentoDAO(sf);
	
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
		 * selectOne
		 */
		Atendimento at1 = new Atendimento();
		// Dados
		at1.setChave();
		at1.getChave().setAtendente(a4);
		at1.getChave().setCliente(c4);
		at1.getChave().setDataHora(LocalDateTime.now());
		// (create)
		atDAO.insere(at1);
		// (delete)
		atDAO.remove(at1);
		// (read - select one)
		atDAO.insere(at1);
		Atendimento at2 = atDAO.selectOne(at1);
		at2.getChave().setDataHora(at1.getChave().getDataHora());
		// Resultado
		//System.out.println(at2.toString());
		
		/*
		 * selectOneCliente
		 */
		Cliente c = new Cliente();
		c.setCpf("22233344455");
		c.setNome("Zelia Santos");
		c.setTelefone("1199866647");
		c.setEmail("email3@customer.com");
		c.setPronome("Sra.");
		cDao.inserir(c);
		Atendimento at3 = new Atendimento();
		// Loop com alguns atendimentos (dois clientes)
		for (int i = 0; i < 10; i++) {
			at3.setChave();
			at3.getChave().setAtendente(a4);
			at3.getChave().setDataHora(LocalDateTime.now());
			at3.getChave().setCliente( (i % 2 == 0) ? c4 : c );
			atDAO.insere(at3);
		}
		List<Atendimento> lista1 = atDAO.selectOneCliente(at3);
		// Resultado
		//lista1.forEach(atendimento -> System.out.println(atendimento.toString()));
		
		/*
		 * selectOneAtendente
		 */
		Atendente a = new Atendente();
		a.setId(2);
		a.setNome("Pedro Silva");
		a.setEmail("email4@example.org");
		a.setDataNascimento(LocalDate.of(1984, 01, 03));
		a.setTelefone("1199467581");
		a.setSalario(2100.0);
		a.setHoraEntrada(7);
		a.setHoraSaida(19);
		aDao.inserir(a);
		Atendimento at4 = new Atendimento();
		// Loop com alguns atendimentos (dois atendentes)
		for (int i = 0; i < 10; i++) {
			at4.setChave();
			//at2.getChave().setAtendente( (i % 2 == 0) ? a4 : a );
			at4.getChave().setAtendente(a);
			at4.getChave().setDataHora(LocalDateTime.now());
			//at2.getChave().setCliente(c4);
			at4.getChave().setCliente( (i % 2 == 0) ? c4 : c );
			atDAO.insere(at4);
		}
		List<Atendimento> lista2 = atDAO.selectOneAtendente(at4);
		// Resultado
		//lista2.forEach(atendimento -> System.out.println(atendimento.toString()));
		
		/*
		 * selectAll
		 */
		List<Atendimento> lista3 = atDAO.selectAll();
		// Resultado
		//lista3.forEach(atendimento -> System.out.println(atendimento.toString()));
	}
}
