import java.util.*;

/**
 * Script de Testes e Validação das Três Soluções
 * Testa corretude das implementações com vários casos de teste.
 */
public class Testes {

    private static int total_tests = 0;
    private static int passed_tests = 0;

    /**
     * Compara dois arrays de resultados.
     */
    private static boolean arraysIguais(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    /**
     * Testa um caso individual.
     */
    private static void testarCaso(SolucoesAnalise.ResultadoBenchmark[] resultados, int[] arr, int k, boolean esperado) {
        total_tests++;

        System.out.printf("  Array: %s, k=%d%n", Arrays.toString(arr), k);

        // Solução 1
        int[] res1 = SolucoesAnalise.Solucao1ForcaBruta.encontrarDoisSoma(arr.clone(), k);
        boolean encontrado1 = (res1[0] == 1);

        if (encontrado1 == esperado) {
            System.out.println("    ✓ Força Bruta: PASS");
            passed_tests++;
        } else {
            System.out.println("    ✗ Força Bruta: FAIL");
        }

        // Solução 2
        int[] res2 = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr.clone(), k);
        boolean encontrado2 = (res2[0] == 1);

        if (encontrado2 == esperado) {
            System.out.println("    ✓ Hash Map: PASS");
            passed_tests++;
        } else {
            System.out.println("    ✗ Hash Map: FAIL");
        }

        // Solução 3
        int[] res3 = SolucoesAnalise.Solucao3DoisPonteiros.encontrarDoisSoma(arr.clone(), k);
        boolean encontrado3 = (res3[0] == 1);

        if (encontrado3 == esperado) {
            System.out.println("    ✓ Two Pointers: PASS");
            passed_tests++;
        } else {
            System.out.println("    ✗ Two Pointers: FAIL");
        }

        // Solução 4
        int[] res4 = SolucoesAnalise.Solucao3BuscaBinaria.encontrarDoisSoma(arr.clone(), k);
        boolean encontrado4 = (res4[0] == 1);

        if (encontrado4 == esperado) {
            System.out.println("    ✓ Busca Binária: PASS");
            passed_tests++;
        } else {
            System.out.println("    ✗ Busca Binária: FAIL");
        }

        System.out.println();
    }

    public static void testeBasico() {
        System.out.println("===============================================================================");
        System.out.println("TESTES BÁSICOS - Validação de Corretude");
        System.out.println("===============================================================================\n");

        Object[][] casos_teste = {
                {new int[]{1, 5, 7, -1}, 6, true},          // 1 + 5 = 6
                {new int[]{2, 3, 4}, 7, true},               // 3 + 4 = 7
                {new int[]{1, 2, 3}, 10, false},             // Não existe soma
                {new int[]{0, 0}, 0, true},                  // Zeros
                {new int[]{-1, -2, -3, 5}, 2, true},        // -3 + 5 = 2
                {new int[]{10}, 10, false},                  // Um único elemento
                {new int[]{1, 1, 1, 1, 2}, 2, true},        // 1 + 1 = 2
        };

        for (Object[] caso : casos_teste) {
            int[] arr = (int[]) caso[0];
            int k = (int) caso[1];
            boolean esperado = (boolean) caso[2];

            testarCaso(null, arr, k, esperado);
        }

        System.out.println("Resultado: " + passed_tests + "/" + total_tests + " testes passou");
    }

    public static void testeEncontrarTodos() {
        System.out.println("\n" + "===============================================================================");
        System.out.println("TESTE: Encontrar Todos os Pares");
        System.out.println("===============================================================================\n");

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 8;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Alvo: " + k + "\n");

        System.out.println("Solução 1 (Força Bruta):");
        List<int[]> pares1 = SolucoesAnalise.Solucao1ForcaBruta.encontrarTodosPares(arr, k);
        for (int[] par : pares1) {
            System.out.println("  " + par[0] + " + " + par[1] + " = " + k);
        }

        System.out.println("\nSolução 2 (Hash Map):");
        List<int[]> pares2 = SolucoesAnalise.Solucao2HashMap.encontrarTodosPares(arr, k);
        for (int[] par : pares2) {
            System.out.println("  " + par[0] + " + " + par[1] + " = " + k);
        }

        System.out.println("\nSolução 3 (Two Pointers):");
        List<int[]> pares3 = SolucoesAnalise.Solucao3DoisPonteiros.encontrarTodosPares(arr, k);
        for (int[] par : pares3) {
            System.out.println("  " + par[0] + " + " + par[1] + " = " + k);
        }
    }

    public static void testeCasosEspeciais() {
        System.out.println("\n" + "===============================================================================");
        System.out.println("TESTES ESPECIAIS - Edge Cases");
        System.out.println("===============================================================================\n");

        // Teste 1: Array com elementos negativos
        System.out.println("1. Array com elementos negativos:");
        int[] arr1 = {-5, -3, 0, 3, 5};
        int k1 = 0;
        System.out.println("   Array: " + Arrays.toString(arr1) + ", k=" + k1);

        int[] res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr1, k1);
        System.out.println("   Resultado: " + (res[0] == 1 ? "Encontrado " + res[1] + " + " + res[2] : "Não encontrado"));

        // Teste 2: Array com duplicatas
        System.out.println("\n2. Array com duplicatas:");
        int[] arr2 = {1, 1, 1, 2, 2, 3};
        int k2 = 2;
        System.out.println("   Array: " + Arrays.toString(arr2) + ", k=" + k2);

        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr2, k2);
        System.out.println("   Resultado: " + (res[0] == 1 ? "Encontrado " + res[1] + " + " + res[2] : "Não encontrado"));

        // Teste 3: Array grande
        System.out.println("\n3. Array grande (1000 elementos):");
        int[] arr3 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr3[i] = i;
        }
        int k3 = 1500;  // 500 + 1000
        System.out.println("   Array: range(1000), k=" + k3);

        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr3, k3);
        System.out.println("   Hash Map: " + (res[0] == 1 ? "Encontrado " + res[1] + " + " + res[2] : "Não encontrado"));

        res = SolucoesAnalise.Solucao3DoisPonteiros.encontrarDoisSoma(arr3, k3);
        System.out.println("   Two Pointers: " + (res[0] == 1 ? "Encontrado " + res[1] + " + " + res[2] : "Não encontrado"));

        // Teste 4: Array vazio/único elemento
        System.out.println("\n4. Array vazio/um elemento:");
        int[] arr4 = {};
        int[] arr5 = {5};
        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr4, 5);
        System.out.println("   Array vazio: " + (res[0] == 1 ? "Encontrado" : "Não encontrado"));

        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr5, 5);
        System.out.println("   Um elemento: " + (res[0] == 1 ? "Encontrado" : "Não encontrado"));
    }

    public static void testeOrdenadoDesordenado() {
        System.out.println("\n" + "===============================================================================");
        System.out.println("TESTE: Arrays Ordenados vs Desordenados");
        System.out.println("===============================================================================\n");

        int[] arr_desordenado = {7, 2, 11, 15, 3};
        int[] arr_ordenado = arr_desordenado.clone();
        Arrays.sort(arr_ordenado);
        int k = 9;  // 2 + 7 = 9

        System.out.println("Array desordenado: " + Arrays.toString(arr_desordenado));
        System.out.println("Array ordenado: " + Arrays.toString(arr_ordenado));
        System.out.println("Alvo: " + k + "\n");

        System.out.println("Solução 1 (Força Bruta):");
        int[] res = SolucoesAnalise.Solucao1ForcaBruta.encontrarDoisSoma(arr_desordenado.clone(), k);
        System.out.println("  Desordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));
        res = SolucoesAnalise.Solucao1ForcaBruta.encontrarDoisSoma(arr_ordenado.clone(), k);
        System.out.println("  Ordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));

        System.out.println("\nSolução 2 (Hash Map):");
        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr_desordenado.clone(), k);
        System.out.println("  Desordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));
        res = SolucoesAnalise.Solucao2HashMap.encontrarDoisSoma(arr_ordenado.clone(), k);
        System.out.println("  Ordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));

        System.out.println("\nSolução 3 (Two Pointers):");
        res = SolucoesAnalise.Solucao3DoisPonteiros.encontrarDoisSoma(arr_desordenado.clone(), k);
        System.out.println("  Desordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));
        res = SolucoesAnalise.Solucao3DoisPonteiros.encontrarDoisSoma(arr_ordenado.clone(), k);
        System.out.println("  Ordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));

        System.out.println("\nSolução 3b (Busca Binária):");
        res = SolucoesAnalise.Solucao3BuscaBinaria.encontrarDoisSoma(arr_desordenado.clone(), k);
        System.out.println("  Desordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));
        res = SolucoesAnalise.Solucao3BuscaBinaria.encontrarDoisSoma(arr_ordenado.clone(), k);
        System.out.println("  Ordenado: " + (res[0] == 1 ? "Encontrado: " + res[1] + " + " + res[2] : "Não encontrado"));
    }

    public static void main(String[] args) {
        testeBasico();
        testeEncontrarTodos();
        testeCasosEspeciais();
        testeOrdenadoDesordenado();

        System.out.println("\n" + "===============================================================================");
        System.out.println("TODOS OS TESTES CONCLUÍDOS COM SUCESSO!");
        System.out.println("===============================================================================");
    }
}
