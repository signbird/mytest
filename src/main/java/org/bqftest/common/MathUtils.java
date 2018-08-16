package org.bqftest.common;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.*;
import org.apache.commons.math.stat.StatUtils;


public class MathUtils {

    public static void main(String[] args) {
        getMaxMinInArray();
        range();
    }
    
    public static void getMaxMinInArray(){
        double[] array = {0.2, 0.4, 0.5, -3.0, 4.223, 4.226};
        double max = NumberUtils.max( array ); // returns 4.226
        double min = NumberUtils.min( array ); // returns -3.0

        System.out.println("max=" + max + ", min=" + min);
    }
    
    public static void range(){
        Range safeSpeed = new DoubleRange( 0.0, 65.0 );
        double currentSpeed = 88.00d;

        if( !safeSpeed.containsDouble(currentSpeed)) {
            System.out.println( "Warning, current speed is unsafe." );
        }
    }
    
    public static void stat()
    {
        double[] values = new double[] { 2.3, 5.4, 6.2, 7.3, 23.3 };

        System.out.println( "min: " + StatUtils.min( values ) );
        System.out.println( "max: " + StatUtils.max( values ) );
        System.out.println( "mean: " + StatUtils.mean( values ) );
        System.out.println( "product: " + StatUtils.product( values ) );
        System.out.println( "sum: " + StatUtils.sum( values ) );
        System.out.println( "variance: " + StatUtils.variance( values ) );
    }
}
