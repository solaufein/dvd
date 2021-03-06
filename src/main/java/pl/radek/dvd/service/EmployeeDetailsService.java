package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.logic.EmployeeDAO;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.Roles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class EmployeeDetailsService implements UserDetailsService {

    private static Logger logger = Logger.getLogger(EmployeeDetailsService.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeDAO.getEmployee(login);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // loggs
        logger.debug("Got employee. login: " + employee.getEmail());
        if (employee.getRolesSet().isEmpty()) {
            logger.debug("Employee Roles: EMPTY");
        } else {
            for (Roles roles : employee.getRolesSet()) {
                logger.debug("Employee Roles: " + roles.getRole());
            }
        }

        return new User(
                employee.getEmail(),
                employee.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(employee.getRolesSet())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Set<Roles> roles) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
        return authList;
    }

    public List<String> getRoles(Set<Roles> role) {

        List<String> roles = new ArrayList<String>();

        for (Roles r : role) {
            if (r.getRole().equals("ADMIN")) {
                roles.add("ROLE_ADMIN");
            }

            if (r.getRole().equals("USER")) {
                roles.add("ROLE_USER");
            }
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
