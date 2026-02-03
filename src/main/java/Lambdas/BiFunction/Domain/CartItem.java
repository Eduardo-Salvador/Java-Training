package Lambdas.BiFunction.Domain;
import java.util.List;
import java.util.function.BiFunction;

public class CartItem {
    private String productName;
    private Integer quantity;
    private Double unitPrice;

    public CartItem(String productName, Integer quantity, Double unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static Double calculateTotal(List<CartItem> items, Double value, BiFunction<Double, Double, Double> calculation) {
        double total = 0D;
        for (CartItem c : items) {
            total += (c.getUnitPrice() * c.getQuantity());
        }
        return calculation.apply(total, value);
    }

    public static Double bonus(CartItem item, Integer bonus, BiFunction<CartItem, Integer, Double> bonusCalculate){
        return bonusCalculate.apply(item, bonus);
    }
}