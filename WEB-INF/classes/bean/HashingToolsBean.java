/* ハッシュ化に必要な共通のツールを纏めたクラス */
package bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashingToolsBean{

    // ハッシュ化する文字列、ソルト、ストレッチング回数を受け取ると、文字列とソルトを連結しストレッチングして返す
    public static String hashWithSalt(String pass, byte[] salt, int stretching) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(pass.getBytes());

        // 決定した回数だけストレッチング
        for (int i = 0; i < stretching; i++) {
            hashedPassword = md.digest(bytesToHex(hashedPassword).getBytes());
        }

        return bytesToHex(hashedPassword);
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}