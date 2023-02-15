import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress = new GameProgress(456, 288, 764, 2345.67);
        GameProgress gameProgress2 = new GameProgress(5435, 3452, 234, 2334544);
        GameProgress gameProgress3 = new GameProgress(435345, 2342, 2341, 535345);

        String path = "/Users/ruslansharipov/Games/savegames/save1.dat";
        String pathZipFile = "/Users/ruslansharipov/Games/savegames/zip_file.zip";
        saveGame(gameProgress, gameProgress2, gameProgress3, path);
        zipFiles(pathZipFile, path);
    }
    public static void saveGame(GameProgress gameProgress, GameProgress gameProgress2, GameProgress gameProgress3, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            oos.writeObject(gameProgress2);
            oos.writeObject(gameProgress3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZipFile, String path) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZipFile));
             FileInputStream fis = new FileInputStream(path)) {
            ZipEntry entry = new ZipEntry("packed_notes.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            File file = new File(path);
            if(file.delete()) {
                System.out.println("Файлы вне архива удалены");
            } else {
                System.out.println("Ошибка в коде!");
            }
    } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}