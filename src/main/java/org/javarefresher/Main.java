package org.javarefresher;

import org.javarefresher.collections.CollectionsTesting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void mainnn(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello World!");

        CollectionsTesting collectionsTesting = new CollectionsTesting();
        System.out.println("\nTesting HashMap");
        collectionsTesting.testHashMap();

        System.out.println("\nTesting MultiMap");
        collectionsTesting.testMultiMap();

        System.out.println("\nTesting LinkedList");
        collectionsTesting.testLinkedList();

        System.out.println("\nTesting ArrayList");
        collectionsTesting.testArrayList();

        System.out.println("\nTesting LinkedHashMap");
        collectionsTesting.testLinkedHashMap();

        System.out.println("\nTesting TreeMap");
        collectionsTesting.testTreeMap();
    }
}