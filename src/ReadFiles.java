import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFiles {
    public static ArrayList<ArrayList<String>> readFile(String fileName) {
        String nLine = "";
        try {
            //inserisce i valori del file in una matrice di ArrayList
            BufferedReader br = new BufferedReader(new FileReader("src/MovieSchedule - Foglio1.csv"));
            //uso una matrice di arraylist perché non so la dimensione dell'array
            ArrayList<ArrayList<String>> dataMatrix = new ArrayList<>();
            int cont = 0;
            while((nLine = br.readLine()) != null) {
                dataMatrix.add(new ArrayList<>());
                String[] data = nLine.split(",");
                for (int i = 0; i < data.length; i++) {
                    dataMatrix.get(cont).add(data[i]);
                }
                cont++;
            }
            return dataMatrix;
        } catch(Exception e) {

        }
        return null;
    }
}
