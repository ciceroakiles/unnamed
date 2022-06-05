package dao.impl;

import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import dao.iface.IAtendimentoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendimento;
import model.Cliente;
import view.Principal;

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
		return converterListas(query);
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
		return converterListas(query);
	}

	@Override
	public List<Atendimento> selectAll() {
		EntityManager em = sf.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT atd.* FROM atendimento atd ");
		buffer.append("JOIN cliente c on c.cpf=atd.cpf ");
		buffer.append("JOIN funcionario f on f.id=atd.id");
		Query query = (Query) em.createNativeQuery(buffer.toString());
		return converterListas(query);
	}

	private List<Atendimento> converterListas(Query query) {
		List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		List<Object[]> lista = query.getResultList();
		for (Object[] objArr: lista) {
			//for (Object o: objArr) { System.out.println(o.toString()); }
			Atendimento at = new Atendimento();
			at.setChave();
			String dateTime = objArr[0].toString();
			buffer.setLength(0);
			buffer.append(dateTime.substring(0, dateTime.length()-3).replace(" ", "T"));
			buffer.append("Z");
			//System.out.println(buffer);
		    DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		    ZonedDateTime parsed = ZonedDateTime.parse(buffer.toString(), formatter.withZone(ZoneId.of("UTC")));
		    //System.out.println(parsed.toLocalDateTime());
			at.getChave().setDataHora(parsed.toLocalDateTime());
			at.getChave().setAtendente(Principal.aDao.buscaPorId((int) objArr[1]));
			at.getChave().setCliente(Principal.cDao.buscaPorId(objArr[2].toString()));
			listaAtendimento.add(at);
		}
		return listaAtendimento;
	}
}
