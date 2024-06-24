package com.spring.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCreditDTO {
    private int id;
    private Date dateEntreeRelation;
    private String observation;



}
