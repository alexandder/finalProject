/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author aleksander
 */
public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public void initialize(Pesel pesel) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.length() != 11) {
            return false;
        } 
        else {
            int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            int j, control;
            int sum = 0;
            int csum = new Integer(value.substring(value.length() - 1)).intValue();
            for (int i = 0; i < value.length() - 1; i++) {
                char c = value.charAt(i);
                j = new Integer(String.valueOf(c)).intValue();
                sum += j * weights[i];
            }
            control = 10 - (sum % 10);
            if (control == 10) {
                control = 0;
            }

            if (control != csum) {
                return false;
            }
        }
        return true;
    }

}
