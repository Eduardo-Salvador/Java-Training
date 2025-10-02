package LogicTrainingWithOOP.CustomNumberDomain;

public abstract class Value {
    private int value;

    public Value(){}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
