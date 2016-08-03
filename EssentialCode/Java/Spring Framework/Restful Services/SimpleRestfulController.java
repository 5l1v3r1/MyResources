import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rovicorp.daq.peopleunbound.domain.PeopleUnboundEntity;
import com.rovicorp.daq.peopleunbound.domain.PeopleUnboundRequest;
import com.rovicorp.daq.peopleunbound.service.PeopleUnboundService;

@RestController
public class SimpleRestfulController {
	
@Autowired
SimpleRestfulService simpleRestfulService;

	@RequestMapping(value="/simpleresource", method= RequestMethod.POST)
	public List<SimpleRestfulEntity> getSimpleData(SimpleRestfulRequest simpleRestfulRequest) {
		
		List<SimpleRestfulEntity> returnedItems = SimpleRestfulService.getSimpleData(simpleRestfulRequest);
		
		return returnedItems;
	}
	
	
}