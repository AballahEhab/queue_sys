package com.Azhar;

public class D_D extends sys_model {


    private int number_of_initial_customers;
    private int t_i;

    public D_D(double service_time, double arrival_time) {
        super(service_time, arrival_time);
    }
    public D_D(double service_time, double arrival_time, int capacity) {
        super(service_time, arrival_time, capacity);
    }


    public D_D(double service_time, double arrival_time, int capacity, int number_of_initial_customers) {
        super(service_time, arrival_time, capacity);
        this.number_of_initial_customers = number_of_initial_customers;
    }

    public double getT_i() {
        return t_i;
    }

    public void setT_i(int t_i) {
        this.t_i = t_i;
    }


    public int getNumber_of_initial_customers() {
        return this.number_of_initial_customers;
    }

    public void setNumber_of_initial_customers(int number_of_initial_customers) {
        this.number_of_initial_customers = number_of_initial_customers;
    }

    private int infinity_calc_n_t(int time){
        return  ((int)(time/getArrival_time())-(int)((time-getArrival_time())/(getService_time())));
    }

    private int infinity_calc_w_q(int n){
        return (int)((getService_time()-getArrival_time())*(n-1));
    }

    private void case_1_calc_ti() {
        int ti = (int) ((getK() - (getArrival_time() / getService_time())) /  ((getService_time()-getArrival_time())/(getService_time()*getArrival_time())));
        ti -=getArrival_time();
        while (test_ti_case_1(ti)) {
            ti -= getArrival_time();
        }
        this.t_i = (int) (ti+getArrival_time());
    }

    private boolean test_ti_case_1(int ti){
            int test_k = ((int)(ti/getArrival_time()))- ((int)((ti/getService_time())-(getArrival_time()/getService_time())));
            if (test_k == getK())
                return  true;
            else
                return false;

    }

    private int case_1_calc_n_t(int t){
        if (t<=t_i){
            if (t>=getArrival_time()){

                return infinity_calc_n_t(t);
            }else{
                return 0;
            }
        }else{
            return (getK()-1);
        }
    }

    private int case_1_w_q(int n){
        if(n==0){
          return 0;
        } else if (n<(t_i /getArrival_time())){
//            System.out.println((int) ((getService_time()-getArrival_time())*(n-1)));
            return (int) ((getService_time()-getArrival_time())*(n-1));
        }else {
            return (int) ((getService_time()-getArrival_time())*(t_i /getArrival_time()-1));
        }
    }

    private void case_2_calc_ti() {

        int ti = (int) (getNumber_of_initial_customers()/((getArrival_time()-getService_time())/(getArrival_time()*getService_time())));
        ti -=getArrival_time();
        while (test_ti_case_2(ti)) {
            ti -= getArrival_time();
        }
        this.t_i = (int) (ti+getArrival_time());
    }

    private boolean test_ti_case_2(int ti){
        int test_M = ((int)(getNumber_of_initial_customers()/(getArrival_time())-(int)(getService_time()/getArrival_time()*getService_time())));
        if (test_M == getNumber_of_initial_customers())
            return  true;
        else
            return false;

    }

    private int case_2_calc_n_t(int t){

        if(t< t_i)
            return (getNumber_of_initial_customers() + (int) (t / getArrival_time()) - (int) (t / getService_time()));
        else if(t>= t_i){
            t -= t_i;
            while(t>=getArrival_time())
               t -=getArrival_time();

            if(t == getArrival_time()-getService_time())
                return 1;
            else
                return 0;

        }
        else
            return -1;  // error

    }

    private int case_2_w_q(int n) {
        if (n == 0) {
            return (int) ((getNumber_of_initial_customers() - 1) * (2 / getService_time()));
        } else if (n <= (int) (t_i / getArrival_time())) {
            return (int) (((getNumber_of_initial_customers() - 1 + n) * (getService_time())) - (n * getArrival_time()));
        } else if (n > (int) (t_i / getArrival_time())) {
            return 0;
        }
        return -1;  // error
    }

    public double calc_n_t(int t){
        if (getService_time() > getArrival_time()) {
            if (getK() == -1) {
                return infinity_calc_n_t(t) ;
            }else{
                return this.case_1_calc_n_t(t);
            }
        } else if (getService_time() < getArrival_time()) {
            return this.case_2_calc_n_t(t);
        }else if(getService_time() == getArrival_time()){
            return (double)this.number_of_initial_customers;
        }
        return -1;
    }

    public double calc_w_q(int n){
        if (getService_time() > getArrival_time()) {

            if (getK() == -1) {

                return infinity_calc_w_q(n) ;
            }else{

                return this.case_1_w_q(n);
            }
        } else if (getService_time() < getArrival_time()) {
            return this.case_2_w_q(n);
        }else if(getService_time() == getArrival_time()){
            return (this.number_of_initial_customers-1)*super.getService_time();
        }
            return -1;

    }


    @Override
    public void run_system() {
        if (getService_time() > getArrival_time()) {

            if (getK() == -1) {
                this.setT_i(-1);
            }else{

                this.case_1_calc_ti();

            }

        } else if (getService_time() < getArrival_time()) {


            this.case_2_calc_ti();


        }else if(getService_time() == getArrival_time()){


            this.setT_i(0) ;
        }else{
            this.setT_i( -1);
        }
    }
}



