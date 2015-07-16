package modelo;
import java.sql.SQLException;
import java.util.List;

public interface Daoable<T> {
	public boolean create(T t) throws SQLException;
	public boolean delete(Object t);
	public boolean upDate(T t);
	public T read(Object t);
	public List<T> readAll();
}
