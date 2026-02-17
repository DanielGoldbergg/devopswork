package com.example.devopsWork.repo;

import com.example.devopsWork.model.VinylProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinylProductRepository extends CrudRepository<VinylProduct, Long> {

}
