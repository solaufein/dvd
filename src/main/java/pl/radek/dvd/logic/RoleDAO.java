package pl.radek.dvd.logic;

import pl.radek.dvd.model.Roles;

import java.util.List;

public interface RoleDAO {
    public List<Roles> getRoles();
    public Roles getRole(int id);
    public void deleteRole(int id);
}
