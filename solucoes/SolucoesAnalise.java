import java.util.*;

/**
 * Três Soluções para o Problema: Verificar se Array Contém Dois Elementos com Soma = k
 *
 * Problema: Dado um array de números inteiros e um valor k, determinar se existem
 * dois elementos distintos cuja soma é igual a k.
 *
 * Análise de Complexidade:
 * 1. Força Bruta: O(n²) tempo, O(1) espaço
 * 2. Hash Map: O(n) tempo, O(n) espaço
 * 3. Two Pointers: O(n log n) tempo (com sort), O(n) espaço
 */

public class SolucoesAnalise {

    /**
     * SOLUÇÃO 1: FORÇA BRUTA (Nested Loops)
     *
     * Princípio: Verifica todos os pares de elementos no array.
     *
     * Complexidade de Tempo:
     * - Melhor caso: O(n²)
     * - Caso médio: O(n²)
     * - Pior caso: O(n²)
     *
     * Complexidade de Espaço: O(1) - sem estruturas auxiliares
     *
     * Vantagens:
     * - Sem uso de memória adicional
     * - Funciona com arrays desordenados
     * - Implementação simples
     *
     * Desvantagens:
     * - Muito lento para arrays grandes (n > 1000)
     * - Não aproveita ordenação
     */
    public static class Solucao1ForcaBruta {

        /**
         * Verifica todos os pares de elementos.
         *
         * @param arr Array de números inteiros
         * @param k   Valor alvo da soma
         * @return Array com [encontrado (1/0), elemento1, elemento2]
         */
        public static int[] encontrarDoisSoma(int[] arr, int k) {
            int n = arr.length;

            // Itera sobre todos os pares (i, j) onde i < j
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] + arr[j] == k) {
                        return new int[]{1, arr[i], arr[j]};
                    }
                }
            }

            return new int[]{0};
        }

        /**
         * Encontra todos os pares cuja soma é k.
         */
        public static List<int[]> encontrarTodosPares(int[] arr, int k) {
            List<int[]> pares = new ArrayList<>();
            int n = arr.length;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] + arr[j] == k) {
                        pares.add(new int[]{arr[i], arr[j]});
                    }
                }
            }

            return pares;
        }
    }

    /**
     * SOLUÇÃO 2: HASH MAP / SET
     *
     * Princípio: Usa estrutura hash para armazenar elementos já visitados.
     * Enquanto itera, verifica se o complemento (k - elemento) está no set.
     *
     * Complexidade de Tempo:
     * - Melhor caso: O(n)
     * - Caso médio: O(n)
     * - Pior caso: O(n)
     *
     * Complexidade de Espaço: O(n) - armazena até n elementos no set
     *
     * Vantagens:
     * - Tempo linear, muito mais rápido que força bruta
     * - Funciona com arrays desordenados
     * - Implementação simples e elegante
     *
     * Desvantagens:
     * - Usa O(n) de memória adicional
     * - Não aproveita ordenação
     */
    public static class Solucao2HashMap {

        /**
         * Usa um set para buscar o complemento de cada elemento.
         *
         * @param arr Array de números inteiros
         * @param k   Valor alvo da soma
         * @return Array com [encontrado (1/0), elemento1, elemento2]
         */
        public static int[] encontrarDoisSoma(int[] arr, int k) {
            Set<Integer> vistos = new HashSet<>();

            for (int num : arr) {
                int complemento = k - num;

                if (vistos.contains(complemento)) {
                    return new int[]{1, num, complemento};
                }

                vistos.add(num);
            }

            return new int[]{0};
        }

        /**
         * Versão com dicionário que armazena índices.
         */
        public static int[] encontrarComMapa(int[] arr, int k) {
            Map<Integer, Integer> mapa = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                int num = arr[i];
                int complemento = k - num;

                if (mapa.containsKey(complemento)) {
                    return new int[]{1, mapa.get(complemento), i};
                }

                mapa.put(num, i);
            }

            return new int[]{0};
        }

        /**
         * Encontra todos os pares sem repetição.
         */
        public static List<int[]> encontrarTodosPares(int[] arr, int k) {
            Set<Integer> vistos = new HashSet<>();
            Set<String> pares = new HashSet<>();  // Evita duplicatas
            List<int[]> resultado = new ArrayList<>();

            for (int num : arr) {
                int complemento = k - num;

                if (vistos.contains(complemento)) {
                    // Cria chave ordenada para evitar (a,b) e (b,a)
                    int a = Math.min(num, complemento);
                    int b = Math.max(num, complemento);
                    String chave = a + "," + b;

                    if (!pares.contains(chave)) {
                        pares.add(chave);
                        resultado.add(new int[]{a, b});
                    }
                }

                vistos.add(num);
            }

            return resultado;
        }
    }

    /**
     * SOLUÇÃO 3: TWO POINTERS (Array Ordenado)
     *
     * Princípio: Ordena o array, depois usa ponteiros no início e final,
     * movendo-os conforme a soma comparada com k.
     *
     * Complexidade de Tempo:
     * - Ordenação: O(n log n)
     * - Busca: O(n)
     * - Total: O(n log n)
     *
     * Complexidade de Espaço: O(n) para ordenação
     *
     * Vantagens:
     * - Mais rápido que força bruta para arrays grandes
     * - Usa menos memória que hash map
     * - Elegante e fácil de entender
     *
     * Desvantagens:
     * - Requer array ordenado (O(n log n))
     * - Perde índices originais após ordenação
     */
    public static class Solucao3DoisPonteiros {

        /**
         * Usa two pointers em array ordenado.
         *
         * @param arr Array de números inteiros (pode estar desordenado)
         * @param k   Valor alvo da soma
         * @return Array com [encontrado (1/0), elemento1, elemento2]
         */
        public static int[] encontrarDoisSoma(int[] arr, int k) {
            // Ordena o array
            int[] arrOrdenado = arr.clone();
            Arrays.sort(arrOrdenado);

            int esquerda = 0;
            int direita = arrOrdenado.length - 1;

            // Move os ponteiros
            while (esquerda < direita) {
                int soma = arrOrdenado[esquerda] + arrOrdenado[direita];

                if (soma == k) {
                    return new int[]{1, arrOrdenado[esquerda], arrOrdenado[direita]};
                } else if (soma < k) {
                    // Se soma é pequena demais, aumenta-a movendo esquerda
                    esquerda++;
                } else {
                    // Se soma é grande demais, diminui-a movendo direita
                    direita--;
                }
            }

            return new int[]{0};
        }

        /**
         * Encontra todos os pares em array ordenado.
         */
        public static List<int[]> encontrarTodosPares(int[] arr, int k) {
            int[] arrOrdenado = arr.clone();
            Arrays.sort(arrOrdenado);

            List<int[]> pares = new ArrayList<>();

            int esquerda = 0;
            int direita = arrOrdenado.length - 1;

            while (esquerda < direita) {
                int soma = arrOrdenado[esquerda] + arrOrdenado[direita];

                if (soma == k) {
                    pares.add(new int[]{arrOrdenado[esquerda], arrOrdenado[direita]});
                    esquerda++;
                    direita--;
                } else if (soma < k) {
                    esquerda++;
                } else {
                    direita--;
                }
            }

            return pares;
        }
    }

    /**
     * VARIAÇÃO: BUSCA BINÁRIA (Array Ordenado)
     *
     * Princípio: Ordena o array e, para cada elemento, faz busca binária
     * do complemento (k - elemento).
     *
     * Complexidade de Tempo:
     * - Ordenação: O(n log n)
     * - Busca binária para cada elemento: O(n * log n)
     * - Total: O(n log n)
     *
     * Complexidade de Espaço: O(n)
     */
    public static class Solucao3BuscaBinaria {

        /**
         * Busca binária para encontrar um valor no array.
         */
        private static int buscaBinaria(int[] arr, int alvo, int excluirIdx) {
            int esquerda = 0;
            int direita = arr.length - 1;

            while (esquerda <= direita) {
                int meio = (esquerda + direita) / 2;

                if (meio == excluirIdx) {
                    esquerda = meio + 1;
                    continue;
                }

                if (arr[meio] == alvo) {
                    return meio;
                } else if (arr[meio] < alvo) {
                    esquerda = meio + 1;
                } else {
                    direita = meio - 1;
                }
            }

            return -1;
        }

        /**
         * Para cada elemento, faz busca binária do complemento.
         */
        public static int[] encontrarDoisSoma(int[] arr, int k) {
            int[] arrOrdenado = arr.clone();
            Arrays.sort(arrOrdenado);

            for (int i = 0; i < arrOrdenado.length; i++) {
                int num = arrOrdenado[i];
                int complemento = k - num;
                int idx = buscaBinaria(arrOrdenado, complemento, i);

                if (idx != -1) {
                    return new int[]{1, num, arrOrdenado[idx]};
                }
            }

            return new int[]{0};
        }
    }

    /**
     * Classe auxiliar para armazenar tempo de execução e resultado.
     */
    public static class ResultadoBenchmark {
        public String solucao;
        public int tamanho;
        public long tempoMs;
        public boolean encontrado;

        public ResultadoBenchmark(String solucao, int tamanho, long tempoMs, boolean encontrado) {
            this.solucao = solucao;
            this.tamanho = tamanho;
            this.tempoMs = tempoMs;
            this.encontrado = encontrado;
        }

        @Override
        public String toString() {
            return String.format("%s (n=%d): %dms", solucao, tamanho, tempoMs);
        }
    }

    public static void main(String[] args) {
        // Teste simples
        int[] arr = {1, 5, 7, -1};
        int k = 6;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Alvo: " + k);
        System.out.println();

        int[] resultado1 = Solucao1ForcaBruta.encontrarDoisSoma(arr.clone(), k);
        System.out.println("Força Bruta: " + (resultado1[0] == 1 ? "Encontrado " + resultado1[1] + " + " + resultado1[2] : "Não encontrado"));

        int[] resultado2 = Solucao2HashMap.encontrarDoisSoma(arr.clone(), k);
        System.out.println("Hash Map: " + (resultado2[0] == 1 ? "Encontrado " + resultado2[1] + " + " + resultado2[2] : "Não encontrado"));

        int[] resultado3 = Solucao3DoisPonteiros.encontrarDoisSoma(arr.clone(), k);
        System.out.println("Two Pointers: " + (resultado3[0] == 1 ? "Encontrado " + resultado3[1] + " + " + resultado3[2] : "Não encontrado"));

        int[] resultado4 = Solucao3BuscaBinaria.encontrarDoisSoma(arr.clone(), k);
        System.out.println("Busca Binária: " + (resultado4[0] == 1 ? "Encontrado " + resultado4[1] + " + " + resultado4[2] : "Não encontrado"));
    }
}
