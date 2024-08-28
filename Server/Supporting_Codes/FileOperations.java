import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations {

    private static final String INPUT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_FILE_NAME = "out.txt";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            System.out.println(line);
            String [] array = line.split(",", -1);
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
        br.close();

        String text = "Hello World";
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        bw.write(text);
        bw.write(System.lineSeparator());
        bw.close();
    }
}