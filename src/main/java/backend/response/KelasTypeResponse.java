package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.KelasType;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public class KelasTypeResponse {
 
     public String status;
     public String message;
     public ArrayList<KelasType> data;

     public KelasTypeResponse (String status, String message, List<KelasType> kelasType) {
         this.status = status;
         this.message = message;
         this.data = new ArrayList<KelasType>();

         for (KelasType item:kelasType) {
             this.data.add(item);
         }
     }

     public KelasTypeResponse (String status, String message, KelasType kelasType) {
         this.status = status;
         this.message = message;
         this.data = new ArrayList<KelasType>();

         if (kelasType != null) {
             this.data.add(kelasType);
         }
     }

     public KelasTypeResponse(String status, String message) {
         this.status = status;
         this.message = message;
     }
 }