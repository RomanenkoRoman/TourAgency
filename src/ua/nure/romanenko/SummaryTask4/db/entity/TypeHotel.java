package ua.nure.romanenko.SummaryTask4.db.entity;

/**
 * TypeHotel
 */
public enum TypeHotel {
    HOTEL, MOTEL, BOUTIQUE, APARTMENTS;


    public String getName() {
        return name().toLowerCase();
    }
}
