package org.codingnojam.springbootjpastudy.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.codingnojam.springbootjpastudy.domain.Category;
import org.codingnojam.springbootjpastudy.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    // 엔티티 내에 비즈니스 로직 넣기

    // 재고 추가
    public void addStockQuantity(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    // 재고 감소
    public void removeStockQuantity(int stockQuantity) {
        int currentStockQuantity = this.stockQuantity;
        if (currentStockQuantity - stockQuantity < 0) {
            throw new NotEnoughStockException("재고가 불 충분 함");
        }
        this.stockQuantity -= stockQuantity;
    }

}
