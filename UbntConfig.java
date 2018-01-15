package javaapplication5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UbntConfig {
private FileReader config;
private BufferedReader reader;

//private FileWriter configSave;
//private BufferedWriter writer;
private List<String> listConfig;
     public static void main(String[] args) {
         
       UbntConfig configReader = new UbntConfig("src\\ubnt.cfg");
//       Set<String> set = configReader.getConfig();
//       for(String s: set){
//           System.out.println(s);
//       }
//       Set<String> setRadio = configReader.assing(set, "radio");
//       System.out.println("\n Radio: ");
//       for(String s2: setRadio){
//           System.out.println(s2);
//       }
//       configReader.changeCountryCode(set, "600");
//     configReader.saveConfig(set,"src\\ubnt.cfg");
    }
     
    public UbntConfig(String fileName){
        this.listConfig = new ArrayList<>();
//        try{
//            //config = new FileReader(fileName);
//          //  configSave = new FileWriter(fileName);
//        }catch(FileNotFoundException e){
//          System.out.println("File: "+fileName+" notFound");
//        }catch(IOException e){
//            e.printStackTrace();
//        }
       // reader = new BufferedReader(config);
       // writer = new BufferedWriter(configSave);
       this.readConfigToList(fileName);
       this.printListConfig();
    }
    
    public Set<String> getConfig(){
        Set<String> set = new HashSet<>(); 
        try{
            String line = reader.readLine(); 
            while(line  != null){
                set.add(line);
                line = reader.readLine();  
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return set;
    }
    public Set<String> assing(Set<String> s,String text){
        Set<String> set = new HashSet<>();
        for(String element: s){
            if(element.contains(text)){
                set.add(element);
            }
        }
        return set;
    }
    public void changeCountryCode(Set<String> s, String CountryCode){
        List<String> list = new ArrayList<>(s);
       
        for(int i =0;i<list.size();i++){
            if((list.get(i).contains("radio.countrycode=")) || (list.get(i).contains("radio.1.countrycode="))){
                String temp = list.get(i).substring(0, list.get(i).indexOf("=")+1);
                System.out.println(temp);
                temp += CountryCode;
                System.out.println(temp);
                list.set(i, temp);
            }
        }
//            for(String element: list){
//                if(element.contains("radio.1.countrycode=")){
//                   String temp = element.substring(0, element.indexOf("=")+1);
//                   System.out.println(temp);
//                   temp += CountryCode;
//                   System.out.println(temp);
//                   element = temp;
//                }
//            }
    s.removeAll(s);
    s.addAll(list);
    }
    public void saveConfig(Set<String> s, String file){
        BufferedWriter writer;
    try {
        writer = new BufferedWriter(new FileWriter(file));
        for(String element: s){          
                writer.append(element);
                writer.newLine();
        } 
        writer.close();
    } catch (IOException ex) {
                Logger.getLogger(UbntConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    private void readConfigToList(String FileName){
    try {
        BufferedReader bf = new BufferedReader(new FileReader(FileName));
        String line = bf.readLine();
        while(line != null){
            this.listConfig.add(line);
            line = bf.readLine();
        }
    } catch (FileNotFoundException ex) {
        Logger.getLogger(UbntConfig.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(UbntConfig.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    private List<String> getListConfig(){
        return this.listConfig;
    }
    private void printListConfig(){
        System.out.println("Reade lines: "+this.listConfig.size()+"\n");
        this.listConfig.forEach(list -> System.out.println(list));
    }
    
}
