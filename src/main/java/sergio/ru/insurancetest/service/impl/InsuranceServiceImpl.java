package sergio.ru.insurancetest.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public Contract findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ContractDto> findAll() {
        List<Contract> contracts = repository.findAllContracts();
        List<ContractDto> dtos = new ArrayList<>();
        return dtos;
    }

    @Override
    public void saveOrUpdate(Contract contract) {
        if (findById(contract.getId())==null) {
            repository.save(contract);
        } else {
            repository.update(contract);
        }
    }

    @Override
    public void remove(int id) {
        repository.remove(id);
    }
}
