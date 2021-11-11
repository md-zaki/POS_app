package Manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import Entity.staff;

public class manageStaff implements Serializable{
    private ArrayList<staff> staffList = new ArrayList<staff>();
    //private static int numStaff=0;

    public manageStaff() throws IOException, ClassNotFoundException
    {
		/*
        try
        {
        FileInputStream fileInputStream = new FileInputStream("testStaffSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        staffList = (ArrayList<staff>) objectInputStream.readObject();
        objectInputStream.close();
        }
        catch (Exception ex){
        	ex.getStackTrace();
        }
		*/
    }

    public ArrayList<staff> getStaffList()
    {
        return this.staffList;
    }

    public void setStaffList(ArrayList<staff> staffList) {
        this.staffList = staffList;
    }

    public void saveStaffList() throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("testStaffSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public manageStaff readStaff() throws ClassNotFoundException, IOException
    {
        FileInputStream fileInputStream = new FileInputStream("testStaffSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageStaff staffList = (manageStaff) objectInputStream.readObject();
        objectInputStream.close();
        return staffList;
    }

    public int getStaffListIndexById(int staffId)
    {
        for (int i = 0 ; i <staffList.size(); i++)
        {
            if (staffList.get(i).getEmployeeId() == staffId)
            {
                return i;
            }
        }
        return -1;
    }

    public void addStaff() throws IOException
    {
        Scanner scan = new Scanner(System.in);
        int conflictCheck;
        System.out.printf("Enter Staff ID: ");
        int staffId = scan.nextInt();
        scan.nextLine();

        conflictCheck = getStaffListIndexById(staffId);
        if (conflictCheck != -1)
        {
            System.out.println("Staff with entered ID already exist.");
            return;
        }

        System.out.printf("Enter Staff Name: ");
        String staffName = scan.nextLine();

        System.out.printf("Enter Staff Gender (M/F): ");
        char staffGender = scan.next().charAt(0);
        scan.nextLine();

        System.out.printf("Enter Staff Job Title: ");
        String staffPos = scan.nextLine();

        staff newStaff = new staff(staffId, staffName, staffGender, staffPos);

        staffList.add(newStaff);
        //manageStaff.numStaff++;

        System.out.println("New Staff Added.");
        saveStaffList();

    }

    public void printStaff(staff staffMem) {
        System.out.println();
        System.out.println("Staff ID: " + staffMem.getEmployeeId());
        System.out.println("Staff Name: " + staffMem.getName());
        System.out.println("Staff Gender: " + staffMem.getGender());
        System.out.println("Staff Position: " + staffMem.getJobTitle());
    }

    public void viewStaffList()
    {
        System.out.println("===============LIST OF STAFF===============");

        if (staffList.size() == 0)
        {
            System.out.println("Staff List is empty");
        }
        else
        {
            for(int i = 0 ; i < staffList.size() ; i++)
            {
                printStaff(staffList.get(i));
                //System.out.println();
            }
        }

        System.out.println("\n===============END OF LIST=================");
    }

    public void viewStaffById(int staffId)
    {
        int idExistCheck;
        System.out.println("===============STAFF DATA===============");

        if (staffList.size() == 0)
        {
            System.out.println("Staff does not exist.");
            System.out.println("\n===============END OF DATA===============");
        }
        else
        {
            idExistCheck = getStaffListIndexById(staffId);
            if (idExistCheck == -1)
            {
                System.out.println("Staff ID does not exist.");
                System.out.println("\n===============END OF DATA===============");
            }
            else
            {
                printStaff(staffList.get(idExistCheck));
                System.out.println("\n===============END OF DATA===============");
            }
        }
    }

    public void updateStaffInfo(int staffId) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        int staffIndex = getStaffListIndexById(staffId);
        int updatedID = staffList.get(staffIndex).getEmployeeId();


        if (staffIndex == -1)
        {
            System.out.println("Staff ID does not exist.");
            return;
        }
        do
        {
            staff updateStaff = new staff(staffList.get(staffIndex).getEmployeeId(), staffList.get(staffIndex).getName(), staffList.get(staffIndex).getGender(), staffList.get(staffIndex).getJobTitle());
            System.out.println("Editing Staff " + staffList.get(staffIndex).getName() + " Information");
            System.out.println("Please select your operations");
            System.out.println("(1) Edit Staff ID");
            System.out.println("(2) Edit Staff Name");
            System.out.println("(3) Edit Staff Gender");
            System.out.println("(4) Edit Staff Position");
            System.out.println("(5) View current staff information");
            System.out.println("(6) Exit");
            System.out.printf("Enter a choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch(choice)
            {
                case 1:
                    System.out.printf("Enter updated ID for staff: ");
                    updatedID = scan.nextInt();
                    scan.nextLine();
                    for(int i = 0 ; i < staffList.size() ; i++)
                    {
                        if (staffList.get(i).getEmployeeId() == updatedID)
                        {
                            System.out.println("Conflicting ID exist");
                            break;
                        }
                    }
                    updateStaff = new staff(updatedID, staffList.get(staffIndex).getName(), staffList.get(staffIndex).getGender(), staffList.get(staffIndex).getJobTitle());
                    staffList.set(staffIndex, updateStaff);
                    System.out.println("Staff " + staffList.get(staffIndex).getName() + " successfully updated with new StaffID of " + staffList.get(staffIndex).getEmployeeId());
                    saveStaffList();
                    //staffIndex = getStaffListIndexById(updatedID);
                    break;

                case 2:
                    System.out.printf("Enter updated name for staff: ");
                    String updatedName = scan.nextLine();
                    updateStaff = new staff(staffList.get(staffIndex).getEmployeeId(), updatedName, staffList.get(staffIndex).getGender(), staffList.get(staffIndex).getJobTitle());
                    staffList.set(staffIndex, updateStaff);
                    System.out.println("Staff name successfully updated to " + updatedName);
                    saveStaffList();
                    break;

                case 3:
                    System.out.printf("Enter updated gender for staff: ");
                    char updatedGender = scan.next().charAt(0);
                    scan.nextLine();
                    updateStaff = new staff(staffList.get(staffIndex).getEmployeeId(), staffList.get(staffIndex).getName(), updatedGender, staffList.get(staffIndex).getJobTitle());
                    staffList.set(staffIndex, updateStaff);
                    System.out.println("Staff gender successfully updated to " + updatedGender);
                    saveStaffList();
                    break;

                case 4:
                    System.out.printf("Enter updated position for staff: ");
                    String updatedPosition = scan.nextLine();
                    updateStaff = new staff(staffList.get(staffIndex).getEmployeeId(), staffList.get(staffIndex).getName(), staffList.get(staffIndex).getGender(), updatedPosition);
                    staffList.set(staffIndex, updateStaff);
                    System.out.println("Staff position successfully updated to " + updatedPosition);
                    saveStaffList();
                    break;
                case 5:
                    viewStaffById(updatedID);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;

            }

        } while (choice != 6);

    }

    

}
