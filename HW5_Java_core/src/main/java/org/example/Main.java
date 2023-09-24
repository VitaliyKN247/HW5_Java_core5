package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {
        String sourceDirectoryPath = "/Users/vitalijkuznecov/IdeaProjects/HW5_Java_core/src/main/java/org/example";
        String backupDirectoryPath = "/Users/vitalijkuznecov/IdeaProjects/backup";

        createBackup(sourceDirectoryPath, backupDirectoryPath);
    }


    /**
     *         // Создание объектов для исходной директории и новой папки
     * @param sourceDirectoryPath
     * @param backupDirectoryPath
     */
    public static void createBackup(String sourceDirectoryPath, String backupDirectoryPath) {

        File sourceDirectory = new File(sourceDirectoryPath);
        File backupDirectory = new File(backupDirectoryPath);

        // Проверка, существует ли исходная директория
        if (!sourceDirectory.exists()) {
            System.out.println("Исходная директория не существует.");
            return;
        }

        // Проверка, существует ли новая папка
        if (!backupDirectory.exists()) {
            // Создание новой папки
            if (backupDirectory.mkdirs()) {
                System.out.println("Новая папка успешно создана.");
            } else {
                System.out.println("Ошибка создания новой папки.");
                return;
            }
        }

        // Получение списка файлов в исходной директории
        File[] files = sourceDirectory.listFiles();

        // Копирование каждого файла в новую папку
        for (File file : files) {
            try {
                Path sourcePath = file.toPath();
                Path destinationPath = new File(backupDirectoryPath + "/" + file.getName()).toPath();
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Создана резервная копия файла: " + file.getName());
            } catch (IOException e) {
                System.out.println("Ошибка при создании резервной копии файла: " + file.getName());
                e.printStackTrace();
            }
        }

        System.out.println("Резервное копирование завершено.");
    }
}
