
package queueing_theory;


public class Deterministic {
    
   double arrivingRate ;
   double serviceRate ;


   private  double arrivingTime ; //  1/m
   private  double serviceTime ;  //  1/L
   private int capcty  ;         // system capacity
   double ti1,ti2;              //
   private int M ;             // number Of Customer when Start system
   
    
//    public Deterministic (double arrivingRate , double serviceRate , int capacty){
//        this.arrivingRate = arrivingRate ;
//        this.serviceRate = serviceRate ;
//        arrivingTime = 1 / arrivingRate ;
//        serviceTime  = 1 / serviceRate ;
//        this.capcty = capacty;
//        
//    }
    
   public Deterministic (double arrivingTime , double serviceTime , int capacty , int M){
        
        this.arrivingTime =  arrivingTime ;
        this.serviceTime  = serviceTime ;
             this.arrivingRate = 1/arrivingTime ;
             serviceRate = 1 / serviceTime ;
       this.capcty = capacty;
       this.M = M;
       ti1=calculat_ti1();
       ti2=calculat_ti2();
    
    }  //
  
               //        D / D / 1 / ∞ / FCFS       أول شكل الطابور المحدد والسعه غير محدوده
              //                   هنا هنحسب لما يكون معدل الوصول أعلي من معدل الخدمه يعني هيبقي فيه إنتظار في الطابور
    
              //      دا لما يكون مفيش سعه 
    public int calculat_N (int time){
        
        int numberOfCustomar ;
        int p1 = (int) ( arrivingRate * time) ;
        int p2 ;
        p2 = (int) ( ( serviceRate * time ) - ( serviceRate / arrivingRate ));
        numberOfCustomar = p1 - p2 ;
        return numberOfCustomar ;
    }  // calculate n(t) for D/D/1


    // هنا هيحسب وقت الإنتظار  لما يكون  مفيش سعه
//    يعني مفيش بلوك السيستم هيقبل كل الناس
    public int calculat_Wq ( int n){
        
        double m = ( 1 / serviceRate) ;
        double L =  (1 / arrivingRate );
        int Wq = (int) (((m) -(L))*( n - 1));
        return Wq ;
    }


    
     public double calculat_ti1 (){
         
         int k = capcty + 1 ;
         double part1 = (k) -(arrivingTime / serviceTime ) ; // الكسر هنا هيتقلب

        double part2 =  serviceTime - arrivingTime  ; // بدل ما نعمل دي هنوحد المقام الاول
        double part3 =  serviceTime * arrivingTime ;
        double part4 = part1 * part3 ;
        double ti = part4 / part2 ;  //هنا حسب القيمه الابتدائيه ودي هنقص منها عشان نوصل ل أول وقت حصل فيه بلوك 
        
          boolean ti_valid=true; 
         
         while (ti_valid) { 
             
              ti_valid = cheek_ti1_isValid( ti , k ); 
             
                 ti-=arrivingTime;
         } 
         return  ti+=arrivingTime;
     }
       
    // هنا هيشوف قيمه ال ت  بتحقق  المعادله ولا لا 
    public boolean cheek_ti1_isValid( double ti , int k  ){
        int part1 = (int) (arrivingRate*ti);
        int part2 = (int) Math.round((ti/serviceTime)-(arrivingTime/serviceTime));
        
        if (k == part1-part2){
            return true ;
        }else{
        return false;}
    }
            
    // حساب عدد الناس اللي في السيستم لما يكون معدل الوصول أكبر من معدل الخدمه  
    public int caculat_n1_With_K(int time){
        int numberOfCustomer ;
        if ( time < arrivingTime){
           return numberOfCustomer = 0 ; 
        }
        else if (arrivingTime < time && time < ti1){
          return numberOfCustomer = calculat_N(time);
        }
        else {
            return numberOfCustomer = capcty ;
        }
        
        
    }
              //   حساب وقت الانتظار لما يكون وقت معدل الوصول أكبر من معدل الخدمه
    
     public double caculat_Wq1_With_K(int numberOfCustomer ){
         double condition = arrivingRate * ti1; 
        
        if (numberOfCustomer < condition ){
            return calculat_Wq (numberOfCustomer);
        }
        else  {
            double part1 =  serviceTime - arrivingTime ; 
            double part2 = condition - 2 ;
            double Wq = part1 * part2 ;
              return Wq;  
        }
    }
     
    //  هنا هحسب الوقت اللي السيستم هيفضي فيه في الحاله التانيه لما يكون معدل الخدمه أعلي من معدل الوصول
     
     public double calculat_ti2 (){
        
         int ti = (int) ( (M) / (serviceRate - arrivingRate) );
         return ti ;
     }

     
     public int caculat_n2_With_K (int time ){
         
         int numberOfCustomer ;
         if ( serviceRate == arrivingRate ){
             numberOfCustomer = M ;
             return numberOfCustomer ;
         }
         else {
             int part1 = (int) ( arrivingRate * time );
             int part2 = (int) ( serviceRate * time );
         numberOfCustomer = M + part1 - part2  ;
         return numberOfCustomer ;
         }
         
    }


     
    public double caculat_Wq2_With_K (int numberOfCustomer){
        int condition = (int) (arrivingRate * ti2);
        double Wq ;
        if ( numberOfCustomer == 0){
            Wq=( (M-1)/(2*serviceRate) );
            return Wq ;
        }
        else if (numberOfCustomer < condition){
            Wq = ( (M-1+numberOfCustomer)*(serviceTime) ) - (numberOfCustomer * arrivingTime);
            return Wq ;
        }
        else if (arrivingTime == serviceTime){
            Wq = (M-1)*(serviceTime);
            return Wq;
        }
        else{
            return 0.0;
        }
    }


    
    public double git_ti(){
        if(arrivingTime<serviceTime){
       return calculat_ti1();
        
        }
        else if (arrivingTime>serviceTime){
               return calculat_ti2();

        }
         return 0;
    }

    public int get_n(int time){
       if(arrivingTime<serviceTime){
       return caculat_n1_With_K(time);
        
        }
        else if (arrivingTime>serviceTime){
               return caculat_n2_With_K(time);

        }
         return 0;
    }
    
    
    
}
