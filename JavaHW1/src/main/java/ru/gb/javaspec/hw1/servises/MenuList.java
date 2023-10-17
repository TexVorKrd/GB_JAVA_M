package ru.gb.javaspec.hw1.servises;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MenuList {
    private Map<Integer,Menu> menuList;

    public MenuList() {
        this.menuList = new HashMap<>();
        Menu exitSelect = new Menu("EXIT");
        exitSelect.setAction( ()-> System.out.println("Выполнение программы завершно"));
        menuList.put(0,exitSelect);
    }

    public boolean addMenu(Integer key,Menu menu){
        if (menu==null||menu.getAction()==null) return false;
        if (this.menuList.containsKey(key)) return false;
        menuList.put(key,menu);
        return  true;
    }

    public Map<Integer, Menu> getMenuList() {
        return menuList;
    }

    public boolean addMenu(Menu menu){
        if (menu==null||menu.getAction()==null) return false;
        Integer key= Collections.max(menuList.keySet())+1;
        System.out.println("----Key="+key);
        return  addMenu(key,menu);
    }

    public void menuShow(){
        System.out.println("----------------");
        menuList.keySet()
                .stream()
                .sorted()
                .forEach((a)-> System.out.println(a+" - "+menuList.get(a).getName().toUpperCase()));
        System.out.println("----------------");

    }


}
