import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import utils.Commons;
import pojos.FieldType;
import pojos.Record;

public class Main {
    public static final String COMMA_STR = ",";
    public static final String OUTPUT_PATH = "C:\\Users\\agams\\OneDrive\\Desktop\\coda-assignment\\output";

    private static List<Map<String, Record>> processData(String[] headers, List<String[]> userDataList) {
        List<Map<String, Record>> employeeRecords = new ArrayList<>();
        int expectedFieldTypesCount = headers.length;
        int totalRecords = userDataList.size();

        for(int i = 0; i < totalRecords; i++){
            Map<String, Record> employeeRecord = new HashMap<>();
            String[] userData = userDataList.get(i);

            if(userData.length != expectedFieldTypesCount){
                System.out.println("Missing fields for Record " + (i+1) + " - hence skipping");
                continue;
            }

            for(int j = 0; j < expectedFieldTypesCount; j++){
                FieldType type = RegexValidator.getFieldType(userData[j]);
                employeeRecord.put(headers[j].trim(), new Record(userData[j].trim(), type.getText()));
            }

            employeeRecords.add(employeeRecord);
        }
        return employeeRecords;
    }

    private static void convertToJsonAndSave(List<Map<String, Record>> employeeRecords) {
        for(int i = 0; i < employeeRecords.size(); i++){
            try {
                FileWriter writer = new FileWriter(new File(OUTPUT_PATH, "user"+ (i+1) +".json"));
                writer.write(Commons.toJson(employeeRecords.get(i)));
                writer.close();
                System.out.println("Successfully wrote record " + (i+1) + " to the file.");
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        Scanner fileScanner;

        String filePath = sc.nextLine();
        File file = new File(filePath);

        try {
            fileScanner = new Scanner(file);
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }

        if(!fileScanner.hasNextLine()){
            throw new RuntimeException("Empty input file found");
        }

        String headersString = fileScanner.nextLine();
        String[] headers = headersString.split(COMMA_STR);
        List<String[]> userDataList = new ArrayList<>();

        while (fileScanner.hasNextLine()){
            userDataList.add(fileScanner.nextLine().split(COMMA_STR));
        }

        List<Map<String, Record>> employeeRecords = processData(headers, userDataList);

        convertToJsonAndSave(employeeRecords);
    }
}
