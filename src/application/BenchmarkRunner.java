package application;

import java.util.Random;
import java.util.function.Supplier;
import entities.Reader; // O único Reader
import entities.Writer; // O único Writer

public class BenchmarkRunner {

    public static double[] runBenchmark(int numThreads, int numRuns, String benchmarkName,
                                        Supplier<AbstractBase> baseSupplier) {
                                            
        double[] temposMedios = new double[numThreads + 1];
        for (int readersCount = 0; readersCount <= numThreads; readersCount++) {
            int writersCount = numThreads - readersCount;
            System.out.println("\nIniciando (" + benchmarkName + "): " + readersCount + " R / " + writersCount + " W");
            long somaTempos = 0;

            for (int run = 0; run < numRuns; run++) {
                AbstractBase base = baseSupplier.get(); 
                Thread[] threads = new Thread[numThreads];
                Random random = new Random();
                int rCriados = 0, wCriados = 0;

                for (int i = 0; i < numThreads; i++) {
                    Thread t;
                    if (rCriados < readersCount && (random.nextBoolean() || wCriados >= writersCount)) {
                        t = new Thread(new Reader(base)); 
                        rCriados++;
                    } else {
                        t = new Thread(new Writer(base)); 
                        wCriados++;
                    }
                    int pos;
                    do { pos = random.nextInt(numThreads); } while (threads[pos] != null);
                    threads[pos] = t;
                }

                long start = System.currentTimeMillis();
                for (Thread t : threads) t.start();
                for (Thread t : threads) {
                    try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
                }
                long end = System.currentTimeMillis();
                somaTempos += (end - start);
            }
            temposMedios[readersCount] = somaTempos / (double) numRuns;
            System.out.println("Concluído -> Tempo médio: " + temposMedios[readersCount] + " ms");
        }
        return temposMedios;
    }

    public static void printTabela(double[] temposMedios, int numThreads) {
        System.out.println("Readers | Writers | Tempo médio");
        System.out.println("-------------------------------");
        for (int i = 0; i <= numThreads; i++) {
            System.out.printf("%7d | %7d | %10.2f\n", i, numThreads - i, temposMedios[i]);
        }
    }
}