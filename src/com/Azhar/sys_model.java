
package com.Azhar;
public class sys_model {

    private double service_time , arrival_time ;     //        1/μ  ,   1/λ
    private double service_rate, arrival_rate;
    private int K;  // System capacity-1

    public sys_model() {
        K = -1;
    }

    public sys_model(double service_time, double arrival_time) {
        this.service_time = service_time;
        this.arrival_time = arrival_time;
        service_rate = 1.0/ this.service_time;
        arrival_rate = 1.0/ this.arrival_time;
        this.K = -1;


    }

    public sys_model(double service_time, double arrival_time, int capacity) {
        this.service_time = service_time;
        this.arrival_time = arrival_time;
        service_rate = 1.0/ this.service_time;
        arrival_rate = 1.0/ this.arrival_time;
        K =capacity+1;
    }


    public double getService_time() {
        return service_time;
    }

    public void setService_time(double service_time) {
        this.service_time = service_time;
        service_rate = 1.0/ this.service_time;
    }

    public double getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(double arrival_time) {
        this.arrival_time = arrival_time;
        this.arrival_rate = 1.0/ this.arrival_time;
    }

    public double getService_rate() {
        return service_rate;
    }

    public void setService_rate(double service_rate) {
        this.service_rate = service_rate;
        this.service_time = 1.0/ this.service_rate;
    }

    public double getArrival_rate() {
        return arrival_rate;
    }

    public void setArrival_rate(double arrival_rate) {
        this.arrival_rate = arrival_rate;
        this.arrival_time = 1.0/this.arrival_rate;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }
    public void run_system(){

    }
}
