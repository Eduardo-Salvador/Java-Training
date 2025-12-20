package LogicTrainingWithOOP.E12.Domain;

public enum PaymentTerms {
    CASH_PIX(1){
        @Override
        public double calculateValue(double valueProduct){
            return valueProduct * 0.85;
        }
    },
    CASH_CREDIT_CARD(2){
        @Override
        public double calculateValue(double valueProduct){
            return valueProduct * 0.9;
        }
    },
    TWO_INSTALLMENTS(3){
        @Override
        public double calculateValue(double valueProduct){
            return valueProduct;
        }
    },
    THREE_OR_MORE_INSTALLMENTS(4){
        @Override
        public double calculateValue(double valueProduct){
            return valueProduct * 1.10;
        }
    };

    private final int value;

    PaymentTerms(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract double calculateValue(double valueProduct);
}