package sergio.ru.insurancetest.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.exception.ServiceException;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.InsuranceService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ContractFormValidator implements Validator {

    private static String NUMBER_REGEXP = "[0-9а-яА-ЯёЁA-Za-z]{1,8}";

    private static String SERIE_REGEXP = "[а-яА-ЯёЁ]{3}";

    private static Integer OFFSET_DAYS = 3;

    private InsuranceService service;

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

        if (contract.getSignDate().isBefore(LocalDate.now())) {
            errors.rejectValue("signDate", "TooEarly.contractForm.signDate");
        }

        if (contract.getOpenDate().isBefore(contract.getSignDate().plusDays(OFFSET_DAYS))) {
            errors.rejectValue("openDate", "TooEarly.contractForm.openDate");
        }

        if (contract.getExpirationDate() != null) {
            if (contract.getExpirationDate().isBefore(contract.getOpenDate().plusDays(1))) {
                errors.rejectValue("expirationDate", "TooEarly.contractForm.expirationDate");
            }
        }

        Pattern p = Pattern.compile(SERIE_REGEXP);
        Matcher m = p.matcher(contract.getSerie());
        if (!m.matches()) {
            errors.rejectValue("serie", "NotMatch.contractForm.serie");
        }

        Pattern p1 = Pattern.compile(NUMBER_REGEXP);
        Matcher m1 = p1.matcher(contract.getNumber());
        if (!m1.matches()) {
            errors.rejectValue("number", "NotMatch.contractForm.number");
        }
    }
}
