package ru.geekbrains.lesson5.HW5;


import java.io.IOException;

import static ru.geekbrains.lesson5.HW5.FileBuckup.*;
import static ru.geekbrains.lesson5.HW5.PtintDirList.printDir;

public class Main {

    public static void main(String[] args) throws IOException {


        String distPath=".";
        String backupPath=".\\buckup";

        // Дерево директорий с файлами
        printDir(distPath);

        //Резервное копирование файлов
        backup(distPath,backupPath);
    }
}
