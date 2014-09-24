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

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fr.ensicaen.index.Index;
import fr.ensicaen.index.IndexHandler;
import fr.ensicaen.search.Query.ValueComparator;
import fr.ensicaen.utils.FileUtils;

/**
 * Main search interface.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Search {
    public static void main(String[] args) throws IOException, ParseException {
        String indexContent = FileUtils.readWholeFileUTF8("./index/index.txt");
        IndexHandler indexHandler = new IndexHandler();
        indexHandler.initializeFromString(indexContent);
        Index index = indexHandler.getIndex();
        Query query = new Query(index);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter request : ");
        Map<String, Float> relevantDocuments = query.search(sc.nextLine());
        //Comparator<Float> comparator =  new ValueComparator(relevantDocuments);
        //TreeMap<String, Float> sorted = new TreeMap<String, Float>();

        for (Map.Entry<String, Float> document : relevantDocuments.entrySet()) {
            System.out.println(document.getKey() + " : " + document.getValue());
        }

        sc.close();
    }
}
