package pl.com.bernas.ioz.security.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.dozer.DozerBeanMapper;

@Startup
@Singleton(name = "dozer")
public class Dozer {

	private DozerBeanMapper mapper = null;

	@PostConstruct
	void init() {		
		List<String> mappings = new ArrayList<String>();
		mappings.add("dozer-global-configuration.xml");
		mappings.add("dozer-global-mappings.xml");
		
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappings);
	}

	public DozerBeanMapper getMapper() {
		return mapper;
	}
}
