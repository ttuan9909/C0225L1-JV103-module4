package com.example.condiments.repository;

import com.example.condiments.entity.Selection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SelectionRepository implements ISelectionRepository {
    private final List<Selection> store = new ArrayList<>();

    @Override
    public Selection save(List<String> condiments) {
        Selection selection = new Selection(condiments);
        store.add(selection);
        return selection;
    }
}
