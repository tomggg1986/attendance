package entity;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Attendance extends Identifier{
    
    private int id;
    private LocalDate date;
    private String subject;
    private Map<String,String> attendances;
    
    public Attendance(String data){
        this.attendances = new HashMap<>();
        String[] temp =data.split(",");  
        this.id = Integer.parseInt(temp[0]);
        this.date = LocalDate.parse(temp[1]);
        this.subject = temp[2];       
        for(int i=3;i<temp.length;i++){
          String[] map = temp[i].split("-");
          this.attendances.put(map[0], map[1]);
        }       
    }
    public Attendance(int id, LocalDate date, String subject){
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.attendances = new HashMap<>();
    }
    @Override
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, String> getAttendances() {
        return attendances;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAttendances(Map<String, String> attendances) {
        this.attendances = attendances;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attendance other = (Attendance) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.attendances, other.attendances)) {
            return false;
        }
        return true;
    }
    public String praseToCsv(){
        String map = "";
        for(Map.Entry<String,String> entry: this.attendances.entrySet()){
           map +=COMMA_DELIMITER+entry.getKey()+"-"+entry.getValue();
        }
        return this.id+COMMA_DELIMITER+this.date+","+this.subject+map+NEW_LINE_SEPARATOR;
    }

    @Override
    public String toString() {
        return "Attendance{" + "id=" + id + ", Date=" + date.toString() + ", subject=" + subject + ", attendances=" + attendances + '}';
    }
    
    
}
