package br.com.silva.duarte.ifevento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.silva.duarte.ifevento.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	Evento findById(long IdEvento);

}
