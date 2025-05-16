package runone;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String FILE_PATH = "contracts.csv";
    //Writing a file using save contract
    public static void saveContract(Contract contract){
        try(FileWriter fileWriter = new FileWriter(FILE_PATH, true)){
            fileWriter.write(contract.toFileString()+"\n");
        }
        catch (IOException e){
            System.out.println("File writing error " + e.getMessage());
        }
    }
}
