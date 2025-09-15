package com.example.condiments.service;

import com.example.condiments.entity.Selection;
import com.example.condiments.repository.ISelectionRepository;
import com.example.condiments.repository.SelectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectionService implements ISelectionService {
    private final ISelectionRepository repository = new SelectionRepository();

    @Override
    public Selection save(List<String> condiments) {
        return repository.save(condiments);
    }
}
