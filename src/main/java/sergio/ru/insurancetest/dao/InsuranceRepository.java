package sergio.ru.insurancetest.dao;

import sergio.ru.insurancetest.exception.ServiceException;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;
import sergio.ru.insurancetest.model.Vehicle;

import java.util.List;

public interface InsuranceRepository {

    Contract findById(Integer id) throws ServiceException;

    ContractType findType(Integer id) throws ServiceException;

    Vehicle findVehicle(String number) throws ServiceException;

    List<Contract> findAllContracts();

    void save(Contract contract) throws ServiceException;

    void update(Contract contract) throws ServiceException;

    void remove(Integer id) throws ServiceException;

    List<String> getAllContractTypes();
}
