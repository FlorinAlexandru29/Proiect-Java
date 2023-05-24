package proiect_java;

public class Main {
    public static <Scanner> void main(String[] args) {
        DataLoader.initializeData();
        Menu menu = new Menu();
        menu.run();
        }
    }

