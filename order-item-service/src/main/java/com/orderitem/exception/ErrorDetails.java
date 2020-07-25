package com.orderitem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;

}