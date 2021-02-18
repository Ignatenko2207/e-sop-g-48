package ua.mainacademy.service;

import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemService {

    private static final Logger LOG = Logger.getLogger(ItemService.class.getName());

    public List<Item> findAll() {
        try {
            ItemDAO itemDAO = new ItemDAO();
            return itemDAO.findAll();
        } catch (Exception e) {
            LOG.severe("Any item was not found");
        }
        return new ArrayList<>();
    }
}
