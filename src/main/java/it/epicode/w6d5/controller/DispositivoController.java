package it.epicode.w6d5.controller;

import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.exception.CustomResponse;
import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.request.DipendenteRequest;
import it.epicode.w6d5.request.DispositivoRequest;
import it.epicode.w6d5.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {

    @Autowired
    DispositivoService dispositivoService;

    @GetMapping("")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try{

            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.getAll(pageable), HttpStatus.OK);

        }catch(Exception e){

            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getDispositivoById(@PathVariable int id){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.getDispositivoById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("")
    public ResponseEntity<CustomResponse> saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.saveDispositivo(dispositivoRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.updateDispositivo(id, dispositivoRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteDispositivo(@PathVariable int id){

        try {
            dispositivoService.deleteDispositivo(id);
            return CustomResponse.emptyResponse("Il dispositivo con id = " + id + " è stato cancellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/{id}/assegna")
    public ResponseEntity<CustomResponse> setDipendente(@PathVariable int id, @RequestParam int idDipendente){
        try {
            Dispositivo d = dispositivoService.assegnaDipendente(idDipendente, id);
            return CustomResponse.success(HttpStatus.OK.toString(), d, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
