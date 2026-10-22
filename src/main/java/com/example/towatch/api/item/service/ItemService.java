package com.example.towatch.api.item.service;

import com.example.towatch.api.item.dto.ItemRequest;
import com.example.towatch.api.item.dto.ItemResponse;
import com.example.towatch.api.item.entity.Item;
import com.example.towatch.api.item.exception.ItemAlreadyExistsException;
import com.example.towatch.api.item.exception.ItemNotFoundException;
import com.example.towatch.api.item.repository.ItemRepository;
import com.example.towatch.api.status.exception.StatusNotFoundException;
import com.example.towatch.api.status.repository.StatusRepository;
import com.example.towatch.api.type.exception.TypeNotFoundException;
import com.example.towatch.api.type.repository.TypeRepository;
import com.example.towatch.api.user.exception.UserNotFoundException;
import com.example.towatch.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final TypeRepository typeRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public ItemService(
            ItemRepository itemRepository,
            TypeRepository typeRepository,
            StatusRepository statusRepository,
            UserRepository userRepository
    ) {
        this.itemRepository = itemRepository;
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public List<ItemResponse> getAll(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return itemRepository.findAllByUserId(userId)
                .stream().map(item -> new ItemResponse(
                        item.getId(),
                        item.getTitle(),
                        item.getTypeId(),
                        item.getStatusId()
                )).toList();
    }

    @Transactional
    public ItemResponse createItem(ItemRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        if (itemRepository.existsByTitleAndUserId(request.title(), userId)) {
            throw new ItemAlreadyExistsException(request.title());
        }

        if (!typeRepository.existsById(request.typeId())) {
            throw new TypeNotFoundException(request.typeId());
        }

        if (!statusRepository.existsById(request.statusId())) {
            throw new StatusNotFoundException(request.statusId());
        }

        Item item = new Item();
        item.setTitle(request.title());
        item.setTypeId(request.typeId());
        item.setStatusId(request.statusId());
        item.setUserId(userId);

        Item newItem = itemRepository.save(item);

        return new ItemResponse(
                newItem.getId(),
                newItem.getTitle(),
                newItem.getTypeId(),
                newItem.getStatusId()
        );
    }

    @Transactional
    public ItemResponse updateItem(Long id, ItemRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        if (!itemRepository.existsByIdAndUserId(id, userId)) {
            throw new ItemNotFoundException(id);
        }

        if (!typeRepository.existsById(request.typeId())) {
            throw new TypeNotFoundException(request.typeId());
        }

        if (!statusRepository.existsById(request.statusId())) {
            throw new StatusNotFoundException(request.statusId());
        }

        Item item = itemRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException(id));

        item.setTitle(request.title());
        item.setTypeId(request.typeId());
        item.setStatusId(request.statusId());

        Item updatedItem = itemRepository.save(item);

        return new ItemResponse(
                updatedItem.getId(),
                updatedItem.getTitle(),
                updatedItem.getTypeId(),
                updatedItem.getStatusId()
        );
    }

    @Transactional
    public void deleteItem(Long id, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        if (!itemRepository.existsByIdAndUserId(id, userId)) {
            throw new ItemNotFoundException(id);
        }

        itemRepository.deleteById(id);
    }
}
