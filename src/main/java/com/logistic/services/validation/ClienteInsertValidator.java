package com.logistic.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.logistic.domain.Cliente;
import com.logistic.domain.enums.TipoCliente;
import com.logistic.dto.ClienteNewDTO;
import com.logistic.repositories.ClienteRepository;
import com.logistic.resources.exception.FieldMessage;
import com.logistic.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {}

	/* vai ser chamado no resources @valid , caso a classe tenha esta anotação*/
	@Override
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (value.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(value.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		//Adiciona os meus erros personalizado no framework spring
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
