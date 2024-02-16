package it.epicode.w6d5.request;

import it.epicode.w6d5.enums.Disponibilita;
import it.epicode.w6d5.enums.Tipologia;
import it.epicode.w6d5.model.Dipendente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {

    @NotNull(message = "Inserire una disponibilità")
    private Disponibilita disponibilita;

    @NotNull(message = "Inserire una tipologia")
    private Tipologia tipologia;

    private Dipendente dipendente;


}
