package ru.edu.space.app.util;


import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.ObjectUtils.isEmpty;

public final class CollectionUtils {

    private CollectionUtils() { }

    /**
     * @param list list
     * @param <E> E
     * @return optional first element
     */
    public static <E> Optional<E> getFirstOrEmpty(List<E> list) {
        if (isEmpty(list)) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    /**
     * @param list list
     * @param <E> E
     * @return last element
     */
    public static <E> E getLastElement(List<E> list) {
        return list.get(list.size() - 1);
    }

    /**
     * @param list list
     * @param element element
     * @param <E> E
     * @return boolean
     */
    public static <E> boolean isLastElement(List<E> list, E element) {
        return list.indexOf(element) == (list.size() -1);
    }

    private static Object getDefaultValuePrimitive(Class<?> fieldType) {
        Class<Double> d = Double.class;
        Class<Float> f = Float.class;
        Class<String> s = String.class;
        Class<Integer> i = Integer.class;
        Class<Long> l = Long.class;
        Class<Short> sort = Short.class;

        if (fieldType.equals(d)) {
            return 0.;
        } else if (fieldType.equals(f)) {
            return 0.;
        } else if (fieldType.equals(s)) {
            return "";
        } else if (fieldType.equals(i)) {
            return 0;
        } else if (fieldType.equals(sort)) {
            return 0;
        } else if (fieldType.equals(l)) {
            return 0L;
        }

        return 0.;
    }

    /**
     * Returns the combined collection without duplicates
     * If the collection objects - you need to correctly override hashCode
     * Not save order
     *
     * @param collection1 collection1
     * @param collection2 collection2
     * @param <E> E
     * @return merged Collection
     */
    public static <E> Collection<E> merge(Collection<E> collection1, Collection<E> collection2) {
        return Stream.concat(collection1.stream(), collection2.stream())
                .collect(toSet());
    }

    /**
     * @param collection1 collection1
     * @param collection2 collection2
     * @return merged Collection
     */
    public static Collection<?> mergeAny(Collection<?> collection1, Collection<?> collection2) {
        return Stream.concat(collection1.stream(), collection2.stream())
                .collect(toSet());
    }

    /**
     * Save order and without duplications
     *
     * @param collection1 collection1
     * @param collection2 collection2
     * @param <E> E
     * @return merged list
     */
    public static <E> List<E> mergeList(List<E> collection1, List<E> collection2) {
        return Stream.of(collection1, collection2)
                .flatMap(Collection::stream)
                .distinct()
                .collect(toList());
    }

    /**
     * @param collection collection
     * @param <E> E
     * @return collection without duplications
     */
    public static <E> Collection<E> removeDuplication(Collection<E> collection) {
        return collection.stream()
                .distinct()
                .collect(toList());
    }

    /**
     *
     * @param collection collection
     * @param <E> E
     * @return not-null collection
     */
    public static <E> Collection<E> convertIfNullToEmptyCollection(Collection<E> collection) {
        return Optional.ofNullable(collection).orElseGet(Collections::emptyList);
    }

    /**
     *
     * @param collection collection
     * @param <E> E
     * @return not-null list
     */
    public static <E> List<E> convertIfNullToEmptyList(Collection<E> collection) {
        return new ArrayList<>(convertIfNullToEmptyCollection(collection));
    }

    /**
     *
     * @param collection collection
     * @param <E> E
     * @return not-null list
     */
    public static <E> Set<E> convertIfNullToEmptySet(Collection<E> collection) {
        return new HashSet<>(convertIfNullToEmptyCollection(collection));
    }

    /**
     * Important! If passed empty param 'collectionForCheckContains' return emptyList;
     *
     * @param collection collection
     * @param collectionForCheckContains collectionForCheckContains
     * @param <E> number
     * @return filtered collection
     */
    public static <E extends Number> Collection<E> getCollectionContainsByPassed(Collection<E> collection, Collection<E> collectionForCheckContains) {
        if (isEmpty(collectionForCheckContains)) {
            return emptySet();
        }

        Set<E> setForCheckContains = new HashSet<>(collectionForCheckContains);     // for faster search

        return convertIfNullToEmptySet(collection).stream()
                .filter(setForCheckContains::contains)
                .collect(toSet());
    }

    /**
     *
     * @param list list
     * @param index index
     * @return exist index or not
     */
    public static boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }

    /**
     * @param collection collection
     * @return boolean
     */
    public static boolean collectionIsNullOrEmpty (Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     *
     * @param collection collection
     * @return boolean
     */
    public static boolean collectionIsNotNullAndNotEmpty (Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * @param collection collection
     * @param <E> type
     * @return boolean
     */
    public static <E> boolean isExistNull(Collection<E> collection) {
        return collection.stream()
                .anyMatch(Objects::isNull);
    }

    /**
     * @param collection collection
     * @param <E> E
     * @return true or false
     */
    public static <E> boolean allElementsIsNull(Collection<E> collection) {
        return collection.stream()
                .noneMatch(Objects::nonNull);
    }

    /**
     * @param collection collection
     * @param <E> type
     * @return collection without null
     */
    public static <E> Collection<E> getCollectionWithoutNull(Collection<E> collection) {
        return collection.stream()
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * @param collection collection
     * @param <E> E
     * @return is exist
     */
    public static <E> boolean isExistAnyNotNullElements(Collection<E> collection) {
        return getCollectionWithoutNull(collection).stream()
                .anyMatch(c -> true);
    }

    /**
     * @param list list
     */
    public static Long getSumLong(Collection<Long> list) {
        if (allElementsIsNull(list)) {
            return null;
        }

        return list.stream()
                .filter(Objects::nonNull)
                .mapToLong(Long::valueOf)
                .sum();
    }

    /**
     * @param list list
     */
    public static Double getSum(Collection<Double> list) {
        if (allElementsIsNull(list)) {
            return null;
        }

        return list.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::valueOf)
                .sum();
    }

    /**
     * @param list list
     * @return null if all elements is null or sum value
     */
    public static Double getSumWithRound(Collection<Double> list, int numbersAfterDot) {
        if (allElementsIsNull(list)) {
            return null;
        }

        Double sum = getSum(list);
        return MathUtils.roundNullable(sum, numbersAfterDot);
    }
}
