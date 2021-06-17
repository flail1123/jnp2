package com.example.project1;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sweater", path = "sweater")
public interface SweaterWarehouse extends PagingAndSortingRepository<Sweater, Long> {

    /*
        Example call:

        http://localhost:8080/account/search/findByCustomer?customer=http://localhost:8080/customer/1
     */

    // TO INACZEJ :
//    @RestResource(path = "findByColor", rel = "findByColor")
//    List<Sweater> findByCustomer(@Param("sweater") Sweater sweater);

//    Sweater findByID(@Param("id") Long sweaterID);
}
