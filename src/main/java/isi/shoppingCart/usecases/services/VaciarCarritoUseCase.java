package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;

public class VaciarCarritoUseCase {
    private CartRepository cartRepository;

    public VaciarCarritoUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public OperationResult execute() {
        Cart cart = cartRepository.getCart();
        if (cart == null || cart.getItems().isEmpty()) {
            return OperationResult.fail("Carrito vacio");
        }

        cart.clearCart();
        cartRepository.save(cart);
        
        return OperationResult.ok("Carrito vaciado correctamente");
    }
}
