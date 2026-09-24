package university.services;

import java.util.ArrayList;
import university.exceptions.InvalidPaymentException;
import university.models.Payment;

public class PaymentService {

    private ArrayList<Payment> payments;

    // Constructor
    public PaymentService() {
        payments = new ArrayList<>();
    }

    // Add Payment
    public void addPayment(Payment payment) {

        if (payment == null) {
            System.out.println("Payment cannot be null.");
            return;
        }

        int studentId = payment.getStudent().getId();

        if (getPaymentByStudentId(studentId) != null) {
            System.out.println(
                    "Payment record already exists for this student."
            );
            return;
        }

        payments.add(payment);

        System.out.println("Payment record added successfully.");
    }

    // Search by Student ID
    public Payment searchPayment(int studentId) {

        for (Payment payment : payments) {

            if (payment.getStudent().getId() == studentId) {
                return payment;
            }
        }

        return null;
    }

    // Search by Student Name
    public Payment searchPayment(String studentName) {

        if (studentName == null) {
            return null;
        }

        for (Payment payment : payments) {

            if (payment.getStudent()
                    .getName()
                    .equalsIgnoreCase(studentName)) {

                return payment;
            }
        }

        return null;
    }

    // Make Payment
    public void makePayment(int studentId, double amount)
            throws InvalidPaymentException {

        Payment payment = getPaymentByStudentId(studentId);

        if (payment == null) {
            throw new InvalidPaymentException(
                    "Payment record not found for student ID: " + studentId
            );
        }

        payment.makePayment(amount);
    }

    // Get Payment by Student ID
    public Payment getPaymentByStudentId(int studentId) {

        return searchPayment(studentId);
    }

    // Get Remaining Fees
    public double getRemainingFees(int studentId) {

        Payment payment = getPaymentByStudentId(studentId);

        if (payment == null) {
            return -1;
        }

        return payment.getRemainingAmount();
    }

    // Get All Payments
    public ArrayList<Payment> getAllPayments() {

        return payments;
    }

    // Display All Payments
    public void displayPayments() {

        if (payments.isEmpty()) {

            System.out.println("No payment records found.");

            return;
        }

        for (Payment payment : payments) {

            displayPayment(payment);
        }
    }

    // Display One Payment
    public void displayPayment(Payment payment) {

        if (payment == null) {

            System.out.println("Payment not found.");

            return;
        }

        payment.printDetails();
    }
}
