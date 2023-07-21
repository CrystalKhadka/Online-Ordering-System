package com.system.online_ordering_system.service.impl;

import com.lowagie.text.pdf.BidiLine;
import com.system.online_ordering_system.dto.CartDto;
import com.system.online_ordering_system.entity.Cart;
import com.system.online_ordering_system.entity.Item;
import com.system.online_ordering_system.entity.User;
import com.system.online_ordering_system.repo.CartRepo;
import com.system.online_ordering_system.repo.ItemRepo;
import com.system.online_ordering_system.service.CartService;
import com.system.online_ordering_system.service.ItemService;
import com.system.online_ordering_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final UserService userService;
    private final ItemService  itemService;
    private final ItemRepo itemRepo;

    @Override
    public void addToCart(CartDto cartDto) {
        Cart cart = cartRepo.findById(cartDto.getId()).orElse(new Cart());
        cart.setCartQty(cartDto.getCartQty());
        cart.setTotalPrice(cartDto.getTotalPrice());

        Optional<Item> optionalItem = itemService.getItemById(cartDto.getItemId());

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            int availableQty = item.getItemQuantity();
            int cartQty = cartDto.getCartQty();
            int newQty = availableQty - cartQty;
            item.setItemQuantity(newQty);
            itemRepo.save(item);
            cart.setItem(item);
        }

        Optional<User> optionalUser = userService.getActiveUser();
        if (optionalUser.isPresent()) {
           User user = optionalUser.get();
            cart.setUser(user);
        }
        cart.setStatus("Pending");
        cartRepo.save(cart);





    }
}
