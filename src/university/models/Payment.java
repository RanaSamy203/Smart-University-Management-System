package university.models;

import university.exceptions.InvalidPaymentException;
import university.interfaces.Payable;
import university.interfaces.Printable;
import university.interfaces.Searchable;

public class Payment implements Payable, Printable, Searchable {

    private Student student;
    private double totalFees;
    private double paidAmount;

    // Constructor
    public Payment(Student student, double totalFees)
            throws InvalidPaymentException {

        if (student == null) {
            throw new InvalidPaymentException("Student cannot be null.");
        }

        if (totalFees < 0) {
            throw new InvalidPaymentException(
                    "Total fees cannot be negative."
            );
        }

        this.student = student;
        this.totalFees = totalFees;
        this.paidAmount = 0;
    }

    // Get Student
    public Student getStudent() {
        return student;
    }

    // Get Total Fees
    public double getTotalFees() {
        return totalFees;
    }

    // Get Paid Amount
    public double getPaidAmount() {
        return paidAmount;
    }

    // Calculate Remaining Amount
    @Override
    public double getRemainingAmount() {
        return totalFees - paidAmount;
    }

    // Check if fully paid
    @Override
    public boolean isFullyPaid() {
        return paidAmount >= totalFees;
    }

    // Make Payment
    @Override
    public void makePayment(double amount) {

        try {

            if (amount <= 0) {
                throw new InvalidPaymentException(
                        "Payment amount must be greater than 0."
                );
            }

            if (amount > getRemainingAmount()) {
                throw new InvalidPaymentException(
                        "Payment amount cannot exceed remaining fees."
                );
            }

            paidAmount += amount;

            System.out.println("Payment successful.");

        } catch (InvalidPaymentException e) {

            System.out.println("Payment Error: " + e.getMessage());
        }
    }

    // Search Payment
    @Override
    public boolean matches(String keyword) {

        if (keyword == null) {
            return false;
        }

        keyword = keyword.toLowerCase();

        return String.valueOf(student.getId()).equals(keyword)
                || student.getName().toLowerCase().contains(keyword);
    }

    // Print Payment Details
    @Override
    public void printDetails() {

        System.out.println("===== Payment Details =====");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Total Fees: " + totalFees);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Remaining Fees: " + getRemainingAmount());
        System.out.println("Fully Paid: " + isFullyPaid());
        System.out.println("===========================");
    }
}
