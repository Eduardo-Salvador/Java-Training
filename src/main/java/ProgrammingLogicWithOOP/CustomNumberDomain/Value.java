package ProgrammingLogicWithOOP.CustomNumberDomain;

public abstract class Value {
    private int valueInt;
    private double valueDouble;
    private boolean valueBool;
    private char valueChar;

    public Value(){}

    public int getValueInt() {
        return valueInt;
    }

    public double getValueDouble() {
        return valueDouble;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public void setValueDouble(double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public boolean isValueBool() {
        return valueBool;
    }

    public void setValueBool(boolean valueBool) {
        this.valueBool = valueBool;
    }

    public char getValueChar() {
        return valueChar;
    }

    public void setValueChar(char valueChar) {
        this.valueChar = valueChar;
    }
}