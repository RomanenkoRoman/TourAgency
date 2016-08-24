package ua.nure.romanenko.SummaryTask4.db.entity;

/**
 * Type of tours
 */
public enum TourType {
    REST, EXCURSION, SHOPPING;


    public String getName() {
        return name().toLowerCase();
    }
}
