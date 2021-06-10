package com.meli.homeAppraisal.domain.dto.request;

import com.meli.homeAppraisal.domain.validator.HomeNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoomRequest {

    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    @NotEmpty(message = "O nome do cômodo não pode estar vazio.")
    @NotBlank(message = "O nome do cômodo não pode estar em branco.")
    @HomeNameConstraint(message = "O nome do cômodo deve começar com uma letra maiúscula.")
    String roomName;


    @NotEmpty(message = "A largura do cômodo não pode estar vazia.")
    @NotBlank(message = "A largura do cômodo não pode estar em branco.")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros")
    Double roomWidth;

    @NotEmpty(message = "O comprimento do cômodo não pode estar vazia.")
    @NotBlank(message = "O comprimento do cômodo não pode estar em branco.")
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros")
    Double roomLength;
}
