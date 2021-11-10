package com.jeux.controllers;

import com.jeux.models.Avis;
import com.jeux.models.Jeu;
import com.jeux.repositories.AvisRepository;
import com.jeux.repositories.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/jeux")
public class JeuController {

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private AvisRepository avisRepository;

    @GetMapping(value = "/")
    List<Jeu> all() {
        return jeuRepository.findAll();
    }

    @GetMapping(value = "/{jeu}/avis")
    List<Avis> getAvis(@PathVariable(name = "jeu", required = true) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jeu introuvable"
            );
        }
        else {
            return avisRepository.findByJeuId(jeu.getId());
        }
    }

    @GetMapping(value = "/{jeu}/dernierAvis")
    List<Avis> getLastAvis(@PathVariable(name = "jeu", required = true) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jeu introuvable"
            );
        }
        else {
            return avisRepository.findByJeuIdOrderByDateEnvoieDesc();
        }
    }

    @GetMapping(value = "/{jeu}")
    Jeu getOne(@PathVariable(name = "jeu", required = false) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jeu introuvable"
            );
        }
        else {
            return jeu;
        }
    }

    //POST
    @PostMapping(value = "/")
    public ResponseEntity<Jeu> saveJeu(@Valid @RequestBody Jeu jeu) {
        jeuRepository.save(jeu);
        return new ResponseEntity<>(jeu, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping(value = "/{jeu}")
    public ResponseEntity<Jeu> update(@PathVariable(name = "jeu", required = false)Jeu jeu,
                                        @Valid @RequestBody Jeu jeuxUpdate, BindingResult bindingResult){
        if(jeu == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jeu introuvable"
            );
        }
        else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }
            else {
                jeuxUpdate.setId(jeu.getId());
                jeuRepository.save(jeuxUpdate);
                return new ResponseEntity<>(jeuxUpdate,HttpStatus.OK);
            }
        }
    }

    //DELETE
    @DeleteMapping(value = "/{jeu}")
    public void deleteOne(@PathVariable(name = "jeu", required = false) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jeu introuvable"
            );
        } else {
            jeuRepository.delete(jeu);
        }
    }
}
