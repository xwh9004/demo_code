package com.learning.jvm;

public class TryCatch {
    public static void main(String[] args) {
        try {
            truItOut();
        }catch (NumberFormatException e) {
            handleException(e);
        } catch (Exception e){
            handleException(e);
        }
    }

    private static void truItOut() {
    }

    private static void handleException(Exception e) {

    }
}
