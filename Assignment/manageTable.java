import java.util.ArrayList;
import java.util.Scanner;
public class manageTable {
    private static ArrayList<Table> tableList = new ArrayList<Table>();

    public static void addTable()
    {
        Scanner scan = new Scanner(System.in);
        int tableNo = tableList.size()+1;
        System.out.println("Key in table size: ");
        int tableSize = scan.nextInt();
        scan.nextLine();
        Table table = new Table(tableNo, tableSize);
        tableList.add(table);
    }

    public static void removeTable()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Key in table size to delete: ");
        int tableNo = scan.nextInt();
        scan.nextLine();
        tableList.remove(tableNo-1);
    }
}
