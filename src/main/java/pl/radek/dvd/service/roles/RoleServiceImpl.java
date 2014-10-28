package pl.radek.dvd.service.roles;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.logic.RoleDAO;
import pl.radek.dvd.model.Roles;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-10-21
 * Time: 13:35
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<RoleData> getRoles() {

        List<Roles> roles = roleDAO.getRoles();
        List<RoleData> roleDataList = convertRolesListToRoleDataList(roles);

        return roleDataList;
    }

    @Override
    public RoleData getRole(int id) {
        Roles role = roleDAO.getRole(id);
        RoleData roleData = convertRolesToRoleData(role);
        return roleData;
    }

    @Override
    public Roles getRoleEntity(int id) {
        Roles role = roleDAO.getRole(id);
        return role;
    }

    @Override
    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }

    private List<RoleData> convertRolesListToRoleDataList(List<Roles> roles) {
        List<RoleData> roleDataList = new LinkedList<RoleData>();

        for (Roles role : roles) {

            RoleData roleData = convertRolesToRoleData(role);

            roleDataList.add(roleData);
        }

        return roleDataList;
    }

    private RoleData convertRolesToRoleData(Roles role) {
        RoleData roleData = new RoleData();
        roleData.setId(role.getId());
        roleData.setRole(role.getRole());
        roleData.setEmployees(role.getEmployees());

        return roleData;
    }
}
