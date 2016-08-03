import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Component;

@Component
public class SimpleRestfulDao{

	@PersistenceContext
	EntityManager entitymanager;
	
	public List<SimpleRestfulEntity> getSimpleData(SimpleRestfulRequest simplerestfulrequest){
		
		StoredProcedureQuery proc = entityManager.createNamedStoredProcedureQuery("GetSimpleData");
		proc.execute();
		List<SimpleRestfulEntity> items = proc.getResultSet();
		
		return items;
	}
}