package ru.netology.manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.Product;
import ru.netology.data.Book;
import ru.netology.data.Smartphone;
import ru.netology.repository.PurchaseRepository;

public class PurchaseManagerTest {
    PurchaseRepository repo = new PurchaseRepository();
    PurchaseManager manager = new PurchaseManager(repo);

    Product book1 = new Book(10, "Властелин колец. Братство кольца", 480, "Дэниел Киз");
    Product book2 = new Book(36, "Политика у шимпанзе", 690, "Франс де Валь");
    Product book3 = new Book(74, "Темная башня. Стрелок", 510, "Стивен Кинг");
    Product book4 = new Book(29, "Властелин колец. Братство кольца", 480, "Дж. Р. Р. Толкин");
    Product book5 = new Book(19, "Хроники заводной птицы", 690, "Харуки Мураками");
    Product smartphone1 = new Smartphone(33, "Apple iPhone 13", 58_400, "Apple");
    Product smartphone2 = new Smartphone(94, "Xiaomi POCO M5", 11_800, "Xiaomi");
    Product smartphone3 = new Smartphone(40, "Xiaomi Redmi Note 10 Pro 8", 20_299, "Xiaomi");
    Product smartphone4 = new Smartphone(18, "Apple iPhone 13 mini ", 51_080, "Apple");
    Product smartphone5 = new Smartphone(69, "vivo Y35 4", 10_604, "vivo");

    @BeforeEach
    void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(book5);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
        manager.add(smartphone4);
        manager.add(smartphone5);
    }

    @Test
    public void shouldSearchProductByOneWordRequestAlsoCheckNumbers() {
        Product[] expected = { smartphone1, smartphone4 };
        Product[] actual = manager.searchBy("13");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchProductByFullProductName() {
        Product[] expected = { smartphone2 };
        Product[] actual = manager.searchBy("Xiaomi POCO M5");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchOneProduct() {
        Product[] expected = { smartphone5 };
        Product[] actual = manager.searchBy("vivo Y35 4");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSeveralProducts() {
        Product[] expected = { book1, book4 };
        Product[] actual = manager.searchBy("Властелин колец");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonexistentProduct() {
        Product[] expected = { };
        Product[] actual = manager.searchBy("Возвращение короля");
        Assertions.assertArrayEquals(expected, actual);
    }

}
