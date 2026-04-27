package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class AumentarDisponibiliadProductoUseCase {
    private ProductRepository productRepository;

    public AumentarDisponibiliadProductoUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OperationResult execute(int productId) {
        Product product = productRepository.findById(productId);

        if (product == null) { return OperationResult.fail("Producto no encontrado"); }

        product.increaseAvailableQuantity(1);
        return OperationResult.ok("Cantidad disponible aumentada correctamente");
    }
}
