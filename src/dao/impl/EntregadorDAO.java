package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.iface.IObjetoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Entregador;

public class EntregadorDAO implements IObjetoDAO<Entregador> {

public SessionFactory sf;
	
	public EntregadorDAO(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void inserir(Entregador obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(obj);
		et.commit();
	}

	@Override
	public void alterar(Entregador obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(obj);
		et.commit();
	}

	@Override
	public void remover(Entregador obj) {
		EntityManager em = sf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(obj);
		et.commit();
	}

	@Override
	public Entregador buscar(Entregador obj) {
		EntityManager em = sf.createEntityManager();
		Entregador e = em.find(Entregador.class, obj.getId());
		return e;
	}

	@Override
	public List<Entregador> listar() {
		List<Entregador> listaEntregador = new ArrayList<Entregador>();
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT catCNH,numCNH,id FROM func_entregador");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] o: lista) {
			Entregador e = new Entregador();
			e.setCatCNH((char) o[0]);
			e.setNumCNH(o[1].toString());
			e.setId(Integer.valueOf((String) o[2]));
			listaEntregador.add(e);
		}
		return listaEntregador;
	}

}
