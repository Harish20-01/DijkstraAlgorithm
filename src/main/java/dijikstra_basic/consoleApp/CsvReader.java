package dijikstra_basic.consoleApp;
import java.util.*;
import java.io.*;
public class CsvReader {
	public static List<String[]> readCsv(String filePath) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split by comma
                String[] parts = line.split(",");
                // clean BOM and whitespace
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();//.replace("\uFEFF", "");
                }
                rows.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
	}
}
