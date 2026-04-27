package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class EliminarProductoCarritoUseCase {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public EliminarProductoCarritoUseCase(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public OperationResult execute(int productId) {
        Product product = productRepository.findById(productId);

        if (product == null) {
            return OperationResult.fail("Producto no encontrado");
        }

        Cart cart = cartRepository.getCart();

        cart.deleteProduct(product);
        cartRepository.save(cart);

        return OperationResult.ok("Producto eliminado del carrito.");
    }
}
