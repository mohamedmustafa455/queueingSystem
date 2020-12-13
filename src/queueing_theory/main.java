/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queueing_theory;

/**
 *
 * @author Abdallah Ehab
 */
public class main {

    public static void main(String[] args) {

        char pattern = 'M';         // Service and arrival pattern  'M' or 'D'
        boolean infinite = false; //system capacity
        int system_capacity = 0;
        double service_time = 8, arrival_time = 4;     //    1/λ  ,   1/μ
//        double arrival_time_by_service_time = service_time / arrival_time;     // => m   where:(1/λ)= m(1/μ)


//        System.out.print(arrival_time_by_service_time == Math.round(arrival_time_by_service_time) && arrival_time_by_service_time != 0);


        // ToDo: beind: λ ,μ there should be a dropdown menue to choose between {arrival rate λ ,arrival time 1/λ }
        if (pattern == 'D') {  // Deterministic queueing model    //number of parallel servers is constant = 1






            if (service_time < arrival_time) {              //   case1: 1/λ <  1/μ





                if (infinite) {  // if system capacity is infinite (K not found)   D/D/1



                } else {  // if system capacity is finite (K found)    D/D/1/k-1



                }

            } else if (service_time > arrival_time) { // case2: 1/λ > 1/μ    //D/D/1/k-1 or D/D/1 the same     //system capacity ignored but initial customers: M is required



            }else{

                // error

            }

        } else if (pattern == 'M') {          // Markov queueing model

            // ToDo: in this model there should be a dropdown menu to chose λ,μ are per minute or per hour

                if(system_capacity == 1){
                    if (infinite) {  // if system capacity is infinite (K not found) M/M/1



                    } else {// if system capacity is finite (K found) M/M/1/K



                    }
                }else if(system_capacity>1){
                    if (infinite) {  // if system capacity is infinite (K not found) M/M/C



                    } else {  // if system capacity is finite (K found)  M/M/C/K



                    }
                }else{

                    //error
                }


        }else{
            // unknown model
        }
    }
}












//               not imortant special case
//                if (arrival_time_by_service_time == Math.round(arrival_time_by_service_time) && arrival_time_by_service_time != 0) {
//
//                    // special case method where:(1/λ)= m(1/μ)
//
//                } else