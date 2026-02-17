package com.example.devopsWork.controller;

import com.example.devopsWork.dto.VinylProductIn;
import com.example.devopsWork.model.VinylProduct;
import com.example.devopsWork.service.VinylProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class VinylProductController {

    @Autowired
    private VinylProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam String query) {
        return new ResponseEntity<>(productService.search(query), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        return new ResponseEntity<>(productService.findByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<?> purchaseProduct(@PathVariable Long id) {
        Optional<VinylProduct> product = productService.findById(id);

        if (product.isEmpty()) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }

        if (!productService.isInStock(id)) {
            return new ResponseEntity<>("Product out of stock", HttpStatus.BAD_REQUEST);
        }


        productService.purchase(id);


        return new ResponseEntity<>(productService.findById(id).get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody VinylProductIn productIn) {
        VinylProduct product = productIn.toVinylProduct();
        product = productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody VinylProductIn productIn) {
        Optional<VinylProduct> dbProduct = productService.findById(id);
        if (dbProduct.isPresent()) {
            productIn.updateVinylProduct(dbProduct.get());
            VinylProduct updated = productService.save(dbProduct.get());
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<VinylProduct> dbProduct = productService.findById(id);
        if (dbProduct.isPresent()) {
            productService.delete(dbProduct.get());
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }
}