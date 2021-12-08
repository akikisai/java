/*
 * Ex12
 * 二つの正の整数を扱う Pairクラスにおいて、以下を作成せよ。
 * (1) 二つの整数の最大公約数を求めるメソッド
 *     public static int gcd(int a, int b)
 * (2) 三つの整数の最大公約数を求めるメソッド
 *     public static int gcd(int a, int b, int c)
 * (3) 二つの整数の最小公倍数を求めるメソッド
 *     public static long lcm(int a, int b)
 *
 * 整数の分母と分子を扱う Fractionクラスにおいて、以下を作成せよ。
 * (4) 分数 b/a を設定するコンストラクタ
 *     public Fraction(int a, int b)
 * (5) 文字列表現を返却するメソッド
 *     public String toString()
 * (6) 整数かどうかを判定するメソッド
 *     public boolean isInteger()
 * (7) 分数 f と比較するメソッド
 *     public int compareTo(Fraction f)
 * (8) 分数 f を加算した結果を返却するメソッド
 *     public Fraction add(Fraction f)
 *
 * 以下について記述せよ
 * (9) 検証
 *     動作確認のために行ったテストの内容と方法を記述
 * (10) 考察
 *     調査したこと、考慮したこと、工夫したことを記述
 */

class Pair {

    // 二つの整数の最大公約数を求める（要作成）
    public static int gcd(int a, int b) {
        // メソッドのコードを記入すること
        if (a < 0) a *= -1;       //負数がある場合正数にする
        if (b < 0) b *= -1;

        if (a < b) {        //a>bという関係にする
            int t = a;
            a = b;
            b = t;
        }
        if (b == 0) return a;    //0が存在するとき、最大公約数は2数の大きほう（0より大きい数または0）
        if (a == 0 && b == 0) return 1;//授業資料より最大公約数は1以上、ここでは0と0の最大公約数は1とする


        while (a % b != 0) {  //余りが0の時終了（ユークリッド法）
            int t = a % b;
            a = b;
            b = t;
        }

        return b; // 不要ならば削除
    }

    // 三つの整数の最大公約数を求める（要作成）
    public static int gcd(int a, int b, int c) {
        // メソッドのコードを記入すること
        if (a < 0) a *= -1;   //負数がある場合正数にする
        if (b < 0) b *= -1;
        if (c < 0) c *= -1;

        int tmp = 0;
        //a,b,cの順番を変え、a>=b>=cという関係にする
        if (b > a) {
            tmp = a;
            a = b;
            b = tmp;
        }
        if (c > b) {
            tmp = b;
            b = c;
            c = tmp;
        }
        if (b > a) {
            tmp = a;
            a = b;
            b = tmp;
        }

        if (a == 0 && b == 0 && c == 0) return 1;  //授業資料より最大公約数は1以上、ここでは0と0の最大公約数は1とする
        if (a == 0 || b == 0 || c == 0) return a;  //0が存在するとき、最大公約数は3数の大きほう（0より大きい数または0）
        if (a == 1 || b == 1 || c == 1) return 1;  //整数1がある場合は最大公約数１


        int i = 0, k = 0, ans = 1, n = a;
        for (k = 0; k < n; k++) {           //すだれ算
            for (i = 2; i <= n; i++) {
                if (a % i == 0 && b % i == 0 && c % i == 0) {
                    ans *= i;
                    a /= i;
                    b /= i;
                    c /= i;
                }
                break;
            }
        }

        return ans; // 不要ならば削除
    }

    // 二つの整数の最小公倍数を求める（要作成）
    public static long lcm(int a, int b) {
        // メソッドのコードを記入すること

        if (a < 0) a *= -1;     //負数がある場合正数にする
        if (b < 0) b *= -1;

        long n1 = a, n2 = b;
        long ans;

        if (a < b) {            //a>bという関係にする
            int t = a;
            a = b;
            b = t;
        }
        while (a % b != 0) {   //余りが0の時終了（ユークリッド法）
            int t = a % b;
            a = b;
            b = t;             //bが2数の最小公約数
        }

        ans = n1 * n2 / b;     //最小公倍数＝2数の掛け算÷2数の最小公約数

        return ans; // 不要ならば削除
    }
}

class Fraction {
    protected int a = 1; // 分母
    protected int b = 0; // 分子

    // 分数 b/a を設定するコンストラクタ（要作成）
    public Fraction(int a, int b) {
        // コンストラクタのコードを記入すること

        if (a == 0) {       //分母0の時1と設定
            this.a = 1;
            this.b = b;
        }
        if (a < 0) {    //分母にマイナスが付かない＋約分する
            this.a = a * -1 / Pair.gcd(a, b);
            this.b = b * -1 / Pair.gcd(a, b);
        } else {
            this.a = a / Pair.gcd(a, b);    //それぞれ最大公約数で割る（約分）
            this.b = b / Pair.gcd(a, b);
        }

    }

    // 文字列表現を返却するメソッド（要作成）
    @Override
    public String toString() {
        // メソッドのコードを記入すること
        String s;
        if (a < 0) {       //分母にマイナスが付かないようにする
            a = a * -1;
            b = b * -1;
        }
        if (a == 1) {       //分母が1の場合分子ｂが返却される
            s = "" + b;
        } else {            //それ以b/aの形で表される
            s = b + "/" + a;
        }
        return s; // 不要ならば削除
    }

    // 整数かどうかを判定するメソッド（要作成）
    public boolean isInteger() {
        // メソッドのコードを記入すること
        if (this.a == 1) return true;   //すでに約分されたので、分母1の場合は整数（true）

        return false; // 不要ならば削除
    }

    // 分数 f と比較するメソッド（要作成）
    public int compareTo(Fraction f) {
        // メソッドのコードを記入すること

        int n = (int) Pair.lcm(this.a, f.a);       //分母＝2つの分母の最小公倍数としたときの
        int n1 = this.b * n / this.a;              //分子（自身）
        int n2 = f.b * n / f.a;                    //分子（比較するほう）

        if (n1 > n2) return 1;                     //分子比較=分数比較(場合分け)
        if (n1 < n2) return -1;
        if (n1 == n2) return 0;

        return 5;
    }

    // 分数 f を加算した結果を返却するメソッド（要作成）
    public Fraction add(Fraction f) {
        // メソッドのコードを記入すること
        int n1 = (int) Pair.lcm(this.a, f.a);                   //分母＝2つの分母の最小公倍数
        int n2 = this.b * (n1 / this.a) + f.b * (n1 / f.a);     //分母が最小公倍数の時の分子

        Fraction ff = new Fraction(n1, n2);

        return ff; // 不要ならば削除
    }
}

/* 検証 -- 動作確認のために行ったテストの内容と方法を記述
  2数の最大公約数では(-4,-6),(1,6),(0,20),(0,0)を入力し正しい結果が確認でき、
  3数の最大公約数も同じように実際の値で検証し、正しいとわかる。最小公倍数についても同じように検証した。
  コンストラクタでは、a=0の場合1になっているか、コンストラクタがちゃんと初期化しているか、
  設定後約分し分母が正になっているかを値入れて検証した。文字列表現では約分した分母が1の場合bが返却されているか、
  そうでない場合約分したb/aが書き出されるかを検証した。
  整数であるかについても、約分した分母が1であればtrue、そうでなければfalseになっているかをみた。
  分数比較メソッドでは、正及び負の分数それぞれの場合正しい数が返却されるかを見た。
  加算メソッドも、正及び負がちゃんと足されるか、約分されているかを見た。
 */

/* 考察 -- 調査したこと、考慮したこと、工夫したことを記述
  調査したものに関しては、例えば0と20の最大公約数は20になり、
  0と0の最大公約数は理論上0～無限大である(このプログラムでは1と設定した)2数の最大公約数ではユークリッド法を使い、
  3数ではすだれ算を使った。すだれ算において外側のfor文の範囲をn(=a:3数の最大値)以下と設定したが、
  ｎは共通約数を割る回数を必ず超えないのでセーフ。最小公倍数では2数の掛け算わる最大公約数であるとわかった。
  分数クラスでは(this.)を使う必要のないところは省略し、また場合分けを注意して丁寧に作成し、
  全体の見栄えを注意して、説明も入れた。メソッドも使い簡略してプログラムを書いた。
  苦労したことは分母をなかなか負数から正数に直せず、最後に書き方のミスと発覚した。
  また最大公約数、最小公倍数が変な数値にならないように、負の値を入力しても1以上の値を出せるようにした。
 */

public class Ex12 {

    static String gakuban = "19EC043"; // 学籍番号を入力すること
    static String yourname = "蔡晟輝"; // 氏名を入力すること

    public static void main(String[] args) {

        System.out.println("gcd(4000, 6000) = " + Pair.gcd(4000, 6000)); // 2000
        System.out.println("gcd(192, 160, 240) = " + Pair.gcd(192, 160, 240)); // 16
        System.out.println("lcm(40000, 60000) = " + Pair.lcm(40000, 60000)); // 120000

        Fraction f1 = new Fraction(60, 40);
        System.out.println("Fraction(60, 40): a = " + f1.a + ", b = " + f1.b); // a=3, b=2
        System.out.println("f1.toString() = " + f1.toString()); // 2/3
        System.out.println("f1.isInteger() = " + f1.isInteger()); // false

        Fraction f2 = new Fraction(-6, 1);
        System.out.println("f2.toString() = " + f2.toString()); // -1/6
        System.out.println("f2.compareTo(f1) = " + f2.compareTo(f1)); // -1
        System.out.println("f2.add(f1) = " + f2.add(f1)); // 1/2

        Fraction f3 = new Fraction(70000, 99999);
        System.out.println("f3.toString() = " + f3.toString()); // 99999/70000
        Fraction f4 = new Fraction(50000, 99999);
        System.out.println("f4.toString() = " + f4.toString()); // 99999/50000
        System.out.println("f4.add(f3) = " + f4.add(f3)); // 299997/87500
    }
}
