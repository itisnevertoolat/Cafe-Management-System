package com.alwaha.cafe.models;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name = "Product.updateStatus", query = "update Product p set p.status=:status where p.id=:id")
@NamedQuery(name="Product.getByCategory",
        query = "select new com.alwaha.cafe.wrapper.ProductWrapper(p.id, p.name) from Product p where p.category.id=:id and p.status='true'")
@NamedQuery(name = "Product.getProductById",
        query = "select new com.alwaha.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price) from Product p where p.id=:id and p.status='true'")
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@NamedQuery(name = "Product.getAllProducts",
        query = "select new com.alwaha.cafe.wrapper.ProductWrapper" +
                "(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p")
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID=123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;
    private String description;
    private Integer price;
    private String status;

}
