package com.example.springcloudclient.collectiontest;

import java.util.*;

/**
 * @Description:map速度测试
 * @USER: 梁思禹
 * @DATE: 2022/3/27
 */
public class MapSpeedTest {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.push(1);
        Object obj = list.pop();
        System.out.println(obj);
        Map map1 = new HashMap();
        Map map2 = new HashMap(10240);
        long cur1 = System.currentTimeMillis();
        for (int i = 0; i < 7400; ++i){
            map1.put(i, i);
        }
        for (Object key : map1.keySet()){
            map1.get(key);
        }
        long cur2 = System.currentTimeMillis();
        System.out.println("默认map添加32个元素的时间为"+(cur2-cur1));
        cur1 = System.currentTimeMillis();
        for (int i = 0; i < 7400; ++i){
            map2.put(i, i);
        }
        Iterator iterator = map2.keySet().iterator();
        while (iterator.hasNext()){
            Object key = iterator.next();
            System.out.println(map2.get(key));
            iterator.remove();
            System.out.println(map2.get(key));
        }
        cur2 = System.currentTimeMillis();
        System.out.println("指定长度map添加32个元素的时间为"+(cur2-cur1));

    }

}
