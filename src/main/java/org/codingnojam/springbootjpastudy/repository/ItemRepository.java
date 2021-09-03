package org.codingnojam.springbootjpastudy.repository;

import lombok.RequiredArgsConstructor;
import org.codingnojam.springbootjpastudy.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

    private final EntityManager em;

    // 저장
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    // 전체조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    // 단건조회
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }
}
