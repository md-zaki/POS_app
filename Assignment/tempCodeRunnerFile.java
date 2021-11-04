FileInputStream fileInputStream = new FileInputStream("testMenuSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
        menu.editMenu();