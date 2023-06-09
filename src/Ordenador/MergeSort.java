package Ordenador;

import java.util.Comparator;
import java.util.LinkedList;

public class MergeSort<T extends Comparable<T>> {
    private LinkedList<T> list;
    private Comparator<T> comparator;

    public MergeSort(LinkedList<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    public LinkedList<T> sort() {
        return this.mergeSort(this.list);
    }

    public LinkedList<T> mergeSort(LinkedList<T> original) {
        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();
        int center;
        if (original.size() == 1) {
            return original;
        } else {
            center = original.size() / 2;
            for (int i = 0; i < center; i++) {
                left.add(original.get(i));
            }
            for (int i = center; i < original.size(); i++) {
                right.add(original.get(i));
            }
            left = mergeSort(left);
            right = mergeSort(right);
            merge(left, right, original);
        }
        return original;
    }

    private void merge(LinkedList<T> left, LinkedList<T> right, LinkedList<T> original) {
        int leftIndex = 0;
        int rightIndex = 0;
        int originalIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {

            if (this.comparator.compare(left.get(leftIndex), right.get(rightIndex)) < 0) {
                original.set(originalIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                original.set(originalIndex, right.get(rightIndex));
                rightIndex++;
            }
            originalIndex++;
        }

        while (leftIndex < left.size()) {
            original.set(originalIndex, left.get(leftIndex));
            originalIndex++;
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            original.set(originalIndex, right.get(rightIndex));
            originalIndex++;
            rightIndex++;
        }
    }

}