package pitexample;

public class MyClass {

    public boolean myMethod(int a) {
        if (a >0) {
            return true;
        }

        return false;
    }

    public int negateVariable(int a) {
        return -a;
    }

    public boolean bitWiseOr(boolean a, boolean b) {
        if (a | b) {
            return true;
        }

        return false;
    }

    public boolean bitWiseAnd(boolean a, boolean b) {
        if (a & b) {
            return true;
        }

        return false;
    }

    public int incrementByOne(int a) {

        int b = a;
        b++;
        return b;
    }

    public int decrementByOne(int a) {

        int b = a;
        b--;
        return b;
    }
}