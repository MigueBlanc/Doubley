class midterm{



    public static  void main(String[] Args ){

        for(int i = 1; i <= 15; i++){
            System.out.printf("\n value of x when n == %d is ", i);
            System.out.println("x = " + Q(i));
        }

    }



    public static int Q(int n){
        if( n == 1)
            return 1;

        else return 1 + 2 * Q(n/2);
    }
}
