package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import dao.iface.IObjetoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Atendente;

public class AtendenteDAO implements IObjetoDAO<Atendente> {

	public SessionFactory sf;
	
	public AtendenteDAO(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void inserir(Atendente obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(obj);
		et.commit();
	}

	@Override
	public void alterar(Atendente obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(obj);
		et.commit();
	}

	@Override
	public void remover(Atendente obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(obj);
		et.commit();
	}

	@Override
	public Atendente buscar(Atendente obj) {
		EntityManager em = sf.createEntityManager();
		Atendente a = em.find(Atendente.class, obj.getId());
		return a;
	}

	public Atendente buscaPorId(int id) {
		EntityManager em = sf.createEntityManager();
		Atendente a = em.find(Atendente.class, id);
		return a;
	}

	@Override
	public List<Atendente> listar() {
		List<Atendente> listaAtendente = new ArrayList<Atendente>();
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT email,hrEntrada,hrSaida,id FROM func_atendente");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] o: lista) {
			Atendente a = new Atendente();
			a.setEmail(o[0].toString());
			a.setHoraEntrada(Integer.valueOf((String) o[1]));
			a.setHoraSaida(Integer.valueOf((String) o[2]));
			a.setId(Integer.valueOf((String) o[3]));
			listaAtendente.add(a);
		}
		return listaAtendente;
	}

}
