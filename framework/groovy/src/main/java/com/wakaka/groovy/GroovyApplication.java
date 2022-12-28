package com.wakaka.groovy;


import groovy.lang.GroovyShell;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.logging.Logger;

@SpringBootApplication
public class GroovyApplication {

    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","b");
        hashMap.put("c","d");
        hashMap.put("e","f");
        //创建GroovyShell
        Object helloo = new GroovyShell().parse("String helloo(HashMap<String,String> map){\n" +
                        "        println map.get(\"a\")\n" +
                        "        println(map)\n" +
                        "        return map.get(\"c\")\n" +
                        "    }")
                .invokeMethod("helloo", hashMap);
        logger.info((String) helloo);

        new GroovyShell().evaluate("println(\"hello world\")");
    }


}
