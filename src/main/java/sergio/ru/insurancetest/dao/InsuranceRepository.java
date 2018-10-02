package sergio.ru.insurancetest.dao;

import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;
import sergio.ru.insurancetest.model.Vehicle;

import java.util.List;

public interface InsuranceRepository {

    Contract findById(Integer id);

    ContractType findType(Integer id);

    Vehicle findVehicle(String number);

    List<Contract> findAllContracts();

    void save(Contract contract);

    void update(Contract contract);

    void remove(Integer id);
}
