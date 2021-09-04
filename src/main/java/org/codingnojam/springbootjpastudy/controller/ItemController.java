package org.codingnojam.springbootjpastudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingnojam.springbootjpastudy.domain.item.Book;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.codingnojam.springbootjpastudy.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("에러발생");
//            return "redirect:/items/new";
            return "items/createItemForm";
        }

        Book book = new Book();
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setName(form.getName());
        book.setIsbn(form.getIsbn());
        book.setAuthor(form.getAuthor());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long id, Model model){

        Book book = (Book) itemService.findOne(id);

        BookForm bookForm = new BookForm();
        bookForm.setAuthor(book.getAuthor());
        bookForm.setName(book.getName());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        bookForm.setId(book.getId());
        bookForm.setIsbn(book.getIsbn());

        model.addAttribute("bookForm", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("bookForm") BookForm bookForm, @PathVariable Long itemId) {

//        Book book = new Book();
//        book.setAuthor(bookForm.getAuthor());
//        book.setName(bookForm.getName());
//        book.setPrice(bookForm.getPrice());
//        book.setStockQuantity(bookForm.getStockQuantity());
//        book.setId(bookForm.getId());
//        book.setIsbn(bookForm.getIsbn());
//
//        itemService.saveItem(book);
        itemService.updateItem(itemId, bookForm);


        return "redirect:/items";

    }

}














