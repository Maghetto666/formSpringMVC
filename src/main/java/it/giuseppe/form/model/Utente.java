package it.giuseppe.form.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Utente {

    @NotEmpty(message="Il nome non può essere vuoto")
    @Size(min = 2, max = 30, message="Il nome deve essere compreso tra i 2 e i 30 caratteri")
    private String nome;

    @NotEmpty(message = "L'email non può essere vuota")
    @Email(message = "Devi inserire un'email")
    private String email;

    private String password;
    private String confermaPassword;
    private String dataDiNascita;
    private String numeroDiTelefono;
}
