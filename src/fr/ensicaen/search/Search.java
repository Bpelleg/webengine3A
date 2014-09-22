/*
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by ENSICAEN students.
 * No portion of this code may be reproduced,
 * copied or revised without written permission
 * of the authors.
 */

package fr.ensicaen.search;

import java.util.Scanner;

import fr.ensicaen.index.Index;

/**
 * This file is used to understand the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Search {
    public static void main(String[] args) {
        Index index = new Index();
        Query query = new Query(index);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your query: ");
        System.out.println("Query: " + query);
        System.out.println("Salton coefficient (Cauchemar.txt): "
                + query.computeSaltonCoefficient("Cauchemar.txt"));

        sc.close();
    }
}
