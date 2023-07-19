package com.system.online_ordering_system.service.impl;

import com.system.online_ordering_system.dto.ItemDto;
import com.system.online_ordering_system.entity.Item;
import com.system.online_ordering_system.repo.ItemRepo;
import com.system.online_ordering_system.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;
    private final String  uploadDirectory = System.getProperty("user.dir") + "/item_img";
    @Override
    public void addItem(ItemDto itemDto) throws IOException {
        Item item = itemRepo.findById(itemDto.getItemId()).orElse(new Item());
        item.setItemName(itemDto.getItemName());
        item.setItemPrice(itemDto.getItemPrice());
        item.setItemDescription(itemDto.getItemDescription());
        item.setItemQuantity(itemDto.getItemQuantity());
        if (itemDto.getItemImage() != null) {
            // Generate a unique image file name using user email and item name
            String imageName = itemDto.getItemName() + "_" + System.currentTimeMillis();
            System.out.println(imageName);
            String originalFilename = itemDto.getItemImage().getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            StringBuilder fileNames = new StringBuilder();
            System.out.println(uploadDirectory);
            Path fileNameAndPath = Paths.get(uploadDirectory, imageName + fileExtension);
            fileNames.append(imageName + fileExtension);
            Files.write(fileNameAndPath, itemDto.getItemImage().getBytes());
            item.setItemImage(imageName + fileExtension);
        }
        itemRepo.save(item);
    }
}
