package com.system.online_ordering_system.controller;

import com.system.online_ordering_system.dto.ItemDto;
import com.system.online_ordering_system.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/add")
    public String addItem() {
        return "Item/addItem";
    }

    @PostMapping("/add")
    public String addItemPost(@Valid ItemDto itemDto) throws Exception {
        itemService.addItem(itemDto);
        return "redirect:/item/add";
    }

}
