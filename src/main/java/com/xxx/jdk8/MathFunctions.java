package com.xxx.jdk8;

import java.util.function.BinaryOperator;

/**
 * Created by xiaowenzi on 2017/11/14.
 */
public enum MathFunctions implements BinaryOperator<Double> {
    SUM {
        @Override
        public Double apply(Double a, Double b) {
            return a.doubleValue() + b.doubleValue();
        }
    },

    SUBSTRACT {
        @Override
        public Double apply(Double a, Double b) {
            return a.doubleValue() - b.doubleValue();
        }
    },

    MULTIPLY {
        @Override
        public Double apply(Double a, Double b) {
            return a.doubleValue() * b.doubleValue();
        }
    },

    DIVIDE {
        @Override
        public Double apply(Double a, Double b) {
            return a.doubleValue() / b.doubleValue();
        }
    }
}
