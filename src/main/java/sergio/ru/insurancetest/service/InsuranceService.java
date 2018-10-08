package sergio.ru.insurancetest.service;

import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;

import java.io.IOException;
import java.util.List;

public interface InsuranceService {

    Contract findById(Integer id);

    List<Contract> findAll();

    void saveOrUpdate(Contract contract);

    void remove(int id);

    List<String> getAllContractTypes();

    void loadToExcel(List<ContractDto> contractDtoList) throws IOException;
}
