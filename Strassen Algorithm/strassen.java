import java.util.Scanner;

public class strassen {
	public static void main(String[] args) {
        int n, cutoff;
        Matrix a, b;
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        cutoff = s.nextInt();

        a = new Matrix(n);
        b = new Matrix(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a.setV(i, j, s.nextInt());

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                b.setV(i, j, s.nextInt());

        Matrix c = a.multStrassen(b, cutoff);

        c.printMatrix();
	}

}

class Matrix {
	private int[][] m;

	public int n; //only square matrices

	public Matrix(int n){
		this.n = n;
		m = new int[n][n];
	}

    //set value at i,j
	public void setV(int i, int j, int val){
		m[i][j] = val;
	}


    // get value at index i,j
	public int v(int i, int j){
		return m[i][j];
	}


    // return a square submatrix from this
	public Matrix getSubmatrix(int startRow, int startCol, int dim){
		Matrix subM = new Matrix(dim);

		for (int i = 0; i<dim ; i++ )
			for (int j=0;j<dim ; j++ )
				subM.setV(i,j, m[startRow+i][startCol+j]);

		return subM;
	}


    // write this matrix as a submatrix from b (useful for the result of multiplication)
	public void putSubmatrix(int startRow, int startCol, Matrix b){
		for (int i = 0; i<b.n ; i++ )
			for (int j=0;j<b.n ; j++ )
				setV(startRow+i,startCol+j, b.v(i,j));
	}


    // matrix addition
	public Matrix sum(Matrix b){
		Matrix c = new Matrix(n);
		for(int i = 0; i< n;i++){
			for(int j = 0; j<n;j++){
				c.setV(i, j, m[i][j]+b.v(i, j));
			}
		}

		return c;
	}

    // matrix subtraction
	public Matrix sub(Matrix b){
		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){
			for(int j = 0; j<n;j++){
				c.setV(i, j, m[i][j]-b.v(i, j));
			}
		}

		return c;
	}



	//simple multiplication
	public Matrix mult(Matrix b){
        Matrix c = new Matrix(n);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < b.n; j++) {
                for (int k = 0; k < this.n; k++) {
                    c.setV(i, j, c.v(i, j) + this.v(i, k) * b.v(k, j));
                }
            }
        }

        return c;
	}


    // Strassen multiplication
	public Matrix multStrassen(Matrix b, int cutoff){
        if (this.n <= cutoff)
            return this.mult(b);
        
        Matrix a11, a12, a21, a22, b11, b12, b21, b22, m1, m2, m3, m4, m5, m6, m7, c11, c12, c21, c22;

        a11 = this.getSubmatrix(0, 0, this.n/2);
        a12 = this.getSubmatrix(0, this.n/2, this.n/2);
        a21 = this.getSubmatrix(this.n/2, 0, this.n/2);
        a22 = this.getSubmatrix(this.n/2, this.n/2, this.n/2);

        b11 = b.getSubmatrix(0, 0, b.n/2);
        b12 = b.getSubmatrix(0, b.n/2, b.n/2);
        b21 = b.getSubmatrix(b.n/2, 0, b.n/2);
        b22 = b.getSubmatrix(b.n/2, b.n/2, b.n/2);

        m1 = (a11.sum(a22)).multStrassen(b11.sum(b22), cutoff);
        m2 = (a21.sum(a22)).multStrassen(b11, cutoff);
        m3 = a11.multStrassen(b12.sub(b22), cutoff);
        m4 = a22.multStrassen(b21.sub(b11), cutoff);
        m5 = (a11.sum(a12)).multStrassen(b22, cutoff);
        m6 = (a21.sub(a11)).multStrassen((b11.sum(b12)), cutoff);
        m7 = (a12.sub(a22)).multStrassen(b21.sum(b22), cutoff);

        c11 = ((m1.sum(m4)).sub(m5)).sum(m7);
        c12 = m3.sum(m5);
        c21 = m2.sum(m4);
        c22 = (((m1.sub(m2)).sum(m3)).sum(m6));

        Matrix nm = new Matrix(n);

        nm.putSubmatrix(0, 0, c11);
        nm.putSubmatrix(0, this.n/2, c12);
        nm.putSubmatrix(this.n/2, 0, c21);
        nm.putSubmatrix(this.n/2, this.n/2, c22);

        return nm;
	}


    public void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.v(i, j) + " ");
            }
            System.out.println();
        }
    }

    public int sumOfElements() {
        int s = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                s += m[i][j];
        }

        return s;
    }

}


