package com.lifePill.poscashierservice.service.Impl;

import com.lifePill.poscashierservice.dto.ApiResponseDTO;
import com.lifePill.poscashierservice.dto.BranchDTO;
import com.lifePill.poscashierservice.dto.CashierDTO;
import com.lifePill.poscashierservice.dto.CashierUpdate.*;
import com.lifePill.poscashierservice.entity.Cashier;
import com.lifePill.poscashierservice.entity.CashierBankDetails;
import com.lifePill.poscashierservice.exception.EntityDuplicationException;
import com.lifePill.poscashierservice.exception.NotFoundException;
import com.lifePill.poscashierservice.repository.CashierBankDetailsRepo;
import com.lifePill.poscashierservice.repository.CashierRepo;
import com.lifePill.poscashierservice.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashierServiceIMPL implements CashierService {

    @Autowired
    private CashierRepo cashierRepo;

    @Autowired
    private CashierBankDetailsRepo cashierBankDetailsRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String saveCashier(CashierDTO cashierDTO) {
        // check if the cashier already exists email or id
        if (cashierRepo.existsById(cashierDTO.getCashierId()) || cashierRepo.existsAllByCashierEmail(cashierDTO.getCashierEmail())) {
            throw new EntityDuplicationException("Cashier already exists");
        } else {

            Cashier cashier = new Cashier(
                    cashierDTO.getCashierId(),
                    cashierDTO.getCashierNicName(),
                    cashierDTO.getCashierFirstName(),
                    cashierDTO.getCashierLastName(),
                    cashierDTO.getCashierPassword(),
                    cashierDTO.getCashierEmail(),
                    cashierDTO.getCashierPhone(),
                    cashierDTO.getCashierAddress(),
                    cashierDTO.getCashierSalary(),
                    cashierDTO.getCashierNic(),
                    cashierDTO.isActiveStatus(),
                    cashierDTO.getPin(),
                    cashierDTO.getGender(),
                    cashierDTO.getDateOfBirth(),
                    cashierDTO.getRole(),
                    cashierDTO.getBranchCode()
                    //  (Set<Order>) cashierDTO.getOrder()
            );

            cashierRepo.save(cashier);
            return "Saved";
        }

    }

    @Override
    public String updateCashier(CashierUpdateDTO cashierUpdateDTO) {
        if (cashierRepo.existsById(cashierUpdateDTO.getCashierId())) {
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateDTO.getCashierId());

            cashier.setCashierNicName(cashierUpdateDTO.getCashierNicName());
            cashier.setCashierEmail(cashierUpdateDTO.getCashierEmail());
            cashier.setCashierNic(cashierUpdateDTO.getCashierNic());
            cashier.setCashierPhone(cashierUpdateDTO.getCashierPhone());
            cashier.setRole(cashierUpdateDTO.getRole());
            cashier.setCashierSalary(cashierUpdateDTO.getCashierSalary());
            cashier.setBranchCode(cashierUpdateDTO.getBranchCode());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "UPDATED CUSTOMER";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierAccountDetails(CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO) {
        if (cashierRepo.existsById(cashierUpdateAccountDetailsDTO.getCashierId())) {
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateAccountDetailsDTO.getCashierId());

            cashier.setCashierFirstName(cashierUpdateAccountDetailsDTO.getCashierFirstName());
            cashier.setCashierLastName(cashierUpdateAccountDetailsDTO.getCashierLastName());
            cashier.setGender(cashierUpdateAccountDetailsDTO.getGender());
            cashier.setCashierAddress(cashierUpdateAccountDetailsDTO.getCashierAddress());
            cashier.setDateOfBirth(cashierUpdateAccountDetailsDTO.getDateOfBirth());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Update cashier account details";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierPassword(CashierPasswordResetDTO cashierPasswordResetDTO) {
        if (cashierRepo.existsById(cashierPasswordResetDTO.getCashierId())) {
            Cashier cashier = cashierRepo.getReferenceById(cashierPasswordResetDTO.getCashierId());

            cashier.setCashierPassword(cashierPasswordResetDTO.getCashierPassword());
            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Reset cashier password";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateRecentPin(CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        if (cashierRepo.existsById(cashierRecentPinUpdateDTO.getCashierId())) {
            Cashier cashier = cashierRepo.getReferenceById(cashierRecentPinUpdateDTO.getCashierId());

            cashier.setPin(cashierRecentPinUpdateDTO.getPin());
            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Reset cashier PIN";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierBankAccountDetails(CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        int cashierId = cashierUpdateBankAccountDTO.getCashierId();

        if (cashierRepo.existsById(cashierId)) {
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            // Check if the cashier already has bank details
            CashierBankDetails existingBankDetails = cashierBankDetailsRepo.findById(cashierId).orElse(null);

            if (existingBankDetails != null) {
                // Update existing bank details
                existingBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
                existingBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
                existingBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
                existingBankDetails.setCashierDescription(cashierUpdateBankAccountDTO.getCashierDescription());
                existingBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
                existingBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(existingBankDetails);
            } else {
                // Create new bank details if not present
                CashierBankDetails newBankDetails = new CashierBankDetails();
                newBankDetails.setCashierId(cashierId);
                newBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
                newBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
                newBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
                newBankDetails.setCashierDescription(cashierUpdateBankAccountDTO.getCashierDescription());
                newBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
                newBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(newBankDetails);
            }

            return "Successfully updated cashier bank account details";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }


    @Override
    public ApiResponseDTO getCashierById(int cashierId) {
        if (cashierRepo.existsById(cashierId)) {
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            String branchBaseUrl = "http://localhost:8083/lifepill/v1/branch/get-all-branches?branchCode=%s";
            String branchCode = cashier.getBranchCode();
            String url = String.format(branchBaseUrl, branchCode);

            ResponseEntity<BranchDTO> responseEntity = restTemplate.getForEntity(url, BranchDTO.class);

            BranchDTO branchDTO = responseEntity.getBody();

            CashierDTO cashierDTO = new CashierDTO(
                    cashier.getCashierId(),
                    cashier.getCashierNicName(),
                    cashier.getCashierFirstName(),
                    cashier.getCashierLastName(),
                    cashier.getCashierPassword(),
                    cashier.getCashierEmail(),
                    cashier.getCashierPhone(),
                    cashier.getCashierAddress(),
                    cashier.getCashierSalary(),
                    cashier.getCashierNic(),
                    cashier.isActiveStatus(),
                    cashier.getGender(),
                    cashier.getDateOfBirth(),
                    cashier.getRole(),
                    cashier.getPin(),
                    cashier.getBranchCode()
                    // (Order) cashier.getOrders()
            );

            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

            apiResponseDTO.setCashierDTO(cashierDTO);
            apiResponseDTO.setBranchDTO(branchDTO);

            return apiResponseDTO;
        } else {
            throw new NotFoundException("No cashier found for that id");
        }

    }

    @Override
    public String deleteCashier(int cashierId) {
        if (cashierRepo.existsById(cashierId)) {
            cashierRepo.deleteById(cashierId);

            return "deleted succesfully : " + cashierId;
        } else {
            throw new NotFoundException("No cashier found for that id");
        }
    }

    @Override
    public List<CashierUpdateBankAccountDTO> getAllCashiersBankDetails() {
        List<CashierBankDetails> getAllCashiersBankDetails = cashierBankDetailsRepo.findAll();

        if (getAllCashiersBankDetails.size() > 0) {
            List<CashierUpdateBankAccountDTO> cashierUpdateBankAccountDTOList = new ArrayList<>();
            for (CashierBankDetails cashierBankDetails : getAllCashiersBankDetails) {
                CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO = new CashierUpdateBankAccountDTO(
                        cashierBankDetails.getCashierId(),
                        cashierBankDetails.getBankName(),
                        cashierBankDetails.getBankBranchName(),
                        cashierBankDetails.getBankAccountNumber(),
                        cashierBankDetails.getCashierDescription(),
                        cashierBankDetails.getMonthlyPayment(),
                        cashierBankDetails.getMonthlyPaymentStatus()
                );
                cashierUpdateBankAccountDTOList.add(cashierUpdateBankAccountDTO);
            }
            return cashierUpdateBankAccountDTOList;
        } else {
            throw new NotFoundException("No Cashier Bank Details Found");
        }
    }

    @Override
    public List<CashierDTO> getAllCashiers() {
        List<Cashier> getAllCashiers = cashierRepo.findAll();

        if (getAllCashiers.size() > 0) {
            List<CashierDTO> cashierDTOList = new ArrayList<>();
            for (Cashier cashier : getAllCashiers) {
                CashierDTO cashierDTO = new CashierDTO(
                        cashier.getCashierId(),
                        cashier.getCashierNicName(),
                        cashier.getCashierFirstName(),
                        cashier.getCashierLastName(),
                        cashier.getCashierPassword(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus(),
                        cashier.getGender(),
                        cashier.getDateOfBirth(),
                        cashier.getRole(),
                        cashier.getPin(),
                        cashier.getBranchCode()
                        // (Order) cashier.getOrders()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        } else {
            throw new NotFoundException("No Cashier Found");
        }
    }


    @Override
    public List<CashierDTO> getAllCashiersByActiveState(boolean activeState) {
        List<Cashier> getAllCashiers = cashierRepo.findByIsActiveStatusEquals(activeState);
        if (getAllCashiers.size() > 0) {
            List<CashierDTO> cashierDTOList = new ArrayList<>();
            for (Cashier cashier : getAllCashiers) {
                CashierDTO cashierDTO = new CashierDTO(
                        cashier.getCashierId(),
                        cashier.getCashierNicName(),
                        cashier.getCashierFirstName(),
                        cashier.getCashierLastName(),
                        cashier.getCashierPassword(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus(),
                        cashier.getGender(),
                        cashier.getDateOfBirth(),
                        cashier.getRole(),
                        cashier.getPin(),
                        cashier.getBranchCode()
                        //  (Order) cashier.getOrders()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        } else {
            // throw new RuntimeException("No Cashier Found");
            throw new NotFoundException("No Cashier Found");
        }
    }


}