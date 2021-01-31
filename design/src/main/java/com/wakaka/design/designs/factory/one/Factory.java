package com.wakaka.design.designs.factory.one;

class Factory{
    public static <T> T getInstance(Class<?> clz){
        try {
            return (T) clz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}