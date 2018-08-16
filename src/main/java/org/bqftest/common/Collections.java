package org.bqftest.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.iterators.UniqueFilterIterator;

public class Collections {
    
    public static void main(String[] args) {
        uniqueIterator();
    }

    public static void uniqueIterator(){
        String[] medals = new String[] { "gold", "silver", "silver", "gold", "bronze" };
        List medalsList = Arrays.asList( medals );

        Iterator uniqueIterator = new UniqueFilterIterator(medalsList.iterator() );
        while(uniqueIterator.hasNext()) {
            System.out.println( "Unique Medal: " + uniqueIterator.next());
        }
    }
}
