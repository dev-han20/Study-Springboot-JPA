package org.codingnojam.springbootjpastudy.service;

import org.assertj.core.api.Assertions;
import org.codingnojam.springbootjpastudy.domain.*;
import org.codingnojam.springbootjpastudy.domain.item.Book;
import org.codingnojam.springbootjpastudy.exception.NotEnoughStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("상품주문")
    public void orderTest() {
        Member member = new Member();
        member.setName("코딩노잼");
        member.setAddress(new Address("판교", "떙땡로", "22424"));
        em.persist(member);

        Book book = new Book();
        book.setName("개발도서");
        book.setPrice(20000);
        book.setStockQuantity(15);
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 3);
        Order findOrder = orderService.findById(orderId);

        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(book.getPrice() * 3);
//        Assertions.assertThat(book.getStockQuantity()).isEqualTo(9); 에러
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(12);

    }

    @Test
    @DisplayName("상품주문취소")
    public void orderCancelTest() {

        Member member = new Member();
        member.setName("코딩노잼");
        member.setAddress(new Address("판교", "떙땡로", "22424"));
        em.persist(member);

        Book book = new Book();
        book.setName("개발도서");
        book.setPrice(20000);
        book.setStockQuantity(15);
        em.persist(book);


        Long id = orderService.order(member.getId(), book.getId(), 5);
        Order order = orderService.findById(id);
        order.cancel();

        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(15);

    }

    @Test
    @DisplayName("재고수량 초과테스트")
    public void stock() {

        Member member = new Member();
        member.setName("코딩노잼");
        member.setAddress(new Address("판교", "떙땡로", "22424"));
        em.persist(member);

        Book book = new Book();
        book.setName("개발도서");
        book.setPrice(20000);
        book.setStockQuantity(15);
        em.persist(book);


        try {
            Long id = orderService.order(member.getId(), book.getId(), 20);
            Order order = orderService.findById(id);
        } catch (NotEnoughStockException e) {
            return;
        }

        Assertions.fail("주문 재고가 부족함이 발생해야함");

    }
}