package edu.nyu.cs9053.homework6;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SafetyDepositBox {
    private static int gems = 0;
    private final long safePassword;

    public SafetyDepositBox(Long safePassword) {
        this.safePassword = safePassword;
    }

    public boolean canGetTools(Mine mine) {
        try {
            Class<?>[] parameterTypes = null;
            Method getTools = mine.getClass().getDeclaredMethod("getTools", parameterTypes);
            Field safetyDepositBox = mine.getClass().getDeclaredField("safetyDepositBox");
            safetyDepositBox.setAccessible(true);
            SafetyDepositBox safetyBox = (SafetyDepositBox) safetyDepositBox.get(mine);
            Safe safe = getTools.getAnnotation(Safe.class);
            if (safe.password() == safetyBox.safePassword) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (NoSuchFieldException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (IllegalAccessException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        }
    }

    public boolean canCashIn(Mine mine, int gems) {
        try {
            Class<?>[] parameterTypes = new Class<?>[] { int.class };
            Method cashIn = mine.getClass().getDeclaredMethod("cashIn", parameterTypes);
            Field safetyDepositBox = mine.getClass().getDeclaredField("safetyDepositBox");
            safetyDepositBox.setAccessible(true);
            SafetyDepositBox safetyBox = (SafetyDepositBox) safetyDepositBox.get(mine);
            Safe safe = cashIn.getAnnotation(Safe.class);
            if (safe.password() == safetyBox.safePassword) {
                SafetyDepositBox.gems += gems;
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (NoSuchFieldException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        } catch (IllegalAccessException e) {
            System.out.printf("Something Bad Happened %s%n", e.getMessage());
            return false;
        }

    }
}
