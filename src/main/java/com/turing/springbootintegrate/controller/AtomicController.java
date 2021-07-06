package com.turing.springbootintegrate.controller;

import com.turing.springbootintegrate.service.DistributedAtomicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtomicController {

    @Autowired
    private DistributedAtomicService distributedAtomicService;

    @RequestMapping(value = "/atomic", method = RequestMethod.GET)
    public String atomic() {
        System.out.println("-----------atomic----------------");
        for (int i = 0; i < 20; i++) {
            new Thread() {
                public void run() {
                    distributedAtomicService.atomic();
                }
            }.start();
        }
        return "ok";
    }

}
