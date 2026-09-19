package br.com.senai.autoescolas164;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteByscript {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senha = "12345678";

        String hash = encoder.encode(senha);

        System.out.println(hash);
    }
}