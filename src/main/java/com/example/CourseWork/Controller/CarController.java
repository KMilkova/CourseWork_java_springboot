package com.example.CourseWork.Controller;

import com.example.CourseWork.Model.Entity.*;
import com.example.CourseWork.Service.CarService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //МАШИНЫ
    @GetMapping("/cars/getByIdCar/{idCar}")
    public ResponseEntity<Car> getCarByID(@PathVariable int idCar) {
        Car car = carService.getCarById(idCar);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/cars/all")
    public ResponseEntity<List<Car>> printAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @GetMapping("/cars/allCarWithOutReg")
    public ResponseEntity<List<Car>> showAllCarWithOutReg() throws SQLException {
        List<Car> cars = carService.showAllCarWithOutReg();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/cars/add")
    public void saveCar(@RequestBody Car car) {
        carService.saveCar(car);
    }

    @PutMapping("/cars/update/{idCar}")
    public void updateCar(@PathVariable int idCar, @RequestBody Car car) {
        carService.updateCar(idCar, car);
    }

    @GetMapping("/cars/last")
    public ResponseEntity<Car> getLast() {
        Car car = carService.getLast_();
        return new ResponseEntity<>(car, HttpStatus.OK);
    }



    @RequestMapping(value = "/deleteJournal/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteJournalById(@PathVariable("id") int id) {
        carService.deleteJournalById(id);
    }


    //ВЛАДЕЛЬЦЫ
    @GetMapping("/carOwner/getById/{idOwner}")
    public ResponseEntity<CarOwner> getOwnerByID(@PathVariable int idOwner) {
        CarOwner carOwner = carService.getCarOwnerById(idOwner);
        return new ResponseEntity<>(carOwner, HttpStatus.OK);
    }

    @GetMapping("/carOwner/all")
    public ResponseEntity<List<CarOwner>> getAllCarOwners() {
        List<CarOwner> carOwners = carService.getAllCarOwners();
        return new ResponseEntity<>(carOwners, HttpStatus.OK);
    }

    @GetMapping("/carOwner/getByIdAutho/{idAuthorization}")
    public ResponseEntity<CarOwner> getOwnerByIdAutho(@PathVariable int idAuthorization) {
        CarOwner carOwner = carService.getCarOwnerByIdAuthorization(idAuthorization);
        return new ResponseEntity<>(carOwner, HttpStatus.OK);
    }




    //ОПЛАТА
    @GetMapping("/payment/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = carService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/payment/getByIdContract/{idContract}")
    public ResponseEntity<List<Payment>> getPaymentsByIdContract(@PathVariable int idContract) {
        List<Payment> payments = carService.getPaymentsByIdContract(idContract);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/payment/getByIdOwner/{idOwner}")
    public ResponseEntity<List<Payment>> getPaymentsByIdOwner(@PathVariable int idOwner) {
        List<Payment> payments = carService.getPaymentsByIdOwner(idOwner);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/payment/getById/{idPayment}")
    public ResponseEntity<Payment> getPaymentsById(@PathVariable int idPayment) {
        Payment payment = carService.getPaymentById(idPayment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PutMapping("/payment/update/{idPayment}")
    public void updatePayment(@PathVariable int idPayment, @RequestBody Payment payment) {
        carService.editPayment(idPayment, payment);
    }

    @PostMapping("/payment/save")
    public void savePayment(@RequestBody Payment payment) {
        carService.savePayment(payment);
    }


    //ИНФОРМАЦИЯ ОБ АВТОМОБИЛЕ
    @GetMapping("/carInfo/all")
    public ResponseEntity<List<CarInformation>> getAllCarInformation() {
        List<CarInformation> carInformationList = carService.getAllCarInformation();
        return new ResponseEntity<>(carInformationList, HttpStatus.OK);
    }

    @GetMapping("/carInfo/getById/{idCarInfo}")
    public ResponseEntity<CarInformation> getCarInfoById(@PathVariable int idCarInfo) {
        CarInformation carInformation = carService.getCarInformationById(idCarInfo);
        return new ResponseEntity<>(carInformation, HttpStatus.OK);
    }

    //СТАТУС
    @GetMapping("/status/all")
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = carService.getAllStatuses();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }


    //МЕСТА
    @GetMapping("/places/all")
    public ResponseEntity<List<ParkingSpace>> getAllPlaces() {
        List<ParkingSpace> spaces = carService.getAllParkingSpaces();
        return new ResponseEntity<>(spaces, HttpStatus.OK);
    }

    @GetMapping("/places/getById/{idPlace}")
    public ResponseEntity<ParkingSpace> getParkingSpaceById(@PathVariable int idPlace) {
        ParkingSpace space = carService.getSpaseById(idPlace);
        return new ResponseEntity<>(space, HttpStatus.OK);
    }

    @GetMapping("/places/allFreeSpaces")
    public ResponseEntity<List<ParkingSpace>> getAllFreePlaces() {
        List<ParkingSpace> spaces = carService.getAllFreeParkingSpaces();
        return new ResponseEntity<>(spaces, HttpStatus.OK);
    }

    //МЕНЕДЖЕРЫ
    @GetMapping("/managers/all")
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = carService.getAllManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/managers/getById/{idManager}")
    public ResponseEntity<Manager> getManagerById(@PathVariable int idManager) {
        Manager manager = carService.getManagerById(idManager);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @PutMapping("/managers/update/{idManager}")
    public void updateManager(@PathVariable int idManager, @RequestBody Manager manager) {
        carService.editManager(idManager, manager);
    }


}





