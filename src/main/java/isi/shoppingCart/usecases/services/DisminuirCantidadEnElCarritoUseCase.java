package isi.shoppingCart.usecases.services;

import java.util.List;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class DisminuirCantidadEnElCarritoUseCase {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public DisminuirCantidadEnElCarritoUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public OperationResult execute(int productId) {
        Product product = productRepository.findById(productId);
        if (product == null) { return OperationResult.fail("Producto no encontrado"); }

        Cart cart = cartRepository.getCart();
        List<CartItem> items = cart.getItems();

        if (items.isEmpty()) { return OperationResult.fail("Carrito vacio"); }

        cart.decreaseQuantityByProductId(product.getId());

        //En caso de que la cantidad caiga a 0
        if (cart.getQuantityByProductId(product.getId()) <= 0) {
            cart.deleteProduct(product);
        }
        
        cartRepository.save(cart);

        return OperationResult.ok("Cantidad disminuida correctamente");
    }
}
