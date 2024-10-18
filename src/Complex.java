import java.util.Objects;

public class Complex {
    private final double real;
    private final double imaginable;



    /**Complex zero**/
    public Complex(){
        this(0,0);
    }
    /**
     * Complex number from real number
     * @param real real number
     * **/
    public Complex(double real){
        this(real,0);
    }
    /**
     * Complex with real and imaginable parts
     * @param real real part of complex number
     * @param imaginable imaginable part of complex number
     **/
    public Complex(double real, double imaginable){
        this.real=real;
        this.imaginable=imaginable;
    }


    /**
     * @return Inverse number for this complex number
     */
    public Complex Inverse(){
        double scale = this.real * this.real + this.imaginable * this.imaginable;
        return new Complex(this.real / scale, -this.imaginable / scale);
    }

    /**
     * Plus operator for complex numbers
     * @param second second summand
     * @return Returns sum of two complex numbers
     */
    public  Complex Plus(Complex second){
        return new Complex(this.real+second.real, this.imaginable+ second.imaginable);
    }
    /**
     * Unary minus operator for complex numbers
     * @return the opposite number
     */
    public Complex Minus(){
        return new Complex(-this.real,-this.imaginable);
    }
    /**
     * Binary minus operator for complex numbers
     * @param subtrahend The number which is subtracted from minuend
     * @return Complex difference between this number as minuend and subtrahend
     */
    public Complex Minus(Complex subtrahend){
        return this.Plus(subtrahend.Minus());
    }

    /**
     * Multiply operator for complex numbers
     * @return Multiplication of two complex numbers
     */
    public Complex Multiply(Complex second){
        double real = this.real * second.real - this.imaginable * second.imaginable;
        double imaginable =  this.real * second.imaginable + this.imaginable * second.real;
        return new Complex(real,imaginable);
    }
    /**
     * Division operator for complex numbers
     * @param divisor divisor in division
     * @return this complex number divided by divisor
     */
    public Complex Division(Complex divisor){

        return this.Multiply(divisor.Inverse());
    }

    @Override
    public String toString() {
        if(imaginable>=0){
            return real +"+"+ (imaginable +"i");
        }
        return real + (imaginable +"i");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(real, complex.real) == 0 && Double.compare(imaginable, complex.imaginable) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginable);
    }
}
