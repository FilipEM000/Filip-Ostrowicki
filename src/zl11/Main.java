package zl11;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        LaptopStore laptopStore = new LaptopStore();

        laptopStore.orders.stream()
                .map(order -> CompletableFuture.supplyAsync(() -> laptopStore.buyLaptop(order.getName(), order.getAmount())))
                .map(CompletableFuture::join)
                .forEach(System.out::println);
    }
}
