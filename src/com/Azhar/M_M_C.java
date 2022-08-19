package com.Azhar;

public class M_M_C extends M_M{

    private int num_of_parellel_servers = 0;  // c:number of parellel servers

    public double getNum_of_parellel_servers() {
        return num_of_parellel_servers;
    }

    public void setNum_of_parellel_servers(int num_of_parellel_servers) {
        this.num_of_parellel_servers = num_of_parellel_servers;
    }

    private void calc_r(){
        setR (super.getArrival_rate()/super.getService_rate());
    }

    private void calc_p(){
        setP(super.getR()/num_of_parellel_servers);
    }

    private void calc_po_infinity(){
        double first_term_result = 0;
        if(super.getP()<1){



            for(int i =0;i<=num_of_parellel_servers-1;i++){

                first_term_result +=(Math.pow(super.getR(),i)/ factorial(i));

            }

            setPo( 1/(first_term_result
                    +((num_of_parellel_servers *Math.pow(super.getR(),num_of_parellel_servers))
                    / factorial(num_of_parellel_servers) *(num_of_parellel_servers-super.getR()))));
        }else if(super.getP()>=1){

            for(int i =0;i<=num_of_parellel_servers-1;i++){

                first_term_result +=(1/ factorial(i))
                        *(Math.pow(super.getR(),i));

            }
            setPo( 1/(first_term_result +
                    ((((1/ factorial(num_of_parellel_servers))
                            *(Math.pow(super.getR(),num_of_parellel_servers)))
                            *((num_of_parellel_servers*super.getService_rate())
                                    /(num_of_parellel_servers*super.getService_rate()-super.getArrival_rate()))))));



        }else{
            setPo(-1);
        }
    }

    private double calc_pn_infinity(int n){
        if(n>=0 && n< num_of_parellel_servers){

            return (Math.pow(super.getR(),n)/factorial(n))*super.getPo();

        }else if(n>= num_of_parellel_servers){

            return ((Math.pow(super.getR(),n)
                    /((Math.pow(num_of_parellel_servers,n-num_of_parellel_servers)
                    * factorial(num_of_parellel_servers)))*super.getPo()));

        }else{
            return -1;// error
        }
    }

    private void calc_Lq_infinity(){
        setL_q (((Math.pow(super.getR(),num_of_parellel_servers+1) /num_of_parellel_servers)
                        /(factorial(num_of_parellel_servers)
                        *Math.pow((1-(super.getR()/num_of_parellel_servers)),2)))*super.getPo());
    }

    private void calc_l_infinity(){
        setL( (super.getL_q()+super.getR()));
    }

    private void calc_wq_infinity(){
        setW_q(super.getL_q()/super.getArrival_rate());
    }

    private void calc_w_infinity(){
        setW( super.getW_q()+super.getService_time());
    }

    private void calc_po_k(){
        double first_term_result = 0;
        for(int i =0;i<=num_of_parellel_servers-1;i++){
            first_term_result +=((Math.pow(super.getR(),i))/(factorial(i)));
        }
        if(super.getP()==1){
            setPo(1/(first_term_result
                    +((Math.pow(super.getR(),num_of_parellel_servers)
                    / factorial(num_of_parellel_servers))
                    *(super.getK()-num_of_parellel_servers+1))));
        }else{
            setPo (1/(first_term_result
                    + (Math.pow(super.getR(),num_of_parellel_servers)/ factorial(num_of_parellel_servers))
                    *((1-Math.pow(super.getP(),super.getK()-num_of_parellel_servers+1))/(1-super.getP()))));
        }
    }

    private double calc_pn_k(int n){
        if( 0 <= n && n< num_of_parellel_servers){

            return ((Math.pow(super.getR(),n)/ factorial(n))*super.getPo());

        }else if( num_of_parellel_servers<=n && n <= super.getK()){

            return ((Math.pow(super.getR(),n)
                    /(Math.pow(num_of_parellel_servers,n-num_of_parellel_servers)
                    * factorial(num_of_parellel_servers)))*super.getPo());

        }else{
            return -1;// error
        }
    }

    private void calc_Lq_k(){

        setL_q(((super.getP()*Math.pow(super.getR(),num_of_parellel_servers)*super.getPo())
                        /(factorial(num_of_parellel_servers) *Math.pow(1-super.getP(),2)))
                        *(1-Math.pow(super.getP(),super.getK()-num_of_parellel_servers+1)
                        -((1-super.getP())*(super.getK()-num_of_parellel_servers+1)*Math.pow(super.getP(),super.getK()-num_of_parellel_servers))));
    }

    private void calc_l_k(){
        int last_term_result =0 ;

        for (int i =0 ; i<=num_of_parellel_servers-1;i++){
            last_term_result += ((num_of_parellel_servers-i)*(Math.pow(super.getR(),i)/ factorial(i)));
        }


        setL(super.getL_q() + num_of_parellel_servers - super.getPo()*last_term_result);
    }

    private void calc_wq_k(){
        setW_q(super.getL_q()/super.getLamda_dash());
    }

    private void calc_w_k(){
        setW(super.getL()/super.getLamda_dash());
    }

    private void calc_lamda_dash(){
        setLamda_dash(super.getArrival_rate()*(1-this.calc_pn_k(super.getK())));
    }

    public double calc_pn(int n){
        if(super.getK()== -1){
            return this.calc_pn_infinity(n);

        }else{
            return this.calc_pn_k(n);

        }
    }

    @Override
    public void run_system() {
        super.run_system();
        this.calc_p();
        this.calc_r();
        this.calc_p();
        if(super.getK()== -1){
            this.calc_po_infinity();
            this.calc_Lq_infinity();
            this.calc_l_infinity();
            this.calc_wq_infinity();
            this.calc_w_infinity();

        }else{
            this.calc_po_k();
            this.calc_Lq_k();
            this.calc_l_k();
            this.calc_lamda_dash();
            this.calc_wq_k();
            this.calc_w_k();

        }
    }

    double calc_ci(){
        return ((num_of_parellel_servers-super.getR())*100);
    }
}
