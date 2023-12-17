package com.example.demo.classes;

import com.example.demo.annotations.ClassDocumentation;
import com.example.demo.annotations.MethodDocumentation;

/**
 * This is a class First.
 * it contains 4 methods
 */
@ClassDocumentation("This is a class First")
public class First {

    /**
     * This is a method First1
     * Inside First
     */
    @MethodDocumentation("This is a method AA inside A")
    public void First1() {
        System.out.println("AA");
    }

    /**
     * This is a method First2
     * Inside First
     */
    @MethodDocumentation("This is a method AB inside A")
    public void First2() {
        System.out.println("AB");
    }

    @MethodDocumentation("This is a method AC inside A")
    public void First3() {
        System.out.println("AC");
    }

    /**
     * This is a method First4
     * Inside First
     */
    public void First4() {
        System.out.println("AD");
    }
}