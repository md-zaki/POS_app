import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class manageTable {
    private static ArrayList<Table> tableList = new ArrayList<Table>();

    public static void addTable() {
        Scanner scan = new Scanner(System.in);
        int tableNo = tableList.size() + 1;
        System.out.println("Key in table size: ");
        int tableSize = scan.nextInt();
        scan.nextLine();
        Table table = new Table(tableNo, tableSize);
        tableList.add(table);
    }

    public static void removeTable() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Key in table size to delete: ");
        int tableNo = scan.nextInt();
        scan.nextLine();
        tableList.remove(tableNo - 1);
    }

    public void saveTables() throws ClassNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("tableSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public mainMenu readTables() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("tableSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
        return menu;
    }
}
