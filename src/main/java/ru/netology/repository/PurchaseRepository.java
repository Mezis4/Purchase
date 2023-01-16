package ru.netology.repository;
import ru.netology.data.Product;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class PurchaseRepository {
    private Product[] product = new Product[0];

    public void save(Product item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException("Товар с таким ID уже существует");
        }

        Product[] purchase = new Product[product.length + 1];
        for (int i = 0; i < product.length; i++) {
            purchase[i] = product[i];
            }
        purchase[purchase.length - 1] = item;
        product = purchase;
    }

    public Product[] getProduct() {
        return product;
    }

    public Product[] findAll() {
        Product[] all = new Product[product.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = product[i];
        }
        return all;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Товар с id " + id + " не найден");
        }
        Product[] tmp = new Product[product.length - 1];
        int copyToIndex = 0;
        for (Product items : product) {
            if (items.getId() != id) {
                tmp[copyToIndex] = items;
                copyToIndex++;
            }
        }
        product = tmp;
    }

    public Product findById(int id) {
        for (Product product : product) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}
