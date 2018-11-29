package pitexample;

public class MyClass {

    public boolean myMethod(int a, boolean flag) {
        if (a >0) {
            return true;
        }
        if (flag) {
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
}