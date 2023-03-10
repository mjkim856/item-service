package spring.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.itemservice.domain.Item;
import spring.itemservice.domain.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();
    // spring 없이 test

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savadItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findByID(item.getId());
        assertThat(findItem).isEqualTo(savadItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2  = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();

        // when
        Item updateParam = new Item("updatedItem", 20000, 30);
        itemRepository.update(id, updateParam);

        // then
        Item findItem = itemRepository.findByID(id);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}
