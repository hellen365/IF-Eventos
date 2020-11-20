package br.com.silva.duarte.ifevento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.silva.duarte.ifevento.models.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long > {
	
	Participante findById(long idParticipante);

}
