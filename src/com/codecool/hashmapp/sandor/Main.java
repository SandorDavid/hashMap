package com.codecool.hashmapp.sandor;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(1, 2);
        System.out.println(map.remove(1));
        System.out.println(map.get(1));
    }
}
