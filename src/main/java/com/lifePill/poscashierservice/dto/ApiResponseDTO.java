package com.lifePill.poscashierservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO {
    private CashierDTO cashierDTO;
    private BranchDTO branchDTO;
}
