package ru.netology.repository;
import ru.netology.data.Product;

public class PurchaseRepository {
    private Product[] product = new Product[0];

    public void save(Product item) {
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
        Product[] movies = new Product[product.length - 1];
        int copyToIndex = 0;
        for (Product poster : product) {
            if (poster.getId() != id) {
                movies[copyToIndex] = poster;
                copyToIndex++;
            }
        }
        product = movies;
    }
}
