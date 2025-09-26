package org.shark.boot15.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")

@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    private String description;

    protected Category() {}
    public static Category createCategory(String categoryName, String description) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        return category;
    }
    @Override
    public String toString() {
      return "Category [id=" + id + ", categoryName=" + categoryName + ", description=" + description + "]";
    }


}
