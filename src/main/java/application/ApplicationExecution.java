package application;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.*;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.*;

public class ApplicationExecution {

    public static Employee checkLogin(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()){
            database.Employee emp = DatabaseExecution.getEmpIdFromDb(username);
            if(emp.getEmpId()!=0){
                System.out.println("--user exists in db");
                long empId = emp.getEmpId();
                if(DatabaseExecution.authenticateUser(empId, password)){
                    return emp;
                }
                else{
                    System.out.println("--wrong password");
                }
            }
            else{
                System.out.println("--no user found in the database with this name");
            }
        }
        else{
            System.out.println("--no employee id or password provided");
        }
        return null;
    }

    public static EmployeeRole getEmployeeLoggedInRole(String userName, String password){
        if(!password.isEmpty() && !userName.isEmpty()){
            EmployeeRole empRole = new EmployeeRole();
            empRole.setEmpRolePassword(password);
            empRole.setEmpId(DatabaseExecution.getEmpIdFromDb(userName).getEmpId());
            empRole.setRoles(DatabaseExecution.getEmployeeRole(empRole).getRoles());
            empRole.setRoleId(DatabaseExecution.getRoleIdFromName(empRole.getRoles().get(0)));

            if(empRole.getRoles()!=null){
                return empRole;
            }
        }
        return null;
    }

    public static List<String> getRoleUIButtons(String role){
        if(role!=null){
            List<String> uiButtons = DatabaseExecution.getRoleUIButtons(role);
            if(!uiButtons.isEmpty()){
                return uiButtons;
            }
        }
        return null;
    }

    public static String getEmployeeFirstName(String username){
        String firstName = "";
        if(username!=null){
            Employee emp = DatabaseExecution.getEmployeeFirstName(username);
            if(!emp.getEmpFirstName().isEmpty()){
                firstName = emp.getEmpFirstName();
            }
        }
        return firstName;
    }

    public static String getEmployeeLastName(String username){
        String lastName = "";
        if(username!=null){
            Employee emp = DatabaseExecution.getEmployeeLastName(username);
            if(!emp.getEmpLastName().isEmpty()){
                lastName = emp.getEmpLastName();
            }
        }
        return lastName;
    }

    public static ArrayList<EmployeeRole> getAllEmpRolesDetails (){
        ArrayList<EmployeeRole> allEmpRolesDetails = DatabaseExecution.getAllEmpRolesDetails();
        if(!allEmpRolesDetails.isEmpty() && allEmpRolesDetails!=null){
            for(EmployeeRole empRole : allEmpRolesDetails){
                empRole.setEmpName(DatabaseExecution.getEmployeeFullNameFromEmpId((int) empRole.getEmpId()));
                empRole.setRoles(new ArrayList<>(Collections.singletonList(DatabaseExecution.getRoleNameFromRoleId((int) empRole.getRoleId()))));
            }
            return allEmpRolesDetails;
        }
        return null;
    }

    public static ArrayList<String> getAllRoles(){
        ArrayList<String> roles = new ArrayList<String>(DatabaseExecution.getAllRoles());
        if(roles!=null && !roles.isEmpty()){
            return roles;
        }
        else{
            System.out.println("nor roles found in the application");
        }
        return null;
    }

    public static boolean modifyAccessForEmployee(String empName, EmployeeRole empRole){
        empRole.setRoleId(DatabaseExecution.getRoleIdFromName(empRole.getRoles().get(0)));
        empRole.setEmpRolePassword(generateEmpPwd(empName, (int)empRole.getRoleId()));
        empRole.setEmpId((int)DatabaseExecution.getEmpIdFromName(empName).getEmpId());
        return DatabaseExecution.modifyAccessForEmployee(empRole);
    }

    public static String generateEmpPwd(String empName, int roleId){
        List<String> names = new ArrayList<>();
        Collections.addAll(names, empName.split(" "));
        int number = roleId-1;
        String empPwd = names.get(0).toLowerCase()+"."+names.get(1).toLowerCase()+"0"+number;
        return empPwd;
    }

    public static ArrayList<String> getAllDept(){
        ArrayList<String> allDept = new ArrayList<String>(DatabaseExecution.getAllDept());
        if(!allDept.isEmpty() && allDept!=null){
            return allDept;
        }
        else{
            System.out.println("-- no departments found in the application");
        }
        return null;
    }

    public static String createEmployee(Employee emp, EmployeeRole empRole, EmployeeDepartment empDept){
        if(emp!=null && empRole!=null){
            emp.setEmpUserName(generateEmpUserName(emp.getEmpFirstName(), emp.getEmpLastName()));
            emp.setEmpId(DatabaseExecution.createEmployee(emp).getEmpId());
            if(emp.getEmpId()!=0){
                if(createEmployeeRole(emp, empRole)){
                    if(createEmployeeDepartment(emp, empDept)){
                        return emp.getEmpUserName();
                    }
                }
            }
        }
        else{ System.out.println("-- no data found for new employee");}
        return null;
    }

    private static String generateEmpUserName(String fname, String lname){
        String first = fname.toLowerCase();
        String last = lname.toLowerCase();
        return  first + last.charAt(0) + "123";
    }

    private static boolean createEmployeeRole(Employee emp, EmployeeRole empRole){
        empRole.setRoleId(DatabaseExecution.getRoleIdFromName(empRole.getRoles().get(0)));
        empRole.setEmpId(emp.getEmpId());
        empRole.setRoleStartDate(new Timestamp(System.currentTimeMillis()));
        empRole.setRoleEndDate(null);
        String plainPwd = generateEmpPwd(emp.getEmpFirstName()+" "+emp.getEmpLastName(), DatabaseExecution.getRoleIdFromName(empRole.getRoles().get(0)));
        String hashPwd = encryptPassword(plainPwd);
        empRole.setEmpRolePassword(hashPwd);
        if(DatabaseExecution.addEmployeeRole(empRole)){
            return true;
        } return false;
    }

    private static boolean createEmployeeDepartment(Employee emp, EmployeeDepartment empDept){
        empDept.setDeptStartDate(new Timestamp(System.currentTimeMillis()));
        empDept.setDeptEndDate(null);
        empDept.setEmpId(emp.getEmpId());
        empDept.setDeptId(DatabaseExecution.getDeptIdFromName(empDept.getDeptName()));
        if(DatabaseExecution.createEmployeeDepartment(empDept)){
            return true;
        } return false;
    }

    private static String encryptPassword(String plainPwd){
        if(!plainPwd.isEmpty() && plainPwd!=null){
            String cipherPwd = BCrypt.withDefaults().hashToString(12, plainPwd.toCharArray());
            return cipherPwd;
        } return null;
    }

    public static boolean setLoginAuditEntry(Employee emp, EmployeeRole empRole){
        if(emp!=null && empRole!=null){
            LoginAudit loginAud = new LoginAudit();
            loginAud.setEmpId(emp.getEmpId());
            loginAud.setRoleId(empRole.getRoleId());
            loginAud.setLoginTime(new Timestamp(System.currentTimeMillis()));
            loginAud.setLogoffTime(null);
            loginAud.setTasksExecuted(0);
            if(DatabaseExecution.setLoginAuditEntry(loginAud)){
                return true;
            }
        } return false;
    }

    public static boolean updateLoginEntry(Employee emp, EmployeeRole empRole, int tasksExecuted){
        if(emp!=null && empRole!=null){
            LoginAudit loginAud = new LoginAudit();
            loginAud.setEmpId(emp.getEmpId());
            loginAud.setRoleId(empRole.getRoleId());
            loginAud.setAuditId(DatabaseExecution.getLatestLoginAudit(loginAud).getAuditId());
            loginAud.setLogoffTime(new Timestamp(System.currentTimeMillis()));
            loginAud.setTasksExecuted(tasksExecuted);
            if(DatabaseExecution.updateLoginAuditEntry(loginAud)){
                return true;
            }
        }return false;
    }

    public static Employee getLatestEmployee(){
        Employee emp = DatabaseExecution.getLatestEmployee();
        if(emp!=null){
            return emp;
        } return null;
    }

    public static EmployeeRole getEmpRoleDetails (Employee emp){
        EmployeeRole empRole = DatabaseExecution.getEmpRoleDetails(emp);
        if(empRole!=null){
            String role = DatabaseExecution.getRoleNameFromRoleId((int)empRole.getRoleId());
            empRole.setRoles(new ArrayList<String>(Collections.singleton(role)));
            return empRole;
        } return null;
    }

    public static EmployeeDepartment getEmpDeptDetails (Employee emp){
        EmployeeDepartment empDept = DatabaseExecution.getEmpDeptDetails(emp);
        if(empDept!=null){
            empDept.setDeptName(DatabaseExecution.getDeptNameFromId(empDept).getDeptName());
            return empDept;
        } return null;
    }

    public static ArrayList<String> getAllArea(){
        ArrayList<String> allArea = new ArrayList<String>(DatabaseExecution.getAllArea());
        if(!allArea.isEmpty() && allArea!=null){
            return allArea;
        }
        else{
            System.out.println("-- no areas found in the application");
        }return null;
    }

    public static Request createRequest(Request req, Employee emp){
        if(req!=null & emp!=null){
            emp.setEmpId(DatabaseExecution.getEmpIdFromDb(emp.getEmpUserName()).getEmpId());
            req.setAreaRequested(Long.valueOf(DatabaseExecution.getAreaIdFromName(req.getAreaName())));
            req.setReqCreatedDate(new Timestamp(System.currentTimeMillis()));
            if(DatabaseExecution.createRequest(req,emp)){
                Request latestReq = DatabaseExecution.getLatestRequest();
                if(setRequestStatus(latestReq, "Pending")){
                    req.setReqId(latestReq.getReqId());
                }
            }
        }return req;
    }

    public static Request getLatestRequest(){
        Request req = DatabaseExecution.getLatestRequest();
        if(req!=null){
            req.setAreaName(Area.getAreaName(req.getAreaRequested()));
            return req;
        }return null;
    }

    public static boolean setRequestStatus(Request req, String reqStatus){
        if(req!=null){
            RequestStatus reqStat = new RequestStatus();
            reqStat.setReqId(req.getReqId());
            reqStat.setReqStatusStartTime(req.getReqCreatedDate());
            reqStat.setReqApproverId(DatabaseExecution.getAreaApproverId(req.getAreaRequested()));
            reqStat.setStatusId(Status.getStatusId(reqStatus));
            if(DatabaseExecution.setRequestStatus(reqStat)){
                return true;
            }
        }return false;
    }

    public static boolean modifyRequestStatus(Request req, String reqStatus){
        if(req!=null){
            RequestStatus reqStat = new RequestStatus();
            reqStat.setReqId(req.getReqId());
            reqStat.setStatusId(Status.getStatusId(reqStatus));
            if(DatabaseExecution.modifyRequestStatus(reqStat)){
                req.setReqCompletedDate(new Timestamp(System.currentTimeMillis()));
                if(DatabaseExecution.modifyRequestCompletedDate(req)){
                    return true;
                }
            }
        }return false;
    }

    public static ArrayList<Request> getAllRequests(Employee emp){
        ArrayList<Request> allRequests = DatabaseExecution.getAllRequests(emp.getEmpId());
        if(allRequests!=null){
            for(Request req : allRequests){
                req.setAreaName(Area.getAreaName(req.getAreaRequested()));
                req.setEmpName(DatabaseExecution.getEmployeeFullNameFromEmpId((int)req.getEmpId()));
                req.setStatus(Status.getStatus(DatabaseExecution.getRequestStatus(req).getStatusId()));
            }
        }else{System.out.println("no requests found for the approver");}
        return allRequests;
    }
}
