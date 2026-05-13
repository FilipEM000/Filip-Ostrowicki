package pd08;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        NumberStats<Double> numberStatsDouble = new NumberStats<>();
        numberStatsDouble.add(6.0);
        numberStatsDouble.add(9.0);
        numberStatsDouble.add(1.0);
        System.out.println(numberStatsDouble.getMin());
        System.out.println(numberStatsDouble.getMax());
        System.out.println(numberStatsDouble.getAverage());
        System.out.println(numberStatsDouble.getSum());
        System.out.println(numberStatsDouble.getCount());
        System.out.println(numberStatsDouble.getSorted());

        NumberStats<Integer> numberStatsInteger = new NumberStats<>();
        numberStatsInteger.add(6);
        numberStatsInteger.add(9);
        numberStatsInteger.add(1);
        System.out.println(numberStatsInteger.getMin());
        System.out.println(numberStatsInteger.getMax());
        System.out.println(numberStatsInteger.getAverage());
        System.out.println(numberStatsInteger.getSum());
        System.out.println(numberStatsInteger.getCount());
        System.out.println(numberStatsInteger.getSorted());

        NumberStats<BigDecimal> numberStatsBigDecimal = new NumberStats<>();
        numberStatsBigDecimal.add(new BigDecimal("6.0"));
        numberStatsBigDecimal.add(new BigDecimal("9.0"));
        numberStatsBigDecimal.add(new BigDecimal("1.0"));
        System.out.println(numberStatsBigDecimal.getMin());
        System.out.println(numberStatsBigDecimal.getMax());
        System.out.println(numberStatsBigDecimal.getAverage());
        System.out.println(numberStatsBigDecimal.getSum());
        System.out.println(numberStatsBigDecimal.getCount());
        System.out.println(numberStatsBigDecimal.getSorted());
    }
}
