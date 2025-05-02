package com.example.CourseWork.Controller;

import com.example.CourseWork.Model.Entity.CarRegistration;
import com.example.CourseWork.Service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class CarRegController {

    private final CarService carService;

    public CarRegController(CarService carService) {
        this.carService = carService;
    }


    //РЕГИСТРАЦИЯ
    @GetMapping("/carReg/all")
    public ResponseEntity<List<CarRegistration>> findAllCarRegistrations() {
        List<CarRegistration> cars = carService.getAllCarRegistrations();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/carReg/getById/{idCarInfo}")
    public ResponseEntity<CarRegistration> findCarRegistrationById(@PathVariable int idCarInfo) {
        CarRegistration carRegistration = carService.getCarRegistrationById(idCarInfo);
        return new ResponseEntity<>(carRegistration, HttpStatus.OK);
    }
    @GetMapping("/carReg/getByIdOwner/{idCarOwner}")
    public ResponseEntity<List<CarRegistration>> findAllCarRegistrationsByCarOwnerId(@PathVariable int idCarOwner) {
        List<CarRegistration> carRegistrations = carService.getCarRegistrationsByCarOwnerId(idCarOwner);
        return new ResponseEntity<>(carRegistrations, HttpStatus.OK);
    }

    @PostMapping("/carReg/saveCarReg")
    public void saveCarRegistration(@RequestBody CarRegistration newCarRegistration) {
        carService.saveCarRegistration(newCarRegistration);
    }

    @GetMapping(value = "/carReg/delete/{idCarReg}")
    public void deleteCarRegistration(@PathVariable int idCarReg) {
        carService.deleteCarRegistration(idCarReg);
    }

    @PutMapping("/carReg/update/{idCarReg}")
    public void updateCarReg(@PathVariable int idCarReg, @RequestBody CarRegistration carRegistration) {
        carService.editCarRegistration(idCarReg, carRegistration);
    }

}
