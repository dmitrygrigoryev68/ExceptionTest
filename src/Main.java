import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BankAccount b1 = new BankAccount("IT766098765565");
        BankAccount b2 = new BankAccount("DE76609876556582");
        BankAccount b3 = new BankAccount("76609876556583");
        BankAccount b4 = new BankAccount("LT76609876556584");
        BankAccount b5 = new BankAccount("DE76609655658585");
        BankAccount b6 = new BankAccount("DE76609876556586");
        BankAccount b7 = new BankAccount("ZE76609876556587");

        Customer c1 = new Customer("Ivan Draga", 1, b1);
        Customer c2 = new Customer("Stepan Praga", 22, b2);
        Customer c3 = new Customer("Oleg Braga", 18, b3);
        Customer c4 = new Customer("Bogdan Wlaga", 17, b4);
        Customer c5 = new Customer("Roman Schpaga", 8, b5);
        Customer c6 = new Customer("Feodor Saga", 4, b6);
        Customer c7 = new Customer("Natalia Draga", 38, b7);

        List<Customer> customers = new LinkedList<>();

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        customers.add(c5);
        customers.add(c6);
        customers.add(c7);

        myMapPrinter(mapErrorsByPerson(validateCustomers(customers)));

    }

    public static List<Error> validateCustomers(List<Customer> customers) {
        List<Error> allErrors = new LinkedList<>();
        for (Customer customer : customers) {
            allErrors.addAll(validateCustomer(customer));
        }
        return allErrors;
    }

    public static List<Error> validateCustomer(Customer customer) {
        List<Error> customerErrors = new LinkedList<>();
        customerErrors.addAll(validateBankAccount(customer));

        try {
            validateCustomerAge(customer.getAge());
        } catch (Exception ex) {
            Error err = new Error("ERROR: Invalid age of customer", customer);
            customerErrors.add(err);
        }
        return customerErrors;
    }

    public static void validateCustomerAge(int age) throws Exception {
        if (age < 17) {
            throw new Exception();
        }
    }

    public static List<Error> validateBankAccount(Customer customer) {
        List<Error> bankAccountErrors = new LinkedList<>();
        try {
            validateBankAccountCountry(customer.getAccount().getIban());
        } catch (Exception ex) {
            bankAccountErrors.add(new Error("ERROR: Bank account's country is not valid", customer));
        }

        try {
            validateIbanLength(customer.getAccount().getIban());
        } catch (Exception ex) {
            bankAccountErrors.add(new Error("ERROR: Invalid IBAN length", customer));
        }
        return bankAccountErrors;
    }

    public static void validateIbanLength(String iban) throws Exception {
        int length = iban.length();
        if (length != 16) {
            throw new Exception();
        }
    }

    public static void validateBankAccountCountry(String iban) throws Exception {
        String country = iban.substring(0, 2);
        if (!country.equals("DE")) {
            throw new Exception();
        }
    }

    public static void myMapPrinter(Map<Customer, List<Error>> map) {
        map.forEach((key, value) -> System.out.println(key +
                value
                        .stream()
                        .map(Error::getErrorMessage)
                        .collect(Collectors.joining("\n")) + "\n"));
    }

    public static Map<Customer, List<Error>> mapErrorsByPerson(List<Error> errors) {
        return errors
                .stream()
                .collect(Collectors.groupingBy(Error::getCustomer));
    }
}