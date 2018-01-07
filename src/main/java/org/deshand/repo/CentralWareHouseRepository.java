package org.deshand.repo;

import java.util.Collection;
import java.util.List;

import org.deshand.model.CentralWareHouse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CentralWareHouseRepository extends MongoRepository<CentralWareHouse, Long>{

	CentralWareHouse findById(String id);

	Collection<CentralWareHouse> findBypartDescriptionLikeIgnoreCase(String filterText);

	Collection<CentralWareHouse> findByshelfNameLikeIgnoreCase(String filterText);

	Collection<CentralWareHouse> findBypartNumberStartsWithIgnoreCase(String filterText);
	
	List<CentralWareHouse> findBy(String data,Pageable pageable); 


	

}


