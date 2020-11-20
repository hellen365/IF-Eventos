package br.com.silva.duarte.ifevento.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Participante implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idParticipante;
	
	@Column
	@NotNull
	private String nome;
	
	@Column(unique = true)
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "idEvento", nullable = false)
	private Evento evento;

	
	public long getIdParticipante() {
		return idParticipante;
	}
	public void setIdParticipante(long idParticipante) {
		this.idParticipante = idParticipante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
}
