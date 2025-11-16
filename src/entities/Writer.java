package entities;



import java.util.List;
import java.util.Random;
import application.AbstractBase; // Depende da abstração

public class Writer implements Runnable {

    private AbstractBase base;

    public Writer(AbstractBase base) {
        this.base = base;
    }

    @Override
    public void run() {
        Random random = new Random();


        base.getWriteLock().lock();
        try {
            List<String> palavras = base.getPalavras();
            for (int i = 0; i < 100; i++) {
                int pos = random.nextInt(palavras.size());
                palavras.set(pos, "MODIFICADO");
            }
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            base.getWriteLock().unlock();
        }
    }
}