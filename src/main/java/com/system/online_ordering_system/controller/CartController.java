package com.system.online_ordering_system.controller;

import com.system.online_ordering_system.dto.CartDto;
import com.system.online_ordering_system.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public String addToCart(@Valid CartDto cartDto) {
        cartService.addToCart(cartDto);
        return "redirect:/dashboard/menu";
    }
}
