package ru.geekbrains.lesson5;

import java.io.File;
import java.util.Arrays;

public class Task2 {

    //TODO: Доработайте класс Tree и метод print который мы разработали на семинаре.
    // Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        //System.out.println(file.getName());
        System.out.printf("[%s]\n",file.getName());

        //File[] files = file.listFiles();
        File[] arayOfDirs = Arrays.stream(file.listFiles())
                .filter(f->f.isDirectory())
                .toArray(File[]::new);
        File[] arayOfFiles = Arrays.stream(file.listFiles())
                .filter(f->!(f.isDirectory()))
                .toArray(File[]::new);




        if (arayOfDirs == null)
            return;
        int subDirTotal = arayOfDirs.length;

//        for (int i = 0; i < arayOfDirs.length; i++){
//            if (arayOfDirs[i].isDirectory())
//                subDirTotal++;
//        }

        int subDirCounter = 0;
        for (int i = 0; i < arayOfDirs.length; i++){
            if (arayOfDirs[i].isDirectory()){
                subDirCounter++;
                print(arayOfDirs[i], indent, subDirCounter == subDirTotal);
            }
        }

//        for (File dir:arayOfDirs) {
//            print(dir, indent, subDirCounter == subDirTotal);
//        }





    }

}
