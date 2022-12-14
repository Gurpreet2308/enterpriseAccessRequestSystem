package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class DatabaseExecution {

    private static Connection conn;
    private static String dbUrl = "jdbc:sqlserver://enterprise-application.database.windows.net:1433;database=enterpriseApplication;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;";
    // jdbc:sqlserver://enterprise-application-db-server.database.windows.net:1433;database=enterpriseApplicationDatabase;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;
    private static PreparedStatement stmnt;

    public int getColumnNo(String colName, String tableName){

        return 0;
    }

    public static boolean getDBConnection(){
        try{
            conn = DBCPDataSource.getConnection(); //with connection pooling
            /* without connection pooling
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            */
            if(conn!=null){ return true; }
            else{
                System.out.println("--DB connection not established");
            }
        }catch (Exception e){ e.printStackTrace();}
        return false;
    }

    public static Employee getEmpIdFromDb(String username){
        Employee emp = new Employee();
        try{
            if(getDBConnection()){
                String query = "select emp_id from employee where emp_user_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,username);

                ResultSet result = stmnt.executeQuery();
                while(result.next()){
                    int id = result.getInt(1);
                    emp.setEmpId(Long.valueOf(id));
                }
                conn.close();
            }
        }catch(Exception e){}
        return emp;
    }

    public static boolean authenticateUser(Long empId, String pwd){
        ArrayList<String> passwords = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select emp_role_password from employee_role where emp_id = ? and role_end_date is null";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,empId.intValue());

                ResultSet r = stmnt.executeQuery();
                if(r!=null){
                    while(r.next()){
                        passwords.add(r.getString(1));
                        BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(), r.getString(1));
                        if(result.verified==true){
                            return true;
                        }
                    }
                }
                else {
                    System.out.println("--no passwords matching");
                }
                if(passwords.contains(pwd)){
                    conn.close();
                    return true;}
            }
        }catch (Exception e){}
        return false;
    }

    public static EmployeeRole getEmployeeRole(EmployeeRole empRole){
        try{
            if(getDBConnection()){
                String query = "select * from employee_role where emp_id= ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)empRole.getEmpId());
                ResultSet result = stmnt.executeQuery();
                if(result.isBeforeFirst()){
                    while (result.next()){
                        if(empRole.getEmpRolePassword().equals(result.getString("emp_role_password"))){
                            empRole.setRoles(new ArrayList<>(Collections.singleton(getRoleNameFromRoleId(result.getInt("role_id")))));
                        }
                        else if(verifyPassword(empRole.getEmpRolePassword(),result.getString("emp_role_password"))){
                            empRole.setRoles(new ArrayList<>(Collections.singleton(getRoleNameFromRoleId(result.getInt("role_id")))));
                        }
                    }
                }
                /*
                String query = "select role_name from role where role_id = (select role_id from employee_role where emp_role_password=?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,pwd);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        employeeRole.setRoles(new ArrayList<String>(Collections.singleton(result.getString(1))));
                        System.out.println(employeeRole.getRoles());
                    }
                }*/
                else{
                    System.out.println("--no role found for the logged in user");
                }
            }
            conn.close();
        }catch(Exception e){}
        return empRole;
    }

    private static boolean verifyPassword(String plainPwd, String cipherPwd){
        BCrypt.Result result = BCrypt.verifyer().verify(plainPwd.toCharArray(), cipherPwd);
        if(result.verified==true){
            return true;
        }
        return false;
    }

    public static List<String> getRoleUIButtons(String role){
        List<String> uiButtons = new ArrayList<String>();
        try{
            if(getDBConnection()){
                String query = "select button_name from ui_button where button_id in (select button_id from role_ui_button where role_id = (select role_id from role where role_name = ?))";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,role);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        uiButtons.add(result.getString(1));
                    }
                }
                else{
                    System.out.println("--no UI buttons found for the role");
                }
            }
            conn.close();
        }catch (Exception e){}
        return uiButtons;
    }

    public static Employee getEmployeeFirstName(String username){
        Employee emp = new Employee();
        try{
            if(getDBConnection()){
                String query = "select emp_first_name from employee where emp_user_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,username);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        emp.setEmpFirstName(result.getString(1));
                    }
                }
                else{
                    System.out.println("--no first name for the user returned");
                }
            }
            conn.close();
        }catch (Exception e){};
        return emp;
    }

    public static Employee getEmployeeLastName(String username){
        Employee emp = new Employee();
        try{
            if(getDBConnection()){
                String query = "select emp_last_name from employee where emp_user_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,username);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        emp.setEmpLastName(result.getString(1));
                    }
                }
                else{
                    System.out.println("--no last name for the user returned");
                }
            }
            conn.close();
        }catch (Exception e){}
        return emp;
    }

    public static ArrayList<EmployeeRole> getAllEmpRolesDetails(){
        ArrayList<EmployeeRole> allEmpRolesDetails = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select * from employee_role order by role_id";
                stmnt = conn.prepareStatement(query);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        EmployeeRole empRoleDetails = new EmployeeRole();
                        empRoleDetails.setRoleId(result.getInt("role_id"));
                        empRoleDetails.setRoleEndDate(result.getTimestamp("role_end_date"));
                        empRoleDetails.setRoleStartDate(result.getTimestamp("role_start_date"));
                        empRoleDetails.setRoleId(result.getInt("role_id"));
                        empRoleDetails.setEmpId(result.getInt("emp_id"));
                        allEmpRolesDetails.add(empRoleDetails);
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return allEmpRolesDetails;
    }

    public  static String getEmployeeFullNameFromEmpId(int empId){
        String empFullName = "";
        try{
            if(getDBConnection()){
                String query = "select emp_first_name, emp_last_name from employee where emp_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,empId);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        empFullName = result.getString("emp_first_name")+" ";
                        empFullName+=result.getString("emp_last_name");
                    }
                }
                else{System.out.println("--No user with this id exists");}
            }
            conn.close();
        }catch(Exception e){}
        return empFullName;
    }

    public static String getRoleNameFromRoleId(int roleId){
        String roleName = "";
        try{
            if(getDBConnection()){
                String query = "select role_name from role where role_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,roleId);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while (result.next()){
                        roleName = result.getString(1);
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return roleName;
    }

    public static ArrayList<String> getAllRoles(){
        ArrayList<String> roles = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select * from role";
                stmnt = conn.prepareStatement(query);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        roles.add(result.getString("role_name"));
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return roles;
    }

    public static boolean modifyAccessForEmployee(EmployeeRole empRole){
        try{
            if(getDBConnection()){
                String query = "select * from employee_role where emp_id = ? and role_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)empRole.getEmpId());//stmnt.setInt(1,empId);
                stmnt.setInt(2,(int)empRole.getRoleId());//stmnt.setInt(2,roleId);

                ResultSet result = stmnt.executeQuery();
                if(result.next()){
                    return updateEmployeeRole(empRole);
                    //update the row with new end date
                }
                else{
                    //insert new role row
                    return addEmployeeRole(empRole);
                }
            }
            conn.close();
        }catch(Exception e){}
        return false;
    }

    private static boolean updateEmployeeRole(EmployeeRole empRole){
        try{
            String query = "UPDATE employee_role set role_end_date = CAST(? as datetime2) WHERE emp_id = ? and role_id = ?";
            stmnt = conn.prepareStatement(query);
            if(empRole.getRoleEndDate()==null){
                stmnt.setString(1,null);
            }
            else{
                stmnt.setTimestamp(1,empRole.getRoleEndDate());//stmnt.setDate(1,new Date(endDate.getTime()));
            }
            stmnt.setInt(2,(int)empRole.getEmpId());//stmnt.setInt(2,emp_id);
            stmnt.setInt(3,(int)empRole.getRoleId());//stmnt.setInt(3,role_id);

            int result = stmnt.executeUpdate();
            if(result>0){
                return true;
            }
        }catch(Exception e){}
        return false;
    }

    public static boolean addEmployeeRole(EmployeeRole empRole){
        try{
            String query = "INSERT into employee_role VALUES (?,?,?,?,?)";
            stmnt = conn.prepareStatement(query);
            stmnt.setTimestamp(1,empRole.getRoleStartDate());//stmnt.setDate(1,new Date(startDate.getTime()));
            if(empRole.getRoleEndDate()==null){
                stmnt.setString(2,null);
            }
            else{
                stmnt.setTimestamp(2,empRole.getRoleEndDate());
                //stmnt.setDate(2,new Date(endDate.getTime()));
            }
            stmnt.setString(3,empRole.getEmpRolePassword());//stmnt.setString(3,empPwd);
            stmnt.setInt(4,(int)empRole.getEmpId());//stmnt.setInt(4,empId);
            stmnt.setInt(5,(int)empRole.getRoleId());//stmnt.setInt(5,roleId);
            System.out.println(query);
            int result = stmnt.executeUpdate();
            if(result>0){
                return true;
            }
        }catch (Exception e){}
        return false;
    }

    public static int getRoleIdFromName(String role){
        int roleId = 0;
        try{
            if(getDBConnection()){
                String query = "select role_id from role where role_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,role);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while (result.next()){
                        roleId = result.getInt(1);
                    }
                    return roleId;
                }
            }
            conn.close();
        }catch(Exception e){}
        return -1;
    }

    public static Employee getEmpIdFromName(String empName){
        Employee emp = new Employee();
        List<String> names = new ArrayList<>();
        Collections.addAll(names,empName.split(" "));
        try{
            if(getDBConnection()){
                String query = "select emp_id from employee where emp_first_name = ? and emp_last_name= ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,names.get(0));
                stmnt.setString(2,names.get(1));

                ResultSet result = stmnt.executeQuery();
                while(result.next()){
                    int id = result.getInt(1);
                    emp.setEmpId(Long.valueOf(id));
                }
                conn.close();
            }
        }catch(Exception e){}
        return emp;
    }

    public static ArrayList<String> getAllDept(){
        ArrayList<String> allDept = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select * from department";
                stmnt = conn.prepareStatement(query);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        allDept.add(result.getString("dept_name"));
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return allDept;
    }

    public static int getDeptIdFromName(String dept){
        int deptId = 0;
        try{
            if(getDBConnection()){
                String query = "select dept_id from department where dept_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,dept);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while (result.next()){
                        deptId = result.getInt(1);
                    }
                    return deptId;
                }
            }
            conn.close();
        }catch(Exception e){}
        return -1;
    }

    public static boolean createEmployeeDepartment(EmployeeDepartment empDept){
        try{
            if(getDBConnection()){
                String query = "INSERT into employee_department VALUES (?,?,?,?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setDate(1, new Date(empDept.getDeptStartDate().getTime()));
                if(empDept.getDeptEndDate()==null){
                    stmnt.setDate(2,null);
                }
                else{
                    stmnt.setDate(2, new Date(empDept.getDeptEndDate().getTime()));
                }
                stmnt.setInt(3,(int)empDept.getEmpId());
                stmnt.setInt(4,(int)empDept.getDeptId());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static Employee createEmployee(Employee emp){
        try{
            if(getDBConnection()){
                String query ="insert into employee VALUES (ABS(checksum(NEWID())),?,?,?,?,?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,emp.getEmpFirstName());
                stmnt.setString(2,emp.getEmpLastName());
                stmnt.setString(3,emp.getEmpPhoneNo());
                stmnt.setString(4,emp.getEmpEmailAddress());
                stmnt.setString(5,emp.getEmpUserName());

                int result = stmnt.executeUpdate();
                if(result>0){
                   emp.setEmpId(getEmpIdFromDb(emp.getEmpUserName()).getEmpId());
                }
            }conn.close();
        }catch (Exception e){}
        return emp;
    }

    public static boolean setLoginAuditEntry(LoginAudit loginAud){
        try{
            if(getDBConnection()){
                String query = "insert into login_audit VALUES (ABS(checksum(NEWID())),?,?,?,?,?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)loginAud.getEmpId());
                stmnt.setInt(2,(int)loginAud.getRoleId());
                stmnt.setTimestamp(3,loginAud.getLoginTime()); //stmnt.setDate(3, new Date(loginAud.getLoginTime().getTime()));
                stmnt.setTimestamp(4,null);
                stmnt.setInt(5,0);
                int result = stmnt.executeUpdate();
                if(result>0){ return true; }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static LoginAudit getLatestLoginAudit(LoginAudit loginAudit){
        try{
            if(getDBConnection()){
                String query = "select top 1 * from login_audit WHERE emp_id = ? and role_id = ? order by login_time DESC";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)loginAudit.getEmpId());
                stmnt.setInt(2,(int)loginAudit.getRoleId());

                ResultSet result = stmnt.executeQuery();
                while(result.next()){
                    int id = result.getInt("audit_id");
                    loginAudit.setAuditId(id);
                }
            }conn.close();
        }catch (Exception e){}
        return loginAudit;
    }
    public static boolean updateLoginAuditEntry(LoginAudit loginAudit){
        try{
            if(getDBConnection()){
                String query = "UPDATE login_audit set logoff_time = ?, tasks_executed = ? WHERE audit_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setTimestamp(1,loginAudit.getLogoffTime());
                stmnt.setInt(2,(int)loginAudit.getTasksExecuted());
                stmnt.setInt(3, (int)loginAudit.getAuditId());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static Employee getLatestEmployee(){
        Employee emp = new Employee();
        try{
            if(getDBConnection()){
                String query = "select * from employee where emp_id IN (select top 1 emp_id from employee_role ORDER by role_start_date DESC)";
                stmnt = conn.prepareStatement(query);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        String name = result.getString("emp_first_name");
                        String lname = result.getString("emp_last_name");
                        String userName = result.getString("emp_user_name");
                        String phoneNo = result.getString("emp_phone_no");
                        String email = result.getString("emp_email_address");
                        long id = Long.valueOf(result.getInt("emp_id"));

                        emp.setEmpId(id);
                        emp.setEmpEmailAddress(email);
                        emp.setEmpPhoneNo(phoneNo);
                        emp.setEmpLastName(lname);
                        emp.setEmpFirstName(name);
                        emp.setEmpUserName(userName);
                    }
                }
            }conn.close();
        }catch (Exception e){}
        return emp;
    }

    public static EmployeeRole getEmpRoleDetails(Employee emp){
        EmployeeRole empRole = new EmployeeRole();
        try{
            if(getDBConnection()){
                String query = "select * from employee_role where emp_id = ? and role_end_date is null";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)emp.getEmpId());
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        empRole.setRoleId(result.getInt("role_id"));
                        empRole.setRoleEndDate(result.getTimestamp("role_end_date"));
                        empRole.setRoleStartDate(result.getTimestamp("role_start_date"));
                        empRole.setRoleId(result.getInt("role_id"));
                        empRole.setEmpId(result.getInt("emp_id"));
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return empRole;
    }

    public static EmployeeDepartment getEmpDeptDetails(Employee emp){
        EmployeeDepartment empDept = new EmployeeDepartment();
        try{
            if(getDBConnection()){
                String query = "select * from employee_department where emp_id = ? and dept_end_date is null";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1,(int)emp.getEmpId());
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        empDept.setDeptId(result.getInt("dept_id"));
                        empDept.setDeptEndDate(result.getTimestamp("dept_end_date"));
                        empDept.setDeptStartDate(result.getTimestamp("dept_start_date"));
                        empDept.setEmpId(Long.valueOf(result.getInt("emp_id")));
                        empDept.setDeptName(result.getString("dept_name"));
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return empDept;
    }

    public static EmployeeDepartment getDeptNameFromId(EmployeeDepartment empDept){
        try{
            if(getDBConnection()){
                String query = "select * from department where dept_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setInt(1, (int)empDept.getDeptId());

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        empDept.setDeptName(result.getString("dept_name"));
                    }
                }
            }conn.close();
        }catch(Exception e){}
        return empDept;
    }

    public static ArrayList<String> getAllArea(){
        ArrayList<String> allArea = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select * from area";
                stmnt = conn.prepareStatement(query);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        allArea.add(result.getString("area_name"));
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return allArea;
    }

    public static int getAreaIdFromName(String area){
        try{
            if(getDBConnection()){
                String query="select area_id from area where area_name = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setString(1,area);
                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while (result.next()){
                        int areaId = result.getInt(1);
                        return areaId;
                    }
                }
            }
            conn.close();
        }catch(Exception e){}
        return 0;
    }

    public static boolean createRequest(Request req, Employee emp){
        try{
            if(getDBConnection()){
                String query="INSERT into request VALUES (ABS(checksum(NEWID())),?,?,?,?);";
                stmnt = conn.prepareStatement(query, new String[]{"req_id"});
                stmnt.setInt(1,(int)emp.getEmpId());
                stmnt.setTimestamp(2,req.getReqCreatedDate());
                stmnt.setString(3,null);
                stmnt.setInt(4,(int)req.getAreaRequested());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch(Exception e){}
        return false;
    }

    public static Request getLatestRequest(){
        Request req = new Request();
        try{
            if(getDBConnection()){
                String query = "select top 1 * from request ORDER by req_created_date DESC";
                stmnt = conn.prepareStatement(query);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        req.setReqId(Long.valueOf(result.getInt("req_id")));
                        req.setReqCreatedDate(result.getTimestamp("req_created_date"));
                        req.setEmpId(Long.valueOf(result.getInt("emp_id")));
                        req.setAreaRequested(Long.valueOf(result.getInt("area_requested")));
                        req.setReqCompletedDate(result.getTimestamp("req_completed_date"));
                    }
                }
            }conn.close();
        }catch (Exception e){}
        return req;
    }

    public static boolean setRequestStatus(RequestStatus reqStat){
        try{
            if(getDBConnection()){
                String query = "insert into request_status VALUES (?,?,?,?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,reqStat.getReqId());
                stmnt.setLong(2,reqStat.getStatusId());
                stmnt.setTimestamp(3,reqStat.getReqStatusStartTime());
                stmnt.setLong(4, reqStat.getReqApproverId());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static boolean modifyRequestStatus(RequestStatus reqStat){
        try{
            if(getDBConnection()){
                String query = "UPDATE request_status set status_id = ? WHERE req_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,reqStat.getStatusId());
                stmnt.setLong(2,reqStat.getReqId());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static boolean modifyRequestCompletedDate(Request req){
        try{
            if(getDBConnection()){
                String query = "UPDATE request set req_completed_date = ? WHERE req_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setTimestamp(1,req.getReqCompletedDate());
                stmnt.setLong(2,req.getReqId());

                int result = stmnt.executeUpdate();
                if(result>0){
                    return true;
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static long getAreaApproverId(Long areaRequested){
        long approverId = 0;
        try{
            if(getDBConnection()){
                String query ="select approver_id from area_approver where area_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,areaRequested);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        approverId = result.getLong("approver_id");
                    }
                }
            }conn.close();
        }catch (Exception e){}
        return approverId;
    }

    public static ArrayList<Request> getAllRequests(long empId){
        ArrayList<Request> allRequests = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query="select * from request where req_id in (select req_id from request_status where req_approver_id = ?) ORDER by req_created_date DESC";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,empId);

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        Request req = new Request();
                        req.setReqId(result.getLong("req_id"));
                        req.setAreaRequested(result.getLong("area_requested"));
                        req.setEmpId(result.getLong("emp_id"));
                        req.setReqCreatedDate(result.getTimestamp("req_created_date"));
                        allRequests.add(req);
                    }
                }
            }conn.close();
        }catch(Exception e){}
        return allRequests;
    }

    public static RequestStatus getRequestStatus(Request req){
        RequestStatus reqStatus = new RequestStatus();
        try{
            if(getDBConnection()){
                String query = "select * from request_status where req_id = ?";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,req.getReqId());

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        reqStatus.setStatusId(result.getLong("status_id"));
                        reqStatus.setReqId(result.getLong("req_id"));
                        reqStatus.setReqStatusStartTime(result.getTimestamp("req_start_time"));
                        reqStatus.setReqApproverId(result.getLong("approver_id"));
                    }
                }
            }
            conn.close();
        }catch (Exception e){}
        return reqStatus;
    }
    public static boolean addAreaToEmployee(EmployeeArea empArea){
        try{
            if(getDBConnection()){
                String query = "INSERT into employee_area VALUES (?,?,?,?)";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,empArea.getAreaId());
                stmnt.setLong(2,empArea.getEmpId());
                stmnt.setTimestamp(3,empArea.getAreaStartDate());
                stmnt.setTimestamp(4, empArea.getAreaEndDate());

                int result = stmnt.executeUpdate();
                if(result>0){
                    conn.close();
                    return true;
                }
            }conn.close();
        }catch(Exception e){}
        return false;
    }

    public static boolean checkAreaForEmployee(EmployeeArea empArea){
        try{
            if(getDBConnection()){
                String query = "select * from employee_area where area_id= ? AND emp_id= ? and area_end_date is NULL;";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,empArea.getAreaId());
                stmnt.setLong(2,empArea.getAreaId());

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        conn.close();
                        return true;
                    }
                }
            }conn.close();
        }catch (Exception e){}
        return false;
    }

    public static ArrayList<Area> getEmployeeAreas(Employee emp){
        ArrayList<Area> empAreas = new ArrayList<>();
        try{
            if(getDBConnection()){
                String query = "select area_id from employee_area where emp_id = ? and area_end_date is NUll";
                stmnt = conn.prepareStatement(query);
                stmnt.setLong(1,emp.getEmpId());

                ResultSet result = stmnt.executeQuery();
                if(result!=null){
                    while(result.next()){
                        Area area = new Area();
                        area.setAreaId(result.getLong("area_id"));
                        empAreas.add(area);
                    }
                }
            }
        }catch (Exception e){}
        return empAreas;
    }
}

