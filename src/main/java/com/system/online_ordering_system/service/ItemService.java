package com.system.online_ordering_system.service;

import com.system.online_ordering_system.dto.ItemDto;

import java.io.IOException;

public interface ItemService {
    void addItem(ItemDto itemDto) throws IOException;
}
