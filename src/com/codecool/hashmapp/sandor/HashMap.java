package com.codecool.hashmapp.sandor;

import java.util.Arrays;
import java.util.LinkedList;


public class HashMap<K, V> {

        private int bucketSize = 16;
        private int totalSize = 0;

        private LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];

        public void put(K key, V value) {
            int position = getHash(key);
            if(elements[position] == null){
                elements[position] = new LinkedList<>();
            }
            LinkedList<KeyValue> list = elements[position];

            for(KeyValue kv : list){
                if(kv.hash == key.hashCode()){
                    throw new IllegalArgumentException("Element already exists in this map");
                }
            }
            list.add(new KeyValue(key, value));
            totalSize++;
            resizeIfNeeded();
        }

        public V get(K key) {
            int position = getHash(key);
            if(elements[position] == null){
                return null;
            }
            LinkedList<KeyValue> list = elements[position];
            for(KeyValue kv : list){
                if(kv.hash == key.hashCode()){
                    return kv.value;
                }
            }
            return null;
        }

        public V remove(K key){
            int position = getHash(key);
            if(elements[position] == null){
                return null;
            }
            LinkedList<KeyValue> list = elements[position];
            for(KeyValue kv : list){
                if(kv.hash == key.hashCode()){
                    list.remove(kv);
                    return kv.value;
                }
            }
            return null;
        }

        private int getHash(K key) {
            return Math.abs(key.hashCode() % elements.length);
        }

        private void resizeIfNeeded(){
            if(totalSize > bucketSize*2){
                bucketSize *= 2;
                elements = Arrays.copyOf(elements, bucketSize);
            }else if(bucketSize > 16 && totalSize < bucketSize/2){
                bucketSize /= 2;
                elements = Arrays.copyOf(elements, bucketSize);
            }

        }

        private class KeyValue {
                K key;
                V value;
                int hash;

                public KeyValue(K key, V value) {
                    this.key = key;
                    this.value = value;
                    hash = key.hashCode();
                }
            }
}