public class DLLtest {


    public static void main(String[] args) {





        int [] arr = {10,20, 30, 40, 50, 60, 70, 80,90,100};

        DoublyLinkedList<Integer> myDLL = new DoublyLinkedList<>();


        for (int i = 0; i < arr.length; i++){
          myDLL.add(arr[i]);
            System.out.println(myDLL);
        }


    }



}
