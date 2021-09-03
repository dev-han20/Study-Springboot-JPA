package org.codingnojam.springbootjpastudy.domain;

import lombok.Getter;
import lombok.Setter;
import org.codingnojam.springbootjpastudy.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    // 정적 팩토리 메서드 생성자 //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStockQuantity(count);
        return orderItem;
    }


    // 비즈니스 로직//
    public void cancel() {
        item.addStockQuantity(count);
    }

    public int getTotalPrice() {
        return this.getOrderPrice() * this.getCount();
    }
}
