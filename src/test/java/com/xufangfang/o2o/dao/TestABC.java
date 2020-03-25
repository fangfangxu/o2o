package com.xufangfang.o2o.dao;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class TestABC {
    @Test
    public void A() {
        System.out.println("A");
    }

    @Test
    public void C() {
        System.out.println("C");
    }

    @Test
    public void B() {
        System.out.println("B");
    }
}
