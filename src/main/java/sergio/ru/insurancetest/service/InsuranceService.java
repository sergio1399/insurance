package sergio.ru.insurancetest.service;

import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;

import java.util.List;

public interface InsuranceService {

    Contract findById(Integer id);

    List<ContractDto> findAll();

    void saveOrUpdate(Contract contract);

    void remove(int id);
}
