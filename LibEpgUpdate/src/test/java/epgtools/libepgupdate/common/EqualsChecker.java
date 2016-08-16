/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.common;

/**
 *
 * @author normal
 * @param <T>
 */
public class EqualsChecker<T> {

    public EqualsChecker() {
    }

    /**
     * @param target1
     * @param target2
     * @param target3
     * @return target1～target3が全て等しく、下記を満たすならtrue。
     * @see java.lang.Object.equals
     */
    public boolean check(final T target1, final T target2, final T target3) {
        boolean ret;
        ret = true;
        System.out.println("target1");
        ret = ret && this.singleParm(target1);
        if (ret == false) {
            return ret;
        }
        System.out.println("target2");
        ret = ret && this.singleParm(target1);
        if (ret == false) {
            return ret;
        }
        System.out.println("target3");
        ret = ret && this.singleParm(target1);
        if (ret == false) {
            return ret;
        }

        System.out.println("target1-2");
        ret = ret && this.doubleParms(target1, target2);
        if (ret == false) {
            return ret;
        }
        System.out.println("target2-3");
        ret = ret && this.doubleParms(target2, target3);
        if (ret == false) {
            return ret;
        }
        System.out.println("target3-1");
        ret = ret && this.doubleParms(target3, target2);
        if (ret == false) {
            return ret;
        }
        
        System.out.println("target1-2-3");
        ret = ret && this.transitive_testEquals(target1, target2, target3);
        
        return ret;
    }

    private synchronized void checkNull(T target1) {
        if (target1 == null) {
            throw new NullPointerException("nullのセットは禁止です。");
        }
    }

    private boolean singleParm(T target) {
        return this.null_testEquals(target) && this.reflexive_testEquals(target);
    }

    private boolean doubleParms(T target1, T target2) {
        return this.symmetric_testEquals(target1, target2) && this.consistent_testEquals(target1, target2);
    }

    /**
     * @return target1がnullを評価したときの値がfalseならばtrue。
     */
    private boolean null_testEquals(T target1) {
        System.out.println("x.equals(null)");
        checkNull(target1);
        return !target1.equals(null);
    }

    /**
     * @return target1がtarget1を評価したときの値がtrueならばtrue。
     */
    private boolean reflexive_testEquals(T target1) {
        System.out.println("reflexive");
        checkNull(target1);
        return target1.equals(target1);
    }

    /**
     * @return 下の2つが等しい値ならばtrue。1.target1がtarget2を評価したときの値
     * 2.target2がtarget1を評価したときの値
     */
    private boolean symmetric_testEquals(T target1, T target2) {
        System.out.println("symmetric");
        checkNull(target1);
        checkNull(target2);
        boolean result1 = target1.equals(target2);
        boolean result2 = target2.equals(target1);
        return result1 == result2;
    }

    /**
     * @return 下の3つがtrueならtrue。 1.target1がtarget2を評価したときの値
     * 2.target2がtarget3を評価したときの値 3.target3がtarget1を評価したときの値
     */
    private boolean transitive_testEquals(T target1, T target2, T target3) {
        System.out.println("transitive");
        checkNull(target1);
        checkNull(target2);
        checkNull(target3);
        boolean result1 = target1.equals(target2);
        boolean result2 = target2.equals(target3);
        boolean result3 = target3.equals(target1);
        return result1 && result2 && result3;
    }

    /**
     * @return 下の2つが等しい値ならばtrue。 1.target1がtarget2を評価したときの値(1回め)
     * 2.target1がtarget2を評価したときの値(2回め)
     */
    private boolean consistent_testEquals(T target1, T target2) {
        System.out.println("consistent");
        checkNull(target1);
        checkNull(target2);
        boolean result1 = target1.equals(target2);
        boolean result2 = target1.equals(target2);
        return result1 == result2;
    }

}
