package com.example.devopsWork.service;

import com.example.devopsWork.model.VinylProduct;
import com.example.devopsWork.repo.VinylProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VinylProductService {

    @Autowired
    private VinylProductRepository repository;

    public Iterable<VinylProduct> all() {
        return repository.findAll();
    }

    public Optional<VinylProduct> findById(Long id) {
        return repository.findById(id);
    }

    public VinylProduct save(VinylProduct product) {
        return repository.save(product);
    }

    public void delete(VinylProduct product) {
        repository.delete(product);
    }



    public List<VinylProduct> search(String query) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(p -> p.getProductName().toLowerCase().contains(query.toLowerCase()) ||
                        p.getArtistOrBrand().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<VinylProduct> findByCategory(String category) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }


    public boolean isInStock(Long id) {
        return repository.findById(id)
                .map(product -> product.getStockQuantity() > 0)
                .orElse(false);
    }


    public void purchase(Long id) {
        repository.findById(id).ifPresent(product -> {
            if (product.getStockQuantity() > 0) {
                product.setStockQuantity(product.getStockQuantity() - 1);
                repository.save(product);
            }
        });
    }
}