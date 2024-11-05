package com.example.wesign.utils

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class KeyStoreManager(private  val context: Context) {
    private lateinit var keyStore: KeyStore
    private lateinit var keyPair: KeyPair

    companion object {
        const val ALIAS_TOKEN = "alias_token"

        private const val TAG = "KeyStoreManager"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_RSA
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

    init {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }

    fun createKey(alias: String) {
        try {
            if (!keyStore.containsAlias(alias)) {
                val keyPairGenerator =
                    KeyPairGenerator.getInstance(ALGORITHM, "AndroidKeyStore")
                val parameterSpec = KeyGenParameterSpec.Builder(
                    alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setEncryptionPaddings(PADDING)
                    .setDigests(KeyProperties.DIGEST_SHA1)
                    .build()
                keyPairGenerator.initialize(parameterSpec)
                keyPair = keyPairGenerator.genKeyPair()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }

    fun getKeyInfo(alias: String): String {
        //val privateKey: PrivateKey = (keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry).privateKey
        val cert = keyStore.getCertificate(alias)
        val publicKey = cert.publicKey

        val publicKeyBytes: ByteArray = Base64.encode(publicKey.encoded, Base64.DEFAULT)
        val pubKeyString = String(publicKeyBytes)

        //val privateKeyBytes: ByteArray = Base64.encode(privateKey.encoded, Base64.DEFAULT)
        //val priKeyString = String(privateKeyBytes)
        return pubKeyString
    }
    fun encryptString(clearText: String, alias: String): String {
        // Generate AES key for symmetric encryption
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256) // 256-bit AES key
        val aesKey = keyGen.generateKey()

        // Encrypt clearText using AES
        val aesCipher = Cipher.getInstance("AES/GCM/NoPadding")
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey)
        val iv = aesCipher.iv // Get the IV for decryption
        val cipherText = aesCipher.doFinal(clearText.toByteArray(Charsets.UTF_8))

        // Encrypt AES key using RSA
        val publicKey = keyStore.getCertificate(alias).publicKey
        val rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedAesKey = rsaCipher.doFinal(aesKey.encoded)

        // Encode and combine IV, encrypted AES key, and ciphertext into a single string
        val encryptedData = Base64.encodeToString(iv + encryptedAesKey + cipherText, Base64.NO_WRAP)
        return encryptedData
    }

    fun decryptString(cipherText: String, alias: String): String {
        // Decode the base64-encoded cipherText into a byte array
        val decodedData = Base64.decode(cipherText, Base64.NO_WRAP)

        // Extract the IV (first 12 bytes), RSA-encrypted AES key (next 256 bytes), and AES-encrypted message (remaining bytes)
        val iv = decodedData.copyOfRange(0, 12) // 12 bytes for GCM IV
        val encryptedAesKey = decodedData.copyOfRange(12, 268) // 256 bytes for RSA-encrypted AES key
        val encryptedMessage = decodedData.copyOfRange(268, decodedData.size)
        // Decrypt the AES key with the RSA private key
        val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
        val rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKeyEntry.privateKey)
        val aesKeyBytes = rsaCipher.doFinal(encryptedAesKey)
        // Reconstruct the AES key from the decrypted bytes
        val aesKey = SecretKeySpec(aesKeyBytes, "AES")
        // Decrypt the message with AES using the decrypted AES key and the IV
        val aesCipher = Cipher.getInstance("AES/GCM/NoPadding")
        val gcmSpec = GCMParameterSpec(128, iv) // 128-bit authentication tag
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec)
        val decryptedText = aesCipher.doFinal(encryptedMessage)

        return String(decryptedText, Charsets.UTF_8)
    }

    fun encryptAny(value: Any, alias: String): String {
        val jsonString = Gson().toJson(value)
        return encryptString(jsonString, alias)
    }
    fun <T> decryptAny(cipherText: String, alias: String, classType: Class<T>): T {
        val decryptedString = decryptString(cipherText, alias)
        return  Gson().fromJson(decryptedString, classType)
    }

    fun getAliases(): String {
        var aliasesString = ""
        val aliases = keyStore.aliases()
        while (aliases.hasMoreElements()) {
            aliasesString += "${aliases.nextElement()}, "
        }
        //textAliases.text = aliasesString
        return aliasesString
    }

    fun deleteKey(alias: String) {
        keyStore.deleteEntry(alias)
    }
}