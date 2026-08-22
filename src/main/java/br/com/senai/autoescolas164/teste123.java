package br.com.senai.autoescolas164;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste123 {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode("admin");

        System.out.println(hash);
    }
}
