package com.spring.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCreditDto {
    private int id;
    private Date dateEntreeRelation;
    private String observation;



}
