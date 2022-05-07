package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOManager {
    Scanner scanner;
    private final String[] errorMessages = {
            "Что-то пошло не так.",
            "Не удалось распознать число.",
            };

    public IOManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void writeMessage(String message){
        System.out.println(message);
    }

    public void writeErrorMessage(String message){
        System.out.println("[Ошибка] " + message);
    }

    public double getDoubleDigit(String message) {
        double value = 0D;
        boolean valid = false;
        System.out.println();
        while (!valid) {
            System.out.print("[Ввод] " +message+": ");
            try {
                String str = scanner.nextLine().trim();
                value = Double.parseDouble(str);
                valid = true;
            } catch (NumberFormatException e) {
                this.writeErrorMessage( this.errorMessages[1] );
            } catch (Exception e) {
                this.writeErrorMessage( this.errorMessages[0] );
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
        return value;
    }

    public int getIntDigit(String message) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("[Ввод] " +message+": ");
            try {
                String str = scanner.nextLine().trim();
                value = Integer.parseInt(str);
                valid = true;
            } catch (NumberFormatException e) {
                this.writeErrorMessage( this.errorMessages[1] );
            } catch (Exception e) {
                this.writeErrorMessage( this.errorMessages[0] );
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
        return value;
    }

    public boolean getBooleanDigit(String message) {
        boolean value = false;
        boolean valid = false;

        while (!valid) {
            System.out.print("[Ввод] " +message+" (Y/n) ");
            try {
                String str = scanner.nextLine().toLowerCase().trim();
                if (str.isEmpty()) {
                    value = true;
                } else {
                    value = Boolean.parseBoolean(str);
                }
                valid = true;
            } catch (Exception e) {
                this.writeErrorMessage( this.errorMessages[0] );
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
        return value;
    }

    public void printTableHeaders(String[] heads) {
        StringBuilder out = new StringBuilder().append("\n");
        while (out.length() < 25) out.append(" ");

        for (int i = 0; i < heads.length ; i++) {
            out.append(heads[i]);
            while (out.length() < 25*(i+2)) out.append(" ");
        }
        this.writeMessage(out.toString());
    }

    public void printTableRow(String[] data) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < data.length ; i++) {
            out.append(data[i]);
            while (out.length() < 25*(i+1)) out.append(" ");
        }
        this.writeMessage(out.toString());
    }

    public void writeDebug(String message) {
        File file = new File("debug.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.append(message+"\n");
            pw.close();
        } catch (IOException e) {

        }
    }
}
