
@Service
public class SimpleRestfulService{
	
	@Autowired
	SimpleRestfulDao simpleRestfulDao;
	
	public List<SimpleRestfulEntity> getSimpleData(SimpleRestfulRequest simpleRestfulRequest){
		assertValidSimpleData(simpleRestfulRequest);
		return simpleRestfulDao.getSimpleData(simpleRestfulRequest);
	}
	
	private void assertValidSimpleData(SimpleRestfulRequest simpleRestfulRequest) throws exception{
		
		if(!simpleRestfulRequest.getSimpleJsonParam.class.equals(java.util.String.class)){
			//throw exception
		}
		
		if(!simpleRestfulRequest.getSimplerJsonParam.class.equals(java.util.String.class)){
			//throw exception
		}
		
		if(!simpleRestfulRequest.getSimpleJsonDate.class.equals(java.util.Date.class)){
			//throw exception
		}
		
	}
	
	
}