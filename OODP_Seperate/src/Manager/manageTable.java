package Manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import Entity.Table;


public class manageTable implements Serializable {

    private static final long serialVersionUID = 12345L;

    private ArrayList<Table> tableList = new ArrayList<Table>();

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    
    public void addTable() {

        Scanner scan = new Scanner(System.in);
        int tableNo;// = tableList.size() + 1;
        int tableSize;

        while (true) {
            Boolean exist = false;
            System.out.println("Key in table no: ");
            tableNo = scan.nextInt();
            for (Table t : tableList) {
                if (t.getTableNo() == tableNo) {
                    exist = true;
                    System.out.println("Table Number already exist!");
                    break;
                }
            }
            if (!exist) {
                break;
            }
        }

        while (true) {
            System.out.println("Key in table size: ");
            tableSize = scan.nextInt();
            scan.nextLine();
            if (tableSize <= 0 || tableSize % 2 == 1 || tableSize > 10) {
                System.out.println("Invalid table size!");
            } else {
                break;
            }

        }

        System.out.println("Table " + tableNo + " of size " + tableSize + " created");
        Table table = new Table(tableNo, tableSize);
        tableList.add(table);
        try {
            saveTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTable() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Key in table number to delete: ");
        int tableNo = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < tableList.size(); i++) {
            // System.out.println("table number: " + tableList.get(i).getTableNo());
            if (tableList.get(i).getTableNo() == tableNo) {
                tableList.remove(i);
            }
        }
        System.out.println("Table " + tableNo + " deleted");
        try {
            saveTables();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printTable() {
        System.out.println("================ TABLES ====================");
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println("Table Number: " + tableList.get(i).getTableNo());
            System.out.println("Table Size: " + tableList.get(i).getTableSize());
            System.out.println("Table Availability: " + tableList.get(i).getIsAvailable());
            System.out.println("--------------------------");
        }
    }

    public void tableReserved(Table table) {
        table.setIsAvailable(false);
    }

    public void freeTable(Table table) {
        table.setIsAvailable(true);
    }

    public void saveTables() throws ClassNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("tableSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public manageTable readTables() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("tableSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageTable tables = (manageTable) objectInputStream.readObject();
        objectInputStream.close();
        return tables;
    }
}
