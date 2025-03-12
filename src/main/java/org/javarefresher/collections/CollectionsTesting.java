package org.javarefresher.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class CollectionsTesting {

    /*
        Initial size - 16, does not maintain order, use when ordering isn't essential
        Default load factor is 0.75
        O(1) time avg case, O(n) worst case due to hash collisions
        Keys hashed to determine their index in an internal array
     */
    public void testHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "ABC");
        map.put(1, "CDE");  // replace old value
        map.put(null, "ABC"); // allows 1 null key
        System.out.println(map);
    }

    public void testMultiMap() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("Fruits", "Bannana");
        multimap.put("Fruits", "Apple");
        multimap.put("Fruits", "Pear");
        multimap.put("Vegetables", "Carrot");

        System.out.println(multimap);
    }

    public void testLinkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Apple");
        linkedList.add("Apple");
        linkedList.add("Banana");
        System.out.println(linkedList);
    }

    public void testArrayList() {
        // Dynamic Resizing - 50% of size, Mutable
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1234);
        arrayList.add(0, 1111);
        arrayList.set(0, 2222); // replace

        System.out.println(arrayList);
    }

    /*
        LINKED HASHMAP
        Hash Table + Doubly Linked List
        Initial size - 16, does not maintain order
        Has insertion order by default, can enable access ordering by setting accessOrder = true
        Default load factor is 0.75
        Allows one null key and multiple null values
        Useful for LRU, predictable iteration order
     */
    public void testLinkedHashMap() {
        // Maintains insertion order using doubly linked list, useful for LRU cache
        LinkedHashMap<Integer, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "abc");
        linkedHashMap.put(2, 22);
        System.out.println(linkedHashMap);
    }

    /*
       TREEMAP
       Red-Black Balanced Tree
       Maintains sorted order of Keys
       O(log n) guaranteed for put, get, and remove operations
     */
    public void testTreeMap() {
        TreeMap<Object, String> treeMap = new TreeMap<>();
        treeMap.put(1, "111");
        treeMap.put(2, "222");
        System.out.println(treeMap);
    }
}
