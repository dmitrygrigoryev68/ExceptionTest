public class Error {

    String errorMessage;
    Customer customer;

    public Error(String error, Customer customer) {
        this.errorMessage = error;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
