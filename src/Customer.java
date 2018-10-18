public class Customer {


    private String name;
    private int age;
    BankAccount account;


    public Customer(String name, int age, BankAccount account) {
        this.name = name;
        this.age = age;
        this.account = account;

    }

    public int getAge() {
        return age;
    }
    public BankAccount getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Customer's name - " + name + ", age: "
                + age + " year(s), IBAN: " + account.getIban() + "\n";
    }
}
