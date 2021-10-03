package edu.neu.coe.info6205.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UF_CLIENT {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter the number of sites: ");
        int sites = Integer.parseInt(reader.readLine());
        int i = 0;
        int sum = 0;
        while(i < 20) {
            int count = count(sites);
            System.out.print("TRIAL " + (i+1) + ": " + count + ", ");
            if(i == 9) System.out.println();
            i++;
            sum += count;
        }
        System.out.println();
        System.out.println("Average of connections generated: " + ((float)sum/20));

    }

    public static int count(int sites) {
        Random r = new Random();
        int connections = 0;
        UF h = new UF_HWQUPC(sites, true);
        while (h.components() > 1) {
            int p = r.nextInt(sites);
            int q = r.nextInt(sites);
            if(!h.isConnected(p, q)) {
                h.union(p, q);
            }
            connections++;
        }

        return connections;
    }
}
