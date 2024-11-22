package com.fiap.taligado.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.DispositivoDTO;
import com.fiap.taligado.model.Dispositivo;
import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.repository.EmpresaRepository;
import com.fiap.taligado.service.DispositivoService;
import com.fiap.taligado.service.FilialService;
import com.fiap.taligado.service.SensorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivos")
public class DispositivoController {

	@Autowired
	private DispositivoService dispositivoService;
	@Autowired
	private FilialService filialService;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping("/{id}/sensores")
	public ModelAndView listarSensores(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("sensor/list");
		DispositivoDTO dispositivo = dispositivoService.buscarPorId(id);
		mv.addObject("dispositivo", dispositivo);
		mv.addObject("sensores", sensorService.buscarPorDispositivo(id)); // Busca sensores do dispositivo
		return mv;
	}

	// Página de listagem
	@GetMapping
	public String listarDispositivos(Model model, Principal principal) {
		// Obtém o ID da empresa a partir do usuário logado
		Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal);

		// Busca dispositivos relacionados à empresa
		List<Dispositivo> dispositivos = dispositivoService.buscarDispositivosPorEmpresa(idEmpresa);
		model.addAttribute("dispositivos", dispositivos);

		return "dispositivo/list";
	}

	private Long obterIdEmpresaDoUsuarioLogado(Principal principal) {
		// Exemplo: Obter empresa associada ao CNPJ do usuário logado
		String cnpj = principal.getName(); // Aqui, o username é o CNPJ
		Empresa empresa = empresaRepository.findByCnpj(cnpj)
				.orElseThrow(() -> new RuntimeException("Empresa não encontrada para o CNPJ: " + cnpj));
		return empresa.getIdEmpresa();
	}

	// Página para criar um novo dispositivo
	@GetMapping("/novo")
	public ModelAndView novoDispositivo(Principal principal) {
		ModelAndView mv = new ModelAndView("dispositivo/form");
		Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal); // Obter ID da empresa logada
		mv.addObject("dispositivo", new DispositivoDTO());
		mv.addObject("filiais", filialService.listarFiliaisPorEmpresa(idEmpresa)); // Buscar apenas filiais da empresa
																					// logada
		return mv;
	}

	// Página para editar um dispositivo existente
	@PostMapping("/editar/{id}")
	public ModelAndView editarDispositivo(@PathVariable Long id, Principal principal) {
		ModelAndView mv = new ModelAndView("dispositivo/form");
		Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal); // Obter ID da empresa logada
		mv.addObject("dispositivo", dispositivoService.buscarPorId(id));
		mv.addObject("filiais", filialService.listarFiliaisPorEmpresa(idEmpresa)); // Buscar apenas filiais da empresa
																					// logada
		return mv;
	}

	// Salvar ou atualizar dispositivo
	@PostMapping
	public ModelAndView salvar(@Valid @ModelAttribute DispositivoDTO dispositivoDTO, 
	                     BindingResult result, 
	                     Model model, 
	                     Principal principal) {
		
		
	    if (result.hasErrors()) {
	        // Recuperar a lista de filiais da empresa logada para repopular o formulário
	        Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal);
	        model.addAttribute("filiais", filialService.listarFiliaisPorEmpresa(idEmpresa));
	        
	        return new ModelAndView("dispositivo/form");
	    }

	    // Salvar ou atualizar o dispositivo
	    dispositivoService.salvarOuAtualizar(dispositivoDTO);
	    return new ModelAndView("redirect:/dispositivos");
	}

	

	// Excluir dispositivo
	@PostMapping("/deletar/{id}")
	public ModelAndView excluir(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/dispositivos");
		try {
			dispositivoService.excluir(id);
		} catch (Exception e) {
			mv.setViewName("dispositivo/list");
			mv.addObject("dispositivos", dispositivoService.buscarTodos());
			mv.addObject("errorMessage",
					"Não foi possível excluir o dispositivo. Verifique dependências antes de tentar novamente.");
		}
		return mv;
	}
}
