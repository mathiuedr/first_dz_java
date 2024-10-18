import java.util.Arrays;
import java.util.Objects;
import MatrixExceptions.MatrixSizeException;
import MatrixExceptions.MatrixOutOfRangeException;
import MatrixExceptions.MatrixDeterminantException;

public class Matrix {
    private final int  rows;
    private final int columns;
    private final Complex[][] container;

    /**
     * Empty matrix of complex numbers constructor
     * @param rows matrix rows number
     * @param columns matrix columns number
     * @throws MatrixSizeException if rows or columns are less than 0
     */
    public Matrix(int rows,int columns) throws MatrixSizeException {
        if (rows>0&&columns>0){
           this.rows=rows;
           this.columns=columns;
           this.container=new Complex[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    this.container[i][j]=new Complex();
                }
            }
        }else{
            throw new MatrixSizeException("Matrix dimensions cannot be non-positive");
        }
    }

    /**
     * empty constructor
     * @throws MatrixSizeException always
     */
    public Matrix() throws MatrixSizeException {
        this(0,0);
    }

    /**
     * checking 2d array of complex numbers sizes
     * @return boolean
     */
    private boolean checkArrayForMatrix(Complex[][] array){
        if(array.length==0 || array[0].length==0){
            return false;
        }
        int initial_Length = array[0].length;
        for(int i=1;i<array.length;i++){
            if(array[i].length!=initial_Length){
                return false;
            }
        }
        return true;
    }

    /**
     * Full matrix constructor
     * @param array it will be container of the matrix
     * @throws MatrixSizeException if array will not match the requirements for container of matrix
     */
    public Matrix(Complex[][] array) throws MatrixSizeException {
        if(checkArrayForMatrix(array)==true){
            this.rows=array.length;
            this.columns=array[0].length;
            this.container = array.clone();
        }else{
            throw new MatrixSizeException("Inappropriate matrix size");
        }
    }

    /**
     * Setter for matrix cell
     * @param row_number integer, number of row to be set
     * @param cell_number integer, number of column to be set
     * @param cell_value complex, value that will be set in cell
     * @throws MatrixOutOfRangeException if the row or cell numbers will be out of matrix borders
     */
    public void setCell(int row_number,int cell_number,Complex cell_value) throws MatrixOutOfRangeException {
        if(row_number>=0&&row_number<rows&&cell_number>=0&&cell_number<columns){
            this.container[row_number][cell_number]=cell_value;
        }else {
            throw new MatrixOutOfRangeException("Out of matrix bounds");
        }
    }

    /**
     * getter for rows
     * @return rows number of this matrix
     */
    public int getRows(){
        return this.rows;
    }
    /**
     * getter for columns
     * @return columns number of this matrix
     */
    public int getColumns(){
        return  this.columns;
    }
    /**
     * Getter for matrix cell
     * @param row_number integer, number of row to be got
     * @param cell_number integer, number of column to be got
     * @return cell_value: complex
     * @throws MatrixOutOfRangeException if the row or cell numbers will be out of matrix borders
     */
    public Complex getCell(int row_number,int cell_number) throws MatrixOutOfRangeException {
        if(row_number>=0&&row_number<rows&&cell_number>=0&&cell_number<columns){
            return this.container[row_number][cell_number];
        }else {
            throw new MatrixOutOfRangeException("Out of matrix bounds");
        }
    }
    /**
     * Plus operator for matrices of complex numbers
     * @param second second summand
     * @return Returns Matrix, sum of two matrices
     * @throws MatrixSizeException if matrices are not the same size
     */
    public Matrix Plus(Matrix second) throws MatrixSizeException {

        if(this.rows==second.rows&&this.columns== second.columns){
            Complex[][] result_container = new Complex[this.rows][this.columns];
            for(int i=0;i<this.rows;i++){
                for(int j=0;j<this.columns;j++){
                    result_container[i][j]=this.container[i][j].Plus(second.container[i][j]);
                }
            }
            return new Matrix(result_container);
        }else{
            throw new MatrixSizeException("Two matrices have incompatible sizes for this operation. They should be the same size");
        }

    }

    /**
     * Unary minus operator for matrix of complex numbers
     * @return the opposite matrix
     */
    public Matrix Minus() throws MatrixSizeException {
        Complex[][] result_container = new Complex[this.rows][this.columns];
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.columns;j++){
                result_container[i][j]=this.container[i][j].Minus();
            }
        }
        return new Matrix(result_container);
    }

    /**
     * Binary minus operator for matrices of complex numbers
     * @param subtrahend The matrix which is subtracted from minuend
     * @return Matrix: difference between this number as minuend and subtrahend
     * @throws MatrixSizeException if matrices are not the same size
     */
    public Matrix Minus(Matrix subtrahend) throws MatrixSizeException {
        return this.Plus(subtrahend.Minus());
    }

    /**
     * Multiply operator for matrices of complex numbers
     * @return Matrix: Multiplication of two matrices
     * @throws MatrixSizeException if columns of first matrix are not equal to rows of the second matrix
     */
    public Matrix Multiply(Matrix second) throws MatrixSizeException {
        if(this.columns==second.rows){
            Complex[][] result_container = new Complex[this.rows][second.columns];
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < second.columns; j++) {
                    result_container[i][j]=new Complex();
                    for (int k=0;k<this.columns;k++){
                        result_container[i][j]=result_container[i][j].Plus(this.container[i][k].Multiply(second.container[k][j]));
                    }
                }
            }
            return new Matrix(result_container);
        }else {
            throw new MatrixSizeException("Two matrices have incompatible sizes for this operation. Columns of this matrix should be equal to the rows of the second matrix");
        }
    }

    /**
     * Multiply operator for matrix of complex numbers and complex number
     * @param second complex number
     * @return Matrix with all elements multiplied by parameter
     */
    public Matrix Multiply(Complex second) throws MatrixSizeException {
        Complex[][] result_container = new Complex[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result_container[i][j]=this.container[i][j].Multiply(second);
            }
        }
        return new Matrix(result_container);
    }

    /**
     * Transposes the matrix
     * @return Transposed matrix
     */
    public Matrix Transpose() throws MatrixSizeException {
        Complex[][] result_container = new Complex[this.columns][this.rows];
        for (int i = 0; i < this.container.length; i++) {
            for (int j = 0; j < this.container[i].length; j++) {
                result_container[j][i]=this.container[i][j];
            }
        }
        return new Matrix(result_container);
    }

    /**
     * Determinant of matrices of size 1 or 2
     * @return Determinant: Complex number
     * @throws MatrixSizeException if matrix size isn`t 1 or 2; or if given matrix is not square
     */
    private Complex SimpleDeterminant() throws MatrixSizeException {
        if(this.rows!=this.columns){
            throw new MatrixSizeException("To calculate the determinant, the matrix should be square");
        }
        switch (this.rows){
            case 1:
                return this.container[0][0];
            case 2:
                return this.container[0][0].Multiply(this.container[1][1] ).
                    Minus(this.container[0][1].Multiply(this.container[1][0]));
            default:
                throw new MatrixSizeException("Matrix size should be 1 or 2 for simple determinant function");
        }

    }

    /**
     * Get cofactor matrix
     * @return Matrix without the stated row and column
     * @throws MatrixSizeException if size of given matrix is less than 2
     */
    private Matrix getCofactor(int row_number,int column_number) throws MatrixSizeException {
        if(this.rows<2) throw new MatrixSizeException("Matrix size should not be less then 2x2");
        int new_row_len=this.container.length-1;
        int new_col_len=this.container[0].length-1;
        Complex[][] cofactor = new Complex[new_row_len][new_col_len];
        int current_row_index=0,current_col_index=0;
        for (int i = 0; i < this.container.length; i++) {
            if(i==row_number) continue;
            for (int j = 0; j < this.container[i].length; j++) {
                if(j==column_number) continue;
                if(current_col_index == new_col_len){
                    current_row_index++;
                    current_col_index=0;
                }

                cofactor[current_row_index][current_col_index++] = this.container[i][j];

            }
        }
        return new Matrix(cofactor);
    }

    /**
     * Calculates the determinant of matrix of complex numbers
     * @return complex number: the determinant of matrix
     * @throws MatrixSizeException if the given matrix isn`t square
     */
    public Complex Determinant() throws MatrixSizeException {
        if(this.rows!=this.columns){
            throw new MatrixSizeException("To calculate the determinant, the matrix should be square");
        }
        if(this.rows<=2) return this.SimpleDeterminant();
        Complex result_sum=new Complex();
        for (int i = 0; i < this.container[0].length; i++) {
            result_sum = result_sum.Plus(new Complex(Math.pow(-1,i)).Multiply(this.container[0][i]).
                    Multiply(this.getCofactor(0,i).Determinant()));
        }
        return result_sum;
    }

    /**
     * Inverse matrix
     * @return Inverse matrix to the given
     * @throws MatrixDeterminantException if determinant of the given matrix equals to 0
     */
    public Matrix Inverse() throws MatrixDeterminantException,MatrixSizeException {
        if(this.Determinant() .equals(new Complex()) ){
            throw new MatrixDeterminantException("To find the inverse matrix, the determinant should not be equal to zero");
        }
        Complex[][] adjoint_container = new Complex[this.rows][this.columns];
        for (int i = 0; i < this.container.length; i++) {
            for (int j = 0; j < this.container[i].length; j++) {
                adjoint_container[i][j]=this.getCofactor(i,j).Determinant().Multiply(new Complex(Math.pow(-1,i+j)));
            }
        }
        return new Matrix(adjoint_container).Transpose().Multiply(this.Determinant().Inverse());
    }

    /**
     * Divides the given matrix by the divisor matrix
     * @param divisor matrix divisor
     * @return divided matrix
     * @throws MatrixSizeException if sizes of the given matrix and inverse of divisor matrix are incompatible for multiplication
     * @throws MatrixDeterminantException if determinant of divisor equals to 0
     */
    public Matrix Divide(Matrix divisor) throws MatrixSizeException,MatrixDeterminantException {
        return this.Multiply(divisor.Inverse());
    }
    @Override
    public String toString() {
        String result ="";
        for (int i = 0; i < container.length; i++) {
            result+=Arrays.toString(container[i])+"\n";
        }
        return result;
    }
}



