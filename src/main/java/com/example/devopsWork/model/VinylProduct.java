package com.example.devopsWork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;


@Entity
@Table(name = "vinyl_products")
public class VinylProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Length(min = 2, max = 100)
    private String productName;

    @NotBlank(message = "Artist or Brand is required")
    @Length(min = 2, max = 100)
    private String artistOrBrand;

    @NotBlank(message = "Category is required (e.g., LP, EP, Accessory)")
    private String category;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    @DecimalMin(value = "0.0", message = "Price must be a positive value")
    private Double price;

    @Length(max = 500)
    private String imageUrl;




    public VinylProduct() {
    }


    public VinylProduct(String productName, String artistOrBrand, String category, Integer stockQuantity, Double price, String imageUrl) {
        this.productName = productName;
        this.artistOrBrand = artistOrBrand;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.imageUrl = imageUrl;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getArtistOrBrand() {
        return artistOrBrand;
    }

    public void setArtistOrBrand(String artistOrBrand) {
        this.artistOrBrand = artistOrBrand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @Override
    public String toString() {
        return "VinylProduct{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", artistOrBrand='" + artistOrBrand + '\'' +
                ", price=" + price +
                ", stock=" + stockQuantity +
                '}';
    }
}
