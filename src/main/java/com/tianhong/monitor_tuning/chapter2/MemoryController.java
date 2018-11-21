package com.tianhong.monitor_tuning.chapter2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: TianhongWang
 * @Date: 2018/11/20
 * @Description
 */

@RestController
public class MemoryController {
    private List<User> userList = new ArrayList<>();
    private List<Class<?>> classList = new ArrayList<Class<?>>();
    /**
     *  模拟堆逸出
     *  堆设置为32M -Xmx32M -Xms32M
     * @return
     */
    @GetMapping("/heap")
    public String heap(){
        int i = 0;
        while(true){
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     * 模拟metaspace逸出
     * @return
     */
    @GetMapping("/nonheap")
    public String nonheap(){
        while(true){
            classList.addAll(Metaspace.createClasses());
        }
    }
}
