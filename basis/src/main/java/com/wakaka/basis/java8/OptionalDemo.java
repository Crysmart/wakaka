package com.wakaka.basis.java8;


import java.util.Optional;

/**
 * @author Crysmart
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional.ofNullable("55").filter(null);
    }
}
