import java.util.*;

class Complex{
	double re;
	double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
    	double tRe = (double)Math.round(re * 100000) / 100000;
    	double tIm = (double)Math.round(im * 100000) / 100000;
        if (tIm == 0) return tRe + "";
        if (tRe == 0) return tIm + "i";
        if (tIm <  0) return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

	public Complex conj() {
		return new Complex(re, -im);
	}

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }


    //potenca komplesnega stevila
    public Complex pow(int k) {

    	Complex c = new Complex(1,0);
    	for (int i = 0; i <k ; i++) {
			c = c.times(this);
		}
    	return c;
    }

}

public class FFT {
    public static void main(String[] args) {
        int n, m;
        Vector<Complex> a = new Vector<Complex>(), b = new Vector<Complex>(), c = new Vector<Complex>();
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        m = (int)Math.pow(2, Math.ceil(Math.log((double)(2*n)) / Math.log((double)2)));

        for (int i = 0; i < n; i++) {
            double tmp = sc.nextDouble();
            a.add(new Complex(tmp, 0));
        }

        for (int i = 0; i < n; i++) {
            double tmp = sc.nextDouble();
            b.add(new Complex(tmp, 0));
        }

        for (int i = 0; i < m-n; i++) {
            a.add(new Complex(0, 0));
            b.add(new Complex(0, 0));
        }

        a = fft(a, false);
        b = fft(b, false);

        for (int i = 0; i < m; i++) {
            c.add(a.get(i).times(b.get(i)));
        }

        c = fft(c, true);

        for (int i = 0; i < m; i++) {
            System.out.print((c.get(i).times(1/(double)m)).toString() + " ");
        }
        System.out.println();
    }

    public static Vector<Complex> fft(Vector<Complex> a, boolean inv) {
        int n = a.size();
        if (n == 1)
            return a;

        Vector<Complex> ys = new Vector<Complex>(), yl = new Vector<Complex>();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                ys.add(a.get(i));
            else
                yl.add(a.get(i));
        }

        ys = fft(ys, inv);
        yl = fft(yl, inv);

        Complex w = new Complex(0, (2 * Math.PI)/n);
        w = w.exp();
        Complex wk = new Complex(1, 0);
        Vector<Complex> y = new Vector<Complex>();

        for (int i = 0; i < n; i++)
            y.add(new Complex(0, 0));

        for (int k = 0; k <= n/2 - 1; k++) {
            y.set(k, ys.get(k).plus(wk.times(yl.get(k))));
            y.set(k+n/2, ys.get(k).minus(wk.times(yl.get(k))));
            if (inv) wk = wk.divides(w);
            else wk = wk.times(w);
        }

        for (int i = 0; i < y.size(); i++)
            System.out.print(y.get(i).toString() + " ");
        System.out.println();

        return y;
    }
}