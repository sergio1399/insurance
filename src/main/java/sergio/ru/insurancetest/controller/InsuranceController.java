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

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class InsuranceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceController.class);

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
        LOGGER.info("index()");
        return "redirect:/contracts";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String showAllContracts(Model model) {
        LOGGER.info("showAllContracts()");
        initModel(model);
        List<Contract> contracts = service.findAll();
        List<ContractDto> dtos = contracts.stream()
                .map(contract -> ContractConverter.convertToDto(contract, modelMapper))
                .collect(Collectors.toList());
        model.addAttribute("contracts", dtos);
        return "list";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.POST)
    public String saveOrUpdateContract(@ModelAttribute("contractForm") @Validated ContractDto contractDto,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) throws ParseException {
        LOGGER.info("saveOrUpdateContract() : {}", contractDto);
        if (result.hasErrors()) {
            initModel(model);
            return "contractform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(contractDto.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Contract added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Contract updated successfully!");
            }

            service.saveOrUpdate(ContractConverter.convertToModel(contractDto, modelMapper));

            return "redirect:/contracts";
        }
    }

    @RequestMapping(value = "/contracts/add", method = RequestMethod.GET)
    public String showAddContractForm(Model model) {
        LOGGER.info("showAddContractForm()");
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
        LOGGER.info("showUpdateContractForm() : {}", id);
        model.addAttribute("contractForm", ContractConverter.convertToDto(service.findById(id), modelMapper));

        initModel(model);

        return "contractform";
    }

    @RequestMapping(value = "/contracts/{id}/remove", method = RequestMethod.POST)
    public String removeContract(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {
        LOGGER.info("removeContract() : {}", id);
        service.remove(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Contract is removed!");

        return "redirect:/contracts";
    }

    @RequestMapping(value = "/contracts/excel", method = RequestMethod.GET)
    public String loadToExcel(final RedirectAttributes redirectAttributes) throws IOException {
        LOGGER.info("loadToExcel()");
        List<Contract> contracts = service.findAll();
        List<ContractDto> dtos = contracts.stream()
                .map(contract -> ContractConverter.convertToDto(contract, modelMapper))
                .collect(Collectors.toList());
        service.loadToExcel(dtos);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Contracts loaded to excel!");

        return "redirect:/contracts";
    }

    @RequestMapping(value = "/contracts/{id}", method = RequestMethod.GET)
    public String showContract(@PathVariable("id") int id, Model model) {
        LOGGER.info("showContract() id: {}", id);
        ContractDto contractDto = ContractConverter.convertToDto(service.findById(id), modelMapper);
        if (contractDto == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Contract not found");
        }
        model.addAttribute("contract", contractDto);

        return "show";
    }

    private void initModel(Model model) {
        List<String> typeList = service.getAllContractTypes();
        Map<String, String> types = new LinkedHashMap<String, String>();
        for (int i = 0; i < typeList.size(); i++) {
            types.put(typeList.get(i), typeList.get(i));
        }
        model.addAttribute("typeList", types);
    }





}
