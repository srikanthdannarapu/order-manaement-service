package com.orderitem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ErrorItems {
    private List<ErrorDetails> errors;
    public void addErrorItems(ErrorDetails errorDetails) {
        this.errors.add(errorDetails);
    }
}
