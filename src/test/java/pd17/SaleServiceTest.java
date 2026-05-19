package pd17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pd17.sale.Sale;
import pd17.sale.SaleRepository;
import pd17.sale.SaleService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

    @Mock
    SaleRepository saleRepository;

    @InjectMocks
    SaleService saleService;

    @Test
    void shouldCalculateTotalRevenue() {
        //given
        when(saleRepository.getAll()).thenReturn(List.of(new Sale(
                new Product(1, "", "", BigDecimal.TEN), "", 3
        )));
        //when
        var result = saleService.getTotalRevenue();
        //then
        assertThat(result).isCloseTo(BigDecimal.valueOf(30), withinPercentage(0.1));
    }

    @Test
    void shouldReturnTop3Products() {
        //given
        Product productA = new Product(1, "A", "Kategoria", BigDecimal.TEN);
        Product productB = new Product(2, "B", "Kategoria", BigDecimal.TEN);
        Product productC = new Product(3, "C", "Kategoria", BigDecimal.TEN);
        Product productD = new Product(4, "D", "Kategoria", BigDecimal.TEN);

        when(saleRepository.getAll()).thenReturn(List.of(
                new Sale(productC, "", 3),
                new Sale(productA, "", 10),
                new Sale(productD, "", 1),
                new Sale(productB, "", 5)
        ));

        //when
        var result = saleService.getTop3Products();

        //then
        assertThat(result)
                .containsExactly(productA, productB, productC);
    }

    @Test
    void shouldReturnRevenueByCategory() {
        //given
        Product productA = new Product(1, "A", "Kategoria", BigDecimal.TEN);
        Product productB = new Product(2, "B", "Kategoria", BigDecimal.TEN);
        Product productC = new Product(3, "C", "Kategoria", BigDecimal.TEN);
        Product productD = new Product(4, "D", "Kategoria", BigDecimal.TEN);

        when(saleRepository.getAll()).thenReturn(List.of(
                new Sale(productC, "", 3),
                new Sale(productA, "", 10),
                new Sale(productD, "", 1),
                new Sale(productB, "", 5)
        ));

        //when
        var result = saleService.getRevenueByCategory();
        Map<String, BigDecimal> expectedResult = new HashMap<>();
        expectedResult.put("Kategoria", new BigDecimal(190));

        assertThat(result)
                .containsExactlyEntriesOf(expectedResult);
    }

    @Test
    void shouldReturnAveragePricePerCategory() {
        //given
        Product productA = new Product(1, "A", "Kategoria", BigDecimal.TEN);
        Product productB = new Product(2, "B", "Kategoria", BigDecimal.TEN);
        Product productC = new Product(3, "C", "Kategoria", BigDecimal.TEN);
        Product productD = new Product(4, "D", "Kategoria", BigDecimal.TEN);

        when(saleRepository.getAll()).thenReturn(List.of(
                new Sale(productC, "", 3),
                new Sale(productA, "", 10),
                new Sale(productD, "", 1),
                new Sale(productB, "", 5)
        ));

        //when
        var result = saleService.getAveragePricePerCategory();
        Map<String, Double> expectedResult = Map.of("Kategoria", 10.0);

        assertThat(result)
                .containsExactlyEntriesOf(expectedResult);
    }
}
