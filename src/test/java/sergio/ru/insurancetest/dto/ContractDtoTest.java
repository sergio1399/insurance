package sergio.ru.insurancetest.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sergio.ru.insurancetest.model.Contract;

import static org.junit.Assert.*;

public class ContractDtoTest {

    private ContractDto contractDtoExpensive;

    private ContractDto contractDtoCheap;

    private static Double SUM_WITH_NDS_BIG = 5500.0;

    private static Double NDS_SUM_BIG = 1100.0;

    private static Double SUM_WITH_NDS_SMALL = 900.0;

    private static Double NDS_SUM_SMALL = 100.0;

    private static Double SUM_NO_NDS_SMALL = 800.0;

    private static Integer NDS_RATE_BIG = 20;

    @Before
    public void init() {
        contractDtoExpensive = makeContractDto(SUM_WITH_NDS_BIG, NDS_SUM_BIG);
        contractDtoCheap = makeContractDto(SUM_WITH_NDS_SMALL, NDS_SUM_SMALL);
    }

    @Test
    public void testSumNoNds() {
        contractDtoCheap.setSumNoNds();
        Assert.assertEquals(SUM_NO_NDS_SMALL, contractDtoCheap.getSumNoNds());
    }

    @Test
    public void testNdsRate() {
        contractDtoExpensive.setNdsRate();
        Assert.assertEquals(NDS_RATE_BIG, contractDtoExpensive.getNdsRate());
    }

    @Test
    public void testAccordBig() {
        contractDtoExpensive.setMinSumAccord();
        Assert.assertEquals(Boolean.TRUE, contractDtoExpensive.getMinSumAccord());
    }

    @Test
    public void testAccordSmall() {
        contractDtoCheap.setMinSumAccord();
        Assert.assertEquals(Boolean.FALSE, contractDtoCheap.getMinSumAccord());
    }

    private ContractDto makeContractDto(Double sumWithNds, Double ndsSum) {
        ContractDto contractDto = new ContractDto();
        contractDto.setSumWithNds(sumWithNds);
        contractDto.setNdsSum(ndsSum);
        return contractDto;
    }

}