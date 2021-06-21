package com.spcn.spcnservices.spcnspchandlingservice.services.shs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TakeInfo {
    Long takeId;
    boolean possiblyLost;
}