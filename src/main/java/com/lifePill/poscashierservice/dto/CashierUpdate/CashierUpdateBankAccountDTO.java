package com.lifePill.poscashierservice.dto.CashierUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateBankAccountDTO {
    private int cashierId;
    private String bankName;
    private String bankBranchName;
    private String bankAccountNumber;
    private String cashierDescription;
    private double monthlyPayment;
    private boolean monthlyPaymentStatus;
}
