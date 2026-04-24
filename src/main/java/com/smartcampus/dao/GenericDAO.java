/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.dao;

import com.smartcampus.model.BaseModel;
import java.util.List;

public class GenericDAO<T extends BaseModel> {
    
    private final List<T> items;
    
    public GenericDAO(List<T> items) {
        this.items = items;
    }
    
    public T getById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
    
    public List<T> getAll(){
        return items;
    }
    
    public void add(T item) {
        items.add(item); //Did not use auto-increment since there are string IDs such as 'TEMP-001'
    }
    
    public void update(T updatedItem) {
        for(int i = 0; i < items.size(); i++) {
            T existing = items.get(i);
            if(existing.getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }
    
    public void delete(String id) {
        items.removeIf(i -> i.getId().equals(id));
    }
    
}
