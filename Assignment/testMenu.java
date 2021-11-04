import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class testMenu implements Serializable{
    public static void main(String[] args) throws Exception{
        manageOrder order = new manageOrder();
        manageOrder.createNewOrder();
    }
}
