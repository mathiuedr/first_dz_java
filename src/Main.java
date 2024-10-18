import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {

            Complex[][] arr1 = new Complex[3][3];
            int counter=1;
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    arr1[i][j]=new Complex(counter++);
                }
            }
            Matrix mat1 = new Matrix(arr1);
            Random counter1=new Random(1L);
            Complex[][] arr2 = new Complex[3][3];
            for (int i = 0; i < arr2.length; i++) {
                for (int j = 0; j < arr2[i].length; j++) {
                    arr2[i][j]=new Complex(counter1.nextDouble(1000)-500,counter1.nextDouble(1000)-500);
                }
            }
            Matrix mat2= new Matrix(arr2);
            Complex[][] arr3=new Complex[3][3];
            arr3[0][0]=new Complex(2,5);
            arr3[0][1]=new Complex(5,7);
            arr3[0][2]=new Complex(7,-1);
            arr3[1][0]=new Complex(6,-33);
            arr3[1][1]=new Complex(3);
            arr3[1][2]=new Complex(4,5);
            arr3[2][0]=new Complex(5,-12);
            arr3[2][1]=new Complex(-2,-128);
            arr3[2][2]=new Complex(-3,-3);
            Matrix mat3= new Matrix(arr3);
            System.out.println(mat1);
            System.out.println(mat2);
            System.out.println(mat1.Plus(mat2));
            System.out.println(mat1.Minus(mat2));
            System.out.println(mat2.Multiply(mat3));
            System.out.println(mat2.Multiply(new Complex(12,11)));
            System.out.println(mat3.Inverse());
            System.out.println(mat3.Determinant());
            System.out.println(mat2.Inverse());
            System.out.println(mat2.Divide(mat3.Multiply(new Complex(3,3))));

        }catch (Exception e){
            System.out.println(e);
        }

    }

}