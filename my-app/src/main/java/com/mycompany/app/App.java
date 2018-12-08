package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App
{
  public void App(){
    new App();
  }

  public static int add(int a, int z)
  {
    return z+a;
  }

  public static int sub(int a, int b)
  {
    //check to see if a is greater than b
    if(a > b && b < a)
      return a-b;
    else{
      int z = 0;
      return z;
    }

  }

  public static boolean compare(int a, int z)
  {
    if(a == 0)
      return true;
    return false;
  }
}


/*

public static void main( String[] args )
{
    System.out.println( "Hello World!" );
}
*/
