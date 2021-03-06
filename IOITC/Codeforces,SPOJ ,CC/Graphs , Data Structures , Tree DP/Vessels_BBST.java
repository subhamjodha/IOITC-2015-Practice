import java.io.*;
import java.util.*;
public class Vessels_BBST
{
    static int N,Q;
    static int cap[]=new int [200006];
    static int fill[]=new int[200006];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        TreeSet<Integer> ts = new TreeSet<Integer>();
        for(int i=1;i<=N;i++)
        {
            cap[i]=sc.nextInt();
            ts.add(i);
        }
        Q = sc.nextInt();
        while(Q-->0)
        {
            int type = sc.nextInt(),pos=sc.nextInt();
            if(type==1)
            {
                int val  = sc.nextInt();
                while(val>0 && pos <= N)
                {
                    Integer nextpos = ts.higher(pos-1); // UpperBound!
                    if(nextpos==null)//If no number greater , all the water will drip out , so break!
                    break;
                    pos = nextpos; 
                    fill[pos]+=val;
                    val = fill[pos] - cap[pos] ;
                    fill[pos]=Math.min(fill[pos],cap[pos]);
                    if(fill[pos]==cap[pos])ts.remove(pos); // Capacity Full!
                    pos++;
                }
            }
            else
                System.out.println(fill[pos]);
        }
    }
}
class InputReader1 
{

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader1(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public String nextLine() {
        int c = read();
        //while (c != '\n' && c != '\r' && c != '\t' && c != -1)
        //c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != '\t' && c != -1);
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

}   