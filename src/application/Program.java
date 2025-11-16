package application;


public class Program {

    public static void main(String[] args) {
        int numThreads = 100;
        int numRuns = 50;

        // --- Benchmark 1: ReadWriteLock ---
        System.out.println("===============================================");
        System.out.println(" 🚀 INICIANDO BENCHMARK 1: ReadWriteLock ");
        System.out.println("===============================================");
        

        double[] temposMediosRW = BenchmarkRunner.runBenchmark(
            numThreads, numRuns, "ReadWrite", () -> new ReadWriteBase()
        );

        System.out.println("\n\n");

        // --- Benchmark 2: Mutex ---
        System.out.println("===============================================");
        System.out.println(" 🐢 INICIANDO BENCHMARK 2: Mutex (ReentrantLock) ");
        System.out.println("===============================================");
        
        // Apenas chama o Runner, passando a "fábrica" da MutexBase
        double[] temposMediosMutex = BenchmarkRunner.runBenchmark(
            numThreads, numRuns, "Mutex", () -> new MutexBase()
        );
        
        // --- Impressão dos Resultados Finais ---
        System.out.println("\n\n=== Tabela de tempos médios (ReadWriteLock) ===");
        BenchmarkRunner.printTabela(temposMediosRW, numThreads);
        
        System.out.println("\n\n=== Tabela de tempos médios (Mutex/ReentrantLock) ===");
        BenchmarkRunner.printTabela(temposMediosMutex, numThreads);
    }
    

}