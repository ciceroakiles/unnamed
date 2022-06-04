package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import dao.iface.IAtendimentoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendimento;

public class AtendimentoDAO implements IAtendimentoDAO {

public SessionFactory sf;
	
	public AtendimentoDAO(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insere(Atendimento atend) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(atend);
		et.commit();
	}

	public void remove(Atendimento atend) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(atend);
		et.commit();
	}

	@Override
	public Atendimento selectOne(Atendimento atend) {
		EntityManager em = sf.createEntityManager();
		Atendimento at = em.find(Atendimento.class, atend.getChave());
		return at;
	}

	@Override
	public List<Atendimento> selectOneCliente(Atendimento atend) {
		List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT atd.* FROM atendimento atd ");
		buffer.append("JOIN cliente c on c.cpf=atd.cpf ");
		buffer.append("JOIN funcionario f on f.id=atd.id ");
		buffer.append("WHERE c.cpf='");
		buffer.append(atend.getChave().getCliente().getCpf());
		buffer.append("'");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		
		// TODO: converter uma lista para outra  
		
		return listaAtendimento;
	}

	@Override
	public List<Atendimento> selectOneAtendente(Atendimento atend) {
		List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT atd.* FROM atendimento atd ");
		buffer.append("JOIN cliente c on c.cpf=atd.cpf ");
		buffer.append("JOIN funcionario f on f.id=atd.id ");
		buffer.append("WHERE f.id= ");
		buffer.append(atend.getChave().getAtendente().getId());
		buffer.append(" ");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		
		// TODO: converter uma lista para outra  
		
		return listaAtendimento;
	}

	@Override
	public List<Atendimento> selectAll() {
		List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT atd.* FROM atendimento atd ");
		buffer.append("JOIN cliente c on c.cpf=atd.cpf ");
		buffer.append("JOIN funcionario f on f.id=atd.id");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		
		// TODO: converter uma lista para outra  
		
		return listaAtendimento;
	}

}
