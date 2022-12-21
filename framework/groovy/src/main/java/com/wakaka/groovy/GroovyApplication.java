package com.wakaka.groovy;


import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;

@SpringBootApplication
public class GroovyApplication {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","b");
        hashMap.put("c","d");
        hashMap.put("e","f");
        //创建GroovyShell
        Object helloo = new GroovyShell().parse("String helloo(HashMap<String,String> map){\n" +
                        "        println map.get(\"a\")\n" +
                        "        println(map)\n" +
                        "        return map.get(\"c\");\n" +
                        "    }")
                .invokeMethod("helloo", hashMap);
        System.out.println((String) helloo);
    }


}
