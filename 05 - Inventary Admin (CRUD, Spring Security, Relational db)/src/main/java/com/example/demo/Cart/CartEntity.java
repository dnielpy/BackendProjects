package com.example.demo.Cart;

import com.example.demo.User.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserEntity user;
    private String username;
    @ElementCollection
    private List<Long> products;

    public CartEntity(UserEntity user, List<Long> products) {
        this.user = user;
        this.products = products;
        this.username = user.getEmail();
    }

    public CartEntity(UserEntity user) {
        this.user = user;
        this.username = user.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}
