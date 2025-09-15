package com.example.condiments.repository;

import com.example.condiments.entity.Selection;

import java.util.List;

public interface ISelectionRepository {
    Selection save(List<String> condiments);
}
