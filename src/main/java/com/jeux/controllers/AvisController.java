package com.jeux.controllers;

import com.jeux.models.Avis;
import com.jeux.models.Jeu;
import com.jeux.repositories.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/avis")
public class AvisController {

    @Autowired
    private AvisRepository avisRepository;

    @GetMapping(value = "/")
    List<Avis> all() {
        return avisRepository.findAll();
    }

    @GetMapping(value = "/{avis}")
    Avis getOne(@PathVariable(name = "avis", required = false) Avis avis){
        if (avis == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"Avis introuvable"
            );
        }
        else {
            return  avis ;
        }
    }

    //POST
    @PostMapping(value = "/{jeu}")
    public ResponseEntity<Avis> saveAvis(@PathVariable(name = "jeu", required = true) Jeu jeu,
                                             @Valid @RequestBody Avis avis ){

        avis.setJeu(jeu);
        avisRepository.save(avis);
        return new ResponseEntity<>(avis,HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(value = "/{avis}")
    public void deleteOne(@PathVariable(name = "avis", required = false) Avis avis) {
        if (avis == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Avis introuvable"
            );
        } else {
            avisRepository.delete(avis);
        }
    }

    //PUT
    @PutMapping(value = "/{avis}")
    public ResponseEntity<Avis> update(@PathVariable(name = "avis", required = false) Avis avis,
                                         @Valid @RequestBody Avis avisUpdate, BindingResult bindingResult){
        if(avis== null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Avis introuvable"
            );
        }
        else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }
            else {
                avisUpdate.setId(avis.getId());
                avisRepository.save(avisUpdate);
                return new ResponseEntity<>(avisUpdate,HttpStatus.OK);
            }
        }
    }
}
