package sergio.ru.insurancetest.utils;

import org.modelmapper.ModelMapper;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;

import java.text.ParseException;

public class ContractConverter {

    public static ContractDto convertToDto(Contract contract, ModelMapper modelMapper) {
        ContractDto contractDto = modelMapper.map(contract, ContractDto.class);
        contractDto.setNdsRate();
        contractDto.setSumNoNds();
        contractDto.setMinSumAccord();
        return contractDto;
    }

    public static Contract convertToModel(ContractDto contractDto, ModelMapper modelMapper) throws ParseException {
        Contract contract = modelMapper.map(contractDto, Contract.class);
        return contract;
    }

}
