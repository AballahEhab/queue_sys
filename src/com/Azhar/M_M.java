package com.Azhar;

public class M_M extends sys_model {

    private double r, p , po , l, l_q, w, w_q , lamda_dash;


    public M_M() {
        super();
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getPo() {
        return po;
    }

    public void setPo(double po) {
        this.po = po;
    }

    public double getL_q() {
        return l_q;
    }

    public void setL_q(double l_q) {
        this.l_q = l_q;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getW_q() {
        return w_q;
    }

    public void setW_q(double w_q) {
        this.w_q = w_q;
    }

    public double getLamda_dash() {
        return lamda_dash;
    }

    public void setLamda_dash(double lamda_dash) {
        this.lamda_dash = lamda_dash;
    }
//
//    public M_M(double service_time, double arrival_time) {
//        super(service_time, arrival_time);
//    }
//
//    public M_M(double service_time, double arrival_time, int capacity) {
//        super(service_time, arrival_time, capacity);
//    }

    public int factorial(int num){
        if (num  > 1) {
            return num* factorial(num-1);
        }
        return 1;
    }







}
