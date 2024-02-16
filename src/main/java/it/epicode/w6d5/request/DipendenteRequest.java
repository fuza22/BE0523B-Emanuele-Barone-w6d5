package it.epicode.w6d5.request;

import it.epicode.w6d5.model.Dispositivo;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {

    @NotNull(message = "Campo obbligatorio")
    @NotEmpty(message = "Campo vuoto")
    @NotBlank
    private String username;
    @NotNull(message = "Campo obbligatorio")
    @NotEmpty(message = "Campo vuoto")
    @NotBlank
    private String nome;
    @NotNull(message = "Campo obbligatorio")
    @NotEmpty(message = "Campo vuoto")
    @NotBlank
    private String cognome;
    @Email(message = "Inserire una mail valida")
    @NotEmpty(message = "Campo vuoto")
    private String email;


}
