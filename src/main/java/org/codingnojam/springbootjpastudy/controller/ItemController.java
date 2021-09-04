package org.codingnojam.springbootjpastudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingnojam.springbootjpastudy.domain.item.Book;
import org.codingnojam.springbootjpastudy.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(@Valid BookForm form, BindingResult result) {
        if(result.hasErrors()){
            log.info("에러발생");
//            return "redirect:/items/new";
            return "items/createItemForm";
        }

        Book book = new Book();
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setName(form.getName());
        book.setItsn(form.getIsbn());
        book.setAuthor(form.getAuthor());

        itemService.saveItem(book);
        return "redirect:/items";
    }
}


