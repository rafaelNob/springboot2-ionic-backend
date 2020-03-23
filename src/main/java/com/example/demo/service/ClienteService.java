package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.enums.TipoCliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.resources.exceptions.DataIntegrityException;
import com.example.demo.resources.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.orElse(null);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	/**
	 * TABELA QUE INSERE RELACIONAMENTO CLIENTE ENDEREÇO E TELEFONES
	 * @param objDto
	 * @return cliente
	 */

	/*
	 * public Cliente fromDTO(ClienteNewDTO objDto) {
	 * 
	 * Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(),
	 * objDto.getCpfouCnpj(),TipoCliente.toEnum(objDto.getTipo()));
	 * 
	 * Cidade cidade = new Cidade(objDto.getCidade(), null, null); Endereco endereco
	 * = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(),
	 * objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente,
	 * cidade); cliente.getEnderecos().add(endereco);
	 * cliente.getTelefones().add(objDto.getTelefone1());
	 * 
	 * if (objDto.getTelefone2() != null) {
	 * cliente.getTelefones().add(objDto.getTelefone2());
	 * 
	 * } if (objDto.getTelefone3() != null) {
	 * cliente.getTelefones().add(objDto.getTelefone3());
	 * 
	 * }
	 * 
	 * return cliente;
	 * 
	 * }
	 */
	
	/*
	 * @Transactional public Cliente inserir(Cliente obj) { obj = repo.save(obj);
	 * repoEnd.saveAll(obj.getEnderecos()); return obj; }
	 */
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
