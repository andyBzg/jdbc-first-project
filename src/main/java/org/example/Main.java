package org.example;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection();

        CarDAO carDAO = new CarDAO(databaseConnection);

        carDAO.insertCar(new Car(9, "BMW 7", 1998, "black", "Germany"));

        System.out.println(carDAO.getCar(9) + "\n");

        Car car = new Car(9, "Mercedes-Benz", 2017, "Metallic", "Germany");
        carDAO.updateCar(9, car);

        carDAO.deleteCar(9);

        carDAO.getAllCars().forEach(System.out::println);

    }
}