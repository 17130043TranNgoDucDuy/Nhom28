package edu.nlu.pharmacy_shop.util;

import org.apache.commons.io.FileUtils;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {
    /**
     * Save the file to the specified directory.
     *
     * @param directoryPath Directory path to save file.
     * @param fileName      File name to save.
     * @param part          The Part object sent from enctype="multipart/form-data" form.
     * @throws IOException If an input or output exception occurred.
     */
    public static void saveFile(String directoryPath, String fileName, Part part) throws IOException {
        File fileSaveDir = new File(directoryPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        part.write(directoryPath + File.separator + fileName);
    }

    /**
     * Copy the file to the specified directory.
     *
     * @param fileServerPath      The file path on the server.
     * @param nameDirectoryServer The directory name on the server.
     * @throws IOException If an input or output exception occurred.
     */
    public static void copyFile(String fileServerPath, String nameDirectoryServer) throws IOException {
        String pathDirectoryApp = "E:\\ProjectJava\\motorbike-shop\\src\\main\\webapp\\images\\" + nameDirectoryServer;

        File fileSaveDir = new File(pathDirectoryApp);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        File originFile = new File(fileServerPath);
        File destFile = new File(pathDirectoryApp + File.separator + originFile.getName());
        FileUtils.copyFile(originFile, destFile);
    }

    /**
     * Delete all files in the specified directory.
     * @param directoryPath The directory path.
     */
    public static void cleanDir(String directoryPath) {
        Path dirPath = Paths.get(directoryPath);

        try {
            Files.list(dirPath).forEach(file -> {
                if (!Files.isDirectory(file)) {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        System.out.println("Could not delete file: " + file);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Could not list directory: " + dirPath);
        }
    }

    /**
     * Delete the specified directory.
     * @param directoryPath The directory path.
     */
    public static void removeDir(String directoryPath) {
        cleanDir(directoryPath);

        try {
            Files.delete(Paths.get(directoryPath));
        } catch (IOException e) {
            System.out.println("Could not remove directory: " + directoryPath);
        }
    }
}
