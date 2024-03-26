package com.lifePill.poscashierservice.service;


import com.lifePill.poscashierservice.dto.ApiResponseDTO;
import com.lifePill.poscashierservice.dto.CashierDTO;
import com.lifePill.poscashierservice.dto.CashierUpdate.*;

import java.util.List;

public interface CashierService {

    public String saveCashier(CashierDTO cashierDTO);
    String updateCashier(CashierUpdateDTO cashierUpdateDTO);
    ApiResponseDTO getCashierById(int cashierId);
    String deleteCashier(int cashierId);
    List<CashierDTO> getAllCashiers();
    List<CashierDTO> getAllCashiersByActiveState(boolean activeState);

    String updateCashierAccountDetails(CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO);

    String updateCashierPassword(CashierPasswordResetDTO cashierPasswordResetDTO);

    String updateRecentPin(CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO);

    String updateCashierBankAccountDetails(CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO);

    List<CashierUpdateBankAccountDTO> getAllCashiersBankDetails();
}
