package pd07;

public record Rental(Resource resource, int numberOfDays, Status status) {

    public double getRentalCost() {
        return resource.getRentalCost();
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return resource + ", liczba dni: " + numberOfDays + ", status: " + status;
    }
}
