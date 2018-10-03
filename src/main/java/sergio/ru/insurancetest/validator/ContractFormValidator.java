package sergio.ru.insurancetest.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.InsuranceService;

@Component
public class ContractFormValidator implements Validator {

    InsuranceService service;

    public ContractFormValidator(InsuranceService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ContractDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ContractDto contract = (ContractDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serie", "NotEmpty.contractForm.serie");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty.contractForm.number");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "signDate", "NotEmpty.contractForm.signDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "openDate", "NotEmpty.contractForm.openDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndsSum","NotEmpty.contractForm.ndsSum");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sumWithNds", "NotEmpty.contractForm.sumWithNds");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vehicleNumber", "NotEmpty.contractForm.vehicleNumber");
    }
}
