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

/**
 * Manager Class for Tables
 * @author Ju Khang, Zaki, Timothy, Malcolm
 * @version 1.0
 * @since 2021-11-11
 */
public class manageTable implements Serializable {

    private static final long serialVersionUID = 12345L;

    /**
     * Array list of all tables in the restaurant
     */
    private ArrayList<Table> tableList = new ArrayList<Table>();

    /**
     * Get the list of tables in the restaurant
     * @return list of tables
     */
    public ArrayList<Table> getTableList() {
        return tableList;
    }

    /**
     * Add a table in the restaurant
     */
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

    /**
     * Remove a table from the restaurant
     */
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

    /**
     * Print a list of all tables in the restaurant
     */
    public void printTable() {
        System.out.println("================ TABLES ====================");
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println("Table Number: " + tableList.get(i).getTableNo());
            System.out.println("Table Size: " + tableList.get(i).getTableSize());
            System.out.println("Table Availability: " + tableList.get(i).getIsAvailable());
            System.out.println("--------------------------");
        }
    }

    /**
     * Function to save the list of tables to a txt file
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void saveTables() throws ClassNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("tableSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Function to populate the list of tables by reading the data from the saved txt file
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public manageTable readTables() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("tableSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageTable tables = (manageTable) objectInputStream.readObject();
        objectInputStream.close();
        return tables;
    }
}
