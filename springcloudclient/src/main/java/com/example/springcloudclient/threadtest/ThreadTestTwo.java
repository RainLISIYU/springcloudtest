package com.example.springcloudclient.threadtest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:使用callable实现斐波那契
 * @USER: 梁思禹
 * @DATE: 2022/2/14
 */
public class ThreadTestTwo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i =0; i<20; i++){
            try {
                System.out.println(executor.submit(new Feibo()).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}

class Feibo implements Callable<String>{

    private static int id = 0;
    private static int pre = 0;
    private static int cur = 0;

    @Override
    public String call() throws Exception {
        id++;
        if (pre == 0){
            pre = cur == 0 ? 0 : 1;
            cur = pre | 1;
        }else{
            int c = cur;
            cur = cur + pre;
            pre = c;
        }
        StringBuffer sb = new StringBuffer("The Thread : ");
        sb.append(id).append(" and FeBo is ").append(cur);
        return sb.toString();
    }
}
