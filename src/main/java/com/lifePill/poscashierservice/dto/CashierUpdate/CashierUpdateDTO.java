package com.lifePill.poscashierservice.dto.CashierUpdate;

import com.lifePill.poscashierservice.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateDTO {
    private int cashierId;
    private String cashierNicName;
    private String cashierEmail;
    private String cashierNic;
    private String cashierPhone;
    private double cashierSalary;
    private Role role;
    private String branchCode;

}