package com.example.devopsWork.dto;

import com.example.devopsWork.model.VinylProduct;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

public class VinylProductIn implements Serializable {

    @NotBlank(message = "Title is required")
    @Length(min = 2, max = 100)
    private String productName;

    @NotBlank(message = "Artist is required")
    @Length(min = 2, max = 100)
    private String artistOrBrand;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(0)
    private Integer stockQuantity;

    @DecimalMin(value = "0.0")
    private Double price;

    @Length(max = 500)
    private String imageUrl;


    public VinylProduct toVinylProduct() {
        return new VinylProduct(
                this.productName,
                this.artistOrBrand,
                this.category,
                this.stockQuantity,
                this.price,
                this.imageUrl
        );
    }


    public void updateVinylProduct(VinylProduct product) {
        product.setProductName(this.productName);
        product.setArtistOrBrand(this.artistOrBrand);
        product.setCategory(this.category);
        product.setStockQuantity(this.stockQuantity);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
    }



    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getArtistOrBrand() { return artistOrBrand; }
    public void setArtistOrBrand(String artistOrBrand) { this.artistOrBrand = artistOrBrand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
