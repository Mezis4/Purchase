package ru.netology.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.Product;
import ru.netology.data.Book;
import ru.netology.data.Smartphone;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class PurchaseRepositoryTest {
    PurchaseRepository repo = new PurchaseRepository();
    Product book1 = new Book(10, "Таинственная история Билли Миллигана", 480, "Дэниел Киз");
    Product book2 = new Book(36, "Политика у шимпанзе", 690, "Франс де Валь");
    Product book3 = new Book(74, "Темная башня. Стрелок", 510, "Стивен Кинг");
    Product book4 = new Book(29, "Властелин колец. Братство кольца", 480, "Дж. Р. Р. Толкин");
    Product book5 = new Book(19, "Хроники заводной птицы", 690, "Харуки Мураками");
    Product book6 = new Book(65, "Мастер и Маргарита", 690, "М. А. Булгаков");
    Product smartphone1 = new Smartphone(33, "Apple iPhone 13", 58_400, "Apple");
    Product smartphone2 = new Smartphone(94, "Xiaomi POCO M5", 11_800, "Xiaomi");
    Product smartphone3 = new Smartphone(40, "Xiaomi Redmi Note 10 Pro 8", 20_299, "Xiaomi");
    Product smartphone4 = new Smartphone(18, "Apple iPhone 13 mini ", 51_080, "Apple");
    Product smartphone5 = new Smartphone(69, "vivo Y35 4", 10_604, "vivo");
    Product smartphone6 = new Smartphone(69, "vivo Y35 4", 10_604, "vivo");

    @BeforeEach
    void setup() {
        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(book4);
        repo.save(book5);
        repo.save(smartphone1);
        repo.save(smartphone2);
        repo.save(smartphone3);
        repo.save(smartphone4);
        repo.save(smartphone5);
    }

    @Test
    public void shouldRemoveById() {
        repo.removeById(74);

        Product[] expected = new Product[] {
                book1,
                book2,
                book4,
                book5,
                smartphone1,
                smartphone2,
                smartphone3,
                smartphone4,
                smartphone5,
        };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldUseNotFoundException () {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(1);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        repo.save(book6);

        Product[] expected = new Product[] {
                book1,
                book2,
                book3,
                book4,
                book5,
                smartphone1,
                smartphone2,
                smartphone3,
                smartphone4,
                smartphone5,
                book6
        };

        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldUseAlreadyExistsException() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(smartphone6);
        });
    }

    @Test
    public void shouldFindAllProducts() {
        Product[] expected = new Product[] {
                book1,
                book2,
                book3,
                book4,
                book5,
                smartphone1,
                smartphone2,
                smartphone3,
                smartphone4,
                smartphone5,
        };

        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveProductById() {
        repo.removeById(book3.getId());

        Product[] expected = new Product[] {
                book1,
                book2,
                book4,
                book5,
                smartphone1,
                smartphone2,
                smartphone3,
                smartphone4,
                smartphone5,
        };
        Product[] actual = repo.getProduct();

        Assertions.assertArrayEquals(expected, actual);
    }
}
