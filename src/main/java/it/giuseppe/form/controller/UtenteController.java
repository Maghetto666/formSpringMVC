package it.giuseppe.form.controller;

import it.giuseppe.form.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UtenteController {

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }

    @PostMapping("/submitForm")
    public String postForm(@ModelAttribute Utente utente, Model model) {
        if ((utente.getNome().equalsIgnoreCase("admin")) || (utente.getNome().equalsIgnoreCase("root"))) {
            return "error";
        }
        if (utente.getNome().length() < 2 || utente.getNome().length() > 20) {
            return "error";
        }

        for (int i = 0; i < utente.getNumeroDiTelefono().length(); i++) {
            char current = utente.getNumeroDiTelefono().charAt(i);
            try {
                Integer.parseInt(current + "");
            } catch (Exception nfEx) {
                if (!(current == '+' || current == ' ')) {
                    return "error";
                }
            }
        }

        if (!Objects.equals(utente.getPassword(), utente.getConfermaPassword())) {
            return "error";
        }
        model.addAttribute("utente", utente);
        return "result";
    }


}
