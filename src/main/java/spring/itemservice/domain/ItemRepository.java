package spring.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    // 멀티스레드 환경에서 Map 사용하면 안 된다. 현재 store는 싱글톤으로 생성되고 있다.
    // 동시에 접근시 atomic 등 다른 걸 사용해야 한다.
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findByID(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    // 감싼 이유 : ArrayList<>에 값을 넣어도 store.values()의 값이 변경되지 않도록 안전하게 한 것이다. (??)

    public void update(long itemid, Item updateParam) {
        Item findItem = findByID(itemid);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
