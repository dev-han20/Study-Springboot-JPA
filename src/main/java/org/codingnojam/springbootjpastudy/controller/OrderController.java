package org.codingnojam.springbootjpastudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingnojam.springbootjpastudy.domain.Member;
import org.codingnojam.springbootjpastudy.domain.Order;
import org.codingnojam.springbootjpastudy.domain.OrderSearch;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.codingnojam.springbootjpastudy.service.ItemService;
import org.codingnojam.springbootjpastudy.service.MemberService;
import org.codingnojam.springbootjpastudy.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Item> items = itemService.findItems();
        List<Member> members = memberService.fineMembers();

        model.addAttribute("items", items);
        model.addAttribute("members", members);

        return "order/orderForm";

    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {


        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String list(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable("orderId") Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }
}
