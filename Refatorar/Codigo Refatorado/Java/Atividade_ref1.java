
class Main {
    public static void main(String[] args) {
       for(int i = 0; i<10; i++){
           for(int j =0; j<10; j++){
               if(i+j>5){
                   System.out.println(i%2==0?i:j);
               }else{
                   for(int k=0; k<5; k++){
                       if(k==i)System.out.println(k);
                   }
               }
           }
       }
    }
}
