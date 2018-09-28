package sergio.ru.insurancetest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.dto.Contract;
import sergio.ru.insurancetest.service.InsuranceService;

import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private InsuranceRepository repository;

    public InsuranceServiceImpl(InsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public List<Contract> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(Contract contract) {

    }

    @Override
    public void remove(int id) {

    }
}
