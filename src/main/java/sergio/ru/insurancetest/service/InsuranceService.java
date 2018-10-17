package sergio.ru.insurancetest.service;

import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.exception.ServiceException;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;

import java.io.IOException;
import java.util.List;

public interface InsuranceService {

    Contract findById(Integer id) throws ServiceException;

    List<Contract> findAll();

    void saveOrUpdate(Contract contract) throws ServiceException;

    void remove(int id) throws ServiceException;

    List<String> getAllContractTypes();

    void loadToExcel(List<ContractDto> contractDtoList) throws IOException;

    List<String> findAllVehicles();
}
