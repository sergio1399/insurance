package sergio.ru.insurancetest.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.InsuranceService;
import sergio.ru.insurancetest.utils.ContractConverter;
import sergio.ru.insurancetest.validator.ContractFormValidator;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InsuranceController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceController.class);

    private static final String DEFAULT_TYPE = "OSAGO";

    private InsuranceService service;

    private ModelMapper modelMapper;

    private ContractFormValidator validator;

    public InsuranceController(InsuranceService service, ContractFormValidator validator, ModelMapper modelMapper) {
        this.service = service;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/contracts";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String showAllContracts(Model model) {
        logger.debug("showAllContracts()");
        List<Contract> contracts = service.findAll();
        List<ContractDto> dtos = contracts.stream()
                .map(contract -> ContractConverter.convertToDto(contract, modelMapper))
                .collect(Collectors.toList());
        model.addAttribute("contracts", dtos);
        return "list";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.POST)
    public String saveOrUpdateContract(@ModelAttribute("userForm") @Validated ContractDto contractDto,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) throws ParseException {
        logger.debug("saveOrUpdateContract() : {}", contractDto);
        if (result.hasErrors()) {
            initModel(model);
            model.addAttribute(contractDto);
            return "contractform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(contractDto.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Contract added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Contract updated successfully!");
            }

            service.saveOrUpdate(ContractConverter.convertToModel(contractDto, modelMapper));

            return "redirect:/contracts/" + contractDto.getId();
        }
    }

    @RequestMapping(value = "/contracts/add", method = RequestMethod.GET)
    public String showAddContractForm(Model model) {
        logger.debug("showAddContractForm()");
        ContractDto contractDto = new ContractDto();
        contractDto.setType(DEFAULT_TYPE);
        LocalDate today = LocalDate.now();
        contractDto.setSignDate(today);
        contractDto.setOpenDate(today);
        contractDto.setExpirationDate(today.plusYears(1));
        model.addAttribute("contractForm", contractDto);

        initModel(model);

        return "contractform";
    }

    @RequestMapping(value = "/contracts/{id}/update", method = RequestMethod.GET)
    public String showUpdateContractForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateContractForm() : {}", id);
        model.addAttribute("contractForm", ContractConverter.convertToDto(service.findById(id), modelMapper));

        initModel(model);

        return "contractform";
    }

    @RequestMapping(value = "/contracts/{id}/remove", method = RequestMethod.POST)
    public String removeContract(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {
        logger.debug("removeContract() : {}", id);
        service.remove(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Contract is removed!");

        return "redirect:/contracts";
    }

    @RequestMapping(value = "/contracts/{id}", method = RequestMethod.GET)
    public String showContract(@PathVariable("id") int id, Model model) {
        logger.debug("showContract() id: {}", id);
        ContractDto contractDto = ContractConverter.convertToDto(service.findById(id), modelMapper);
        if (contractDto == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Contract not found");
        }
        model.addAttribute("contract", contractDto);

        return "show";
    }

    private void initModel(Model model) {

    }



}
