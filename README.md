# Análise de Algoritmos: Dois Elementos com Soma = k

## Objetivo
Implementar e analisar três soluções para o problema: verificar se um array contém dois elementos cuja soma é igual a um valor k.

## Visão Geral das Soluções Prioritárias
1. Força Bruta (O(n²))
   - Percorre todos os pares possíveis de elementos.
   - Simples de implementar e direta.
   - Usar apenas para arrays pequenos ou validação inicial.

2. Hash Map (O(n))
   - Armazena elementos já vistos em um Set (ou Map).
   - Checa se o complemento (k - num) já foi visto;
   - Melhor performance para workloads gerais (n linear).

3. Two Pointers (O(n log n))
   - Ordena o array e usa ponteiros em ambas extremidades.
   - Excelente tradeoff sem memórias extras grandes.
   - Ideal para quando ordenação já estiver dada ou aceitável.

> Dica: A sequência recomendada de priorização para uso prático é:
> 1) Hash Map, 2) Two Pointers, 3) Força Bruta (apenas para casos pequenos ou compreensão).

## Estrutura do Projeto

```
├── solucoes/
│   └── SolucoesAnalise.java      # Implementação das 4 soluções
├── testes/
│   ├── Testes.java               # Testes de validação
│   ├── Benchmark.java            # Medições de performance
│   ├── visualizar.py             # Geração de gráficos
│   └── benchmark_crescimento.csv # Dados gerados pelo Benchmark
├── graficos/
│   ├── 01_crescimento_tamanho.png
│   ├── 02_escala_linear.png
│   └── 04_complexidade_teorica.png
└── relatorio/
    └── RELATORIO.md              # Análise completa
```

## Soluções Implementadas

### 1. Força Bruta (O(n²))
- **Conceito:** Verifica todos os pares de elementos
- **Tempo:** O(n²)
- **Espaço:** O(1)
- **Vantagens:** Sem memória adicional
- **Desvantagens:** Muito lento para arrays grandes

### 2. Hash Map (O(n))
- **Conceito:** Usa Set para armazenar elementos visitados
- **Tempo:** O(n)
- **Espaço:** O(n)
- **Vantagens:** Tempo linear, mais rápido
- **Desvantagens:** Usa O(n) de memória

### 3. Two Pointers (O(n log n))
- **Conceito:** Ordena e usa ponteiros nas extremidades
- **Tempo:** O(n log n)
- **Espaço:** O(n)
- **Vantagens:** Elegante, bom balanço
- **Desvantagens:** Requer ordenação

### 4. Busca Binária (O(n log n))
- **Conceito:** Para cada elemento, busca binária do complemento
- **Tempo:** O(n log n)
- **Espaço:** O(n)
- **Nota:** Similar ao Two Pointers em performance

## Como Compilar e Executar

### Pré-requisitos
- Java 8 ou superior
- Python 3 (opcional, para geração de gráficos)
- Biblioteca Python: matplotlib (opcional)
- Nenhuma outra dependência (apenas Java / Python para gráficos)

### Compilação Java

```bash
# Navegar para a pasta testes
cd testes

# Compilar todas as classes
javac ..\solucoes\SolucoesAnalise.java Testes.java Benchmark.java
```

### Execução dos Testes de Corretude

```bash
# Executa validação do algoritmo
java Testes
```

Saída esperada: PASS em todas as soluções e mensagem final `TODOS OS TESTES CONCLUÍDOS COM SUCESSO!`

### Execução do Benchmark

```bash
# Executa benchmark e gera benchmark_crescimento.csv
java Benchmark
```

### Geração de Gráficos (opcional) - Python

```bash
# Se estiver na raiz do projeto, entre em testes
cd testes

# Instale matplotlib se necessário
pip install matplotlib

# Gere os gráficos a partir do CSV de benchmark
python visualizar.py
```

Arquivos gerados:
- `graficos/01_crescimento_tamanho.png`
- `graficos/02_escala_linear.png`
- `graficos/04_complexidade_teorica.png`

### Visualização dos Gráficos

No Windows, abra com Explorer / duplo clique ou use:

```bash
start graficos\01_crescimento_tamanho.png
start graficos\02_escala_linear.png
start graficos\04_complexidade_teorica.png
```

### Observações

- O código principal é Java; Python é apenas para plotagem de gráficos (arquivo `testes/visualizar.py`).
- Se não quiser usar Python, abra o `testes/benchmark_crescimento.csv` em Excel/LibreOffice e gere os gráficos manualmente.

### Executar Testes

```bash
# Rodar testes de validação
java Testes
```

**Saída esperada:**
```
===============================================================================
TESTES BÁSICOS - Validação de Corretude
===============================================================================

Testando tamanho: 100 elementos
  ✓ Força Bruta: PASS
  ✓ Hash Map: PASS
  ✓ Two Pointers: PASS
  ✓ Busca Binária: PASS
...
```

### Executar Benchmarks

```bash
# Rodar medições de performance
java Benchmark
```

**Saída esperada:**
```
================================================================================
BENCHMARK: Crescimento de Tamanho de Entrada
================================================================================

Testando tamanho: 100 elementos
  Força Bruta: 0ms
  Hash Map: 0ms
  Two Pointers: 1ms
  Busca Binária: 1ms

Testando tamanho: 200 elementos
...
```

Este comando gera o arquivo `benchmark_crescimento.csv` com os dados.

## Visualizar Dados

O arquivo CSV pode ser importado em qualquer ferramenta de visualização:
- **Excel:** Abrir arquivo e criar gráficos
- **Google Sheets:** Upload e criar gráficos
- **Outras ferramentas:** Tableau, Power BI, etc

## Análise de Complexidade

### Força Bruta
```
Tempo: O(n²)
- For i=1 até n: O(n)
  - For j=i+1 até n: O(n)
    - Comparação: O(1)
- Total: O(n) × O(n) = O(n²)

Espaço: O(1) - apenas variáveis de iteração
```

### Hash Map
```
Tempo: O(n)
- For cada elemento: O(n)
  - Consulta em Set: O(1) em média
  - Inserção em Set: O(1) em média
- Total: O(n) × O(1) = O(n)

Espaço: O(n) - armazena até n elementos no Set
```

### Two Pointers
```
Tempo: O(n log n)
- Sort: O(n log n)
- Two pointers: O(n)
- Total: O(n log n) + O(n) = O(n log n)

Espaço: O(n) - cópia do array para ordenação
```

### Busca Binária
```
Tempo: O(n log n)
- Sort: O(n log n)
- For cada elemento: O(n)
  - Busca binária: O(log n)
- Total: O(n log n) + O(n × log n) = O(n log n)

Espaço: O(n) - cópia do array
```

## Comparação Teórica

| Solução | Tempo | Espaço | Melhor Caso | Pior Caso | Observações |
|---------|-------|--------|------------|-----------|------------|
| Força Bruta | O(n²) | O(1) | O(n²) | O(n²) | Mais lenta em geral |
| Hash Map | O(n) | O(n) | O(n) | O(n) | **Mais rápida** |
| Two Pointers | O(n log n) | O(n) | O(n log n) | O(n log n) | Requer array ordenado |
| Busca Binária | O(n log n) | O(n) | O(n log n) | O(n log n) | Similar a Two Pointers |

## Efeito da Ordenação

### Arrays Desordenados
- **Força Bruta & Hash Map:** Sem diferença
- **Two Pointers:** Precisa de ordenação (O(n log n))
- **Busca Binária:** Precisa de ordenação (O(n log n))

### Arrays Ordenados
- **Força Bruta & Hash Map:** Sem benefício
- **Two Pointers:** Usa diretamente sem sort adicional
- **Busca Binária:** Usa diretamente sem sort adicional

**Conclusão:** Para arrays já ordenados, Two Pointers e Busca Binária são mais eficientes.

## Resultados dos Benchmarks

### Crescimento de Tamanho
Os benchmarks medem o tempo de execução para arrays de diferentes tamanhos:

| Tamanho | Força Bruta (ms) | Hash Map (ms) | Two Pointers (ms) | Busca Binária (ms) |
|---------|------------------|---------------|--------------------|-------------------|
| 100 | < 1 | < 1 | < 1 | < 1 |
| 1000 | ~10 | 1 | 2 | 2 |
| 10000 | ~1000 | 5 | 8 | 8 |
| 50000 | - (timeout) | 30 | 45 | 50 |

**Nota:** Hash Map é a solução mais rápida em geral.

### Casos Especiais (10.000 elementos)

#### Array Desordenado
- Hash Map: ~5ms (mais rápido)
- Two Pointers: ~8ms
- Busca Binária: ~8ms

#### Array Ordenado
- Hash Map: ~5ms (sem benefício)
- Two Pointers: ~7ms (sem custo de sort)
- Busca Binária: ~7ms (sem custo de sort)

#### Array com Duplicatas
- Hash Map: ~5ms (sem problema)
- Two Pointers: ~8ms (processa duplicatas)
- Busca Binária: ~8ms (processa duplicatas)

## Insights e Conclusões

### 1. Hash Map é a Melhor Solução Geral
- Tempo linear O(n): mais rápida para todos os tamanhos
- Funciona igualmente bem com arrays ordenados ou desordenados
- Recomendado para problemas do "two sum" em produção

### 2. Two Pointers é Bom para Arrays Ordenados
- Se o array já está ordenado, Two Pointers é competitivo
- Usa menos memória que Hash Map (mais previsível)
- Bom balanço entre tempo e espaço

### 3. Força Bruta é Inviável
- Cresce rapidamente (O(n²))
- Não é prático para n > 1000
- Útil apenas para fins educacionais

### 4. Efeito da Ordenação
- Arrays já ordenados podem usar Two Pointers/Busca Binária diretamente
- Elimina o custo de O(n log n) de ordenação
- Hash Map não se beneficia de ordenação

### 5. Espaço vs Tempo
- Hash Map: O(n) tempo, O(n) espaço (melhor custo-benefício)
- Two Pointers: O(n log n) tempo, O(n) espaço (menos memória que Hash Map se sem sort)
- Força Bruta: O(n²) tempo, O(1) espaço (pior opção)

## Testes Implementados

### Testes Básicos
- Array com elementos positivos e negativos
- Array pequeno
- Array grande (1000 elementos)
- Arrays com zeros
- Arrays com duplicatas
- Casos onde não existe solução

### Casos Especiais
- Array vazio
- Array com um único elemento
- Array com muitas duplicatas
- Array com números negativos
- Arrays muito grandes (100.000 elementos)

### Verificação de Ordenação
- Testa ambas as versões (ordenado e desordenado)
- Verifica se as soluções funcionam independente de ordenação
- Mede diferença de performance

## Arquivos Gerados

1. **SolucoesAnalise.java**: Implementação das 4 soluções
2. **Testes.java**: 4 conjuntos de testes com casos especiais
3. **Benchmark.java**: Medições automáticas de performance
4. **benchmark_crescimento.csv**: Dados brutos das medições (importar no Excel)

## Referências

- Big O Notation: https://en.wikipedia.org/wiki/Big_O_notation
- Two Sum Problem: https://leetcode.com/problems/two-sum/
- Hash Tables: https://en.wikipedia.org/wiki/Hash_table
- Binary Search: https://en.wikipedia.org/wiki/Binary_search_algorithm

---

**Data:** Março 2026  
**Linguagem:** Java + Python  
**Prazo:** 24/03 até 23h59
