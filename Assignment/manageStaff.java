import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class manageStaff implements Serializable{
	private ArrayList<staff> staffList = new ArrayList<staff>();
	private static int numStaff=0;
	Scanner scan = new Scanner(System.in);

	public manageStaff() throws IOException, ClassNotFoundException
	{
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
	}
	
	public ArrayList<staff> getStaffList()
	{
		return this.staffList;
	}
	
    public void setStaffList(ArrayList<staff> staffList) {
        this.staffList = staffList;
    }
	
	public int getNumStaff()
	{
		return this.numStaff;
	}
	
	public void setNumStaff(int numStaff)
	{
		this.numStaff = numStaff;
	}
	
	public void addStaff()
	{
		System.out.printf("Enter Staff ID: ");
		int staffId = scan.nextInt();
		scan.nextLine();
		
		for(int i=0 ; i < staffList.size() ; i++)
		{
			if(staffList.get(i).getEmployeeId() == staffId)
			{
				System.out.println("Staff with entered ID already exist.");
				return;
			}
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
		manageStaff.numStaff++;
		
		System.out.println("New Staff Added.");

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
		System.out.println("===============STAFF DATA===============");
		
		if (staffList.size() == 0)
		{
			System.out.println("Staff List is empty");
		}
		else
		{
			for (int i=0 ; i<staffList.size() ; i++)
			{
				if (staffList.get(i).getEmployeeId() == staffId)
				{
					printStaff(staffList.get(i));
					System.out.println("\n===============END OF DATA===============");
					return;
				}
			}
			System.out.println("Staff ID does not exist.");
			System.out.println("\n===============END OF DATA===============");
		}
	}
	
	public void updateStaffInfo(int staffId)
	{
		int index = -1;
		int choice;
		for (int i=0; i<staffList.size(); i++)
		{
			if(staffList.get(i).getEmployeeId() == staffId)
			{
				index = i;
			}
		}
		
		if (index == -1)
		{
			System.out.println("Staff ID does not exist.");
			return;
		}
		
		do
		{
		System.out.println("Editing Staff " + staffList.get(index).getName() + " Information");
		System.out.println("Please select your operations");
		System.out.println("(1) Edit Staff ID");
		System.out.println("(2) Edit Staff Name");
		System.out.println("(3) Edit Staff Gender");
		System.out.println("(4) Edit Staff Position");
		System.out.println("(5) Exit");
		System.out.printf("Enter a choice: ");
		choice = scan.nextInt();
		scan.nextLine();
		switch(choice)
		{
		case 1:
			System.out.printf("Enter updated ID for staff: ");
			int updatedID = scan.nextInt();
			scan.nextLine();
			for(int i = 0 ; i < staffList.size() ; i++)
			{
				if (staffList.get(i).getEmployeeId() == updatedID)
				{
					System.out.println("Conflicting ID exist");
					break;
				}
			}
			staff updateStaff = new staff(updatedID, staffList.get(index).getName(), staffList.get(index).getGender(), staffList.get(index).getJobTitle());
			staffList.set(index, updateStaff);
			System.out.println("Staff " + staffList.get(index).getName() + " successfully updated with new StaffID of " + staffList.get(index).getEmployeeId());
			break;
			
		case 2:
			break;
			
		case 3:
			break;
			
		case 4:
			break;		
			
		default:
			System.out.println("Please enter a valid choice");
				
		}
		
		} while (choice != 5);
		
		
		
	}
	
	public void start() throws IOException, ClassNotFoundException
	{
        int choice;
        do{
        System.out.println("(1) View All Staffs");
        System.out.println("(2) View Staff by ID");
        System.out.println("(3) Add Staff");
        System.out.println("(4) Update Staff Info");
        System.out.println("(6) Exit");
        System.out.println("Select a choice: ");
        choice = scan.nextInt();
        scan.nextLine();
        	switch (choice) {
	            case 1: 
	            		viewStaffList();
	                    break;
	            case 2:
	            		System.out.printf("Enter Staff ID: ");
	            		int staffId = scan.nextInt();
	            		scan.nextLine();
	            		viewStaffById(staffId);
	            		break;
	            case 3:
	            		addStaff();
	                    break;
	            case 4:
		            	System.out.printf("Enter Staff ID: ");
	            		staffId = scan.nextInt();
	            		scan.nextLine();
	            		updateStaffInfo(staffId);
	            		break;
	            default:
	            	//System.out.println("Please enter a valid option");
        	}
        } while (choice != 6);
		

		
	}

}
