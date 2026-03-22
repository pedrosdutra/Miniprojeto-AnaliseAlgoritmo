import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Script de Análise de Performance e Benchmarking
 * Mede o tempo de execução de cada solução com diferentes tamanhos de entrada.
 * Gera dados para visualização em gráficos.
 */
public class Benchmark {

    private static final int TIMEOUT_SEGUNDOS = 30;
    private Random random = new Random(42);  // Seed para reproducibilidade

    /**
     * Gera array desordenado com valores aleatórios.
     */
    private int[] gerarArrayDesordenado(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(2000001) - 1000000;
        }
        return arr;
    }

    /**
     * Gera array ordenado.
     */
    private int[] gerarArrayOrdenado(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(2000001) - 1000000;
        }
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Gera array com muitas duplicatas.
     */
    private int[] gerarArrayComDuplicatas(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        return arr;
    }

    /**
     * Mede o tempo de execução de uma solução.
     */
    private long medirTempoExecucao(String nomeMetodo, int[] arr, int k) {
        long inicio = System.currentTimeMillis();

        try {
            if (nomeMetodo.equals("ForcaBruta")) {
                SolucoesAnalise.Solucao1ForcaBruta.encontrarDoisSoma(arr.clone(), k);
            } else if (nomeMetodo.equals("HashMap")) {
                SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr.clone(), k);
            } else if (nomeMetodo.equals("TwoPonteiros")) {
                SolucoesAnalise.Solucao3DoisPonteiros.encontrarDoisSoma(arr.clone(), k);
            } else if (nomeMetodo.equals("BuscaBinaria")) {
                SolucoesAnalise.Solucao3BuscaBinaria.encontrarDoisSoma(arr.clone(), k);
            }
        } catch (Exception e) {
            System.err.println("Erro ao executar " + nomeMetodo + ": " + e.getMessage());
            return -1;
        }

        long fim = System.currentTimeMillis();
        return fim - inicio;
    }

    /**
     * Benchmark principal: crescimento de tamanho de entrada.
     */
    public Map<String, List<Map<String, Object>>> benchmarkCrescimento() {
        System.out.println("================================================================================");
        System.out.println("BENCHMARK: Crescimento de Tamanho de Entrada");
        System.out.println("================================================================================\n");

        int[] tamanhos = {
            100, 200, 300, 500, 1000, 2000, 3000, 5000,
            7500, 10000, 15000, 20000, 30000, 50000
        };

        Map<String, List<Map<String, Object>>> dados = new HashMap<>();
        dados.put("forca_bruta", new ArrayList<>());
        dados.put("hash_map", new ArrayList<>());
        dados.put("two_pointers", new ArrayList<>());
        dados.put("busca_binaria", new ArrayList<>());

        for (int tamanho : tamanhos) {
            System.out.println("Testando tamanho: " + tamanho + " elementos");

            int[] arr = gerarArrayDesordenado(tamanho);
            int k = arr[tamanho / 2] + arr[tamanho / 3];  // Uma soma válida

            // Solução 1: Força Bruta (apenas para arrays pequenos)
            if (tamanho <= 5000) {
                try {
                    long tempo = medirTempoExecucao("ForcaBruta", arr, k);
                    if (tempo >= 0) {
                        System.out.println("  Força Bruta: " + tempo + "ms");
                        Map<String, Object> entry = new HashMap<>();
                        entry.put("tamanho", tamanho);
                        entry.put("tempo_ms", (double)tempo);
                        dados.get("forca_bruta").add(entry);
                    }
                } catch (Exception e) {
                    System.out.println("  Força Bruta: TIMEOUT/ERRO");
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("tamanho", tamanho);
                    entry.put("tempo_ms", null);
                    dados.get("forca_bruta").add(entry);
                }
            } else {
                System.out.println("  Força Bruta: PULADO (tamanho muito grande)");
                Map<String, Object> entry = new HashMap<>();
                entry.put("tamanho", tamanho);
                entry.put("tempo_ms", null);
                dados.get("forca_bruta").add(entry);
            }

            // Solução 2: Hash Map
            long tempo = medirTempoExecucao("HashMap", arr, k);
            System.out.println("  Hash Map: " + tempo + "ms");
            Map<String, Object> entry = new HashMap<>();
            entry.put("tamanho", tamanho);
            entry.put("tempo_ms", (double)tempo);
            dados.get("hash_map").add(entry);

            // Solução 3: Two Pointers
            tempo = medirTempoExecucao("TwoPonteiros", arr, k);
            System.out.println("  Two Pointers: " + tempo + "ms");
            entry = new HashMap<>();
            entry.put("tamanho", tamanho);
            entry.put("tempo_ms", (double)tempo);
            dados.get("two_pointers").add(entry);

            // Solução 3b: Busca Binária
            tempo = medirTempoExecucao("BuscaBinaria", arr, k);
            System.out.println("  Busca Binária: " + tempo + "ms");
            entry = new HashMap<>();
            entry.put("tamanho", tamanho);
            entry.put("tempo_ms", (double)tempo);
            dados.get("busca_binaria").add(entry);

            System.out.println();
        }

        return dados;
    }

    /**
     * Benchmark para casos especiais.
     */
    public Map<String, Map<String, Double>> benchmarkCasosEspeciais() {
        System.out.println("\n" + "================================================================================");
        System.out.println("BENCHMARK: Casos Especiais");
        System.out.println("================================================================================\n");

        int tamanho_teste = 10000;

        Map<String, Map<String, Double>> dados = new HashMap<>();
        dados.put("desordenado", new HashMap<>());
        dados.put("ordenado", new HashMap<>());
        dados.put("duplicatas", new HashMap<>());

        // Caso 1: Desordenado
        System.out.println("1. Array DESORDENADO (" + tamanho_teste + " elementos):");
        int[] arr_desordenado = gerarArrayDesordenado(tamanho_teste);
        int k = arr_desordenado[tamanho_teste / 2] + arr_desordenado[tamanho_teste / 3];

        long tempo = medirTempoExecucao("HashMap", arr_desordenado, k);
        System.out.println("  Hash Map: " + tempo + "ms");
        dados.get("desordenado").put("hash_map", (double)tempo);

        tempo = medirTempoExecucao("TwoPonteiros", arr_desordenado, k);
        System.out.println("  Two Pointers: " + tempo + "ms");
        dados.get("desordenado").put("two_pointers", (double)tempo);

        tempo = medirTempoExecucao("BuscaBinaria", arr_desordenado, k);
        System.out.println("  Busca Binária: " + tempo + "ms");
        dados.get("desordenado").put("busca_binaria", (double)tempo);

        // Caso 2: Ordenado
        System.out.println("\n2. Array ORDENADO (" + tamanho_teste + " elementos):");
        int[] arr_ordenado = gerarArrayOrdenado(tamanho_teste);
        k = arr_ordenado[tamanho_teste / 2] + arr_ordenado[tamanho_teste / 3];

        tempo = medirTempoExecucao("HashMap", arr_ordenado, k);
        System.out.println("  Hash Map: " + tempo + "ms");
        dados.get("ordenado").put("hash_map", (double)tempo);

        tempo = medirTempoExecucao("TwoPonteiros", arr_ordenado, k);
        System.out.println("  Two Pointers: " + tempo + "ms");
        dados.get("ordenado").put("two_pointers", (double)tempo);

        tempo = medirTempoExecucao("BuscaBinaria", arr_ordenado, k);
        System.out.println("  Busca Binária: " + tempo + "ms");
        dados.get("ordenado").put("busca_binaria", (double)tempo);

        // Caso 3: Com Duplicatas
        System.out.println("\n3. Array com DUPLICATAS (" + tamanho_teste + " elementos):");
        int[] arr_duplicatas = gerarArrayComDuplicatas(tamanho_teste);

        tempo = medirTempoExecucao("HashMap", arr_duplicatas, k);
        System.out.println("  Hash Map: " + tempo + "ms");
        dados.get("duplicatas").put("hash_map", (double)tempo);

        tempo = medirTempoExecucao("TwoPonteiros", arr_duplicatas, k);
        System.out.println("  Two Pointers: " + tempo + "ms");
        dados.get("duplicatas").put("two_pointers", (double)tempo);

        tempo = medirTempoExecucao("BuscaBinaria", arr_duplicatas, k);
        System.out.println("  Busca Binária: " + tempo + "ms");
        dados.get("duplicatas").put("busca_binaria", (double)tempo);

        return dados;
    }

    /**
     * Salva dados em arquivo CSV.
     */
    private void salvarDadosCSV(Map<String, List<Map<String, Object>>> dados, String arquivo) {
        try {
            FileWriter writer = new FileWriter(arquivo);
            writer.write("Tamanho,ForcaBruta,HashMap,TwoPonteiros,BuscaBinaria\n");

            List<Map<String, Object>> primeira = dados.get("hash_map");
            for (Map<String, Object> item : primeira) {
                int tamanho = (int) item.get("tamanho");
                writer.write(tamanho + ",");

                // Força Bruta
                Double tempo_fb = null;
                for (Map<String, Object> fb : dados.get("forca_bruta")) {
                    if ((int) fb.get("tamanho") == tamanho) {
                        tempo_fb = (Double) fb.get("tempo_ms");
                        break;
                    }
                }
                writer.write((tempo_fb != null ? String.format("%.2f", tempo_fb) : "") + ",");

                // HashMap
                Double tempo_hm = (Double) item.get("tempo_ms");
                writer.write(String.format("%.2f", tempo_hm) + ",");

                // Two Pointers
                Double tempo_tp = null;
                for (Map<String, Object> tp : dados.get("two_pointers")) {
                    if ((int) tp.get("tamanho") == tamanho) {
                        tempo_tp = (Double) tp.get("tempo_ms");
                        break;
                    }
                }
                writer.write(String.format("%.2f", tempo_tp) + ",");

                // Busca Binária
                Double tempo_bb = null;
                for (Map<String, Object> bb : dados.get("busca_binaria")) {
                    if ((int) bb.get("tamanho") == tamanho) {
                        tempo_bb = (Double) bb.get("tempo_ms");
                        break;
                    }
                }
                writer.write((tempo_bb != null ? String.format("%.2f", tempo_bb) : ""));

                writer.write("\n");
            }

            writer.close();
            System.out.println("Dados salvos em: " + arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();

        // Benchmark 1: Crescimento
        Map<String, List<Map<String, Object>>> dadosCrescimento = benchmark.benchmarkCrescimento();
        benchmark.salvarDadosCSV(dadosCrescimento, "benchmark_crescimento.csv");

        // Benchmark 2: Casos especiais
        Map<String, Map<String, Double>> dadosEspeciais = benchmark.benchmarkCasosEspeciais();

        System.out.println("\n" + "================================================================================");
        System.out.println("BENCHMARKS CONCLUÍDOS!");
        System.out.println("================================================================================");
    }
}
