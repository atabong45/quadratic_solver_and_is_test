/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2;

/**
 *
 * @author atabong
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class QuadraticSolverTest {
    
    private final QuadraticSolver solver = new StandardQuadraticSolver();
    private static final double DELTA = 1e-9; // Précision pour les comparaisons de nombres à virgule flottante
    
    @Test
    public void testTwoRealSolutions() {
        // Cas standard: deux racines distinctes
        QuadraticSolver.QuadraticResult result = solver.solve(1.0, -3.0, 2.0); // x² - 3x + 2 = 0, solutions: 1 et 2
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
        assertEquals(2, result.getNumberOfSolutions());
        assertArrayEquals(new double[]{1.0, 2.0}, result.getSolutions(), DELTA);
        
        // Cas avec coefficient négatif: -x² + 4x - 3 = 0
        result = solver.solve(-1.0, 4.0, -3.0);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
        assertEquals(2, result.getNumberOfSolutions());
        assertArrayEquals(new double[]{1.0, 3.0}, result.getSolutions(), DELTA);
        
        // Cas avec valeurs très grandes
        result = solver.solve(1e8, 2e8, 1.0);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
        
        // Cas avec coefficients très petits
        result = solver.solve(1e-6, 3e-6, 1e-6);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
    }
    
    @Test
    public void testOneRealSolution() {
        // Racine double: x² - 2x + 1 = 0, solution: x = 1
        QuadraticSolver.QuadraticResult result = solver.solve(1.0, -2.0, 1.0);
        assertEquals(QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, result.getType());
        assertEquals(1, result.getNumberOfSolutions());
        assertEquals(1.0, result.getSolutions()[0], DELTA);
        
        // Autre cas: 4x² - 12x + 9 = 0, solution: x = 1.5
        result = solver.solve(4.0, -12.0, 9.0);
        assertEquals(QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, result.getType());
        assertEquals(1.5, result.getSolutions()[0], DELTA);
    }
    
    @Test
    public void testNoRealSolution() {
        // x² + 1 = 0 (pas de solution réelle)
        QuadraticSolver.QuadraticResult result = solver.solve(1.0, 0.0, 1.0);
        assertEquals(QuadraticSolver.SolutionType.NO_REAL_SOLUTION, result.getType());
        assertEquals(0, result.getNumberOfSolutions());
        
        // x² + 2x + 5 = 0 (pas de solution réelle)
        result = solver.solve(1.0, 2.0, 5.0);
        assertEquals(QuadraticSolver.SolutionType.NO_REAL_SOLUTION, result.getType());
    }
    
    @Test
    public void testLinearSolution() {
        // 0x² + 2x + 4 = 0 => 2x = -4 => x = -2
        QuadraticSolver.QuadraticResult result = solver.solve(0.0, 2.0, 4.0);
        assertEquals(QuadraticSolver.SolutionType.LINEAR_SOLUTION, result.getType());
        assertEquals(1, result.getNumberOfSolutions());
        assertEquals(-2.0, result.getSolutions()[0], DELTA);
        
        // 0x² - 3x + 6 = 0 => -3x = -6 => x = 2
        result = solver.solve(0.0, -3.0, 6.0);
        assertEquals(QuadraticSolver.SolutionType.LINEAR_SOLUTION, result.getType());
        assertEquals(2.0, result.getSolutions()[0], DELTA);
    }
    
    @Test
    public void testInfiniteSolutions() {
        // 0x² + 0x + 0 = 0 (infinité de solutions)
        QuadraticSolver.QuadraticResult result = solver.solve(0.0, 0.0, 0.0);
        assertEquals(QuadraticSolver.SolutionType.INFINITE_SOLUTIONS, result.getType());
        assertEquals(Integer.MAX_VALUE, result.getNumberOfSolutions());
    }
    
    @Test
    public void testNoSolution() {
        // 0x² + 0x + 5 = 0 (pas de solution)
        QuadraticSolver.QuadraticResult result = solver.solve(0.0, 0.0, 5.0);
        assertEquals(QuadraticSolver.SolutionType.NO_SOLUTION, result.getType());
        assertEquals(0, result.getNumberOfSolutions());
    }
    
    @Test
    public void testNumericalStability() {
        // Test avec des valeurs qui pourraient causer des problèmes numériques
        QuadraticSolver.QuadraticResult result = solver.solve(1e-10, 1.0, 1e-10);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
        
        // Test avec des coefficients très proches mais pas identiques
        result = solver.solve(1.0, 2.0, 1.0 + 1e-12);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
    }
    
    @Test
    public void testPairwiseScenarios() {
        // Quelques cas issus du test pairwise
        testSinglePairwiseCase(1e8, 0.0, 1e8, QuadraticSolver.SolutionType.NO_REAL_SOLUTION);
        testSinglePairwiseCase(100.0, -1e-6, 0.0, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS);
        testSinglePairwiseCase(1e-6, 1e8, -1e8, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS);
        testSinglePairwiseCase(0.0, 1e-6, 100.0, QuadraticSolver.SolutionType.LINEAR_SOLUTION);
        testSinglePairwiseCase(-1e-6, 100.0, 1e-6, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS);
        testSinglePairwiseCase(0.0, 0.0, 0.0, QuadraticSolver.SolutionType.INFINITE_SOLUTIONS);
    }
    
    private void testSinglePairwiseCase(double a, double b, double c, QuadraticSolver.SolutionType expectedType) {
        QuadraticSolver.QuadraticResult result = solver.solve(a, b, c);
        assertEquals("Pour a=" + a + ", b=" + b + ", c=" + c, expectedType, result.getType());
    }
}
