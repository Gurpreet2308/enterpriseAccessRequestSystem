package database;

public class Area {

  private long areaId;
  private String areaName;


  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
    this.areaId = areaId;
  }


  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public static String getAreaName(long areaId){
    if(areaId!=0){
      switch ((int)areaId){
        case -799945917:
          return "Documentation";
        case -790760566:
          return "IT Lab II";
        case -685336499:
          return "IT Lab I";
        case -591467320:
          return "Meet Lobby";
        case 943363324:
          return "Cafeteria";
      }
    }return null;
  }

}
