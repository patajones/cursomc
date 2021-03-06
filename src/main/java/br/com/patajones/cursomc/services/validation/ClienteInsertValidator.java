package br.com.patajones.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.domain.enums.TipoCliente;
import br.com.patajones.cursomc.dto.ClienteNewDTO;
import br.com.patajones.cursomc.repositories.ClienteRepository;
import br.com.patajones.cursomc.resources.exception.FieldMessage;
import br.com.patajones.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CPF Inválido", "CpfOuCnpj"));
		}
		if (dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CNPJ Inválido", "CpfOuCnpj"));
		}
		
		Cliente c = repo.findByEmail(dto.getEmail());
		if (c!=null) {
			list.add(new FieldMessage("Email já existe.", "email"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMsg()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
