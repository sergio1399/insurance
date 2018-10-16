package sergio.ru.insurancetest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.exception.ServiceException;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;
import sergio.ru.insurancetest.service.InsuranceService;
import sergio.ru.insurancetest.service.IntegrationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    private InsuranceRepository repository;

    private IntegrationService integrationService;

    public InsuranceServiceImpl(InsuranceRepository repository, IntegrationService integrationService) {
        this.repository = repository;
        this.integrationService = integrationService;
    }

    @Transactional
    @Override
    public Contract findById(Integer id) throws ServiceException {
        LOGGER.info("InsuranceService findById() {}", id);
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Contract> findAll() {
        LOGGER.info("InsuranceService findAll()");
        List<Contract> contracts = repository.findAllContracts();
        return contracts;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(Contract contract) throws ServiceException {
        LOGGER.info("InsuranceService saveOrUpdate()");
        if (findById(contract.getId())==null) {
            repository.save(contract);
        } else {
            repository.update(contract);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(int id) throws ServiceException {
        LOGGER.info("InsuranceService remove()");
        repository.remove(id);
    }

    @Transactional
    @Override
    public List<String> getAllContractTypes() {
        LOGGER.info("InsuranceService getAllContractTypes()");
        return repository.getAllContractTypes();
    }

    @Transactional
    @Override
    public void loadToExcel(List<ContractDto> contractDtoList) throws IOException {
        LOGGER.info("InsuranceService loadToExcel()");
        integrationService.loadToExcel(contractDtoList);
    }
}
