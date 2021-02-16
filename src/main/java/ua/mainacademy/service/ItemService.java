package ua.mainacademy.service;

import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;

import java.util.List;

public class ItemService {

    public List<Item> findAll() {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findAll();
    }
}
