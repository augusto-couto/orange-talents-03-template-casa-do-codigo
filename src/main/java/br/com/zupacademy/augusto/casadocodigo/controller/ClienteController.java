package br.com.zupacademy.augusto.casadocodigo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.augusto.casadocodigo.dto.ClienteDto;
import br.com.zupacademy.augusto.casadocodigo.form.ClienteForm;
import br.com.zupacademy.augusto.casadocodigo.model.Cliente;
import br.com.zupacademy.augusto.casadocodigo.model.Estado;
import br.com.zupacademy.augusto.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.PaisRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private PaisRepository paisRepository;
	private EstadoRepository estadoRepository;
	
	public ClienteController(ClienteRepository clienteRepository,
			PaisRepository paisRepository, EstadoRepository estadoRepository) {
		this.clienteRepository = clienteRepository;
		this.paisRepository = paisRepository;
		this.estadoRepository = estadoRepository;
	}
	
	@PostMapping
	public ClienteDto cadastrar(@RequestBody @Valid ClienteForm clienteForm) {
		Cliente cliente = clienteForm.converte(paisRepository);
		
		List<Estado> estadosDessePais = estadoRepository.findByPaisNome(cliente.getPais().getNome());
		if (estadosDessePais.isEmpty()) {
			cliente.setEstado(null);
		} else {
			
			if (clienteForm.getNomeEstado().isEmpty()) {
				throw new ResponseStatusException(HttpStatus.CONFLICT,
						"O país contém os estados e a seleção de um é obrigatória.");
			} else {
				Estado estado = estadoRepository
						.findByNomeAndPaisId(clienteForm.getNomeEstado(), cliente.getPais().getId());
				cliente.setEstado(estado);
			}
		}
			
		clienteRepository.save(cliente);
		return new ClienteDto(cliente);
	}
}
