package sergio.ru.insurancetest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.InsuranceService;
import sergio.ru.insurancetest.validator.ContractFormValidator;

import java.time.LocalDate;

@Controller
public class InsuranceController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceController.class);

    private InsuranceService service;

    private ContractFormValidator validator;

    public InsuranceController(InsuranceService service, ContractFormValidator validator) {
        this.service = service;
        this.validator = validator;
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
        model.addAttribute("contracts", service.findAll());
        return "list";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.POST)
    public String saveOrUpdateContract(@ModelAttribute("userForm") @Validated Contract contract,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {
        logger.debug("saveOrUpdateContract() : {}", contract);
        if (result.hasErrors()) {
            initModel(model);
            return "contractform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(contract.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Contract added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Contract updated successfully!");
            }

            service.saveOrUpdate(contract);

            return "redirect:/contracts/" + contract.getId();
        }
    }

    @RequestMapping(value = "/contracts/add", method = RequestMethod.GET)
    public String showAddContractForm(Model model) {
        logger.debug("showAddContractForm()");
        Contract contract = new Contract();
        contract.setContractTypeId(2);
        LocalDate today = LocalDate.now();
        contract.setSignDate(today);
        contract.setOpenDate(today);
        contract.setExpirationDate(today.plusYears(1));
        model.addAttribute("contractForm", contract);

        initModel(model);

        return "contractform";
    }

    @RequestMapping(value = "/contracts/{id}/update", method = RequestMethod.GET)
    public String showUpdateContractForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateContractForm() : {}", id);
        Contract contract = service.findById(id);
        model.addAttribute("contractForm", contract);

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
        Contract contract = service.findById(id);
        if (contract == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Contract not found");
        }
        model.addAttribute("contract", contract);

        return "show";
    }

    private void initModel(Model model) {

    }

}
