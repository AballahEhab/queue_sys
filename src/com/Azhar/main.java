/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Azhar;


/**
 *
 * @author Abdallah Ehab
 */
public class main {

    public static void main(String[] args) {


//        D_D d_d =new D_D(1,3,4,7);
//        System.out.println(d_d.case_2_calc_ti());

//        System.out.println(d_d.getArrival_time());
//        System.out.println(d_d.getService_time());



        char pattern = 'D';         // Service and arrival pattern  'M' or 'D'
        boolean infinite = false; //system capacity
        int capacity = 4,num_of_parrel_servers =1, num_of_initial_cst =7 ,time =11, n_for_wq = 0;
        double service_time = 6, arrival_time = 4;     //    1/λ  ,   1/μ
        double service_rate = 60, arrival_rate = 50;        //    λ  ,   μ
        sys_model system;
//         ToDo: beind: λ ,μ there should be a dropdown menue to choose between {arrival rate λ ,arrival time 1/λ }
        if (pattern == 'D') {  // Deterministic queueing model    //number of parallel servers is constant = 1
            D_D  d_d ;

            if (service_time > arrival_time) {              //   case1:   1/μ > 1/λ
                if (infinite) {  // if system capacity is infinite (K not found)   D/D/1

                    d_d = new D_D(service_time,arrival_time);
                    System.out.println(d_d.getService_time());
                    System.out.println(d_d.getArrival_time());
                    d_d.run_system();
//                    System.out.println(d_d.getK());
//                    System.out.println(d_d.getT_i());
                    System.out.println("0");
                    System.out.println(d_d.calc_n_t(time));
                    System.out.println(d_d.calc_w_q(n_for_wq));

                } else {  // if system capacity is finite (K found)    D/D/1/k-1
                    System.out.println("1");
                    d_d = new D_D(service_time,arrival_time,capacity);
                    d_d.run_system();
                    System.out.println(d_d.getService_time());
                    System.out.println(d_d.getArrival_time());
                    System.out.println(d_d.getK());
                    System.out.println(d_d.getT_i());
                    System.out.println(d_d.calc_n_t(time));
                    System.out.println(d_d.calc_w_q(n_for_wq));

                }

            } else if (service_time < arrival_time) { // case2:   1/μ < 1/λ   // D/D/1/k-1 or D/D/1 the same     //system capacity ignored but initial customers: M is required
                System.out.println("2");
                d_d = new D_D(service_time,arrival_time,capacity,num_of_initial_cst);
                System.out.println(d_d.getT_i());
                System.out.println(d_d.calc_n_t(time));
                System.out.println(d_d.calc_w_q(n_for_wq));


            }else if(service_time == arrival_time ){  //  1/μ = 1/λ
                System.out.println("3");
                int ti =0;
                int n_of_t = num_of_initial_cst;
                int wq = (int) ((num_of_initial_cst-1)*(service_time));


            }else{

                // error

            }

        } else if (pattern == 'M') {          // Markov queueing model

            // ToDo: in this model there should be a dropdown menu to chose λ,μ are per minute or per hour

                if(num_of_parrel_servers == 1){
                    M_M_1 m_m_1;
                    if (infinite) {  // if system capacity is infinite (K not found) M/M/1
                        m_m_1 = new M_M_1();
                        m_m_1.setArrival_rate(arrival_rate);
                        m_m_1.setService_rate(service_rate);
                        m_m_1.run_system();
                        System.out.println("M M 1");
                        System.out.println("l  "+m_m_1.getL());
                        System.out.println("lq   "+m_m_1.getL_q());
                        System.out.println("po  "+m_m_1.getPo());
                        System.out.println("w  "+m_m_1.getW());
                        System.out.println("wq  "+m_m_1.getW_q());


                    } else { // if system capacity is finite (K found) M/M/1/K
                        m_m_1 = new M_M_1();
                        m_m_1.setArrival_rate(arrival_rate);
                        m_m_1.setService_rate(service_rate);
                        m_m_1.setK(capacity+1);
                        m_m_1.run_system();
                        System.out.println("M M 1 K");
                        m_m_1.getP();
                        System.out.println("l  "+m_m_1.getL());
                        System.out.println("lq   "+m_m_1.getL_q());
                        System.out.println("po  "+m_m_1.getPo());
                        System.out.println("w  "+m_m_1.getW());
                        System.out.println("wq  "+m_m_1.getW_q());

                    }
                }else if(num_of_parrel_servers>1){
                    M_M_C m_m_c;
                    if (infinite) {  // if system capacity is infinite (K not found) M/M/C
                        m_m_c = new M_M_C();
                        m_m_c.setArrival_rate(arrival_rate);
                        m_m_c.setService_rate(service_rate);
                        m_m_c.setNum_of_parellel_servers(num_of_parrel_servers);
                        m_m_c.run_system();
                        System.out.println("M M C");
                        System.out.println("l  "+m_m_c.getL());
                        System.out.println("lq   "+m_m_c.getL_q());
                        System.out.println("po  "+m_m_c.getPo());
                        System.out.println("w  "+m_m_c.getW());
                        System.out.println("wq  "+m_m_c.getW_q());

                    } else {  // if system capacity is finite (K found)  M/M/C/K
                        m_m_c = new M_M_C();
                        System.out.println("M M C K");

                        m_m_c.setArrival_rate(arrival_rate);
                        m_m_c.setService_rate(service_rate);
                        m_m_c.setNum_of_parellel_servers(num_of_parrel_servers);
                        m_m_c.setK(capacity);
                        m_m_c.run_system();

                        System.out.println("l  "+m_m_c.getL());
                        System.out.println("lq   "+m_m_c.getL_q());
                        System.out.println("po  "+m_m_c.getPo());
                        System.out.println("w  "+m_m_c.getW());
                        System.out.println("wq  "+m_m_c.getW_q());
                        System.out.println("pn  "+m_m_c.calc_pn(7));

                    }
                }else{

                    //error
                }


        }else{
            // unknown model
        }
    }
}