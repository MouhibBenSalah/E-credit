package com.spring.user.DTO;

import com.spring.user.Enum.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private double revenuMensuel;
    private double salaire;
    private double chargesMensuelles;
    private int age;
    private EmploymentType employmentType;
}
