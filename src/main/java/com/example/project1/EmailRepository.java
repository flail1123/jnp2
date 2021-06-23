package com.example.project1;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "email", path = "email")
public interface EmailRepository extends PagingAndSortingRepository<UserEmail, Integer> {
}
