package ru.edu.space.app.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.util.ObjectUtils.isEmpty;


public final class ArrayUtils {

    private ArrayUtils() { }

    /**
     * @param list list
     * @param objectClass objectClass
     * @param <T> T
     * @return array with T
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] convertListToArray(List<T> list, Class<T> objectClass) {
        if (isEmpty(list)) {
            return (T[]) Array.newInstance(objectClass, 0);
        }

        T[] listAsArray = (T[]) Array.newInstance(objectClass, list.size());
        list.toArray(listAsArray);
        return listAsArray;
    }

    /**
     * @param first first
     * @param second second
     * @param <T> T
     * @return concat array
     */
    public static <T> T[] concatArray(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * @param arr arr
     * @return array without duplication
     */
    public static Long[] removeDuplicatesFromArray(Long[] arr) {
        if (arr == null) {
            return null;
        }

        Set<Long> alreadyPresent = new HashSet<>();
        Long[] whitelist = new Long[arr.length];
        int i = 0;

        for (Long element : arr) {
            if (alreadyPresent.add(element)) {
                whitelist[i++] = element;
            }
        }

        return Arrays.copyOf(whitelist, i);
    }
}
