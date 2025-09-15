package com.example.condiments.service;

import com.example.condiments.entity.Selection;

import java.util.List;

public interface ISelectionService {
    Selection save(List<String> condiments);
}
