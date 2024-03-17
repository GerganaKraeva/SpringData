package bg.softuni.modelmapperexercise.service;

import bg.softuni.modelmapperexercise.service.dtos.CartItemDTO;

public interface ShoppingCartService {
    String addItem(CartItemDTO item);

    String deleteItem(CartItemDTO item);
    String buyItem();

}
