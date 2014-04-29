package pl.radek.dvd.model;

import javax.persistence.*;

/**
 * User: Sola
 * Date: 2014-04-29
 * Time: 19:17
 */

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "role", nullable = false)
    private String role;


}
