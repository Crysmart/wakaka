package com.wakaka.groovy.script

class Testt {
    def hello(){
        println("hello world")
    }

    String helloo(HashMap<String,String> map){
        println map.get("a")
        println(map)
        return map.get("a");
    }

    def run(){
        int count = 5;
        boolean a = count > 5;
        boolean b = count < 5;
        return a|b;
    }
}
