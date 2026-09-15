package university.interfaces;

public interface Payable {
    void makePayment(double amount) throws Exception;
    double getRemainingAmount();
    boolean isFullyPaid();
}
