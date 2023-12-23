package com.tuhinal.employeemanagement.service;

public class JunitTestDemoTest {


  public static void beforeOnlyOnceForAllTestCases() {
    System.out.println("BOOFAT");
  }

  public void setup() {
    System.out.println("===============setup()=============");
  }
  public void testM1() {
    System.out.println("===============testM1()=============");
  }
  public void testM2() {
    System.out.println("===============testM2()=============");
  }
  public void tierDown() {
    System.out.println("===============tierDown()=============");
  }

  public static void afterOnlyOnceForAllTestCases() {
    System.out.println("AOOFAT");
  }
}
