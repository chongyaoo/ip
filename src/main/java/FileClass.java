import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileClass {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        String file2 = "src/main/lines.txt";
        try {
            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        try {
            appendToFile(file2, System.lineSeparator() + "third line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        try {
            printFileContents("src/main/lines.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}