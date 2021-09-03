package org.codingnojam.springbootjpastudy.service;

import org.assertj.core.api.Assertions;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.codingnojam.springbootjpastudy.domain.item.Movie;
import org.codingnojam.springbootjpastudy.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @PersistenceContext
    EntityManager em;


    @Test
    public void ItemAdd() {
        Movie mv = new Movie();
        mv.setName("코딩영화");
        mv.setPrice(15000);
        mv.setActor("아이유");
        mv.setDirector("코딩노잼");

        itemService.saveItem(mv);
        Item item = itemService.findOne(mv.getId());
        em.flush();

        System.out.println(item);
        System.out.println(mv);
        Assertions.assertThat(item).isEqualTo(mv);
        Assertions.assertThat(item.getId()).isEqualTo(mv.getId());

    }

}

