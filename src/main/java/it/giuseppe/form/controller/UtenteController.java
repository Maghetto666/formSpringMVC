package it.giuseppe.form.controller;

import it.giuseppe.form.model.Utente;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UtenteController {

    @GetMapping("/form")
    public String getForm(@ModelAttribute Utente utente) {
        return "form";
    }

    @PostMapping("/submitForm")
    public String postForm(@Valid @ModelAttribute Utente utente, BindingResult res, Model model) {
        if ((utente.getNome().equalsIgnoreCase("admin")) || (utente.getNome().equalsIgnoreCase("root"))) {
            model.addAttribute("error", "Il nome inserito non può essere utilizzato.");
            return "error";
        }
//        if (utente.getNome().length() < 2 || utente.getNome().length() > 20) {
//            model.addAttribute("error", "Il nome inserito deve avere una lunghezza compresa tra i 2 ed i 20 caratteri.");
//            return "error";
//        }
        if (res.hasErrors()) {
            return "form";
        }

        for (int i = 0; i < utente.getNumeroDiTelefono().length(); i++) {
            char current = utente.getNumeroDiTelefono().charAt(i);
            try {
                Integer.parseInt(current + "");
            } catch (Exception nfEx) {
                if (!(current == '+' || current == ' ')) {
                    model.addAttribute("error", "Il numero di telefono può contenere solo numeri, spazi e il carattere \"+\"");
                    return "error";
                }
            }
        }

        if (!Objects.equals(utente.getPassword(), utente.getConfermaPassword())) {
            model.addAttribute("error", "Le due password devono coincindere.");
            return "error";
        }
        model.addAttribute("utente", utente);
        return "result";
    }


}
