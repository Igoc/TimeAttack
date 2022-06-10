package com.example.timeattack.domain;

import com.example.timeattack.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Getter
public class User extends Timestamp {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    public User(UserDto userDto) {
        try {
            id = userDto.getId();
            password = encrypt(userDto.getPassword());
            name = userDto.getName();
            age = userDto.getAge();
            gender = userDto.getGender();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void update(UserDto userDto) {
        try {
            password = encrypt(userDto.getPassword());
            name = userDto.getName();
            age = userDto.getAge();
            gender = userDto.getGender();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes());

        return convertBytesToHex(messageDigest.digest());
    }

    private String convertBytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }
}
