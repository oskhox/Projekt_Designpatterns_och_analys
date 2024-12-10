package Menus.CottageDetails;

import Menus.MenuScreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CottageDetails implements MenuScreen {


    private final String filePath;

    public CottageDetails(String filePath) {
        this.filePath = filePath;
    }

    public void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) !=null) {
                System.out.println(line);
            }
        }catch (IOException e){
            System.err.println("Error reading file " + e.getMessage());
        }
    }

    @Override
    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        readFromFile();
        System.out.println("\n\"Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:\" ");

        while(true) {
            String input = scanner.nextLine();
            if(input.trim().equalsIgnoreCase("e") || input.trim().equalsIgnoreCase("exit")) {
                break;
            }else{
                System.out.println("Felaktig inmatning, skriv 'exit' eller 'e' för att återgå till huvudmenyn  ");
            }
        }
    }
}
