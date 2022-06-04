package dao.iface;

import java.util.List;

public interface IObjetoDAO<T> {

	public void inserir(T obj);
	public void alterar(T obj);
	public void remover(T obj);
	public T buscar(T obj);
	public List<T> listar();
}
