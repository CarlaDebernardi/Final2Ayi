package com.ayi.final2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorValidationDto {
    private int status;
    private HashMap<String, String> mistakes;
}
