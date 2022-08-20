package database;


public class Employee {

  private long empId;
  private String empFirstName;
  private String empLastName;
  private String empPhoneNo;
  private String empEmailAddress;
  private String empUserName;

  public Employee() {
  }

  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }


  public String getEmpFirstName() {
    return empFirstName;
  }

  public void setEmpFirstName(String empFirstName) {
    this.empFirstName = empFirstName;
  }


  public String getEmpLastName() {
    return empLastName;
  }

  public void setEmpLastName(String empLastName) {
    this.empLastName = empLastName;
  }


  public String getEmpPhoneNo() {
    return empPhoneNo;
  }

  public void setEmpPhoneNo(String empPhoneNo) {
    this.empPhoneNo = empPhoneNo;
  }


  public String getEmpEmailAddress() {
    return empEmailAddress;
  }

  public void setEmpEmailAddress(String empEmailAddress) {
    this.empEmailAddress = empEmailAddress;
  }


  public String getEmpUserName() {
    return empUserName;
  }

  public void setEmpUserName(String empUserName) {
    this.empUserName = empUserName;
  }

}
