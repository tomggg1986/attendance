package main;

import entity.Attendance;
import entity.Participant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Reader {
    List<Attendance> attend;
    List<Participant> parti;
    Scanner command;
    File participantFile;
    File attendanceFile;
    
   public Reader(){
        this.parti = new ArrayList<>();
        this.attend = new ArrayList<>();
        this.command = new Scanner(System.in);
        try{
            this.readFParticipant(this.participantFile = new File("src\\participant.csv"));
            this.readFileAttendance(this.attendanceFile = new File("src\\attendance.csv"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        }        
    }    
    private void readFParticipant(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file,"UTF-8");
        while(scanner.hasNextLine()){  
            System.out.println("readparticipant");
            parti.add(new Participant(scanner.nextLine()));
        }
        scanner.close();
    }
     private void readFileAttendance(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file,"UTF-8");
        while(scanner.hasNextLine()){            
            attend.add(new Attendance(scanner.nextLine()));
        }
        scanner.close();
    }
     public void showMenu(){
         System.out.println("-----------------------------Attendances List-----------------------------------\n");
         System.out.println("a - show Participand");
         System.out.println("b - show Paricicpand attendances");
         System.out.println("c - show all attendances");
         System.out.println("d - show attendances by date");
         System.out.println("e - add Participant");
         System.out.println("f - add Attendance");
         System.out.println("q - Quit system");
         System.out.println("Select operation");
         String com = this.command.next();
         
         switch (com){
            case "a":
               this.showParticipant(); 
               this.backToMenu();
               break;
            case "b":
               this.showParticipandAttend();  
               this.backToMenu();
               break;
            case "c":
               this.showAttendance();
               break;
            case "d":
                this.showAttendanceByDate();
                break;
            case "e":
                this.addParticipant();
                break; 
            case "f":
                this.addAttendance();
                break;
            case "q":
                this.quit();
                break;
         }        
     }
    public void showParticipant(){
        ListIterator iter = this.parti.listIterator();
        System.out.println("Size "+this.parti.size());
        while(iter.hasNext()){
            Participant p = (Participant)iter.next();
            System.out.println(p);
        }        
    }    
    public void showAttendance(){
        Iterator inter = this.attend.iterator();
        while(inter.hasNext()){
             Attendance a = (Attendance)inter.next();        
             System.out.println("Appendance for: "+a.getDate().toString());
             Set<String> key = a.getAttendances().keySet(); 
             Iterator keyI = key.iterator();
             while(keyI.hasNext())
             {
                 String keyS = (String)keyI.next();
                 System.out.println("Participant: "
                         +getParticipantById(Integer.parseInt(keyS)).getName()
                         +" "+getParticipantById(Integer.parseInt(keyS)).getLastName()
                         +" - "+a.getAttendances().get(keyS));
             }
             
        }
        this.backToMenu();
    }
      private boolean showAttendanceByDate(String date){ //return true if find matching date 
        Iterator inter = this.attend.iterator();
        boolean isFound = false;
        while(inter.hasNext()){
             Attendance a = (Attendance)inter.next();
             if(a.getDate().toString().equals(date)){
                System.out.println("Appendance for: "+a.getDate().toString());
                Set<String> key = a.getAttendances().keySet(); 
                Iterator keyI = key.iterator();
                while(keyI.hasNext())
                 {
                    String keyS = (String)keyI.next();
                    System.out.println("Participant: "
                         +getParticipantById(Integer.parseInt(keyS)).getName()
                         +" "+getParticipantById(Integer.parseInt(keyS)).getLastName()
                         +" - "+a.getAttendances().get(keyS));
               } 
                isFound = true;
             }  
        }
        return isFound;
    }
    private void showParticipandAttend(){
        Scanner sc = new Scanner(System.in);
        this.showParticipant();
        System.out.println("Select participand id:");
        int id = sc.nextInt();
        this.showAttendanceByParticipand(id);
    }
      private void showAttendanceByParticipand(int id){
        Iterator inter = this.attend.iterator();
        while(inter.hasNext()){
             Attendance a = (Attendance)inter.next();               
                Set<String> key = a.getAttendances().keySet(); 
                //Iterator keyI = key.iterator();
                for(String keyI: key)
                {
                    int key_int = Integer.parseInt(keyI);
                    if( key_int == id)
                    {
                         System.out.println("Participant: "
                         +getParticipantById(key_int).getName()
                         +" "+getParticipantById(key_int).getLastName()
                         +" - "+"Date"+a.getDate()+" "+a.getAttendances().get(Integer.toString(key_int)));
                    }
               }   
        }
       }
    private Participant getParticipantById(int id){
        return this.parti.stream().filter(e -> e.getId() == id).collect(Collectors.toList()).get(0);
    }
    public void showAttendanceByDate(){
        // Runtime.getRuntime().exec("cls");
        System.out.println("Choose date (format yyyy-mm-dd):");
        String date =  new Scanner(System.in).nextLine();
        if(!this.showAttendanceByDate(date))
            System.out.println("No matching date id system");
        this.backToMenu();
     
    } 
    private void backToMenu(){
        System.out.println("Back to menu press m");
        Scanner sc = new Scanner(System.in);
        if(sc.next().equals("m"))
            this.showMenu();
        sc.close();
    }
    private int generateParticipantId(){
        int id =0; 
       for(int i = 0;i< this.parti.size();i++){
           if(id<this.parti.get(i).getId()){
               id = this.parti.get(i).getId();
           }
       }
        return ++id;
    }
     private int generateAttendanceId(){
        int id =0; 
       for(int i = 0;i< this.attend.size();i++){
           if(id<this.attend.get(i).getId()){
               id = this.attend.get(i).getId();
           }
       }
        return ++id;
    }
    private void addParticipant(){
        Scanner sc = new Scanner(System.in);
        String name;
        String lastName;
        LocalDate brithDay;
        String telephoneNumber;
        System.out.println("Name:");
        name = sc.nextLine();
        System.out.println(name);
        System.out.println("LastName:");
        lastName = sc.nextLine();
        System.out.println("Brithday (yyyy-mm-dd):");
        brithDay = LocalDate.parse(sc.nextLine());
        System.out.println("Telephone number:");
        telephoneNumber = sc.nextLine();
        Participant p = new Participant(this.generateParticipantId(),name,lastName,brithDay,telephoneNumber);
        if(this.addParticipant(p)){ 
            System.out.println("Participant add to list");
            System.out.println("back to menu press m add another participant press p");
            String c  = this.command.next();
            if(c.equals("m")){
                //sc.close();
                this.showMenu();
            }    
            else if(c.equals("p"))
                this.addParticipant();
        }else{
            System.out.println("This participant is already in system  ");
            System.out.println("back to menu press m add another participant press p");
            String c  = this.command.next();
            if(c.equals("m")){
               // sc.close();
                this.showMenu();
            }  
            else if(c.equals("p"))
                this.addParticipant();
        }
    
    }
    private boolean addParticipant(Participant p){
       for(int i = 0;i<this.parti.size();i++){
           if(this.parti.get(i).equals(p))
               return false;
       } 
       System.out.println("Wyszło");
       this.parti.add(p);
       return true;
    }
    private void quit(){
        this.command.close();
        if(this.saveParticipant()){
            System.out.println("Save changesa");
        }
        
        
    }
    private boolean saveParticipant(){
        try {
            FileWriter fw = new FileWriter(this.participantFile);
            for(Participant participant : this.parti){
                fw.append(participant.praseToCsv());
            }
            //fw.flush();
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("saveError");
            return false;
        }
        return true;
    }
    public void addAttendance(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Attendance date:");
         LocalDate date = LocalDate.parse(sc.nextLine());
         System.out.println("Subject:");
         String subject = sc.nextLine();
         this.attend.add(new Attendance(this.generateAttendanceId(),date,subject));
         System.out.println("Addtendance added");
//           this.attend.forEach(a -> System.out.println(a.praseToCsv()));
    }
    public static void main(String[] args){
        Reader reader = new Reader();
//        System.out.println("Participants: ");
//        reader.parti.forEach(p -> System.out.println(p));
//        reader.attend.forEach(p -> System.out.println(p));
//        reader.showAttendance();
//        System.out.println("Appredance by date");
//        reader.showAttendanceByDate("2017-12-01");
          //eSystem.setProperty("file.encoding", "UTF-8");
          //System.out.println(System.getProperty("file.encoding"));
            
        reader.showMenu();
}
   
}
