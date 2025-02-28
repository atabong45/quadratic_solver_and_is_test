package com.mycompany.tp2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests améliorés pour le résolveur d'équations quadratiques avec messages d'erreur personnalisés
 * et validation des solutions par évaluation du polynôme
 */
public class EnhancedQuadraticSolverTest {
    
    private final QuadraticSolver solver = new StandardQuadraticSolver();
    private static final double TOLERANCE = 1e-6;
    
    /**
     * Méthode de test générique qui vérifie une équation et affiche un message personnalisé en cas d'échec
     */
    private void testQuadraticCase(double a, double b, double c, QuadraticSolver.SolutionType expectedType, String testDescription) {
        QuadraticSolver.QuadraticResult result = solver.solve(a, b, c);
        
        // Utilise la classe PolynomialValidator pour vérifier le résultat
        String errorMessage = PolynomialValidator.validateQuadraticResult(a, b, c, result, expectedType, TOLERANCE);
        
        // Si un message d'erreur est retourné, le test échoue avec ce message personnalisé
        if (errorMessage != null) {
            fail(testDescription + ": " + errorMessage);
        }
        
        // Vérifications supplémentaires pour les cas spécifiques
        if (expectedType == QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS) {
            assertEquals("Le nombre de solutions doit être 2", 2, result.getNumberOfSolutions());
        } else if (expectedType == QuadraticSolver.SolutionType.ONE_REAL_SOLUTION 
                || expectedType == QuadraticSolver.SolutionType.LINEAR_SOLUTION) {
            assertEquals("Le nombre de solutions doit être 1", 1, result.getNumberOfSolutions());
        } else if (expectedType == QuadraticSolver.SolutionType.NO_REAL_SOLUTION 
                || expectedType == QuadraticSolver.SolutionType.NO_SOLUTION) {
            assertEquals("Le nombre de solutions doit être 0", 0, result.getNumberOfSolutions());
        }
    }

    // === Deux racines réelles distinctes === (28 cas)
    @Test
    public void testCase1() {
        testQuadraticCase(1.00e-06, 1.00e+08, -1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 1: a=1.00e-06, b=1.00e+08, c=-1.00e+08");
    }

    @Test
    public void testCase2() {
        testQuadraticCase(-1.00e-06, 1.00e+02, 1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 2: a=-1.00e-06, b=1.00e+02, c=1.00e-06");
    }
    
    @Test
    public void testCase3() {
        testQuadraticCase(-1.00e+02, -1.00e+02, -1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 3: a=-1.00e+02, b=-1.00e+02, c=-1.00e-06");
    }
    
    @Test
    public void testCase4() {
        testQuadraticCase(-1.00e+08, -1.00e+08, -1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 4: a=-1.00e+08, b=-1.00e+08, c=-1.00e+02");
    }
    
    @Test
    public void testCase5() {
        testQuadraticCase(1.00e+08, 1.00e+08, -1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 5: a=1.00e+08, b=1.00e+08, c=-1.00e+02");
    }
    
    @Test
    public void testCase6() {
        testQuadraticCase(1.00e+02, 1.00e+08, -1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 6: a=1.00e+02, b=1.00e+08, c=-1.00e-06");
    }
    
    @Test
    public void testCase7() {
        testQuadraticCase(1.00e-06, 1.00e+02, 1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 7: a=1.00e-06, b=1.00e+02, c=1.00e+02");
    }
    
    @Test
    public void testCase8() {
        testQuadraticCase(-1.00e-06, 1.00e+08, 1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 8: a=-1.00e-06, b=1.00e+08, c=1.00e+02");
    }
    
    @Test
    public void testCase9() {
        testQuadraticCase(-1.00e+02, 1.00e+08, 1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 9: a=-1.00e+02, b=1.00e+08, c=1.00e-06");
    }
    
    @Test
    public void testCase10() {
        testQuadraticCase(-1.00e+08, 1.00e+08, 1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 10: a=-1.00e+08, b=1.00e+08, c=1.00e+08");
    }
    
    @Test
    public void testCase11() {
        testQuadraticCase(1.00e+08, -1.00e-06, -1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 11: a=1.00e+08, b=-1.00e-06, c=-1.00e+08");
    }
    
    @Test
    public void testCase12() {
        testQuadraticCase(1.00e+08, -1.00e+08, -1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 12: a=1.00e+08, b=-1.00e+08, c=-1.00e-06");
    }
    
    @Test
    public void testCase13() {
        testQuadraticCase(1.00e-06, -1.00e-06, -1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 13: a=1.00e-06, b=-1.00e-06, c=-1.00e+02");
    }
    
    @Test
    public void testCase14() {
        testQuadraticCase(1.00e+02, -1.00e+08, -1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 14: a=1.00e+02, b=-1.00e+08, c=-1.00e+08");
    }
    
    @Test
    public void testCase15() {
        testQuadraticCase(1.00e+08, 1.00e+02, 0.00e+00, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 15: a=1.00e+08, b=1.00e+02, c=0.00e+00");
    }
    
    @Test
    public void testCase16() {
        testQuadraticCase(-1.00e-06, -1.00e+02, 0.00e+00, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 16: a=-1.00e-06, b=-1.00e+02, c=0.00e+00");
    }
    
    @Test
    public void testCase17() {
        testQuadraticCase(-1.00e-06, -1.00e+08, 0.00e+00, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 17: a=-1.00e-06, b=-1.00e+08, c=0.00e+00");
    }
    
    @Test
    public void testCase18() {
        testQuadraticCase(-1.00e-06, -1.00e-06, 1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 18: a=-1.00e-06, b=-1.00e-06, c=1.00e+08");
    }
    
    @Test
    public void testCase19() {
        testQuadraticCase(-1.00e+02, -1.00e+08, 1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 19: a=-1.00e+02, b=-1.00e+08, c=1.00e+02");
    }
    
    @Test
    public void testCase20() {
        testQuadraticCase(-1.00e+08, -1.00e-06, 1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 20: a=-1.00e+08, b=-1.00e-06, c=1.00e+02");
    }
    
    @Test
    public void testCase21() {
        testQuadraticCase(-1.00e-06, 1.00e+02, -1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 21: a=-1.00e-06, b=1.00e+02, c=-1.00e+02");
    }
    
    @Test
    public void testCase22() {
        testQuadraticCase(-1.00e+02, -1.00e-06, 1.00e+08, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 22: a=-1.00e+02, b=-1.00e-06, c=1.00e+08");
    }
    
    @Test
    public void testCase23() {
        testQuadraticCase(-1.00e+08, 1.00e+02, 1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 23: a=-1.00e+08, b=1.00e+02, c=1.00e-06");
    }
    
    @Test
    public void testCase24() {
        testQuadraticCase(1.00e-06, -1.00e+02, 1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 24: a=1.00e-06, b=-1.00e+02, c=1.00e-06");
    }
    
    @Test
    public void testCase25() {
        testQuadraticCase(1.00e+02, 1.00e-06, -1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 25: a=1.00e+02, b=1.00e-06, c=-1.00e+02");
    }
    
    @Test
    public void testCase26() {
        testQuadraticCase(-1.00e+08, 1.00e+02, -1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 26: a=-1.00e+08, b=1.00e+02, c=-1.00e-06");
    }
    
    @Test
    public void testCase27() {
        testQuadraticCase(1.00e-06, -1.00e+08, 1.00e-06, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 27: a=1.00e-06, b=-1.00e+08, c=1.00e-06");
    }
    
    @Test
    public void testCase28() {
        testQuadraticCase(-1.00e+08, 1.00e-06, 1.00e+02, QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, "Cas 28: a=-1.00e+08, b=1.00e-06, c=1.00e+02");
    }
    
    // === Une racine double === (6 cas)
    @Test
    public void testCase29() {
        testQuadraticCase(1.00e+02, -1.00e-06, 0.00e+00, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 1: a=1.00e+02, b=-1.00e-06, c=0.00e+00");
    }
    
    @Test
    public void testCase30() {
        testQuadraticCase(1.00e-06, 0.00e+00, 0.00e+00, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 2: a=1.00e-06, b=0.00e+00, c=0.00e+00");
    }
    
    @Test
    public void testCase31() {
        testQuadraticCase(-1.00e-06, 0.00e+00, -1.00e-06, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 3: a=-1.00e-06, b=0.00e+00, c=-1.00e-06");
    }
    
    @Test
    public void testCase32() {
        testQuadraticCase(-1.00e+02, 1.00e-06, 0.00e+00, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 4: a=-1.00e+02, b=1.00e-06, c=0.00e+00");
    }
    
    @Test
    public void testCase33() {
        testQuadraticCase(-1.00e+08, 0.00e+00, 0.00e+00, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 5: a=-1.00e+08, b=0.00e+00, c=0.00e+00");
    }
    
    @Test
    public void testCase34() {
        testQuadraticCase(1.00e-06, 1.00e-06, -1.00e-06, QuadraticSolver.SolutionType.ONE_REAL_SOLUTION, "Cas 6: a=1.00e-06, b=1.00e-06, c=-1.00e-06");
    }
    
    // === Pas de racine réelle (deux racines complexes) === (12 cas)
    @Test
    public void testCase35() {
        testQuadraticCase(1.00e+08, 0.00e+00, 1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 1: a=1.00e+08, b=0.00e+00, c=1.00e+08");
    }
    
    @Test
    public void testCase36() {
        testQuadraticCase(1.00e+08, 1.00e-06, 1.00e-06, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 2: a=1.00e+08, b=1.00e-06, c=1.00e-06");
    }
    
    @Test
    public void testCase37() {
        testQuadraticCase(1.00e+02, 0.00e+00, 1.00e-06, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 3: a=1.00e+02, b=0.00e+00, c=1.00e-06");
    }
    
    @Test
    public void testCase38() {
        testQuadraticCase(1.00e+08, -1.00e+02, 1.00e+02, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 4: a=1.00e+08, b=-1.00e+02, c=1.00e+02");
    }
    
    @Test
    public void testCase39() {
        testQuadraticCase(-1.00e-06, 1.00e-06, -1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 5: a=-1.00e-06, b=1.00e-06, c=-1.00e+08");
    }
    
    @Test
    public void testCase40() {
        testQuadraticCase(-1.00e+02, 1.00e+02, -1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 6: a=-1.00e+02, b=1.00e+02, c=-1.00e+08");
    }
    
    @Test
    public void testCase41() {
        testQuadraticCase(-1.00e+08, -1.00e+02, -1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 7: a=-1.00e+08, b=-1.00e+02, c=-1.00e+08");
    }
    
    @Test
    public void testCase42() {
        testQuadraticCase(1.00e+02, 1.00e+02, 1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 8: a=1.00e+02, b=1.00e+02, c=1.00e+08");
    }
    
    @Test
    public void testCase43() {
        testQuadraticCase(1.00e-06, 1.00e-06, 1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 9: a=1.00e-06, b=1.00e-06, c=1.00e+08");
    }
    
    @Test
    public void testCase44() {
        testQuadraticCase(1.00e+02, 0.00e+00, 1.00e+02, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 10: a=1.00e+02, b=0.00e+00, c=1.00e+02");
    }
    
    @Test
    public void testCase45() {
        testQuadraticCase(-1.00e+02, 0.00e+00, -1.00e+02, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 11: a=-1.00e+02, b=0.00e+00, c=-1.00e+02");
    }
    
    @Test
    public void testCase46() {
        testQuadraticCase(1.00e+02, -1.00e+02, 1.00e+08, QuadraticSolver.SolutionType.NO_REAL_SOLUTION, "Cas 12: a=1.00e+02, b=-1.00e+02, c=1.00e+08");
    }
    
    // === Une solution (équation linéaire) === (7 cas)
    @Test
    public void testCase47() {
        testQuadraticCase(0.00e+00, 1.00e-06, 1.00e+02, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 1: a=0.00e+00, b=1.00e-06, c=1.00e+02");
    }
    
    @Test
    public void testCase48() {
        testQuadraticCase(0.00e+00, 1.00e+08, 0.00e+00, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 2: a=0.00e+00, b=1.00e+08, c=0.00e+00");
    }
    
    @Test
    public void testCase49() {
        testQuadraticCase(0.00e+00, -1.00e-06, -1.00e-06, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 3: a=0.00e+00, b=-1.00e-06, c=-1.00e-06");
    }
    
    @Test
    public void testCase50() {
        testQuadraticCase(0.00e+00, -1.00e+02, -1.00e+02, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 4: a=0.00e+00, b=-1.00e+02, c=-1.00e+02");
    }
    
    @Test
    public void testCase51() {
        testQuadraticCase(0.00e+00, -1.00e-06, 1.00e-06, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 5: a=0.00e+00, b=-1.00e-06, c=1.00e-06");
    }
    
    @Test
    public void testCase52() {
        testQuadraticCase(0.00e+00, -1.00e+08, 1.00e+08, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 6: a=0.00e+00, b=-1.00e+08, c=1.00e+08");
    }
    
    @Test
    public void testCase53() {
        testQuadraticCase(0.00e+00, 1.00e+02, -1.00e+02, QuadraticSolver.SolutionType.LINEAR_SOLUTION, "Cas 7: a=0.00e+00, b=1.00e+02, c=-1.00e+02");
    }
    
    // === Pas de solution (équation c = 0 où c ≠ 0) === (1 cas)
    @Test
    public void testCase54() {
        testQuadraticCase(0.00e+00, 0.00e+00, -1.00e+08, QuadraticSolver.SolutionType.NO_SOLUTION, "Cas 1: a=0.00e+00, b=0.00e+00, c=-1.00e+08");
    }
    
    // === Infinité de solutions (équation 0 = 0) === (1 cas supplémentaire pour compléter à 55)
    @Test
    public void testCase55() {
        testQuadraticCase(0.00e+00, 0.00e+00, 0.00e+00, QuadraticSolver.SolutionType.INFINITE_SOLUTIONS, "Cas supplémentaire: a=0.00e+00, b=0.00e+00, c=0.00e+00");
    }
    

    @Test
    public void testDirectPolynomialEvaluation() {
        // Test direct de la fonction d'évaluation du polynôme
        double a = 1.0, b = -5.0, c = 6.0; // x² - 5x + 6 = 0, solutions: 2 et 3
        
        QuadraticSolver.QuadraticResult result = solver.solve(a, b, c);
        assertEquals(QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS, result.getType());
        double[] solutions = result.getSolutions();
        
        // Vérifie directement que f(x1) et f(x2) sont proches de zéro
        for (int i = 0; i < solutions.length; i++) {
            double evaluation = PolynomialValidator.evaluateQuadratic(a, b, c, solutions[i]);
            String message = String.format("La solution x%d = %.6g doit donner une valeur proche de zéro", i+1, solutions[i]);
            assertTrue(message, Math.abs(evaluation) < TOLERANCE);
        }
    }
}