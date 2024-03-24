package com.lifePill.poscashierservice.dto.CashierUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierRecentPinUpdateDTO {
    private int cashierId;
    private int pin;
}
