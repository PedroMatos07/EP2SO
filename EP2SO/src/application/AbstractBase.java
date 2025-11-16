package application;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock; // Importa a interface Lock

public abstract class AbstractBase {
    
    protected List<String> palavras = new ArrayList<>();
    

    public AbstractBase() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/bd.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                palavras.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getPalavras() {
        return palavras;
    }
    

    public abstract Lock getReadLock();
    public abstract Lock getWriteLock();
}