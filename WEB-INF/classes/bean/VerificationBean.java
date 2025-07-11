/* このプログラムは、パスワード、ハッシュ値、ソルト、ストレッチング回数を受け取るとtrueかfalseを返すクラスである */
package bean;

public class VerificationBean extends HashingToolsBean{
    public boolean verification(String pass, String hash, String salt, int stretching){
        // 検証を実行
        try {
            // ソルトをバイト配列に変換
            byte[] byteSalt = super.hexToBytes(salt);

            // パスワードをソルトと結合し、指定回数だけストレッチング
            String hashCheck = super.hashWithSalt(pass, byteSalt, stretching);

            // ハッシュ値が一致するか検証
            return hashCheck.equals(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
