package senac.farmacia2;

import java.util.List;

public interface BaseDAO<T> {

	public boolean inserir(T t);

	public boolean alterar(T t);

	public boolean excluir(T t);

	public List<T> listarTodos();

}
