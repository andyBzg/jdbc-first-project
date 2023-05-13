package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private static final String SELECT_ALL = "SELECT * FROM cars";
    private static final String INSERT = "INSERT INTO cars VALUES (%d, '%s', %d, '%s', '%s')";
    private static final String SELECT_BY_ID = "SELECT * FROM cars WHERE id=%d";
    private static final String UPDATE_BY_ID = "UPDATE cars SET brand='%s', year=%d, color='%s', country='%s' WHERE id=%d";
    private static final String DELETE_BY_ID = "DELETE FROM cars WHERE id=%d";
    private final DatabaseConnection databaseConnection;


    public CarDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public List<Car> getAllCars() {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = extractCarFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Car getCar(int id) {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format(SELECT_BY_ID, id);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return extractCarFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Car();
    }

    public void insertCar(Car car) {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format(INSERT, car.getId(), car.getBrand(), car.getYear(), car.getColor(), car.getCountry());
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(int id, Car car) {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format(UPDATE_BY_ID, car.getBrand(), car.getYear(), car.getColor(), car.getCountry(), id);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format(DELETE_BY_ID, id);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Car extractCarFromResultSet(ResultSet resultSet) {
        try {
            return new Car(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Car();
    }
}
