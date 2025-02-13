package repositiry.custom.impl;

import modle.Items;
import repositiry.custom.ItemRepository;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    public static ItemRepositoryImpl instance;

    public  static ItemRepositoryImpl getInstance(){
        if (instance==null){
            instance=new ItemRepositoryImpl();
        }
        return instance;

    }
    @Override
    public boolean Add(Items entity) {


        return false;
    }

    @Override
    public Items search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(Items entity) {
        return false;
    }

    @Override
    public List<Items> getAll() {
        return List.of();
    }
}
