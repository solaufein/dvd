package pl.radek.dvd.model;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 31.01.14
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    public static final String CLIENT = "client";
    public static final String ID = "id";
    public static final String FIRSTNAME = "first_name";
    public static final String LASTNAME = "last_name";
    public static final String PESEL = "pesel";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String PHONENUMBER = "phone_number";
    public static final String EMAIL = "email";

    public static final String FIELD = "field";
    public static final String ORDER = "order";
    public static final String CLIENTLIST = "clientList";
    public static final String ERRORS = "errors";

    public static final String CURRENTPAGE = "currentPage";
    public static final String NO_OF_PAGES = "noOfPages";

    //for jstl, el, jsp purposes
    public String asc = ASC;
    public String desc = DESC;

    public String client = CLIENT;
    public String id = ID;
    public String firstname = FIRSTNAME;
    public String lastname = LASTNAME;
    public String pesel = PESEL;
    public String city = CITY;
    public String street = STREET;
    public String phonenumber = PHONENUMBER;
    public String email = EMAIL;

    public String field = FIELD;
    public String order = ORDER;
    public String clientlist = CLIENTLIST;
    public String errors = ERRORS;

    public String currentpage = CURRENTPAGE;
    public String noofpages = NO_OF_PAGES;

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getClientlist() {
        return clientlist;
    }

    public void setClientlist(String clientlist) {
        this.clientlist = clientlist;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getNoofpages() {
        return noofpages;
    }

    public void setNoofpages(String noofpages) {
        this.noofpages = noofpages;
    }
}
