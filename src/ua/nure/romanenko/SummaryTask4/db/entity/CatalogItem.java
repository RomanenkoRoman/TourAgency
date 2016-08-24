package ua.nure.romanenko.SummaryTask4.db.entity;

/**
 * Catalog item entity.
 */
/*
public class CatalogItemOLD extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private String name;

    private Integer price;

    private Long categoryId;

    private Integer peopleQuantity;

    private Integer typeHotel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(Integer peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public Integer getTypeHotel() {
        return typeHotel;
    }

    public void setTypeHotel(Integer typeHotel) {
        this.typeHotel = typeHotel;
    }

    @Override
    public String toString() {
        return "CatalogItem [name=" + name + ", price=" + price + ", categoryId="
                + categoryId + ", getId()=" + getId() + "]";
    }

}
*/
public class CatalogItem extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private boolean hot;

    private String name;

    private int price;

    private String categoryId;

    private int peopleQuantity;

    private String typeHotel;



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(int peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTypeHotel() {
        return typeHotel;
    }

    public void setTypeHotel(String typeHotel) {
        this.typeHotel = typeHotel;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "categoryId='" + categoryId + '\'' +
                ", hot=" + hot +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", peopleQuantity=" + peopleQuantity +
                ", typeHotel='" + typeHotel + '\'' +
                '}';
    }
}