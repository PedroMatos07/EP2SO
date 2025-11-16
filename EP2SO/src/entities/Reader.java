package entities;


import java.util.List;
import java.util.Random;
import application.AbstractBase; // Depende da abstração

public class Reader implements Runnable {
	
	private AbstractBase base;
	
	public Reader(AbstractBase base) {
		this.base = base;
	}

	 @Override
	    public void run() {
	        Random random = new Random();

            // Pede o "lock de leitura" (seja ele qual for)
	        base.getReadLock().lock();
	        try {
	            List<String> palavras = base.getPalavras();
	            for (int i = 0; i < 100; i++) {
	                int pos = random.nextInt(palavras.size());
	                String palavra = palavras.get(pos);
	            }
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            base.getReadLock().unlock();
	        }
	    }
}