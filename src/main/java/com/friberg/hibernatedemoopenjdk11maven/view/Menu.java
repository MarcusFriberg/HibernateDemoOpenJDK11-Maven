package com.friberg.hibernatedemoopenjdk11maven.view;

import com.friberg.hibernatedemoopenjdk11maven.Car;
import com.friberg.hibernatedemoopenjdk11maven.CarController;

import java.util.List;
import java.util.Scanner;

public class Menu {
    CarController carController;

    public Menu(CarController theCarController) {
        this.carController = theCarController;
    }

    public void showMainMenu() {
        System.out.println("Select an action:");
        System.out.println("0 : List all cars");
        System.out.println("1 : Add a car");
        System.out.println("2 : Delete a car");
        System.out.println("3 : Update a car");
        handleMenuInput();
    }

    public void handleMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 0 :
                showAllCars();
                break;
            case 1 :
                addACar();
                break;
            case 2 :
                deleteACar();
                break;
            case 3 :
                updateCar();
                break;
            default :
                System.out.println("Bad input!");
        }
    }

    public void showAllCars() {
        List<Car> cars = carController.getAllCars();
        System.out.println("------ Showing all Cars -------");
        for(Car car : cars) {
            System.out.println("Bil med egenskaperna: " + car.getBrand() + ", " + car.getModel());
        }
        showMainMenu();
    }

    public void addACar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ange fabrikat");
        String brand = scanner.nextLine();
        System.out.println("Ange modell");
        String model = scanner.nextLine();
        System.out.println("Ange pris");
        int price = scanner.nextInt();
        boolean isRetired = false;
        Car theCarToAdd = new Car(brand, model, price, isRetired);
        if(carController.addCar(theCarToAdd)) {
            System.out.println("Allt gick bra, bilen är tillagd");
        } else {
            System.out.println("Något gick fel!");
        }
        showMainMenu();
    }

    public void deleteACar() {
        List<Car> cars = carController.getAllCars();
        System.out.println("------ Showing all Cars -------");
        for(Car car : cars) {
            System.out.println("Ange " +car.getCarId() + " för att ta bort " + car.getBrand() + ", " + car.getModel());
        }
        Scanner scanner = new Scanner(System.in);
        int carIdToDelete = scanner.nextInt();
        // On the line below we can also use otherDeleteCar method if we want to use SQL-query to perform the delete.
        if(carController.deleteCar(carIdToDelete)) {
            System.out.println("Allt gick bra, bilen är borttagen :)");
        } else {
            System.out.println("Något gick fel!");
        }
        showMainMenu();
    }

    public void updateCar() {
        boolean isRetired;
        List<Car> cars = carController.getAllCars();
        System.out.println("------ Showing all Cars -------");
        for(Car car : cars) {
            System.out.println("Ange " +car.getCarId() + " för att uppdatera " + car.getBrand() + ", " + car.getModel());
        }
        Scanner scanner = new Scanner(System.in);
        int carIdToUpdate = scanner.nextInt();
        Car theCarToUpdate = carController.getCarById(carIdToUpdate);
        System.out.println("Du har valt att uppdatera: " + theCarToUpdate.getBrand() +
                ", " + theCarToUpdate.getModel() +
                ", " + theCarToUpdate.getPrice() +
                ", retired: " + theCarToUpdate.isRetired());
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Ange fabrikat");
        String brand = scanner2.nextLine();
        System.out.println("Ange modell");
        String model = scanner2.nextLine();
        System.out.println("Ange pris");
        String priceString = scanner2.nextLine();
        int price = Integer.parseInt(priceString);
        System.out.println("Retired? true/false");
        String retired = scanner2.nextLine();
        if(retired.equals("true")) {
            isRetired= true;
        } else {
            isRetired = false;
        }
        theCarToUpdate.setBrand(brand);
        theCarToUpdate.setModel(model);
        theCarToUpdate.setPrice(price);
        theCarToUpdate.setRetired(isRetired);
        if(carController.updateCar(theCarToUpdate)) {
            System.out.println("Allt gick bra, bilen är uppdaterad :)");
        } else {
            System.out.println("Något gick fel!");
        }
        showMainMenu();

    }
}
