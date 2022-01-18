package optic_fusion1.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        return keyGen.generateKeyPair();
    }

    public static KeyPair loadRsaKeyPair(File publicKeyFile, File privateKeyFile) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream publicKeyInputStream = new FileInputStream(publicKeyFile);
        byte[] encodedPubKey = new byte[(int) publicKeyFile.length()];
        publicKeyInputStream.read(encodedPubKey);
        publicKeyInputStream.close();

        FileInputStream privateKeyInputStream = new FileInputStream(privateKeyFile);
        byte[] encodedPrivKey = new byte[(int) privateKeyFile.length()];
        privateKeyInputStream.read(encodedPrivKey);
        privateKeyInputStream.close();

        KeyFactory factory = KeyFactory.getInstance("DSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPubKey);
        PublicKey publicKey = factory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivKey);
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static void saveRsaKeyPair(KeyPair keyPair, File publicKeyFile, File privateKeyFile) throws IOException {
        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        publicKeyFile.createNewFile();
        privateKeyFile.createNewFile();

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        FileOutputStream publicKeyFileOutputStream = new FileOutputStream(publicKeyFile);
        publicKeyFileOutputStream.write(publicKeySpec.getEncoded());
        publicKeyFileOutputStream.close();

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        FileOutputStream privateKeyFileOutputStream = new FileOutputStream(privateKeyFile);
        privateKeyFileOutputStream.write(privateKeySpec.getEncoded());
        privateKeyFileOutputStream.close();
    }

    public static PublicKey getPublicKeyFromBytes(byte[] encodedPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("DSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        return factory.generatePublic(publicKeySpec);
    }

    public static boolean verifySignature(byte[] signature, PublicKey publicKey, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signature);
    }
}
