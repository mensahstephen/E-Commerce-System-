public class Payment {
    private boolean successful;
    private double amount;
    private PaymentMethod method;

    public Payment(double amount, PaymentMethod method) {
        this.amount = amount;
        this.method = method;
        this.successful = false;
    }
    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public boolean isSuccessful() {
        successful = Math.random() < 0.9;
        return successful;
    }
}
