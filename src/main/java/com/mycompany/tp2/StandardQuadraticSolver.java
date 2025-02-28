/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2;

/**
 *
 * @author atabong
 */
/**
 * Implémentation de l'interface QuadraticSolver pour résoudre les équations du second degré
 */
public class StandardQuadraticSolver implements QuadraticSolver {
    
    // Constante pour la comparaison des nombres à virgule flottante
    private static final double EPSILON = 1e-10;
    
    /**
     * Détermine si un nombre double est suffisamment proche de zéro pour être considéré comme nul
     */
    private boolean isZero(double value) {
        return Math.abs(value) < EPSILON;
    }
    
    @Override
    public QuadraticResult solve(double a, double b, double c) {
        // Cas où a est nul (équation linéaire ou cas particulier)
        if (isZero(a)) {
            return solveLinearCase(b, c);
        }
        
        // Calcul du discriminant
        double discriminant = b * b - 4 * a * c;
        
        // Cas où le discriminant est proche de zéro (racine double)
        if (isZero(discriminant)) {
            double solution = -b / (2 * a);
            return new QuadraticResult(SolutionType.ONE_REAL_SOLUTION, solution);
        }
        
        // Cas où le discriminant est positif (deux racines réelles)
        if (discriminant > 0) {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            
            // Utilisation d'une formule numériquement stable pour éviter les erreurs d'annulation
            double x1, x2;
            if (b >= 0) {
                x1 = (-b - sqrtDiscriminant) / (2 * a);
                x2 = (2 * c) / (-b - sqrtDiscriminant);
            } else {
                x1 = (2 * c) / (-b + sqrtDiscriminant);
                x2 = (-b + sqrtDiscriminant) / (2 * a);
            }
            
            return new QuadraticResult(SolutionType.TWO_REAL_SOLUTIONS, x1, x2);
        }
        
        // Cas où le discriminant est négatif (pas de solution réelle)
        return new QuadraticResult(SolutionType.NO_REAL_SOLUTION);
    }
    
    /**
     * Résout le cas où a = 0, ce qui donne une équation linéaire bx + c = 0 ou des cas particuliers
     */
    private QuadraticResult solveLinearCase(double b, double c) {
        // Cas où b est aussi nul
        if (isZero(b)) {
            // Si c est nul, infinité de solutions
            if (isZero(c)) {
                return new QuadraticResult(SolutionType.INFINITE_SOLUTIONS);
            } 
            // Si c n'est pas nul, pas de solution
            else {
                return new QuadraticResult(SolutionType.NO_SOLUTION);
            }
        }
        
        // Cas d'une équation linéaire standard: bx + c = 0
        double solution = -c / b;
        return new QuadraticResult(SolutionType.LINEAR_SOLUTION, solution);
    }
}
