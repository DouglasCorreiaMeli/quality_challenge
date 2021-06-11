package com.meli.homeAppraisal.domain.dto.request;

import com.meli.homeAppraisal.domain.validator.HomeNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HomeRequest {

    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    @NotEmpty(message = "o nome da propriedade não pode estar vazio.")
    @NotBlank(message = "o nome da propriedade não pode estar em branco.")
    @HomeNameConstraint(message = "O nome da propriedade deve começar com uma letra maiúscula.")
    String propName;

    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    @NotEmpty(message = "o nome do bairro não pode estar vazio.")
    @NotBlank(message = "o nome do bairro não pode estar em branco.")
    String propDistrict;

    @Valid
    @NotEmpty(message = "a casa precisa ter cômodos")
    List<RoomRequest> rooms;
}
