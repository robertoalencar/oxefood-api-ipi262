package br.edu.ifpe.oxefood.api.voto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class VotoService {
    
    private final VotoRepository repository;

    public VotoService(VotoRepository repository) {
        this.repository = repository;
    }

    public Voto build(VotoDTO dto) {

        Voto voto = null;

        if (dto.getId() == null) { //Montado para o cadastro

            voto = new Voto();

        } else { //Consultado para a alteração

            voto = repository.findById(dto.getId()).get();
        }
        
        voto.setDataVotacao(LocalDate.now());
        voto.setNomeEleitor(dto.getNomeEleitor());
        voto.setNumeroRegistroEleitoral(dto.getNumeroRegistroEleitoral());
        voto.setNumeroCandidatoDepFederal(dto.getNumeroCandidatoDepFederal());
        voto.setNumeroCandidatoDepEstadual(dto.getNumeroCandidatoDepEstadual());
        voto.setNumeroCandidatoSenador(dto.getNumeroCandidatoSenador());
        voto.setNumeroCandidatoGovernador(dto.getNumeroCandidatoGovernador());
        voto.setNumeroCandidatoPresidente(dto.getNumeroCandidatoPresidente());
        
        return voto;
    }

    @Transactional
    public Voto cadastrar(VotoDTO dto) {

        Voto voto = build(dto);
        voto.setHabilitado(true);
        return repository.save(voto); //insert into ...
    }

    public List<Voto> listar() {

        return repository.findAll(); // SELECT * FROM Voto
    }

    public Voto buscarPorId(Long id) {

        return repository.findById(id).get(); // SELECT * FROM Voto WHERE id = ?
    }

    @Transactional
    public void remover(Long id) {

        Voto voto = repository.findById(id).get();
        voto.setHabilitado(false);
        repository.save(voto); //update ...
    }

}