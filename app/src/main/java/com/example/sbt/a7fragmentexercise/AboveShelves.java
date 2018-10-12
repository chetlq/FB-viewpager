package com.example.sbt.a7fragmentexercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AboveShelves {
  //  private  Map<String,Shelves> list;
    private  List<Shelves> myList;

    private  static  AboveShelves aboveShelves;

    public static AboveShelves get() {
        if (aboveShelves == null) {
            aboveShelves = new AboveShelves();
        }
        return aboveShelves;
    }
    private AboveShelves( ) {
    }

//    public AboveShelves(Map<String,Shelves> list) {
//        myList= new ArrayList<>();
//        for(Map.Entry entry : list.entrySet()){
//            //Log.d(TAG,"Shelves : " + entry.getValue());
//            Shelves sh = new Shelves((Map<String, String>) entry.getValue(), entry.getKey().toString()) ;
//            myList.add(sh);
//        }
//    }

    public void setShelves(Map<String,Shelves> list) {
        myList= new ArrayList<>();
        for(Map.Entry entry : list.entrySet()){
            //Log.d(TAG,"Shelves : " + entry.getValue());
            Shelves sh = new Shelves((Map<String, String>) entry.getValue(), entry.getKey().toString()) ;
            myList.add(sh);
        }
        Collections.reverse(myList);
    }

    public void deleteShelves(){
        myList.clear();
    }

    public Shelves getShelve(String id){
        for (Shelves Shelve : myList) {
            if (Shelve.getId().equals(id)) {
                return Shelve;
            }
        }
        return null;
//        Shelves sh=new Shelves();
//        for(Map.Entry entry : list.entrySet()){
//            if (entry.getKey().equals(id)) {
//                Log.d(TAG,"Shelves : " + entry.getValue());
//                sh = new Shelves((Map<String, String>) entry.getValue()) ;
//                break;
//            };
//        }
//        return sh;
    }

    public  List<Shelves> getShelves() { return myList;   }


}

