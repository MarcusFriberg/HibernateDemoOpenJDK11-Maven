package com.friberg.hibernatedemoopenjdk11maven;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class CarController {

    EntityManagerFactory entityManagerFactory = HelloApplication.ENTITY_MANAGER_FACTORY;


    public CarController() {
    }


    // Använder TypedQuery<Car> och entitymanagerns createQuery för att hämta alla bilar.
    public List<Car> getAllCars() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<Car> carList = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Car> allCarsQuery = entityManager.createQuery("from Car", Car.class);
            carList = allCarsQuery.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return carList;

    }

    // Använder Entity managerns persist-method
    public boolean addCar(Car theCar) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(theCar);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }

    // Använder Entity managerns find-method
    public Car getCarById(int theId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Car car;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            car = entityManager.find(Car.class, theId);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            entityManager.close();
            return null;
        }
        entityManager.close();
        return car;
    }

    // Använder Entity managerns remove och flush methods.
    public boolean deleteCar(int theCarID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Car theCarToRemove = entityManager.find(Car.class, theCarID);
            // Call remove-method of the EntityManager on the rental-entity passed to the method to remove it
            // from the managed objects.
            entityManager.remove(theCarToRemove);
            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }

    // Använder NativeQuery (för att kunna använda standard SQL fråga)
    public boolean otherDeleteCar(int theCarID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query deleteCarQuery = entityManager.createNativeQuery("DELETE from vehicle WHERE vehicle_id = "+theCarID);
            deleteCarQuery.executeUpdate();
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }

    // Använder Entity managerns merge-method
    public boolean updateCar(Car theCar) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Car theCarToUpdate = entityManager.find(Car.class, theCar.getCarId());
            theCarToUpdate.setBrand(theCar.getBrand());
            theCarToUpdate.setModel(theCar.getModel());
            theCarToUpdate.setPrice(theCar.getPrice());
            theCarToUpdate.setRetired(theCar.isRetired());
            entityManager.merge(theCarToUpdate);

            // Commit the changes
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }
}
