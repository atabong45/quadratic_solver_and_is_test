package com.mycompany.tp2;

/**
 * Classe utilitaire pour valider les solutions d'équations polynomiales
 */
public class PolynomialValidator {
    
    // Seuil de tolérance par défaut pour considérer une valeur comme "zéro"
    private static final double DEFAULT_TOLERANCE = 1e-9;
    
    /**
     * Calcule la valeur du polynôme ax² + bx + c pour une valeur x donnée
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @param x valeur à évaluer
     * @return la valeur du polynôme
     */
    public static double evaluateQuadratic(double a, double b, double c, double x) {
        return a * x * x + b * x + c;
    }
    
    /**
     * Vérifie si une solution est valide pour un polynôme quadratique
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @param solution valeur à tester comme solution
     * @param tolerance seuil de tolérance
     * @return true si la solution est valide, false sinon
     */
    public static boolean isSolutionValid(double a, double b, double c, double solution, double tolerance) {
        double result = evaluateQuadratic(a, b, c, solution);
        return Math.abs(result) < tolerance;
    }
    
    /**
     * Vérifie si une solution est valide pour un polynôme quadratique avec la tolérance par défaut
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @param solution valeur à tester comme solution
     * @return true si la solution est valide, false sinon
     */
    public static boolean isSolutionValid(double a, double b, double c, double solution) {
        return isSolutionValid(a, b, c, solution, DEFAULT_TOLERANCE);
    }
    
    /**
     * Vérifie si le nombre de solutions retournées correspond au type de résultat attendu
     * 
     * @param result résultat de l'équation quadratique
     * @return true si le nombre de solutions correspond au type, false sinon
     */
    public static boolean validateNumberOfSolutions(QuadraticSolver.QuadraticResult result) {
        int expectedSolutions = 0;
        
        switch (result.getType()) {
            case TWO_REAL_SOLUTIONS:
                expectedSolutions = 2;
                break;
            case ONE_REAL_SOLUTION:
            case LINEAR_SOLUTION:
                expectedSolutions = 1;
                break;
            case INFINITE_SOLUTIONS:
                return true; // Cas spécial - pas besoin de vérifier le nombre exact
            case NO_REAL_SOLUTION:
            case NO_SOLUTION:
                expectedSolutions = 0;
                break;
        }
        
        return (result.getType() != QuadraticSolver.SolutionType.INFINITE_SOLUTIONS && 
                result.getSolutions().length == expectedSolutions);
    }
    
    /**
     * Vérifie complètement une solution quadratique
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @param result résultat retourné par le solveur
     * @param expectedType type de solution attendu
     * @param tolerance seuil de tolérance
     * @return message d'erreur ou null si tout est correct
     */
    public static String validateQuadraticResult(
            double a, double b, double c, 
            QuadraticSolver.QuadraticResult result, 
            QuadraticSolver.SolutionType expectedType,
            double tolerance) {
            
        // Vérifier si le type de solution correspond à ce qui est attendu
        if (result.getType() != expectedType) {
            return String.format(
                "Type de solution incorrect pour a=%.6g, b=%.6g, c=%.6g: attendu %s, obtenu %s",
                a, b, c, expectedType, result.getType());
        }
        
        // Vérifier si le nombre de solutions est cohérent avec le type
        if (!validateNumberOfSolutions(result)) {
            return String.format(
                "Nombre de solutions incorrect pour a=%.6g, b=%.6g, c=%.6g, type %s: attendu %d, obtenu %d",
                a, b, c, result.getType(), 
                result.getType() == QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS ? 2 : 
                (result.getType() == QuadraticSolver.SolutionType.ONE_REAL_SOLUTION || 
                 result.getType() == QuadraticSolver.SolutionType.LINEAR_SOLUTION ? 1 : 0),
                result.getSolutions().length);
        }
        
        // Vérifier chaque solution si applicable
        if (result.getType() == QuadraticSolver.SolutionType.TWO_REAL_SOLUTIONS || 
            result.getType() == QuadraticSolver.SolutionType.ONE_REAL_SOLUTION ||
            result.getType() == QuadraticSolver.SolutionType.LINEAR_SOLUTION) {
            
            for (int i = 0; i < result.getSolutions().length; i++) {
                double solution = result.getSolutions()[i];
                double evaluation = evaluateQuadratic(a, b, c, solution);
                
                if (Math.abs(evaluation) >= tolerance) {
                    return String.format(
                        "Solution invalide pour a=%.6g, b=%.6g, c=%.6g: x%d=%.9g donne f(x)=%.9g (devrait être proche de 0)",
                        a, b, c, i+1, solution, evaluation);
                }
            }
        }
        
        // Tout est correct
        return null;
    }
    
    /**
     * Vérifie complètement une solution quadratique avec la tolérance par défaut
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @param result résultat retourné par le solveur
     * @param expectedType type de solution attendu
     * @return message d'erreur ou null si tout est correct
     */
    public static String validateQuadraticResult(
            double a, double b, double c, 
            QuadraticSolver.QuadraticResult result, 
            QuadraticSolver.SolutionType expectedType) {
            
        return validateQuadraticResult(a, b, c, result, expectedType, DEFAULT_TOLERANCE);
    }
}