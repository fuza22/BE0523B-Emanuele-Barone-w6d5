package it.epicode.w6d5.service;

import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.repository.DipendenteRepository;
import it.epicode.w6d5.repository.DispositivoRepository;
import it.epicode.w6d5.request.DispositivoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAll(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispositivoById(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).orElseThrow(()->new NotFoundException("Dispositivo con id= " + id + " non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest){
        Dispositivo d = new Dispositivo();
        d.setDisponibilita(dispositivoRequest.getDisponibilita());
        d.setTipologia(dispositivoRequest.getTipologia());

        return dispositivoRepository.save(d);
    }

    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException {
        Dispositivo d = new Dispositivo();
        d.setDipendente(dispositivoRequest.getDipendente());
        d.setDisponibilita(dispositivoRequest.getDisponibilita());
        d.setTipologia(dispositivoRequest.getTipologia());



        return dispositivoRepository.save(d);
    }

    public void deleteDispositivo(int id) throws NotFoundException {
        Dispositivo d = getDispositivoById(id);
        dispositivoRepository.delete(d);
    }

    public Dispositivo assegnaDipendente(int idDispositivo, int idDipendente) throws NotFoundException{

        Dipendente dipendente = dipendenteService.getDipendenteById(idDipendente);
        Dispositivo dispositivo = getDispositivoById(idDispositivo);
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);

    }

}
