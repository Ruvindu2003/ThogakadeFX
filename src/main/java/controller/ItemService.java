package controller;

import modle.Items;

import java.util.List;

public interface ItemService {


    List<Items> getAll();

    Items searchItem(String item);

    boolean updateItem(Items customer);

    boolean deleteItem(String  code);

    void  Additems(Items items);


}
