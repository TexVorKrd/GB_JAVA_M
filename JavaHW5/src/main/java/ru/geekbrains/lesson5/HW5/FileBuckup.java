package ru.geekbrains.lesson5.HW5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class FileBuckup {

    /**
     * Добавляется файлы в переданный массив
     *
     * @param file - начальная дирректория
     * @param fileList - массив с файлами
     */
    private static void fileList(File file, ArrayList<File> fileList) {
        if (file == null || !file.exists()) return;
        if (file.isFile()) {
            fileList.add(file);
            return;
        }
        File[] arayOfDirs = file.listFiles();
        for (int i = 0; i < arayOfDirs.length; i++) {
            fileList(arayOfDirs[i], fileList);
        }
    }
    /**
     * Добавляется дирректории в переданный массив
     *
     * @param file - начальная дирректория
     * @param fileList - массив с дирректориями
     */
    private static void dirList(File file, ArrayList<File> fileList) {

        if (file == null || !file.exists()) return;
        if (file.isDirectory())
            fileList.add(file);

        File[] arayOfDirs = Arrays.stream(file.listFiles())
                .filter(File::isDirectory)
                .toArray(File[]::new);

        for (int i = 0; i < arayOfDirs.length; i++) {
            dirList(arayOfDirs[i], fileList);
        }
    }


    /**
     * Возвращает список списко объектов типа File из переданной директории и поддиректорий,
     * которые являются файлами.
     *
     * @param file - начальны каталог
     * @return - Массив Файлов
     */
    private static ArrayList<File> creatFileList(File file){
        ArrayList<File> fileList = new ArrayList<>();
        fileList(file, fileList);
        return fileList;
    }

    /**
     * Возвращает список списко объектов типа File из переданной директории и поддиректорий,
     * которые являются директориями.
     *
     * @param file - начальны каталог
     * @return - Массив Файлов
     */
    private static ArrayList<File> creatDirList(File file){

        ArrayList<File> dirList = new ArrayList<>();
        if (file == null || !file.exists() || !file.isDirectory()) return dirList;
        dirList(file, dirList);
        return dirList;
    }

    /**
     * Проверка имени и возможности создания дирректории которая указана для резервного копирования
     * @param buckupName - Пусть к новой директории
     * @return - Имя директори. Может бфть изменено, если указанная дирректория уже существует.
     * @throws IOException - ошибка доступа к файлам
     */
    private static String createBackupDir(String buckupName) throws IOException {
        String testName = buckupName;
        File backupDir = new File(testName);

        int counter = 1;
        boolean dirIsExist = backupDir.exists();

        while (dirIsExist) {
            System.out.printf("Такой каталог уже есть %s", testName);
            testName = buckupName + "(" + counter + ")";
            backupDir = new File(testName);
            dirIsExist = backupDir.exists();
            counter++;
        }
        Files.createDirectory(Path.of(testName));
        return testName;
    }

    /**
     * Бэкап всей информации из указанной директории в новую.
     * Новая директория не должна существовать на момент бэкапа
     * @param distPath - источник для бэкапа
     * @param backupPath - назначение бэкапа
     */
    public static void backup (String distPath,String backupPath) throws IOException {

        //Проверяем существование пути источника
        File disFile = new File(distPath);
        if (!disFile.exists() || !disFile.isDirectory()) {
            System.out.println("Неверно задан путь для резервирования");
            return;
        }

        //Получаем структуру дирректорий для копирования
        ArrayList<File> dirList = creatDirList(new File(distPath));

        //Получаем список файлов для копирования
        ArrayList<File> fileList = creatFileList(new File(distPath));

        //Создаем дирректорию куда будем копировать, если каталог для копирования уже существует, создаем с дугим именем и возвращаем его.
        String backupName = createBackupDir(backupPath);

        //Созаем структуру каталогов
        dirList.remove(0);
        for (File file : dirList) {
            String str = backupName + file.getPath().substring(1);
            Files.createDirectory(Path.of(str));
        }

        //Копируем файлы
        for (File file : fileList) {
            String fileFromPath = file.getPath();
            String fileToPath = backupName + file.getPath().substring(1);
            Files.copy(new File(fileFromPath).toPath(), new File(fileToPath).toPath());
        }
    }

}
