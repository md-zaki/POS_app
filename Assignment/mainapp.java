import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class mainapp implements Serializable{
    public static void main(String[] args) throws Exception{
        
        FileInputStream fileInputStream = new FileInputStream("testMenuSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
        menu.editMenu();

        FileOutputStream fileOutputStream = new FileOutputStream("testMenuSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(menu);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
