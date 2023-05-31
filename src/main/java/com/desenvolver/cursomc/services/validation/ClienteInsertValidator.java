package com.desenvolver.cursomc.services.validation;

import com.desenvolver.cursomc.dto.ClienteNewDTO;
import com.desenvolver.cursomc.enums.TipoCliente;
import com.desenvolver.cursomc.resources.resources.exception.FieldMessage;

import java.util.ArrayList;
import java.util.List;

import com.desenvolver.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert ann) {
    }
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

     if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj","CPF invalido"));
     }
     if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj","CNPJ invalido"));
     }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFildName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
