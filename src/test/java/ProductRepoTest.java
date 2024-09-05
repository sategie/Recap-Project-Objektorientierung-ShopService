import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Optional<Product> actual = repo.getProductById("1");

        //THEN
        Product expected = new Product("1", "Apfel");
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getProductById_NotFound() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        //WHEN
        Optional<Product> actual = repo.getProductById("2");
        //THEN
        assertFalse(actual.isPresent());
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");
        //WHEN
        Product actual = repo.addProduct(newProduct);
        //THEN
        Product expected = new Product("2", "Banane");
        assertEquals(expected, actual);
        assertTrue(repo.getProductById("2").isPresent());
        assertEquals(expected, repo.getProductById("2").get());
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        //WHEN
        repo.removeProduct("1");
        //THEN
        assertFalse(repo.getProductById("1").isPresent());
    }
}
