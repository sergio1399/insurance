package sergio.ru.insurancetest.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.exception.ServiceException;
import sergio.ru.insurancetest.model.Contract;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {InsuranceServiceImpl.class})
@MockBean({IntegrationServiceImpl.class})
@EnableAutoConfiguration
public class InsuranceServiceImplTest {

    @Autowired
    private InsuranceServiceImpl service;

    @MockBean
    private InsuranceRepository repository;

    private static Integer ID = 23;

    private static String NUMBER = "rt91jk";

    private static String SERIA = "ячс";

    @Test
    public void findByIdTest() throws ServiceException {
        when(repository.findById(ID)).thenReturn(makeContract());
        Contract contract = service.findById(ID);
        Assert.assertEquals(makeContract(), contract);
    }

    @Test
    public void findAllTest() {
        when(repository.findAllContracts()).thenReturn(makeContractList());
        List<Contract> resultList = service.findAll();
        Assert.assertEquals(makeContractList(), resultList);
    }


    private Contract makeContract() {
        Contract contract = new Contract();
        contract.setId(ID);
        contract.setNumber(NUMBER);
        contract.setSerie(SERIA);
        return contract;
    }

    private List<Contract> makeContractList() {
        return Collections.singletonList(makeContract());
    }

}