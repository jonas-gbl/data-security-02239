package gr.matamis.server;

import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashedFileBasedAuthenticator extends FileBasedAuthenticator {


    public HashedFileBasedAuthenticator(Path passwordFile) throws IOException {
        super(passwordFile);
    }

    @Override
    public boolean isAuthenticated(Credentials credentials) {

        String usenameToAuthenticate = credentials.getUsername();
        String passwordToVerify = credentials.getPassword();

        boolean isAuthenticated = false;
        if (passwords.containsKey(usenameToAuthenticate)) {
            String storedSaltAndBase64HashedPassword = passwords.get(usenameToAuthenticate);
            String[] saltAndPassword = storedSaltAndBase64HashedPassword.split("\\$");

            String salt = saltAndPassword[0];
            String storedBase64HashedPassword = saltAndPassword[1];

            Base64.Decoder base64Decoder = Base64.getDecoder();
            byte[] saltBytes = base64Decoder.decode(salt);


            try {

                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

                messageDigest.update(saltBytes);
                byte[] hashedPasswordBytes = messageDigest.digest(passwordToVerify.getBytes());
                Base64.Encoder base64Encoder = Base64.getEncoder();
                String base64HashedPassword = base64Encoder.encodeToString(hashedPasswordBytes);

                isAuthenticated = base64HashedPassword.equals(storedBase64HashedPassword);


            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }


        }

        return isAuthenticated;

    }
}
