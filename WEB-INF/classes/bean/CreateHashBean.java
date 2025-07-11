/* このプログラムは、パスワードを受け取るとハッシュ値、ソルト、ストレッチング回数を返すクラスである */
package bean;

import java.security.SecureRandom;

public class CreateHashBean extends HashingToolsBean{
    public String[] createhash(String pass){
        try {
            // ソルトを生成
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // ストレッチング回数をランダムに決定 (10000から20000まで)
            int stretching = 10000 + random.nextInt(10001);

            // 決定した回数だけストレッチング
            String hashedPassword = super.hashWithSalt(pass, salt, stretching);

            // ソルトとハッシュを16進数文字列に変換
            String saltString = bytesToHex(salt);

            return new String[]{hashedPassword, saltString, Integer.toString(stretching)};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}