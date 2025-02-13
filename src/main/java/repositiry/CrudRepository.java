package repositiry;

import java.util.List;

public interface CrudRepository<T, ID> extends SuperRepository {

    boolean Add(T entity);
    T search (ID id);
    boolean delete (ID id);
    boolean update (T entity);
    List<T>getAll ();

}
