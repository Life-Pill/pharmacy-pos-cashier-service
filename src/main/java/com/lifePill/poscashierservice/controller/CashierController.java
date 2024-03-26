package com.lifePill.poscashierservice.controller;

import com.lifePill.poscashierservice.dto.ApiResponseDTO;
import com.lifePill.poscashierservice.dto.CashierDTO;
import com.lifePill.poscashierservice.dto.CashierUpdate.*;
import com.lifePill.poscashierservice.service.CashierService;
import com.lifePill.poscashierservice.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("lifepill/v1/cashier")
@CrossOrigin
public class CashierController {

    @Autowired
    private CashierService cashierService;
    @PostMapping("/save")
    public String saveCashier(@RequestBody CashierDTO cashierDTO){
        cashierService.saveCashier(cashierDTO);
        return "saved";
    }
    @PutMapping("/update")
    public String updateCashier(@RequestBody CashierUpdateDTO cashierUpdateDTO){
        String message = cashierService.updateCashier(cashierUpdateDTO);
        return message;
    }
    @PutMapping("/updateAccountDetails")
    public String updateCashierAccountDetails(@RequestBody CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO){
        String message = cashierService.updateCashierAccountDetails(cashierUpdateAccountDetailsDTO);
        return message;
    }
    @PutMapping("/updateBankAccountDetails")
    public String updateCashierBankAccountDetails(@RequestBody CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO){
        String message = cashierService.updateCashierBankAccountDetails(cashierUpdateBankAccountDTO);
        return message;
    }

    @PutMapping("/updatePassword")
    public String updateCashierPassword(@RequestBody CashierPasswordResetDTO cashierPasswordResetDTO){
        String message = cashierService.updateCashierPassword(cashierPasswordResetDTO);
        return message;
    }

    @PutMapping("/updateRecentPin")
    public String updateRecentPin(@RequestBody CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO){
        String message = cashierService.updateRecentPin(cashierRecentPinUpdateDTO);
        return message;
    }

    @GetMapping(path = "/get-by-id",params = "id")
    public ResponseEntity<ApiResponseDTO> getCashierById(@RequestParam(value = "id") int cashierId) {
        ApiResponseDTO apiResponseDTO = cashierService.getCashierById(cashierId);
        return new ResponseEntity<>(apiResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-cashier/{id}")
    public String deleteCashier(@PathVariable(value = "id") int cashierId){
        String deleted = cashierService.deleteCashier(cashierId);
        return deleted;
    }
/*
    @GetMapping(path="/get-all-cashiers")
    public List<CashierDTO> getAllCashiers(){
        List<CashierDTO> allCashiers = cashierService.getAllCashiers();
        return allCashiers;
    }
    */
@GetMapping(path="/get-all-cashiers")
public ResponseEntity<StandardResponse> getAllCashiers(){
    List<CashierDTO> allCashiers = cashierService.getAllCashiers();
    return new ResponseEntity<>(
            new StandardResponse(201,"SUCCESS", allCashiers),
            HttpStatus.OK
    );
}
    @GetMapping(path = "/get-all-cashiers-by-active-state/{status}")
    public List<CashierDTO> getAllCashiersByActiveState(@PathVariable(value = "status") boolean activeState) {
        List<CashierDTO> allCashiers = cashierService.getAllCashiersByActiveState(activeState);
        return allCashiers;
    }

@GetMapping(path = "/get-all-cashiers-bank-details")
public ResponseEntity<StandardResponse> getAllCashiersBankDetails() {
    List<CashierUpdateBankAccountDTO> allCashiersBankDetails = cashierService.getAllCashiersBankDetails();
    return new ResponseEntity<StandardResponse>(
            new StandardResponse(201, "SUCCESS", allCashiersBankDetails),
            HttpStatus.OK
    );
}
}