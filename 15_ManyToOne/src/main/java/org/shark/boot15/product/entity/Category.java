package org.shark.boot15.product.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")

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
    
    // 양방향 매핑 추가
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    protected Category() {}
    public static Category createCategory(String categoryName, String description) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        return category;
    }
    
    public void addProduct(Product product) {
      products.add(product);
      product.setCategory(this);
    }
    
    public void removeProduct(Product product) {
      products.remove(product);
      product.setCategory(null);
    }
    
    @Override
    public String toString() {
      return "Category [id=" + id
          + ", categoryName=" + categoryName
          + ", description=" + description
          + ", productCount=" + products.size()
          + "]";
    }


}
