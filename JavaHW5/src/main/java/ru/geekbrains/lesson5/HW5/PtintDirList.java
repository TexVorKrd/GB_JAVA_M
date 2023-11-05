package ru.geekbrains.lesson5.HW5;

import java.io.File;
import java.util.Arrays;

public class PtintDirList {

    /**
     * Выводит в консоль дерево каталогаов с файлами начиная с указанного
     *
     * @param file - Начальный каталог.
     * @param indent - отступ
     * @param isLast - Флаг, что передваваемый каталог является последним в списке.
     */
    private static void printDirsContent(File file, String indent, boolean isLast) {
        if (file == null || file.listFiles() == null) return;
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.printf("[%s]\n", file.getName());

        File[] arayOfDirs = Arrays.stream(file.listFiles())
                .filter(File::isDirectory)
                .toArray(File[]::new);

        File[] arayOfFiles = Arrays.stream(file.listFiles())
                .filter(File::isFile)
                .toArray(File[]::new);

        for (int i = 0; i < arayOfDirs.length; i++) {
            printDirsContent(arayOfDirs[i], indent, arayOfDirs.length - 1 == i);
        }

        for (File arayOfFile : arayOfFiles) {
            System.out.print(indent);
            System.out.printf(" %s\n", arayOfFile.getName());
        }
    }

    /**
     * Выводит в консоль дерево каталогаов с файлами начиная с указанного.
     *
     * @param path - Путь к каталогу с которого начинать вывод.
     */
    public static void printDir(String path) {
        if (path == null) return;
        if (path.equals("")) path = ".";
        File dir = new File(path);
        if (!(dir.exists() && dir.isDirectory())) return;

        printDirsContent(dir, "", true);

    }
}
