package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "stock")
    private long stock;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ProductEntity(String name, double cost, String longDescription, String shortDescription, long stock, byte[] image, CategoryEntity category) {
        this.name = name;
        this.cost = cost;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    public ProductEntity() {
    }

    public ProductDTO toDto(){
        return new ProductDTO(name, cost, longDescription, shortDescription, stock, image, category);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
