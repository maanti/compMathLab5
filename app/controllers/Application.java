package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import java.lang.Math.*;

public class Application extends Controller {
    private static int functionTypeInt;
    private static Double aDouble, bDouble, precisionDouble;
    private static int partsNumber;
    private static Double inacuracy;
    public static void index() {
        render();
    }

    public static void integrate(String functionType, String a, String b, String precision){
        functionTypeInt = Integer.parseInt(functionType);
        aDouble = Double.parseDouble(a);
        bDouble = Double.parseDouble(b);
        precisionDouble = Double.parseDouble(precision);
        String imgSrc = "";
        String result = "";
        Double resultDouble = null;


        switch (functionTypeInt){
            case 1:
                imgSrc = "http://mathurl.com/ycyopmlu.png";
                resultDouble = solve(1, aDouble, bDouble, precisionDouble);
                break;
            case 2:
                imgSrc = "http://mathurl.com/yb2sjlqs.png";
                resultDouble = solve(2, aDouble, bDouble, precisionDouble);
                break;
            case 3:
                imgSrc = "http://mathurl.com/y7vqvj9c.png";
                resultDouble = solve(3, aDouble, bDouble, precisionDouble);
                break;
            case 4:
                imgSrc = "http://mathurl.com/y9fanz8k.png";
                resultDouble = solve(4, aDouble, bDouble, precisionDouble);
                break;
        }
        resultDouble *= Math.pow(10, precisionDouble);
        int temp = (int) Math.round(resultDouble);
        resultDouble = (double) temp/(Math.pow(10, precisionDouble));
        functionType = String.valueOf(functionTypeInt);
        a = String.valueOf(aDouble);
        b = String.valueOf(bDouble);
        precision = String.valueOf(precision);
        result = String.valueOf(resultDouble);
        String inaccStr = String.valueOf(inacuracy);
        String prtsNum = String.valueOf(partsNumber);
        render(functionType, imgSrc, a, b, precision, result, inaccStr, prtsNum);
    }

    public static double f(int type, Double x){
        Double result = null;
        switch (type){
            case 1:
                result = Math.sin(x) * Math.pow(Math.E,x);
                break;
            case 2:
                result = Math.log(9 * Math.sqrt(x));
                break;
            case 3:
                result = Math.pow(x,(Math.cos(Math.E*x))) * Math.atan(x);
                break;
            case 4:
                result = Math.sqrt(Math.pow(x,3) - Math.pow(x, 2) + 3*x)/Math.pow(x, Math.E);
                break;
        }
        return result;
    }

    public static double solve(int type, Double aDouble, Double bDouble, Double precisionDouble){
        Double eps = Math.pow(10, -precisionDouble);
        Double h;
        Double sum = 0.0;
        Double result1 = null;
        Double result2 = 0.0;
        partsNumber = 1;
        h = (bDouble - aDouble)/partsNumber;
        for(int i = 1; i < partsNumber; i++){
            sum += f(type, aDouble + i * h);
        }
        result1 = h * (((f(type, aDouble) + f(type, bDouble)) / 2) + sum);
        while (Math.abs(result1 - result2) > eps){
            sum = 0.0;
            result2 = result1;
            partsNumber *= 2;
            h = (bDouble - aDouble)/partsNumber;
            for(int i = 1; i < partsNumber; i++){
                sum += f(type, aDouble + i * h);
            }
            result1 = h * (((f(type, aDouble) + f(type, bDouble)) / 2) + sum);
        }
        inacuracy = Math.abs(result1 - result2);
        return result1;
    }
}