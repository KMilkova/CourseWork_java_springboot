package com.example.CourseWork.Controller;

import com.example.CourseWork.Model.Entity.Contractdocument;
import com.example.CourseWork.Model.Entity.Contracts;
import com.example.CourseWork.Service.CarService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class ContractsController {

    private final CarService carService;

    public ContractsController(CarService carService) {
        this.carService = carService;
    }

    //ДОГОВОРЫ
    @GetMapping("/contracts/all")
    public ResponseEntity<List<Contracts>> getAllContracts() {
        List<Contracts> contracts = carService.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/contracts/getByIdOwner/{idOwner}")
    public ResponseEntity<List<Contracts>> getAllContractsByIdCarOwner(@PathVariable int idOwner) {
        List<Contracts> contracts = carService.getAllContractsByIdCarOwner(idOwner);
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/contracts/getById/{idContract}")
    public ResponseEntity<Contracts> getContractById(@PathVariable int idContract) {
        Contracts contract = carService.getContractById(idContract);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
    @PostMapping("/contracts/save")
    public void saveContract(@RequestBody Contracts contract) {
        carService.saveContract(contract);
    }

    @PutMapping("/contracts/update/{idContract}")
    public void updateContract(@PathVariable int idContract, @RequestBody Contracts contract) {
        carService.updateContract(idContract, contract);
    }


    @GetMapping(value = "/contracts/delete/{idContract}")
    public void deleteContract(@PathVariable int idContract) {
        carService.deleteContract(idContract);
    }


    //ДОКУМЕНТЫ
    @PostMapping("/contractdoc/save")
    public ResponseEntity<?> createContractDoc(@RequestBody Contracts contract) {
        try {
            Contractdocument savedContract = carService.saveContractDoc(contract);
            return ResponseEntity.ok().body(savedContract);
        } catch (DocumentException e) {
            return ResponseEntity.status(500).body("Error generating PDF");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/contractdoc/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        Contractdocument fileEntity = carService.getFile(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileEntity.getDocumentName(), StandardCharsets.UTF_8).build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileEntity.getDocumentData());
    }

    @GetMapping("/contractdoc/all")
    public ResponseEntity<List<Contractdocument>> getAllContractDoc() {
        List<Contractdocument> managers = carService.getAllContractDoc();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/contractdoc/deleteContractdoc/{id}")
    public void deleteContractDoc(@PathVariable("id") int id) {
        carService.deleteContractDoc(id);
    }
}
