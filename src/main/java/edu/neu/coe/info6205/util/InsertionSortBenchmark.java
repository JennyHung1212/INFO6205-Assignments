package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import org.ini4j.Ini;

import java.util.*;

public class InsertionSortBenchmark {
    final static private int[] size = new int[]{500, 1000, 2000, 4000, 8000, 16000};
    final static Ini ini = new Ini();
    final static Config config = new Config(ini);

    public static void main(String[] args) {
        System.out.println("============== Sort random arrays ==============");
        for (int i = 0; i < size.length; i++) {
            System.out.println("Size: " + size[i]);
            testWithRaondom(size[i]);
        }
        System.out.println("============== Sort ordered arrays ==============");
        for (int i = 0; i < size.length; i++) {
            System.out.println("Size: " + size[i]);
            testWithOrdered(size[i]);
        }
        System.out.println("============== Sort partially-ordered arrays ==============");
        for (int i = 0; i < size.length; i++) {
            System.out.println("Size: " + size[i]);
            testWithPartiallyOrdered(size[i]);
        }
        System.out.println("============== Sort reverse-ordered arrays ==============");
        for (int i = 0; i < size.length; i++) {
            System.out.println("Size: " + size[i]);
            testWithReverseOrdered(size[i]);
        }
    }

    private static void testWithRaondom(int size) {
        final Helper<Integer> helper = new BaseHelper<Integer>("InsertionSort",  size, System.currentTimeMillis(), config);
        final Class<Integer> cl = Integer.class;
        final Integer[] xs = helper.random(cl, a -> a.nextInt(10000) );
        final SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.sort(xs);
        final Benchmark<Integer[]> bm = new Benchmark_Timer<Integer[]>("Insertion sort: ordered", b -> sorter.sort(b));
        double result = bm.run(xs, 10);
        System.out.println(result);
    }

    private static void testWithOrdered(int size) {
        final List<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        final Integer[] xs = list.toArray(new Integer[0]);
        final Helper<Integer> helper = new BaseHelper<Integer>("InsertionSort",  size, System.currentTimeMillis(), config);
        final SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.sort(xs);
        final Benchmark<Integer[]> bm = new Benchmark_Timer<Integer[]>("Insertion sort: ordered", b -> sorter.sort(b));
        double result = bm.run(xs, 10);
        System.out.println(result);
    }

    private static void testWithPartiallyOrdered(int size) {
        final List<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            if (i > size/2) list.add(new Random().nextInt());
            else list.add(i);
        }
        final Integer[] xs = list.toArray(new Integer[0]);
        final Helper<Integer> helper = new BaseHelper<Integer>("InsertionSort",  size, System.currentTimeMillis(), config);
        final SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.sort(xs);
        final Benchmark<Integer[]> bm = new Benchmark_Timer<Integer[]>("Insertion sort: ordered", b -> sorter.sort(b));
        double result = bm.run(xs, 10);
        System.out.println(result);
    }

    private static void testWithReverseOrdered(int size) {
        final List<Integer> list = new ArrayList<Integer>(size);
        for (int i = size; i > 0; i--) {
            list.add(i);
        }
        final Integer[] xs = list.toArray(new Integer[0]);
        final Helper<Integer> helper = new BaseHelper<Integer>("InsertionSort",  size, System.currentTimeMillis(), config);
        final SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.sort(xs);
        final Benchmark<Integer[]> bm = new Benchmark_Timer<Integer[]>("Insertion sort: ordered", b -> sorter.sort(b));
        double result = bm.run(xs, 10);
        System.out.println(result);
    }

}
