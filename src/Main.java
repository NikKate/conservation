import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(2, 5, 6, 7.0);
        GameProgress game2 = new GameProgress(5, 2, 1, 4.2);
        GameProgress game3 = new GameProgress(10, 8, 70, 9.3);
        saveGame("C//Games//savegames/game1.dat", game1);
        saveGame("C//Games//savegames/game2.dat", game2);
        saveGame("C//Games//savegames/game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C//Games//savegames/game1.dat");
        arrayList.add("C//Games//savegames/game2.dat");
        arrayList.add("C//Games//savegames/game3.dat");
        zipFiles("C//Games//savegames/zip.zip", arrayList);
        File game1Doc = new File("C//Games//savegames/game1Doc.dat");
        File game2Doc = new File("C//Games//savegames/game2Doc.dat");
        File game3Doc = new File("C//Games//savegames/game3Doc.dat");
        game1Doc.delete();
        game2Doc.delete();
        game3Doc.delete();


    }

    public static void saveGame(String way, GameProgress game) {
        try {
            FileOutputStream fos = new FileOutputStream(way);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String name : arrayList) {
                try (FileInputStream fis = new FileInputStream(name)) {
                    ZipEntry entry1 = new ZipEntry(name);
                    zout.putNextEntry(entry1);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}