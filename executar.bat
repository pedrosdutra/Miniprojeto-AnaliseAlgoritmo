@echo off
REM Script de Compilação e Execução do Projeto
REM Projeto: Análise de Algoritmos - Two Sum Problem

setlocal enabledelayedexpansion

echo.
echo ===============================================================================
echo PROJETO: Análise de Algoritmos - Two Sum Problem
echo ===============================================================================
echo.

cd /d "%~dp0"  

rem Compila a classe de solução que está em solucoes/ e os testes que estão em testes/
cd testes

echo [1/3] Compilando arquivos Java...
echo.

javac -d . "..\solucoes\SolucoesAnalise.java" "Testes.java" "Benchmark.java"

if errorlevel 1 (
    echo.
    echo ERRO: Falha na compilaçao!
    echo Verifique se Java está instalado e no PATH.
    echo.
    pause
    exit /b 1
)

echo.
echo [SUCESSO] Compilação concluída!
echo.

echo ===============================================================================
echo [2/3] Executando Testes de Validação...
echo ===============================================================================
echo.

java -cp . Testes

echo.
echo [SUCESSO] Testes concluídos!
echo.

echo ===============================================================================
echo [3/3] Executando Benchmarks (pode levar alguns minutos)...
echo ===============================================================================
echo.

java Benchmark

echo.
echo [SUCESSO] Benchmarks concluídos!
echo.
echo Os dados foram salvos em: benchmark_crescimento.csv
echo Você pode importar este arquivo no Excel para visualizar gráficos.
echo.

echo ===============================================================================
echo PROJETO CONCLUÍDO COM SUCESSO!
echo ===============================================================================
echo.
echo Arquivos gerados:
echo   - Código-fonte: SolucoesAnalise.java, Testes.java, Benchmark.java
echo   - Dados: benchmark_crescimento.csv (importar no Excel)
echo   - Relatório: ../relatorio/RELATORIO.md
echo   - Documentação: ../README.md
echo.
echo Para visualizar gráficos:
echo   - Abra a pasta testes e logo depois graficos e lá estará em png os gráficos de crescimento.
echo   - Abra o Excel, importe benchmark_crescimento.csv na pasta testes para visualizar o crescimento dos tempos de execução.
echo.

pause
