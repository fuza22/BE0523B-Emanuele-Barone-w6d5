package it.epicode.w6d5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;


}
