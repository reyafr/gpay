package com.gpay.gpay.service;

public class TestService {

    public static int perkalianSederhana(int j, int k) {
        int hasil = 0;
        while (j > 0) {
//            total +=k; // kesalahan nya variable tampungan 'total' tidak terdefinisi
            hasil += k; // variable 'hasil' merupakan variable tampungan yang benar
            j--;
        }
        return hasil;
    }

    public static void main(String args[]){
        System.out.println(perkalianSederhana(5,5));

    }
}
