package com.xufangfang.o2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException {
    private static final long serialVersionUID = 6849139913459719040L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
