package it.epicode.w6d5.service;

import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.repository.DipendenteRepository;
import it.epicode.w6d5.request.DipendenteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Page<Dipendente> getAll(Pageable pageable){
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getDipendenteById(int id) throws NotFoundException {
        return dipendenteRepository.findById(id).orElseThrow(()->new NotFoundException("Dipendente con id= " + id + " non trovata"));
    }

    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest){
        Dipendente d = new Dipendente();
        d.setNome(dipendenteRequest.getNome());
        d.setCognome(dipendenteRequest.getCognome());
        d.setEmail(dipendenteRequest.getEmail());
        d.setUsername(dipendenteRequest.getUsername());

        return dipendenteRepository.save(d);
    }

    public Dipendente updateDipendente(int id, DipendenteRequest dipendenteRequest) throws NotFoundException {
        Dipendente d = getDipendenteById(id);
        d.setNome(dipendenteRequest.getNome());
        d.setCognome(dipendenteRequest.getCognome());
        d.setEmail(dipendenteRequest.getEmail());
        d.setUsername(dipendenteRequest.getUsername());


        return dipendenteRepository.save(d);
    }

    public void deleteDipendente(int id) throws NotFoundException {
        Dipendente d = getDipendenteById(id);
        dipendenteRepository.delete(d);
    }

    public Dipendente uploadImmagine(int id, String url) throws NotFoundException{

        Dipendente d = getDipendenteById(id);
        d.setLogo(url);

        return dipendenteRepository.save(d);


    }

}
