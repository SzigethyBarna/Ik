package data.structure;

import java.util.HashMap;
import java.util.HashSet;

public class MultiSet<E>{
    
    private HashMap<E, Integer> elemToCount

    @SafeVarargs
    public MultiSet(E... elems){
        elemToCount = new HashMap<Integer>();
        for ( E elem: elems ){
            add(elem);
        }
    }

    public int Add(E elem){
        int count = elemToCount.getOrDefault(elem,0) + 1;
        elemToCount.put(elem, count);
        return count;
    }

    public int getCount(E elem){
        return elemToCount.getOrDefault(elem, 0);
    }

    public MultiSet<E> intersect(MultiSet<E> otherMultiSet){
        MultiSet<E> result= new MultiSet<E>();

        for(HashMap.Entry<E, Integer> entry: elemToCount.entrySet){
            if(!otherMultiSet.elemToCount.containsKey(entry.getKey())){
                continue;
            }

            int count1 = entry.getValue();
            int count2 = otherMultiSet.getCount(entry.getKey());
            result.elemToCount.put(entry.getKey(), Math.min(count1,count2));
        }
        return result;
    }

    public int size(){
        int result=0;

        for(Integer multiplicity: elemToCount.values()){
            result += multiplicity;
        }

        return result;
    }

     public int countExcept(HashSet<E> notCounted){
        int result=0;

        for(HashSet<E, Integer> entry: elemToCount.entrySet()){
        if(!notCounted.contains(entry.getKey())){
                result += entry.getValue();
            }
        }

        return result;
    }
}