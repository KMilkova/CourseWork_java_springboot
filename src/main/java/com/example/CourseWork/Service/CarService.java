package com.example.CourseWork.Service;
import com.example.CourseWork.Dao.*;
import com.example.CourseWork.Model.Entity.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private CarDao carsDao = new CarDaoImpl();
    private CarOwnerDao ownerDao = new CarOwnerDaoImpl();
    private AuthorizationDao authorizationDao = new AuthorizationDaoImpl();
    private ContractsDao contractDao = new ContractsDaoImpl();
    private PaymentDao paymentDao = new PaymentDaoImpl();

    //МАШИНЫ
    public List<Car> getAllCars() {
        return carsDao.getAllCars();
    }

    public Car getCarById(int idCar) {
        return carsDao.getCarById(idCar);
    }

    public void saveCar(Car car) {
        carsDao.saveCar(car);
    }

    public void updateCar(int id, Car car) {
        carsDao.updateCar(id, car);
    }

    public Car getLast_() {
        return carsDao.getLast();
    }

    //ЖУРНАЛ
    public void deleteJournalById(int id) {
        carsDao.deleteJournalById(id);
    }


    //РЕГИСТРАЦИЯ
    public List<CarRegistration> getAllCarRegistrations() {
        return carsDao.getAllCarRegistrations();
    }

    public CarRegistration getCarRegistrationById(int idCarInformation) {
        return carsDao.getCarRegistrationById(idCarInformation);
    }

    public List<CarRegistration> getCarRegistrationsByCarOwnerId(int idCarOwner) {
        return carsDao.getCarRegistrationsByCarOwnerId(idCarOwner);
    }

    public void deleteCarRegistration(int idRegistration) {
        carsDao.deleteCarRegistration(idRegistration);
    }

    public void saveCarRegistration(CarRegistration newCarRegistration) {
        carsDao.saveCarRegistration(newCarRegistration);
    }

    public void editCarRegistration(int idCarRegistration, CarRegistration newCarRegistration) {
        carsDao.editCarRegistration(idCarRegistration, newCarRegistration);
    }

    public List<Car> showAllCarWithOutReg() throws SQLException {
        return carsDao.showAllCarWithOutReg();
    }

    //ВЛАДЕЛЕЦ
    public CarOwner getCarOwnerById(int id) {
        return ownerDao.getCarOwnerById(id);
    }

    public List<CarOwner> getAllCarOwners() {
        return ownerDao.getAllCarOwners();
    }

    public CarOwner getLast() {
        return ownerDao.getLast();
    }



    public void saveCarOwner(CarOwner carOwner) {
        ownerDao.saveCarOwner(carOwner);
    }

    public CarOwner getCarOwnerByIdAuthorization(int idAuthorization){
        return ownerDao.getCarOwnerByIdAuthorization(idAuthorization);
    }


    //ДОГОВОРЫ
    public List<Contracts> getAllContracts() {
        return contractDao.getAllContracts();
    }

    public List<Contracts> getAllContractsByIdCarOwner(int idCarOwner) {
        return contractDao.getAllContractsByIdCarOwner(idCarOwner);
    }

    public Contracts getContractById(int idContract) {
        return contractDao.getContractById(idContract);
    }


    public void saveContract(Contracts contract) {
        contractDao.saveContract(contract);
    }

    public void updateContract(int idContract, Contracts newContract) {
        contractDao.updateContract(idContract, newContract);
    }

    public void deleteContract(int idContract) {
        contractDao.deleteContract(idContract);
    }

    //ОПЛАТА
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    public List<Payment> getPaymentsByIdContract(int idContract) {
        return paymentDao.getPaymentsByIdContract(idContract);
    }

    public List<Payment> getPaymentsByIdOwner(int idOwner){
        return paymentDao.getPaymentsByIdOwner(idOwner);
    }


    public void savePayment(Payment payment){
        paymentDao.savePayment(payment);
    }
    public void editPayment(int idPayment, Payment payment){
        paymentDao.editPayment(idPayment,payment);
    }

    public Payment getPaymentById(int idPayment){
        return paymentDao.getPaymentById(idPayment);
    }

    //ИНФОРМАЦИЯ ОБ АВТОМОБИЛЕ
    public List<CarInformation> getAllCarInformation() {
        return carsDao.getAllCarInformation();
    }

    public CarInformation getCarInformationById(int idCarInformation) {
        return carsDao.getCarInformationById(idCarInformation);
    }


    //СТАТУС
    public List<Status> getAllStatuses() {
        return carsDao.getAllStatuses();
    }

    //АВТОРИЗАЦИЯ
    public void saveAuthorization(Authorization authorization) {
        authorizationDao.saveAuthorization(authorization);
    }

    public Status getStatusByID(int id) {
        return carsDao.getStatusByID(id);
    }

    //МЕСТА
    public List<ParkingSpace> getAllParkingSpaces() {
        return contractDao.getAllParkingSpaces();
    }

    public ParkingSpace getSpaseById(int idSpace) {
        return contractDao.getSpaseById(idSpace);
    }

    public List<ParkingSpace> getAllFreeParkingSpaces() {
        return contractDao.getAllFreeParkingSpaces();
    }

    //МЕНЕДЖЕРЫ
    public List<Manager> getAllManagers(){
        return contractDao.getAllManagers();
    }
    public Manager getManagerById(int idManager){
        return contractDao.getManagerById(idManager);
    }
    public void editManager(int idManager, Manager manager){
        contractDao.editManager(idManager,manager);
    }

    //ДОКУМЕНТ
        private final ContractDocRepo contractRepository;

    public Contractdocument saveContractDoc(Contracts contract) throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        // Use a built-in font that supports Cyrillic characters
        BaseFont russianFont = BaseFont.createFont("C:\\Users\\nasty\\CourseWork\\src\\main\\resources\\fonts\\ofont.ru_Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font font = new com.itextpdf.text.Font(russianFont, 14);

        // Add paragraphs with the specified font
        document.add(new Paragraph("Владелец автомобиля: "+contract.getIdRegistration().getIdOwner().getFullName(), font));
        document.add(new Paragraph("Номер автомобиля: " + contract.getIdRegistration().getIdCar().getCarNumber(), font));
        document.add(new Paragraph("Номер договора: " + contract.getContractNumber(), font));
        document.add(new Paragraph("Место: " + contract.getIdSpace().getPlaceNumber() + ", этаж " + contract.getIdSpace().getFloor(), font));
        document.add(new Paragraph("Менеджер: " + contract.getIdManager().getFioManager(), font));
        document.add(new Paragraph("Дата начала: " + contract.getStartDate(), font));
        document.add(new Paragraph("Дата окончания: " + contract.getEndDate(), font));
        document.add(new Paragraph("Статус: " + contract.getIdStatus().getStatusType(), font));

        document.close();

        Contractdocument contractEntity = new Contractdocument();
        contractEntity.setDocumentName(String.valueOf(contract.getContractNumber()));
        contractEntity.setDocumentType("application/pdf");
        contractEntity.setDocumentData(out.toByteArray());
        contractEntity.setIdOwner(contract.getIdRegistration().getIdOwner());
        return contractRepository.save(contractEntity);
    }

    public Contractdocument getFile(Integer fileId) {
        return contractRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<Contractdocument> getAllContractDoc(){
        return contractRepository.findAll();
    }
    public void deleteContractDoc(int idDocumentation){
        contractDao.deleteContractDoc(idDocumentation);
    }
}