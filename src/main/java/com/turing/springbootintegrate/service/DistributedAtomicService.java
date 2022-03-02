package com.turing.springbootintegrate.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryForever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 分布式计数器
 *
 * @author ycyoes
 * @date 2021-07-06
 */
@Service
public class DistributedAtomicService {

    //----------此处未初始化，本service无法运行-----------
    private CuratorFramework curatorFramework;

    public void atomic() {
        DistributedAtomicInteger counter = new DistributedAtomicInteger(curatorFramework, "/atomic", new RetryForever(100));

        try {
            AtomicValue<Integer> value = counter.increment();
            String str = "";
            str += "===" + Thread.currentThread().getName() + "===";
            str += "原值为：" + value.preValue();   //上一次修改成功之后的值
            str += ",更改后的值为： " + value.postValue(); //如果成功就是最新的值，失败的话就是0
            str += ",状态： " + value.succeeded(); //成功为true,失败为false
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
