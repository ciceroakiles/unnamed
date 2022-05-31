package dao;

import java.util.List;

public interface _IObjetoDAO<T> {

	public void inserir(T obj);
	public void alterar(T obj);
	public void remover(T obj);
	public T buscar(T obj);
	public List<T> listar();
}
