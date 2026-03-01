import java.net.StandardSocketOptions;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String CarId;
    private String CarBrand;
    private String CarModel;
    private double BasePricePerDay;
    private boolean IsAvailable;
    public Car(String CarId,String CarBrand,String CarModel,double BasePricePerDay){
        this.CarId = CarId;
        this.CarBrand = CarBrand;
        this.CarModel = CarModel;
        this.BasePricePerDay = BasePricePerDay;
        this.IsAvailable = true;
    }

    public String getCarId(){
        return CarId;
    }
    public String getCarBrand(){
        return CarBrand;
    }
    public String getCarModel(){
        return CarModel;
    }
    public double calculatePrice(int days){
        return BasePricePerDay * days;
    }
    public boolean isAvailable(){
        return IsAvailable;
    }
    public void rent(){
        IsAvailable = false;
    }
    public void returnCar(){
        IsAvailable = true;
    }
}

class Customer{
    private String customerId;
    private String customerName;

    public Customer(String customerId,String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
}

class Rental{
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car,Customer customer,int days){
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car,Customer customer,int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else {
            System.out.println("Sorry, car is not available to rent.");
        }
    }

    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for(Rental rental : rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null) {
            rentals.remove(rentalToRemove);
        }
        else System.out.println("Car was not rented.");
    }

    public void menu(){
        Scanner Input = new Scanner(System.in);

        while(true){
            System.out.print(
                    "===================== Car Rental System =====================\n"
                  + "1. Rent a Car.\n"
                  + "2. Return a Car.\n"
                  + "3. Exit.\n"
                  + "===============================================================\n"
                  + "Enter your choice : "
            );

            int choice = Input.nextInt();
            Input.nextLine();

            if(choice == 1){
                System.out.println(
                        "===================== Rent a Car =====================\n"
                        + "Enter your name : "
                );
                String customerName = Input.nextLine();

                System.out.println("Available Cars: ");
                for(Car car:cars){
                    if(car.isAvailable()){
                        System.out.println(
                                car.getCarId() + ' '
                                + car.getCarBrand() + ' '
                                + car.getCarModel());
                    }
                }
                System.out.print("Enter the car Id you want to rent : ");
                String carId = Input.nextLine();

                System.out.print("Enter the number of days for rental : ");
                int days = Input.nextInt();
                Input.nextLine();

                Customer newcustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newcustomer);

                Car selectedCar = null;
                for(Car car:cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }
                if(selectedCar != null){
                    double totalPrice = selectedCar.calculatePrice(days);
                    System.out.print(
                            "====== Rental Information ======\n" +
                            "Customer ID : " + newcustomer.getCustomerId() + "\n" +
                            "Customer Name : " + newcustomer.getCustomerName() + "\n" +
                            "Car : " + selectedCar.getCarBrand() + ' ' + selectedCar.getCarModel() + "\n" +
                            "Rental Days : " + days + "\n"
                    );
                    System.out.printf("Total Price is : $%.2f%n" , totalPrice);
                    System.out.println("\nConfirm rental (Y/N) :");
                    String confirm = Input.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newcustomer,days);
                        System.out.println("\nCar rented successfully <3");
                    }
                    else {
                        System.out.println("\nRental canceled.");
                    }
                }
                else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }

            }
            else if(choice == 2){
                System.out.print(
                        "===== Return A Car =====" + "\n" +
                        "Enter the car ID you want to return : "
                );
                String carID = Input.nextLine();

                Car carToReturn = null;
                for(Car car:cars){
                    if(car.getCarId().equals(carID)){
                        carToReturn = car;
                        break;
                    }
                }
                if(carToReturn != null){
                    Customer customer = null;
                    for(Rental rental:rentals){
                        if(rental.getCar() == carToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer != null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully " + customer.getCustomerName());
                    }
                    else System.out.println("Car was not rent or rental information is missing.");
                }
                else System.out.println("Invalid car ID or car was not rent.");

            }
            else if(choice == 3) break;
            else {
                System.out.println("Invalid choice, Please enter a valid option.");
            }
        }
        System.out.println("Thanks for using car rental system!");
    }
}

public class Main {
    public static void main(String[] args){
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001","BMW" , "M4", 150.0);
        Car car2 = new Car("C002","Toyota","Corolla", 50.0);
        Car car3 = new Car("C003","Honda","Accord",80.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}