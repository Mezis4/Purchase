package ru.netology.manager;
import ru.netology.data.Product;
import ru.netology.repository.PurchaseRepository;

public class PurchaseManager {
    private PurchaseRepository repo;

    public PurchaseManager(PurchaseRepository repo) {
        this.repo = repo;
    }

    public void add(Product product) {
        repo.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repo.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                int amount = 0;
                for (Product results : result) {
                    tmp[amount] = results;
                    amount++;
                }
                tmp[result.length] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}