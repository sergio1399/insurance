package sergio.ru.insurancetest.service;

import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IntegrationService {

    void loadToExcel(List<ContractDto> contractList) throws IOException;

}
