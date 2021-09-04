package org.codingnojam.springbootjpastudy.service;

import lombok.RequiredArgsConstructor;
import org.codingnojam.springbootjpastudy.controller.BookForm;
import org.codingnojam.springbootjpastudy.domain.item.Book;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.codingnojam.springbootjpastudy.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, BookForm bookForm) {
        Book book = (Book) itemRepository.findById(itemId);
        book.change(bookForm);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findById(id);
    }
}
