package dao.iface;

import java.util.List;
import model.Atendimento;

public interface IAtendimentoDAO {

	public void insere(Atendimento atend);
	public Atendimento selectOne(Atendimento atend);
	public List<Atendimento> selectOneCliente(Atendimento atend);
	public List<Atendimento> selectOneAtendente(Atendimento atend);
	public List<Atendimento> selectAll();
}
