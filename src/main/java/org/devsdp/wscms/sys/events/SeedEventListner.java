package org.devsdp.wscms.sys.events;


import org.devsdp.wscms.auth.dao.PermissionDao;
import org.devsdp.wscms.auth.dao.UserDao;
import org.devsdp.wscms.auth.dao.UserRoleDao;
import org.devsdp.wscms.auth.model.Permission;
import org.devsdp.wscms.auth.model.Role;
import org.devsdp.wscms.auth.model.User;
import org.devsdp.wscms.auth.service.impl.RoleServiceImpl;
import org.devsdp.wscms.auth.service.interfaces.UserService;
import org.devsdp.wscms.complaint.dao.DepartmentDao;
import org.devsdp.wscms.complaint.dao.DivisionDao;
import org.devsdp.wscms.complaint.model.Department;
import org.devsdp.wscms.complaint.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class SeedEventListner {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DivisionDao divisionDao;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        try{
            boolean roles =  permissionDao.findById(1L).isPresent();
            if(!roles){
                seedRoles();
                seedUsers();
                seedDepartment();
                seedDivisions();
            }
        }catch (Exception ignored){
            System.out.println("this is the error");
            System.out.println(ignored.getMessage());
        }

    }

    private void seedRoles(){

        try{
            List<Permission> userPermissions = Arrays.asList(
                    new Permission("ADMIN_PRIVILEGES", "Admin privileges")
            );

            List<Permission> publicUserPermissions = Arrays.asList(
                    new Permission("CREATE_COMPLAINT", "Create Complaint"),
                    new Permission("LIST_OWN_COMPLAINT", "List own complaints")
            );


            for(Permission item:userPermissions){
                permissionDao.save(item);
            }

            for(Permission item:publicUserPermissions){
                permissionDao.save(item);
            }

            Role userRole = new Role();
            userRole.setDescription("Admin");
            userRole.setName("ADMIN");
            userRole.setPermissions(userPermissions);
            userRoleDao.save(userRole);

            Role publicUser = new Role();
            publicUser.setDescription("Public");
            publicUser.setName("PUBLIC");
            publicUser.setPermissions(publicUserPermissions);
            userRoleDao.save(publicUser);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void seedUsers(){
        User admin;

        try{
            admin = new User();
            Role userRole = userRoleDao.findById((long) 1).get();
            admin.setFirstName("Default");
            admin.setLastName("Admin");
            admin.setEmail("admin@sample.com");
            admin.setIsEmailVerified("TRUE");
            admin.setPassword(bcryptEncoder.encode("12345678"));
            admin.setRole(userRole);
            admin = userRepository.save(admin);
            userService.setUserRole(1, admin);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void seedDepartment() {
        Department wildLife = Department
                .builder()
                .name("Wild Life")
                .build();

        Department forestry = Department
                .builder()
                .name("Forestry")
                .build();

        try{
           departmentDao.save(wildLife);
           departmentDao.save(forestry);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void seedDivisions(){
        Department department1 = new Department();
        department1.setId(1);

        Department department2 = new Department();
        department2.setId(2);

        Map<String, String> divisionsWithProvinces = Map.ofEntries(
                Map.entry("Colombo", "Western"),
                Map.entry("Gampaha", "Western"),
                Map.entry("Kalutara", "Western"),
                Map.entry("Kandy", "Central"),
                Map.entry("Matale", "Central"),
                Map.entry("Nuwara Eliya", "Central"),
                Map.entry("Galle", "Southern"),
                Map.entry("Matara", "Southern"),
                Map.entry("Hambantota", "Southern"),
                Map.entry("Jaffna", "Northern"),
                Map.entry("Kilinochchi", "Northern"),
                Map.entry("Mannar", "Northern"),
                Map.entry("Vavuniya", "Northern"),
                Map.entry("Mullaitivu", "Northern"),
                Map.entry("Batticaloa", "Eastern"),
                Map.entry("Ampara", "Eastern"),
                Map.entry("Trincomalee", "Eastern"),
                Map.entry("Kurunegala", "North Western"),
                Map.entry("Puttalam", "North Western"),
                Map.entry("Anuradhapura", "North Central"),
                Map.entry("Polonnaruwa", "North Central"),
                Map.entry("Badulla", "Uva"),
                Map.entry("Monaragala", "Uva"),
                Map.entry("Ratnapura", "Sabaragamuwa"),
                Map.entry("Kegalle", "Sabaragamuwa")
        );
        divisionsWithProvinces.forEach((division, province) -> {
            Division tempDivision1 = createDivision(division, province, department1);
            Division tempDivision2 = createDivision(division, province, department2);

            divisionDao.save(tempDivision1);
            divisionDao.save(tempDivision2);
        });

    }

    private Division createDivision(String name, String province, Department department) {
        Division division = new Division();
        division.setName(name);
        division.setProvince(province);
        division.setCategoryId(department);
        return division;
    }
}
