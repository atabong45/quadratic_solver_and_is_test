/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp2;

/**
 *
 * @author atabong
 */
/**
 * Interface définissant les méthodes pour résoudre des équations du second degré.
 * L'équation est de la forme ax² + bx + c = 0
 */
public interface QuadraticSolver {
    
    /**
     * Énumération des types de solutions possibles pour une équation du second degré
     */
    enum SolutionType {
        TWO_REAL_SOLUTIONS,      // Deux solutions réelles distinctes
        ONE_REAL_SOLUTION,       // Une solution réelle (racine double)
        NO_REAL_SOLUTION,        // Pas de solution réelle (solutions complexes)
        LINEAR_SOLUTION,         // Solution linéaire (a=0, b≠0)
        INFINITE_SOLUTIONS,      // Infinité de solutions (a=0, b=0, c=0)
        NO_SOLUTION              // Pas de solution (a=0, b=0, c≠0)
    }
    
    /**
     * Classe représentant le résultat de la résolution d'une équation du second degré
     */
    class QuadraticResult {
        private final SolutionType type;
        private final double[] solutions;
        
        public QuadraticResult(SolutionType type, double... solutions) {
            this.type = type;
            this.solutions = solutions;
        }
        
        public SolutionType getType() {
            return type;
        }
        
        public double[] getSolutions() {
            return solutions;
        }
        
        public int getNumberOfSolutions() {
            switch (type) {
                case TWO_REAL_SOLUTIONS:
                    return 2;
                case ONE_REAL_SOLUTION:
                case LINEAR_SOLUTION:
                    return 1;
                case INFINITE_SOLUTIONS:
                    return Integer.MAX_VALUE;
                case NO_REAL_SOLUTION:
                case NO_SOLUTION:
                default:
                    return 0;
            }
        }
    }
    
    /**
     * Résout l'équation du second degré ax² + bx + c = 0
     * 
     * @param a coefficient du terme x²
     * @param b coefficient du terme x
     * @param c terme constant
     * @return un objet QuadraticResult contenant le type de solution et les valeurs des solutions si elles existent
     */
    QuadraticResult solve(double a, double b, double c);
}

