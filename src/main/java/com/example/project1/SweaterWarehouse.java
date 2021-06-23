package com.example.project1;

import com.example.project1.SweaterFactory.Sweater;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sweater", path = "sweater")
public interface SweaterWarehouse extends PagingAndSortingRepository<Sweater, Integer> {
}
