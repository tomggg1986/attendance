package entity;

import java.time.LocalDate;
import java.util.Objects;


public class Participant extends Identifier {
    
    private int id;
    private String name;
    private String lastName;
    private LocalDate brithDay;
    private String telephoneNumber;

    public Participant(String data){
        System.out.println(data);
        String[] temp =  data.split(COMMA_DELIMITER, 5);
        this.id = Integer.parseInt(temp[0]);
        this.name = temp[1];
        this.lastName = temp[2];
        this.telephoneNumber = temp[4];       
        this.brithDay = LocalDate.parse(temp[3]);            
    }
    public Participant(int id,String name,String lastName, LocalDate brithDay, String telephoneNumber){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.brithDay = brithDay;
        this.telephoneNumber = telephoneNumber;
    }
    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBrithDay() {
        return brithDay;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBrithDay(LocalDate brithDay) {
        this.brithDay = brithDay;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public String praseToCsv(){
        return this.id+COMMA_DELIMITER+this.name+","+this.lastName+COMMA_DELIMITER+this.brithDay.toString()+COMMA_DELIMITER+this.telephoneNumber+NEW_LINE_SEPARATOR;
    }
    @Override
    public int hashCode() {
        int hash = this.id;
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
        final Participant other = (Participant) obj; 
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if(!Objects.equals(this.brithDay, other.brithDay)){
            return false;
        }
        if(!Objects.equals(this.telephoneNumber, other.telephoneNumber)){
        return false;
    }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", lastName=" + lastName + ", brithDay=" + this.brithDay.toString() + ", telephoneNumber=" + telephoneNumber;
    }
    
    
}
