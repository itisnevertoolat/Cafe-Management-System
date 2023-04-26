package com.alwaha.cafe.models;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name="Category.getAllCategories",
        query = "select c from Category c where c.id in (select p.category from Product p where p.status='true')")
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
}
