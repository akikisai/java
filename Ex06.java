/*
 * Ex06
 * (1) 整数の配列 ｔ を引数として受け取り、配列 ｔ の3要素を3辺の長さとする三角形が構成可能か
 *     どうかを判定するメソッド isTriangle を作成せよ。
 *     public static boolean isTriangle(int[] t)
 *
 * (2) 整数の配列 ｔ の配列（2次元配列） tary を引数として受け取り、配列 ｔ の3要素を3辺の
 *     長さとする三角形の面積が最大となる t のインデックスを返却するメソッド maxTriangle
 *     を作成せよ。
 *     public static int maxTriangle(int[][] tary)
 */

class Ex06 {
    static String gakuban = "19EC043"; // 学籍番号を入力すること
    static String yourname = "蔡晟輝"; // 氏名を入力すること

    // 三角形かどうかを判定するメソッド
    public static boolean isTriangle(int[] t) {
        // メソッドのコードを記入
        if (t.length == 3) {                //tの要素数が3の時
            int tmp = t[0];                 //小さい順に並ぶ
            if (t[1] < t[0]) {
                t[0] = t[1];
                t[1] = tmp;
            }
            if (t[2] < t[1]) {
                tmp = t[2];
                t[2] = t[1];
                t[1] = tmp;
            }
            if ((t[0] + t[1]) > t[2]) return true;      //小さい2数が大きい数より大→→→true

        }

    return false;              //それ以外false
    }

    // 面積が最大の三角形を抽出するメソッド
    public static int maxTriangle(int[][] tary) {
        // メソッドのコードを記入
        double menseki=0,maxmenseki=0;
        int n=0;
        double s=0;             //ヘロンの公式でのs
        /*
        ヘロンの公式　三角形三辺がabcの時、
        面積＝(s(s-a)(s-b)(s-c))^(1/2)
        ただし s=(a+b+c)/2
         */
        for(int i=0;i<tary.length;i++){
            if(tary[i].length==3 && isTriangle(tary[i])==true){         //要素数が3かつ三角形になれる

                s=(tary[i][0]+tary[i][1]+tary[i][2])/2.;
                menseki=Math.sqrt(s*(s-tary[i][0])*(s-tary[i][1])*(s-tary[i][2]));
                if(menseki>maxmenseki){maxmenseki=menseki; n=i;}
                //面積計算し、次の配列要素がこの面積より大きければ置き換える、この時の番号を記録。
                //さらに次の面積が同じ面積であっても、番号が記録されない
            }
        }
        if(menseki==0) return -1; //mensekiが初期値で、代入されないということは、すべて偽になる
        return n;
    }

    /* 検証 -- 動作確認テストの内容と方法を記述
       正しく動作するためにtarray配列を最初のまま実行し(全部3要素)、結果があっているかを確認する。
       次に、2番目(インデックスが1になる)ところを3要素、ちゃんとした三角形になるように値を設定し、後の値は全部偽になるように設定する。
       これで実行し自分の設定した三角形がtrue出てきているか、それ以外全部falseになっているか、
       面積最大の三角形抽出メソッドで1[数値,数値,数値]という結果が出てきているか。またtarray配列すべてを3要素の偽、
       4要素の偽と設定し、falseになるか、-1は表示されるかを確認した。また全く同じ要素を入れて、面積では、
       ちゃんと最初のほうのインデックス番号が出てくるかを確認した。以上で正しく動作できると確認できた。

     */

    /* 考察 -- 調査したこと、考慮したこと、工夫したことを記述
        まず三角形の三辺から面積を求めるヘロンの公式について調べ、lengthを活用し、なるべく簡潔に作成することを心掛けた。
        最初は拡張for文使うと考えていたがやっぱり無理で普通のfor文に戻し、要素数が3という条件があるのでそのまま値について直接調べ、
        条件にあっているかを調べた。maxTriangleメソッドのなかではisTriangleメソッドを使うことによってコードが省略できた。
        面積を作るときはdouble型に注意してコードを作った。
        またmaxTriangleメソッドであらかじめ変数を宣言することで見やすく使いやすくできた。
        出来栄えとしてはプログラムが正確に動くが、もっと簡潔にできるんじゃないかなと思う。
        またelse文使わなかったが本来あるほうがプログラムとして完成度高いという気がした。

     */

    /*追記　--　プログラム簡単の説明
    プログラム作成においてまず三角形を判定するメソッドでは、if文で要素数が3なら、中の配列を小さい順に並べ、
    小さい2数を足して一番大きい数より大きい場合にtrue、これ以外は全部falseになる。
    要素数が4の場合要素数が3の条件文を飛ばすのでfalseになる。面積最大の三角形抽出メソッドでは、
    要素数3かつ三角形になれる場合、面積を計算し変数に代入して、次の三角形面積が大きければまた代入する、
    また最初に出てきた1番大きい面積のインデクス番号を記録する。

     */

    // メインメソッド（テストプログラム）
    public static void main(String[] args) {
        int[][] tarray = {
                { 7, 3, 6,1 }, { 8, 10, 5 }, { 8, 5, 10 }, { 2, 5, 8 },
                { 6, 10, 3 }, { 5, 10, 4 }, { 80, 40, 40 }, { 5, 4, 10 },
        };
        System.out.println("Testing isTriangle ...");
        for (int[] t : tarray) {
            System.out.printf("%s %s\n",
                    java.util.Arrays.toString(t), isTriangle(t));
        }
        System.out.println("Testing maxTriangle ...");
        int k = maxTriangle(tarray);
        System.out.print(k);
        if (k >= 0) {
            System.out.printf(" %s\n",
                    java.util.Arrays.toString(tarray[k]));
        } else {
            System.out.println();
        }
    }
}