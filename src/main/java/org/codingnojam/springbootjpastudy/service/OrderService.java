package org.codingnojam.springbootjpastudy.service;

import lombok.RequiredArgsConstructor;
import org.codingnojam.springbootjpastudy.domain.Delivery;
import org.codingnojam.springbootjpastudy.domain.Member;
import org.codingnojam.springbootjpastudy.domain.Order;
import org.codingnojam.springbootjpastudy.domain.OrderItem;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.codingnojam.springbootjpastudy.repository.ItemRepository;
import org.codingnojam.springbootjpastudy.repository.MemberRepository;
import org.codingnojam.springbootjpastudy.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findById(itemId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


    public Order findById(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
