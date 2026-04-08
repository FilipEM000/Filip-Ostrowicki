package zl10;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

public class DiscountTask {

    public static void main(String[] args) {
        User user1 = new User(
                new Subscription(true, "  sub15 "),
                null,
                200
        );

        User user2 = new User(
                new Subscription(true, "   "),
                new ReferralProgram(true, " ref20 "),
                300
        );

        User user3 = new User(
                null,
                null,
                1500
        );

        User user4 = new User(
                new Subscription(false, "sub50"),
                new ReferralProgram(true, "   "),
                100
        );

        User user5 = null;

        System.out.println(resolveDiscountCode(user1)); // SUB15
        System.out.println(resolveDiscountCode(user2)); // REF20
        System.out.println(resolveDiscountCode(user3)); // LOYAL20
        System.out.println(resolveDiscountCode(user4)); // DEFAULT10
        System.out.println(resolveDiscountCode(user5)); // DEFAULT10
    }

    // LEGACY CODE DO PRZEPISANIA
    public static String resolveDiscountCode(User user) {

        return Optional.ofNullable(user)
                .flatMap(User::getSubscription)
                .filter(Subscription::active)
                .map(Subscription::discountCode)
                .flatMap(DiscountTask::normalizeCode)
                .or(() -> Optional.ofNullable(user)
                        .flatMap(User::getReferralProgram)
                        .filter(ReferralProgram::enabled)
                        .map(ReferralProgram::referralCode)
                        .flatMap(DiscountTask::normalizeCode))
                .or(() -> Optional.ofNullable(user)
                        .filter(u -> u.getLoyaltyPoints() >= 1000)
                        .map(u -> "LOYAL20"))
                .orElse("DEFAULT10");
    }

    private static Optional<String> normalizeCode(String code) {
        return Optional.ofNullable(code)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .map(String::toUpperCase);
    }

    @AllArgsConstructor
    static class User {
        private final Subscription subscription;
        private final ReferralProgram referralProgram;
        @Getter
        private final int loyaltyPoints;

        public Optional<Subscription> getSubscription() {
            return Optional.ofNullable(subscription);
        }

        public Optional<ReferralProgram> getReferralProgram() {
            return Optional.ofNullable(referralProgram);
        }
    }

    record Subscription(boolean active, String discountCode) {

    }

    record ReferralProgram(boolean enabled, String referralCode) {

    }
}