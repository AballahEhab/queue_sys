package com.Azhar;

public class M_M_1 extends M_M {

    public M_M_1(){
        super();
    }


    private void calc_p(){
        super.setP(super.getArrival_rate()/super.getService_rate());
    }


    private void calc_po_infinity(){
        this.calc_p();
        super.setPo(1-super.getP());
    }

    private double calc_pn_infinity(int n){
        return (Math.pow(super.getP(),n)*(1-super.getP()));
    }

    private void calc_Lq_infinity(){
        super.setL_q (super.getArrival_rate()*super.getW_q());
    }

    private void calc_l_infinity(){

        super.setL(super.getArrival_rate()*super.getW());
    }

    private void calc_wq_infinity(){
        super.setW_q ((super.getArrival_rate())/(super.getService_rate()*(super.getService_rate()-super.getArrival_rate())));
    }

    private void calc_w_infinity(){
        super.setW (1/(super.getService_rate()-super.getArrival_rate()));
    }

    private void calc_po_k(){
        this.calc_p();
        if(super.getP() == 1){
            setPo(1/(super.getK()+1));
        }else{
            super.setPo((1-super.getP())/(1-Math.pow(super.getP(),super.getK()+1)));
        }
    }

    private double calc_pn_k(int n){
        if(super.getP() == 1){
            return super.getPo();
        }else{
            return  (Math.pow(super.getP(),n)*super.getPo());
        }
    }

    private void calc_Lq_k(){
        super.setL_q( super.getL()-super.getP()*(1-calc_pn_k(super.getK())));

    }

    private void calc_l_k(){
        if(super.getP() ==1){
            super.setL (super.getK()/2);
        }else{
            super.setL(super.getP()*((1-(Math.pow(super.getP(),super.getK())*(super.getK()+1))+(super.getK()*Math.pow(super.getP(),super.getK()+1)))/((1-super.getP())*(1-Math.pow(super.getP(),super.getK()+1)))));
        }
            }

    private void calc_wq_k(){
        super.setW_q ((super.getW()*super.getService_rate()-1)/super.getService_rate());
    }

    private void calc_w_k(){
        System.out.println("lamda dash"+super.getLamda_dash());
        System.out.println("L "+super.getL());

        super.setW(getL()/super.getLamda_dash());
    }

    private void calc_lamda_dash(){
        super.setLamda_dash(super.getArrival_rate()*(1-this.calc_pn_k(getK())));
    }

    public double calc_pn(int n){
        if(super.getK()== -1){
            return calc_pn_infinity(n);
        }else{
            return calc_pn_k(n);

        }
    }


    @Override
    public void run_system() {
        super.run_system();
        this.calc_p();
        if(super.getK()== -1){
            this.calc_po_infinity();
            this.calc_w_infinity();
            this.calc_wq_infinity();
            this.calc_l_infinity();
            this.calc_Lq_infinity();




        }else{
            System.out.println("K");
            this.calc_po_k();
            this.calc_l_k();
            this.calc_Lq_k();
            this.calc_lamda_dash();
            this.calc_w_k();
            this.calc_wq_k();

        }
    }
}
