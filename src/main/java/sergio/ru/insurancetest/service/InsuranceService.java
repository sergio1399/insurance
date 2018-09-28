package sergio.ru.insurancetest.service;

import sergio.ru.insurancetest.dto.Contract;

import java.util.List;

public interface InsuranceService {
    Contract findById(Integer id);

    List<Contract> findAll();

    void saveOrUpdate(Contract contract);

    void remove(int id);
}
