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
    private ArrayList<Table> tableList = new ArrayList<Table>();

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("(1) Create Table");
            System.out.println("(2) Remove Table");
            System.out.println("(3) Display all Tables");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    addTable();
                    break;
                case 2:
                    removeTable();
                    break;
                case 3:
                    printTable();
                case 6:
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }

        } while (choice != 6);
    }

    public void addTable() {

        Scanner scan = new Scanner(System.in);
        int tableNo = tableList.size() + 1;
        System.out.println("Key in table size: ");
        int tableSize = scan.nextInt();
        scan.nextLine();
        System.out.println("Table " + tableNo + " of size " + tableSize + " created");
        Table table = new Table(tableNo, tableSize);
        //System.out.println("Table " + table.getTableNo() + " of size " + table.getTableSize() + " created");
        tableList.add(table);
        try {
            saveTables();
        } catch (Exception e) {
            // TODO Auto-generated catch block
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

    public void tableReserved(Table table)
    {
        table.setIsAvailable(false);
    }

    public void freeTable(Table table)
    {
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
