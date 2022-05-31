package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import dao._IObjetoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Cliente;

public class ClienteDAO implements _IObjetoDAO<Cliente> {

	public SessionFactory sf;
	
	public ClienteDAO(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void inserir(Cliente cli) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(cli);
		transaction.commit();
	}

	@Override
	public void alterar(Cliente cli) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(cli);
		transaction.commit();
	}

	@Override
	public void remover(Cliente cli) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(cli);
		transaction.commit();
	}

	@Override
	public Cliente buscar(Cliente cli) {
		EntityManager entityManager = sf.createEntityManager();
		cli = entityManager.find(Cliente.class, cli.getCpf());
		return cli;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		EntityManager entityManager = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT cpf_cliente, nome_cliente, email_cliente, celular_cliente, pronome_tratamento ");
		buffer.append("FROM cliente ORDER BY nome_cliente");
		Query query = (Query) entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Cliente cli = new Cliente();
			cli.setCpf(obj[0].toString());
			cli.setNome(obj[1].toString());
			cli.setEmail(obj[2].toString());
			cli.setTelefone(obj[3].toString());
			cli.setPronome(obj[4].toString());
			clientes.add(cli);
		}
		return clientes;
	}

}
