package sergio.ru.insurancetest.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.InsuranceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private InsuranceRepository repository;

    public InsuranceServiceImpl(InsuranceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Contract findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Contract> findAll() {
        List<Contract> contracts = repository.findAllContracts();
        return contracts;
    }

    @Transactional
    @Override
    public void saveOrUpdate(Contract contract) {
        if (findById(contract.getId())==null) {
            repository.save(contract);
        } else {
            repository.update(contract);
        }
    }

    @Transactional
    @Override
    public void remove(int id) {
        repository.remove(id);
    }
}
