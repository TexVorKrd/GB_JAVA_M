package ru.gb.javaspec.hw1.servises;

import ru.gb.javaspec.hw1.cesar_crypto.Crypto;

import java.util.Scanner;

public class Conroler {
    public MenuList getMenuList() {
        return menuList;
    }

    Crypto coder;
    MenuList menuList;

    String msg=null;
    boolean isCrypt;

    public Conroler(Crypto coder) {
        this.coder = coder;
        String test="тестирование алгоритма шифрования вврщащйшращиigghbkhb";
        if (test.equals(coder.decrypt(coder.crypt(test)))) {
            menuCreater();
        }else {
            System.out.println("Алгоритм шифрования не подтвержден");

        }

    }

    private void menuCreater(){
        menuList= new MenuList();
        Menu inputMsg=new Menu("Ввод сообщения");
        inputMsg.setAction(()-> {
            System.out.println("Ввод сообщения для шифрования. Если сообщение уже есть оно будет стерто");
            this.msg=new Scanner(System.in).nextLine();
        });
        menuList.addMenu(inputMsg);

        Menu cryptMsg=new Menu("шифрование");
        cryptMsg.setAction(()-> {
            System.out.println("Выполнение шифрования");

            if (msg==null){
                System.out.println("Сначала введите сообщение. Шифрование невозможно");
            }else if(isCrypt){
                System.out.println("Соощение уже зашифровано");
                isCrypt=true;
            }else
            {




                this.msg = coder.crypt(this.msg);
                System.out.println("Cообщение зашифровано");
                isCrypt=true;
            }


        });
        menuList.addMenu(cryptMsg);

        Menu encryptMsg=new Menu("дешифровние");
        encryptMsg.setAction(()-> {
            System.out.println("Выполнение расшифровки");
            if (msg==null){
                System.out.println("Сначала введите сообщение. Шифрование невозможно");
            }else if(!isCrypt){
                System.out.println("Соощение не зашифроано");
            }else
            {


                this.msg = coder.decrypt(this.msg);
                System.out.println("Cообщение расшифровано");
                isCrypt=false;
            }
        });
        menuList.addMenu(encryptMsg);


    }

    public void lanch(){
        System.out.println("Демо шифрования/дешифрования сообщения");
        Scanner scanner=new Scanner(System.in);
        boolean isExit=false;
        StringBuilder view;
        String selectMenu;
        while (!isExit){
            view=new StringBuilder();
            System.out.println(view.append("-----\n").append("MSG=").append(this.msg).append("\n").toString());
            menuList.menuShow();
            selectMenu=scanner.nextLine();


            try {


                int sMenu = Integer.parseInt(selectMenu);
                if (sMenu == 0) isExit = true;
                if (menuList.getMenuList().containsKey(sMenu)) {
                    menuList.getMenuList().get(sMenu).getAction().run();
                } else {
                    System.out.println("ыберите доступный пункт меню");
                }
            }
            catch (NumberFormatException exception){
                System.out.println("Выберит корректно пункт меню");
            }

        }
    }


}
