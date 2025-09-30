package org.shark.boot17.product.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")

@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Integer categoryId;
  
  @Column(name = "category_name")
  private String categoryName;
  
  // 하위 카테고리(category_id)와 
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_category_id")
  private Category parent;
  
  @OneToMany(mappedBy = "parent")
  private List<Category> children;
  
  @OneToMany(mappedBy = "category")
  private List<Product> products;
  
  protected Category() {}
  
  public static Category createCategory(String categoryName, Category parent) {
    Category category = new Category();
    category.setCategoryName(categoryName);
    category.setParent(parent);
    return category;
  }

  // @ManyToOne은 포함, @OneToMany는 불표함
  @Override
  public String toString() {
    return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", parent=" + (parent != null ? parent : null)
        + "]";
  }
  
  
}
