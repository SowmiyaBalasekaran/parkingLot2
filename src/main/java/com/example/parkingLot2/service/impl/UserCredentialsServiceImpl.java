package com.example.parkingLot2.service.impl;

import com.example.parkingLot2.entity.UserCredentials;
import com.example.parkingLot2.repository.UserCredentialsRepository;
import com.example.parkingLot2.service.UserCredentialsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCredentialsServiceImpl implements UserCredentialsService {

  private final UserCredentialsRepository userCredentialsRepository;
  @Override
  @SneakyThrows
  public Boolean checkUserCredentials(String userName, String password) {
    SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
    UserCredentials userCredentials = userCredentialsRepository.findByUserName(userName);
    if(decrypt(userCredentials.getPassword(), secretKey).equalsIgnoreCase(password)) {
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

  @Override
  @SneakyThrows
  public Boolean registerUser(UserCredentials userCredentials, String password) {
    SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
    Optional<UserCredentials> existingUserCredentials = Optional.ofNullable(userCredentialsRepository.findByUserName(userCredentials.getUserName()));

    if (existingUserCredentials.isPresent()) {
      return false;
    } else {
      byte[] encryptedPassword  = encrypt(password, secretKey);
      userCredentials.setPassword(encryptedPassword);
      userCredentialsRepository.save(userCredentials);
      return true;
    }
  }

  private static byte[] encrypt(String message, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
  }

  private static String decrypt(byte[] encryptedMessage, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

}
