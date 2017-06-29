/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3.Model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 * @param <Long>
 * @param <Docente>
 */
public class ValueComparator<Long, Docente extends Comparable<Docente>> implements Comparator<Long> {
    //criada para comparar o map 

    Map<Long, Docente> map = new HashMap<>();

    public ValueComparator(Map<Long, Docente> map) {
        this.map.putAll(map);
    }

    @Override
    public int compare(Long s1, Long s2) {

        return map.get(s1).compareTo(map.get(s2));//descending order	
    }
}
