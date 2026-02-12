package com.dinesh.restapidemotelusko.service;

import com.dinesh.restapidemotelusko.exception.ItemNotFoundException;
import com.dinesh.restapidemotelusko.model.ElectronicItem;
import com.dinesh.restapidemotelusko.repository.ElectronicItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectronicItemService {

    @Autowired
    private ElectronicItemRepository repository;

    public List<ElectronicItem> getAllItems() {
        return repository.findAll();
    }

    public ElectronicItem getItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
    }

    public ElectronicItem createItem(ElectronicItem item) {
        return repository.save(item);
    }

    public ElectronicItem updateItem(Long id, ElectronicItem itemDetails) {
        ElectronicItem item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));

        item.setName(itemDetails.getName());
        item.setCategory(itemDetails.getCategory());
        item.setPrice(itemDetails.getPrice());
        item.setQuantity(itemDetails.getQuantity());
        
        return repository.save(item);
    }

    public void deleteItem(Long id) {
        ElectronicItem item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
        repository.delete(item);
    }
}
